package proyectoreddit.exceptions;

public class RedditApplicationException extends RuntimeException {
    public RedditApplicationException(String exception){
        super(exception);
    }
}
