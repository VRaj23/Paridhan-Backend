package varadraj.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private String signingKey = "TestKey";
	
	public String validateToken(String token) {
		
		String username = null;
		try {
			Claims body = Jwts.parser()
					.setSigningKey(this.signingKey)
					.parseClaimsJws(token)
					.getBody();
			
			username = (String) body.get("username");
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return username;
	}
}
