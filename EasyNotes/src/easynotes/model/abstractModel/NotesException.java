package easynotes.model.abstractModel;

public class NotesException extends Exception {

    public NotesException(String message) {
        super(message);
    }
    
    public NotesException(String message, Throwable cause) {
        super(message, cause);
    }
}
