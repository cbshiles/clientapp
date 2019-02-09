package demoapps.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
	
    @Autowired
    private OAuth2RestOperations oauthTemplate;
    
    @Value("${authSrvr.base}")
    private String authBase;
	
	public OAuth2AccessToken getToken() {
    	return oauthTemplate.getAccessToken();
	}
	
	public String get(String url) {
		return oauthTemplate.getForObject(authBase+url, Object.class).toString();
	}
}
