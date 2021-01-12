package es.urjc.code.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
    	
    	//CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
    	//model.addAttribute("token", token.getToken());   	
    	
    	return "login";
    }
    
    @GetMapping("/loginerror")
    public String loginerror() {
    	return "loginerror";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
    	
    	//CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
    	//model.addAttribute("token", token.getToken());   	
    	
        return "home";
    }
}
