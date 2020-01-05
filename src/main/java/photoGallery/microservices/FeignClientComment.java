package photoGallery.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;
import photoGallery.model.photoComment.PhotoComment;

import javax.xml.ws.Response;
import java.util.List;

@FeignClient(name = "photoComment-service")
public interface FeignClientComment {


        @GetMapping("/comment/{id}")
        PhotoComment getComment(@PathVariable("id") String id);

        @PostMapping(value="/uploadComment", consumes = "application/json")
        void postComment(@RequestPart("file") PhotoComment file);

        @DeleteMapping("/comment/delete/{id}")
        void deleteComment(@PathVariable String id);

        @PutMapping(value = "/comment/update", consumes =  "application/json")
        void updateComment(@RequestPart("file") PhotoComment file);

        @GetMapping("/comment/all/{id}")
        List<PhotoComment> getPhotoComments(@PathVariable("id") String id);

}
