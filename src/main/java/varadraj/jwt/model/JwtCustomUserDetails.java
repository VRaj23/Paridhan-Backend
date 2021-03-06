package varadraj.jwt.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtCustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	 private String token;
     private String username;
     private Collection<? extends GrantedAuthority> authorities;
     
     

	public JwtCustomUserDetails(String token, String username, 
			Collection<? extends GrantedAuthority> authorities) {
		
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUsername(String username) {
		this.username = username;
	}	

}
