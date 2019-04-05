package crew;
public class InsufficientItemInStock extends RuntimeException {
    public InsufficientItemInStock() {}

    public InsufficientItemInStock(String message) {
        super(message);
    }
}
