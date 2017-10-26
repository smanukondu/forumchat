
package com.wiz.jspforum.web.basic.mvc.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String defaultLoginPage(@RequestParam(value="error", required=false) String error, 
		@RequestParam(value="logout", required=false) String logout, 
		Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails)auth.getPrincipal();
			System.out.println(userDetail);
		}
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}
		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		return "LOGIN_SECURITY_FORM_PAGE";
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String defaultWelcomePage() {
		
		return "LOGIN_SECURITY_FORM_PAGE";
	}
}
