package es.codeurjc.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModelAttributes {
	
	@ModelAttribute("userName")
	public String userName() {
		return "Juan";
	}
}
