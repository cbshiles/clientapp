package demoapps.client.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.annotation.Resource; 
import javax.sql.DataSource; 
 
import org.springframework.beans.factory.annotation.Qualifier; 

import demoapps.client.Props;

@Configuration
@EnableOAuth2Client
public class ClientConfig {
	
    @Value("${app.clientId}")
    private String clientId;
 
    @Value("${app.clientSecret}")
    private String clientSecret;
    
    @Autowired
    private Props props;
    
	@Value("${app.resourceId}")
	private String resourceId;
	
	 @Resource 
	 @Qualifier("accessTokenRequest") 
	 private AccessTokenRequest accessTokenRequest; 
	
    @Bean
    //@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    //, proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2ProtectedResourceDetails getResourceDetails() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId(resourceId);
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setScope(Arrays.asList("read", "write"));
        
        details.setAccessTokenUri(props.tokenUri());
        details.setUserAuthorizationUri(props.authUri());
        details.setPreEstablishedRedirectUri(props.redirectUri());
        details.setUseCurrentUri(false);
        return details;
    }
    
    @Autowired
    private AccessTokenProvider accessTokenProvider;
 
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2RestOperations oAuthTemplate(OAuth2ClientContext clientContext) {
    	  OAuth2RestTemplate template = new OAuth2RestTemplate(getResourceDetails(), new DefaultOAuth2ClientContext(accessTokenRequest)); 
    	  //AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider())); 
    	  //provider.setClientTokenServices(clientTokenServices()); 
    	 // accessTokenProvider.
    	  template.setAccessTokenProvider(accessTokenProvider);
    	  return template; 
    	//return new OAuth2RestTemplate(getResourceDetails(), clientContext);
    }

}
