package photoGallery.model.PhotoFile;

import photoGallery.model.photoComment.PhotoComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "photos")
public class PhotoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    private Long voteCounter;

    private Long rating;

    @Lob
    private byte[] data;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "photo", orphanRemoval = true)
    private List<PhotoComment> comments = new ArrayList<>();

    public PhotoFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(Long voteCounter) {
        this.voteCounter = voteCounter;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
