package varadraj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *This class sets value of server.port based on whether active profile is prod or not
 */

@Component
public class PortConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	
	@Autowired
	private Environment environment;
	
	private int HTTPSport = 8443;
	
	@Override
	public void customize(ConfigurableServletWebServerFactory container) {
		this.setPortByProfile();
		container.setPort(this.HTTPSport);
	}
	    
	private void setPortByProfile() {
		String[] profile = this.environment.getActiveProfiles();
		if(profile[0].equals("prod")) {
			this.HTTPSport = 443;
		}
		System.out.println(profile[0]+" "+this.HTTPSport);
	}
}
