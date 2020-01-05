package photoGallery.model.photoComment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import photoGallery.model.PhotoFile.PhotoFile;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class PhotoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentText;

    private String commentAuthor;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photo_id", nullable=false)
    private PhotoFile photo;

    public PhotoComment() {
    }

    public PhotoComment(String commentText, String commentAuthor, PhotoFile photo) {
        this.commentText = commentText;
        this.commentAuthor = commentAuthor;
        this.photo = photo;
    }

    public PhotoComment(String commentText, String commentAuthor) {
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

    public PhotoFile getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoFile photo) {
        this.photo = photo;
    }


}
