package varadraj.jwt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import varadraj.jwt.model.JwtAuthToken;
import varadraj.jwt.model.JwtCustomUserDetails;

@Component
public class JwtAuthProvider extends AbstractUserDetailsAuthenticationProvider{
	
	@Autowired
	private JwtValidator jwtValidator;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}	

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected UserDetails retrieveUser(String user, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		JwtAuthToken jwtAuthToken = (JwtAuthToken) authentication;
		
		String token = jwtAuthToken.getToken();
		
		String username = jwtValidator.validateToken(token);
		
		if( username == null) {
			throw new RuntimeException("Invalid Token");
		}
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("admin");
		
		return new JwtCustomUserDetails(token, username, grantedAuthorities);
	}

}
