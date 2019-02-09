package demoapps.client.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
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
	
	@Value("${authSrvr.base}")
	private String authBase;
	
	@Value("${app.redirectUri}")
	private String redirectUri;

    @Bean //assumes authorize & token endpoints are at standard locations
    public OAuth2RestOperations oAuthTemplate(OAuth2ClientContext clientContext) {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId(resourceId);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setScope(Arrays.asList("read", "write"));
        details.setUserAuthorizationUri(authBase+"/oauth/authorize");
        details.setAccessTokenUri(authBase+"/oauth/token");
        details.setPreEstablishedRedirectUri(redirectUri);
        details.setUseCurrentUri(false);   
    	return new OAuth2RestTemplate(details, clientContext); 
    }

}
