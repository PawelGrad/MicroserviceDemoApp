package photoGallery.microservices;

import feign.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "photoFile-service")
public interface PhotoService {

    @GetMapping("/photo/{id}")
    PhotoFile getPhoto(@PathVariable("id") String id);

    @PostMapping(value="/uploadFile", consumes = "multipart/form-data" )
    Response postPhoto(@RequestPart("file") MultipartFile file);

    @DeleteMapping("/photo/delete/{id}")
    Response deletePhoto(@PathVariable String id);

    @PutMapping(value = "/photo/update", consumes =  "application/json")
    Response updatePhoto(@RequestPart("file") PhotoFile file);
}
