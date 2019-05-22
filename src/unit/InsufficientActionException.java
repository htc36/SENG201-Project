package unit;

/**
 * Exception that is thrown when an unit attempts 
 * to do some action when they are out of actions
 */
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