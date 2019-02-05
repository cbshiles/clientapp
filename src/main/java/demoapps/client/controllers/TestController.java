package demoapps.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TestController {
	
    @Autowired
    private OAuth2RestOperations oauthTemplate;
	
	@GetMapping("/")
	public String c() {
		return "shakti";
	}
	
	/*
	 riComponentsBuilder builder = 
    			UriComponentsBuilder.fromHttpUrl("http://localhost:8080/oauth/authorize")
    	        .queryParam("grant_type", "authorization_code")
    	        .queryParam("client_id", clientId)
    	        .queryParam("password", clientSecret)
    	        .queryParam("redirect_uri", tokenRedirect);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    	HttpEntity<?> entity = new HttpEntity<>(headers);

    	String url = builder.toUriString();
    	ResponseEntity<String> result;
    	try {
    	//result = oauthTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
    	
    	} catch (Exception ex) {
    		log.debug(ex.getMessage());
    	}
	 */
	
    //landing page between authorize & token OAuth endpoints
    @RequestMapping("/landing")
    public String land(@RequestParam(required = false) String code) {
    	
    	OAuth2AccessToken result2 = oauthTemplate.getAccessToken();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("<!DOCTYPE>");
    	sb.append("<html>");
    	sb.append("<head>");

    	sb.append("</head>");
    	sb.append("<body>");
    	sb.append("<p>"+code+"</p>");
    	sb.append("</body>");
    	sb.append("</html>");
    	return sb.toString();
    }

}
