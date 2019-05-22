package unit;

/**
 * Exception that is thrown when attempting to delete
 * an non existent crew member
 */
public class CrewMemberNotFoundException extends RuntimeException {

    /**
     * Constructor for CrewMemberNotFoundException
     */
    public CrewMemberNotFoundException() {}

    /**
     * Constructor for CrewMemberNotFoundException
     * @param message the error message
     */
    public CrewMemberNotFoundException(String message) {
        super(message);
    }

}
