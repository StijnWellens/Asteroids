package asteroids.model.programs.exceptions;

public class IllegalProgramException extends RuntimeException {

	public IllegalProgramException(String message) {
		super("Illegal operation in progrram found: " + message);
	}
	private static final long	serialVersionUID	= 1L;

}
