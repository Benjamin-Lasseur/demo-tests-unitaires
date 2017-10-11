package dev.exception;

public class CalculException extends RuntimeException {

	/** serialVersionUID : long */
	private static final long serialVersionUID = -9151883460325317628L;

	/**Constructeur de l'exception
	 * @param expression
	 */
	public CalculException(String expression) {
		super("Expression " + expression + " non valide!");
	}

}
