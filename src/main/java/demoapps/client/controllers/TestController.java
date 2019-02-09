package demoapps.client.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoapps.client.services.OAuthService;

@RestController
public class TestController {
	
	@GetMapping("/landing")
	public String c(HttpServletRequest request) {
		//OAuth2AccessToken token = authSrvc.getToken();
		String s = authSrvc.get("/close/howdy");
		return s;
	}

	@Autowired
	OAuthService authSrvc;
	
    //landing page between authorize & token OAuth endpoints
    @RequestMapping("/launch")
    public String land(HttpServletResponse res) {
    	String s = authSrvc.get("/close/hello");
    	
    	
    	return "beans";
    }

}
