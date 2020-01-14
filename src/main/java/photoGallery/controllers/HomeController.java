package photoGallery.controllers;

import feign.FeignException;
import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.exceptions.PhotoNotFoundException;
import photoGallery.microservices.FeignClientCommentService;
import photoGallery.microservices.FeignClientPhotoService;
import photoGallery.model.PhotoFile.PhotoFileDTO;
import photoGallery.model.photoComment.PhotoCommentDTO;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@Controller
public class HomeController {

    private FeignClientCommentService feignClientCommentService;
    private FeignClientPhotoService feignClientPhotoService;

    public HomeController(FeignClientCommentService feignClientCommentService, FeignClientPhotoService feignClientPhotoService) {
        this.feignClientCommentService = feignClientCommentService;
        this.feignClientPhotoService = feignClientPhotoService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("photos", feignClientPhotoService.getAll());
        return "allPhotos";
    }

    @GetMapping("/uploadFile")
    public String test(){
        return "addPhoto";
    }

    @PostMapping("/uploadFile")
    public String uploadPhoto(@RequestParam("file") MultipartFile file, Model model) {
        try {
            byte[] data = file.getBytes();
            InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            if(!(mimeType.startsWith("image/"))){
                model.addAttribute("message", "Wrong file format");
                return "addPhoto";
            }
        feignClientPhotoService.postPhoto(file);
        return "redirect:/";
        } catch (NullPointerException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");
        }
         catch (IOException e) {
             throw new PhotoNotFoundException("File Error");
         }
    }

    @GetMapping("/photo/delete/{id}")
    public String deletePhoto(@PathVariable String id) {
        try {
            feignClientPhotoService.deletePhoto(id);
            return "redirect:/all";
        } catch (FeignException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");
        }
    }

    @GetMapping("/photo/show/{id}")
    public String showFile(@PathVariable String id, Model model) {

        model.addAttribute("photo", feignClientPhotoService.getPhotoDTO(id));
        model.addAttribute("comments", feignClientCommentService.getPhotoComments(id));
        return "showPhoto";
    }

    @PostMapping("/photo/show/{id}")
    public String ratePhoto(@PathVariable("id") String id, @RequestParam("rating") Long rating){
        try {
        feignClientPhotoService.ratePhoto(id,rating);
        return "redirect:/photo/show/" + id;
        } catch (FeignException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");
        }
    }

    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("photos", feignClientPhotoService.getAll());
        return "allPhotos";
    }

    @PostMapping("/photo/addComment/{id}")
    public String commentPhoto(@RequestParam("comment") String comment, @RequestParam("author") String author, @PathVariable("id") String id) {
        PhotoFileDTO photo = feignClientPhotoService.getPhotoDTO(id);
        feignClientCommentService.postComment(new PhotoCommentDTO(comment,author,photo));
        return "redirect:/photo/show/" + id;
    }


}
