package demoapps.client.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;

@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${app.loginPath}")
	private String loginPath;
		
//	@Autowired
//    private UserDetailsSrvc userDetailsSrvc;
//	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
//	
//	
//	   @Bean
//	    public DaoAuthenticationProvider authenticationProvider() {
//	        DaoAuthenticationProvider authProvider
//	          = new DaoAuthenticationProvider();
//	        authProvider.setUserDetailsService(detailsSrvc);
//	        authProvider.setPasswordEncoder(passwordEncoder());
//	        return authProvider;
//	    }
//	    
//	   @Autowired
//	    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		   auth.userDetailsService(userDetailsSrvc).passwordEncoder(passwordEncoder());
//	    }
//	   
	
//	   @Override
//	   @Bean(name="myAuthenticationManager")
//	   public AuthenticationManager authenticationManagerBean() throws Exception {
//	       return authenticationManager();
//	   }
	   
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .csrf().disable()
	        //.authorizeRequests()
	        //.antMatchers("/close/**").authenticated()
	        //.antMatchers("/oauth/authorize").permitAll()
	        //.antMatchers("/oauth/authorize").authenticated()
	        //.and()	        
//	        .addFilterAfter(new OAuth2ClientContextFilter(),
//	          AbstractPreAuthenticatedProcessingFilter.class)
//	        .addFilterAfter(openIdConnectFilter(), OAuth2ClientContextFilter.class)
	        //.httpBasic()	
	        //.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(loginPath))
	        //.formLogin().and()
	        //.authorizeRequests()
	        //.anyRequest()
	        .anonymous()
	        .and()
	        .authorizeRequests()
	        .anyRequest().permitAll()
	        ;
	    }
	    
	    
	    @Override
	 	public void configure(WebSecurity web) throws Exception {
	 		web.ignoring().antMatchers("/**");
	 		// Spring Security should completely ignore URLs starting with /resources/
	 				//.antMatchers("/resources/**");
	 	}

}
