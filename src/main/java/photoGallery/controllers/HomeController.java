package photoGallery.controllers;

import feign.FeignException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.exceptions.PhotoNotFoundException;
import photoGallery.microservices.FeignClientCommentService;
import photoGallery.microservices.FeignClientPhotoService;
import photoGallery.model.PhotoFile.PhotoFileDTO;
import photoGallery.model.photoComment.PhotoCommentDTO;

@Controller
public class HomeController {

    private FeignClientCommentService feignClientCommentService;
    private FeignClientPhotoService feignClientPhotoService;

    public HomeController(FeignClientCommentService feignClientCommentService, FeignClientPhotoService feignClientPhotoService) {
        this.feignClientCommentService = feignClientCommentService;
        this.feignClientPhotoService = feignClientPhotoService;
    }

    @GetMapping("/uploadFile")
    public String test(){
        return "addPhoto";
    }

    @PostMapping("/uploadFile")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
        feignClientPhotoService.postPhoto(file);
        return "redirect:/uploadFile";
        } catch (NullPointerException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");
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
