package photoGallery.controllers;

import feign.FeignException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.exceptions.PhotoNotFoundException;
import photoGallery.microservices.FeignClientComment;
import photoGallery.model.PhotoFile.PhotoFile;
import photoGallery.model.PhotoFile.PhotoFileDTO;
import photoGallery.model.PhotoFile.PhotoService;
import photoGallery.model.photoComment.PhotoComment;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private FeignClientComment feignClientComment;
    private PhotoService photoService;

    public HomeController(FeignClientComment feignClientComment, PhotoService photoService) {
        this.feignClientComment = feignClientComment;
        this.photoService = photoService;
    }

    @GetMapping("/uploadFile")
    public String test(){
        return "addPhoto";
    }

    @PostMapping("/uploadFile")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
        photoService.uploadPhoto(file);
        return "redirect:/uploadFile";
        } catch (NullPointerException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");
        }
    }

    @GetMapping("/photo/delete/{id}")
    public String deletePhoto(@PathVariable String id) {
        try {
            photoService.deletePhoto(id);
            return "redirect:/all";
        } catch (FeignException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");
        }
    }

    @GetMapping("/photo/show/{id}")
    public String showFile(@PathVariable String id, Model model) {
        try {
        PhotoFile photo = photoService.getPhoto(id);
        model.addAttribute("photo", photoService.convertToDTO(photo));
        model.addAttribute("comments", feignClientComment.getPhotoComments(id));
        return "showPhoto";
        } catch (NullPointerException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");

        }
    }

    @PostMapping("/photo/show/{id}")
    public String ratePhoto(@PathVariable("id") String id, @RequestParam("rating") Long rating){
        try {
        PhotoFile photo = photoService.getPhoto(id);
        photoService.changeRating(rating, photo);
        return "redirect:/photo/show/" + id;
        } catch (NullPointerException e) {
            throw new PhotoNotFoundException("Photo doesn't exist");

        }
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<PhotoFile> photos = photoService.getAll();
        List<PhotoFileDTO> convertedPhotos = new ArrayList<>();
        photos.forEach(photo -> {
            convertedPhotos.add(photoService.convertToDTO(photo));
        });
        model.addAttribute("photos", convertedPhotos);
        return "allPhotos";
    }

    @PostMapping("/photo/addComment/{id}")
    public String commentPhoto(@RequestParam("comment") String comment, @RequestParam("author") String author, @PathVariable("id") String id) {
        PhotoFile photo = photoService.getPhoto(id);
        photo.getComments().add(new PhotoComment(comment,author));
        photoService.updatePhoto(photo);
        return "redirect:/photo/show/" + id;
    }


}
