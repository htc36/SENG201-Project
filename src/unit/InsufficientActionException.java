package unit;

public class InsufficientActionException extends IllegalStateException {

    public InsufficientActionException() {}

    public InsufficientActionException(String message) {
        super(message);
    }

}
