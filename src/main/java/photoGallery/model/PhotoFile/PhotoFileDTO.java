package photoGallery.model.PhotoFile;

import photoGallery.model.photoComment.PhotoComment;

import java.util.List;

public class PhotoFileDTO {

    private Long id;

    private String fileName;

    private String fileType;

    private Long voteCounter;

    private Long rating;

    private String data;

    private List<PhotoComment> comments;

    public PhotoFileDTO() {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<PhotoComment> getComments() {
        return comments;
    }

    public void setComments(List<PhotoComment> comments) {
        this.comments = comments;
    }
}
