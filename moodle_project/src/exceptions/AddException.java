package exceptions;

public class AddException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddException() {
		super();
	}

	public AddException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddException(String message) {
		super(message);
	}

	public AddException(Throwable cause) {
		super(cause);
	}

}
