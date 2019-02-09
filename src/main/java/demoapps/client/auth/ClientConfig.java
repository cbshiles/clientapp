package demoapps.client.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class ClientConfig {
	
    @Value("${app.clientId}")
    private String clientId;
 
    @Value("${app.clientSecret}")
    private String clientSecret;
        
	@Value("${app.resourceId}")
	private String resourceId;
	
	@Value("${app.clientAuthUri}")
	private String clientAuthUri;
	
	@Value("${app.tokenUri}")
	private String tokenUri;
	
	@Value("${app.redirectUri}")
	private String redirectUri;
	
    @Bean
    public OAuth2ProtectedResourceDetails getResourceDetails() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId(resourceId);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setScope(Arrays.asList("read", "write"));
        details.setUserAuthorizationUri(clientAuthUri);
        details.setAccessTokenUri(tokenUri);
        details.setPreEstablishedRedirectUri(redirectUri);
        details.setUseCurrentUri(false);
        return details;
    }
    
    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;
 
    @Bean
    public OAuth2RestOperations oAuthTemplate(OAuth2ClientContext clientContext) {
    	  OAuth2RestTemplate template = new OAuth2RestTemplate(getResourceDetails(), oAuth2ClientContext); 
    	  //template.setMessageConverters();
    	  return template; 
    }
    
    @Bean
	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

}
