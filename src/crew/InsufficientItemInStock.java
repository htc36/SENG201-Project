package crew;

/**
 * Exception that is thrown when there are not enough items in the stock
 */
public class InsufficientItemInStock extends RuntimeException {
    /**
     * Constructor for InsufficientItemInStock
     */
    public InsufficientItemInStock() {}

    /**
     * Constructor for InsufficientItemInStock
     * @param message the error message
     */
    public InsufficientItemInStock(String message) {
        super(message);
    }
}
