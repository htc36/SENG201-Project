package game;
public class InsufficientFundException extends RuntimeException {
    /**
     * Default constructor for Insufficient Fund Exception
     */
    public InsufficientFundException() {}

    /**
     * Constructor for Insufficient Fund Exception with message
     * @param message The message
     */
    public InsufficientFundException(String message) {
        super(message);
    }
}
