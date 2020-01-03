package photoGallery;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;
import photoGallery.model.photoComment.PhotoComment;

import javax.xml.ws.Response;

@FeignClient(name = "photoComment-service")
public interface CommentService {


        @GetMapping("/comment/{id}")
        PhotoComment getComment(@PathVariable("id") String id);

        @PostMapping(value="/uploadComment", consumes = "application/json")
        Response postComment(@RequestPart("file") PhotoFile file);

        @DeleteMapping("/comment/delete/{id}")
        Response deleteComment(@PathVariable String id);

        @PutMapping(value = "/comment/update", consumes =  "application/json")
        Response updateComment(@RequestPart("file") PhotoFile file);


}
