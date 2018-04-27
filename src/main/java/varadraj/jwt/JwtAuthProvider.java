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
		
		String[] tokenDetails = jwtValidator.validateToken(token);
		String username = tokenDetails[0]; 
		String role = tokenDetails[1]; 
				
		if( username == null || role == null) {
			throw new RuntimeException("Invalid Token");
		}
		//TODO why called twice ?
		//System.out.println(username+" "+role+" "+System.currentTimeMillis());
		//
		List<GrantedAuthority> grantedAuthorities = null;
		
		if( role.equals("admin") )
			grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		else
			grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
	
	
		return new JwtCustomUserDetails(token, username, grantedAuthorities);
	}

}
