package photoGallery.exceptions;

public class PhotoNotFoundException extends NullPointerException{
    public PhotoNotFoundException(String s) {
        super(s);
    }
}
