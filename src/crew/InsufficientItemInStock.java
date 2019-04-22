package crew;
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
