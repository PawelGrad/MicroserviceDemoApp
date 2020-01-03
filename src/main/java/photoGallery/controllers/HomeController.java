package photoGallery.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.microservices.CommentService;
import photoGallery.microservices.PhotoService;
import photoGallery.model.PhotoFile.PhotoFile;

@Controller
public class HomeController {

    private PhotoService feignClient;
    private CommentService commentService;

    public HomeController(PhotoService feignClient, CommentService commentService) {
        this.feignClient = feignClient;
        this.commentService = commentService;
    }


    @GetMapping("/")
    public String test(){
        return "test";
    }

    @PostMapping("/uploadFile")
    public String uploadPhoto(@RequestParam("file") MultipartFile file) {
        feignClient.postPhoto(file);
        return "redirect:/";
    }

    @GetMapping("/photo/update/{id}")
    public String updatePhoto(@PathVariable("id") String id) {
        PhotoFile file = feignClient.getPhoto(id);
        file.setRating(10L);
        feignClient.updatePhoto(file);
        return "redirect:/";
    }

    @GetMapping("/photo/delete/{id}")
    public String deletePhoto(@PathVariable String id) {
        feignClient.deletePhoto(id);
        return "redirect:/";
    }

    @GetMapping("/photo/show/{id}")
    public String showFile(@PathVariable String id, Model model) {
        PhotoFile photo = feignClient.getPhoto(id);
        byte[] encodeBase64 = Base64.encodeBase64(photo.getData());
        try {
            model.addAttribute("photo", new String(encodeBase64, "UTF-8"));
        } catch (Exception e) {

        }
        return "image";
    }

}
