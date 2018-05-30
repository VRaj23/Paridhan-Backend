package varadraj.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	@Value("${jwt.key}")
	private String signingKey;
	
	public String[] validateToken(String token) {
		
		String[] tokenDetails = new String[2];
		try {
			Claims body = Jwts.parser()
					.setSigningKey(this.signingKey)
					.parseClaimsJws(token)
					.getBody();
			
			tokenDetails[0] = (String) body.get("username");
			tokenDetails[1] = (String) body.get("role");
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return tokenDetails;
	}
}
