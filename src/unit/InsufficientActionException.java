package unit;

public class InsufficientActionException extends IllegalStateException {

    /**
     * Constructor for InsufficientActionException
     */
    public InsufficientActionException() {}

    /**
     * Constructor for InsufficientActionException
     * @param message the error message
     */
    public InsufficientActionException(String message) {
        super(message);
    }

}