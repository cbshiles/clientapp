package demoapps.client.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoapps.client.services.ApiService;

@RestController
public class TestController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/landing")
	public String c(HttpServletRequest request) {
		log.info("The request has landed.");
		//OAuth2AccessToken token = authSrvc.getToken();
		String s = authSrvc.get("/close/howdy");
		return s;
	}

	@Autowired
	ApiService authSrvc;
	
    //landing page between authorize & token OAuth endpoints
    @RequestMapping("/launch")
    public String land(HttpServletResponse res) {
    	String s = authSrvc.get("/close/hello");
    	
    	
    	return "beans";
    }

}
