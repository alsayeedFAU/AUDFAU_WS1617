public class IllegalCrosswordSpecException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code;

	public IllegalCrosswordSpecException(int code) {
		super("Illegal riddle specification, code = " + code);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}