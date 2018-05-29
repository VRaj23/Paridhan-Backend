package varadraj.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissing extends AuthenticationException{

	private static final long serialVersionUID = 1L;
	
	public JwtTokenMissing() {
		super("Jwt Token is Missing");
	}

}
