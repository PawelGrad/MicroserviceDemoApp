package photoGallery.model.PhotoFile;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.microservices.FeignClientPhoto;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Transactional
public class PhotoService {

    private FeignClientPhoto feignClientPhoto;

    public PhotoService(FeignClientPhoto feignClientPhoto) {
        this.feignClientPhoto = feignClientPhoto;
    }

    public PhotoFile getPhoto(String id) {
        return feignClientPhoto.getPhoto(id);
    }

    public void updatePhoto(PhotoFile photoFile) {
        feignClientPhoto.updatePhoto(photoFile);
    }

    public void deletePhoto(String id) {
        feignClientPhoto.deletePhoto(id);
    }

    public void uploadPhoto(MultipartFile multipartFile) {
        feignClientPhoto.postPhoto(multipartFile);
    }

    public List<PhotoFile> getAll(){
        return feignClientPhoto.getAll();
    }

    public void changeRating(Long rating, PhotoFile photoFile){
        photoFile.setVoteCounter(photoFile.getVoteCounter()+1L);
        photoFile.setRating(photoFile.getRating()+rating);
        feignClientPhoto.updatePhoto(photoFile);
    }

    public String photoToString(byte[] data) {
        byte[] encodeBase64 = Base64.encodeBase64(data);
        return new String(encodeBase64, StandardCharsets.UTF_8);
    }

    public PhotoFileDTO convertToDTO(PhotoFile photofile){
        PhotoFileDTO photoDTO = new PhotoFileDTO();
        photoDTO.setId(photofile.getId());
        photoDTO.setData(photoToString(photofile.getData()));
        photoDTO.setRating(photofile.getRating());
        photoDTO.setVoteCounter(photofile.getVoteCounter());
        photoDTO.setComments(photofile.getComments());
        return photoDTO;
    }
}
