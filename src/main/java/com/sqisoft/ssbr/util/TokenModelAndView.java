package com.sqisoft.ssbr.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public class TokenModelAndView extends ModelAndView{

	public void setToken(HttpSession session) { 
		
		String tokenString = session.getAttribute("_TOKEN_").toString();
		String tokenTag = "<input type=\"hidden\" name=\"token\" value=\""+ tokenString +"\" />";
		super.addObject("token", tokenTag);
	}
}
