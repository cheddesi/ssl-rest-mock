package hello;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application extends WebSecurityConfigurerAdapter {

	@Autowired
	private CDDConfig cddProperties;

	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)").userDetailsService(userDetailsService());
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
    	
        return new UserDetailsService() {
        	
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            	System.out.println("Received CN value from client: "+username);
            	System.out.println("One of the expected value as per application property cdd.config.usernames: "+ cddProperties.getUsernames());
            	String[] cns = cddProperties.getUsernames().split(",");
            	for (String cn : cns) {
                    if (username.equals(cn)) {
                    	System.out.println("Matched user name: "+cn);
                        return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
                    }
				}
                throw new UsernameNotFoundException("Unauthorized User!");
            }
        };
    }
}
