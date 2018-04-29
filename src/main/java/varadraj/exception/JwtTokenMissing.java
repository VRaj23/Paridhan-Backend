package varadraj.exception;

public class JwtTokenMissing extends Exception{

	private static final long serialVersionUID = 1L;
	
	public JwtTokenMissing() {
		super("Jwt Token is Missing");
	}

}
