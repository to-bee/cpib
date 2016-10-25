package error;

public class LexicalError extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LexicalError(String string) {
		System.err.println("Error found in: "+string);
	}

}
