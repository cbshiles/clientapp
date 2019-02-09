package demoapps.client.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true)
//@Order(Ordered.LOWEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${app.loginPath}")
	private String loginPath;
   
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests()
	        .anyRequest().authenticated()
	          .and()
	          .httpBasic()
	          .and()
	          //.addFilterAfter(new OAuth2ClientContextFilter(), HeaderWriterFilter.class)
	        ;
	    }
	    
    
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	          .withUser("user1").password(passwordEncoder().encode("user1Pass"))
	          .authorities("ROLE_USER");
	    }
	    
		   @Override
		   @Bean(name="myAuthenticationManager")
		   public AuthenticationManager authenticationManagerBean() throws Exception {
		       return authenticationManager();
		   }
	    
		@Bean
		public PasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		}
	    
	    @Override
	 	public void configure(WebSecurity web) throws Exception {
	 				web.ignoring().antMatchers("/open/**");
	 	}

}
