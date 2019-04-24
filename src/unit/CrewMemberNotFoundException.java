package unit;

public class CrewMemberNotFoundException extends RuntimeException {

	/**
	 * constructor for CrewMemberNotFoundException
	 */
	public CrewMemberNotFoundException() {}

	/**
	 * constructor for CrewMemberNotFoundException
	 * @param message the error message
	 */
	public CrewMemberNotFoundException(String message) {
		super(message);
	}

}
