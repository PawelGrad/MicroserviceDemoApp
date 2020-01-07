package photoGallery.microservices;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.cloud.openfeign.FeignClient;
import photoGallery.model.PhotoFile.PhotoFileDTO;

import java.util.List;

@FeignClient(name = "photoFile-service")
public interface FeignClientPhotoService {


    @GetMapping("/photo/show/{id}")
    PhotoFileDTO getPhotoDTO(@PathVariable("id") String id);

    @PostMapping(value="/uploadFile", consumes = "multipart/form-data" )
    void postPhoto(@RequestPart("file") MultipartFile file);

    @DeleteMapping("/photo/delete/{id}")
    void deletePhoto(@PathVariable String id);

    @PutMapping(value = "/photo/update", consumes =  "application/json")
    void updatePhoto(@RequestPart("file") PhotoFileDTO file);

    @PutMapping(value = "/photo/rate/{id}", consumes =  "application/json")
    void ratePhoto(@PathVariable String id, @RequestPart("rating") Long rating);

    @GetMapping("/photo/all")
    List<PhotoFileDTO> getAll();
}
