package photoGallery.microservices;

import feign.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "photoFile-service")
public interface FeignClientPhoto {

    @GetMapping("/photo/{id}")
    PhotoFile getPhoto(@PathVariable("id") String id);

    @PostMapping(value="/uploadFile", consumes = "multipart/form-data" )
    void postPhoto(@RequestPart("file") MultipartFile file);

    @DeleteMapping("/photo/delete/{id}")
    void deletePhoto(@PathVariable String id);

    @PutMapping(value = "/photo/update", consumes =  "application/json")
    void updatePhoto(@RequestPart("file") PhotoFile file);

    @GetMapping("/photo/all")
    List<PhotoFile> getAll();
}
