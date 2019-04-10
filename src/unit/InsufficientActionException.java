package unit;

public class InsufficientActionException extends IllegalStateException {

    /**
     * constructor for InsufficientActionException
     */
    public InsufficientActionException() {}

    /**
     * constructor for InsufficientActionException
     * @param message the error message
     */
    public InsufficientActionException(String message) {
        super(message);
    }

}
