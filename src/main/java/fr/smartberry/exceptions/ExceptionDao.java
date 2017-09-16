package fr.smartberry.exceptions;

/**
 * Exception liee aux DAO.
 */
public class ExceptionDao extends Exception {
	private static final long serialVersionUID = 1L;


	public ExceptionDao() {
		super();
	}

	public ExceptionDao(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionDao(String message) {
		super(message);
	}

	public ExceptionDao(Throwable cause) {
		super(cause);
	}

}
