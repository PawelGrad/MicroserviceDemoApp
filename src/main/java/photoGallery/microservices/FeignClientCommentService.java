package photoGallery.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import photoGallery.model.photoComment.PhotoCommentDTO;

import java.util.List;

@FeignClient(name = "photoComment-service")
public interface FeignClientCommentService {


        @GetMapping("/comment/{id}")
        PhotoCommentDTO getComment(@PathVariable("id") String id);

        @PostMapping(value="/uploadComment", consumes = "application/json")
        void postComment(@RequestPart("file") PhotoCommentDTO file);

        @DeleteMapping("/comment/delete/{id}")
        void deleteComment(@PathVariable String id);

        @PutMapping(value = "/comment/update", consumes =  "application/json")
        void updateComment(@RequestPart("file") PhotoCommentDTO file);

        @GetMapping("/comment/all/{id}")
        List<PhotoCommentDTO> getPhotoComments(@PathVariable("id") String id);

}
