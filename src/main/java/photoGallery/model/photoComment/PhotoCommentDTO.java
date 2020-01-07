package photoGallery.model.photoComment;

import photoGallery.model.PhotoFile.PhotoFileDTO;


public class PhotoCommentDTO {

    private Long id;

    private String commentText;

    private String commentAuthor;

    private PhotoFileDTO photo;

    public PhotoCommentDTO() {
    }

    public PhotoCommentDTO(String commentText, String commentAuthor, PhotoFileDTO photo) {
        this.commentText = commentText;
        this.commentAuthor = commentAuthor;
        this.photo = photo;
    }

    public PhotoCommentDTO(String commentText, String commentAuthor) {
        this.commentText = commentText;
        this.commentAuthor = commentAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public PhotoFileDTO getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoFileDTO photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "PhotoComment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", commentAuthor='" + commentAuthor + '\'' +
                ", photo=" + photo +
                '}';
    }
}
