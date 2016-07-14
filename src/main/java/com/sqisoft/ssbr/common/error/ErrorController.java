package com.sqisoft.ssbr.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	/**
	 * 400 ������ �߻��Ͽ��� �� handling�Ѵ�.
	 * @return
	 */
	@RequestMapping("/error/error400")
	public ModelAndView error400() {
		return new ModelAndView("/error/400code");
	}
	
	/**
	 * 404 ������ �߻��Ͽ��� �� handling�Ѵ�.
	 * @return
	 */
	@RequestMapping("/error/error404")
	public ModelAndView error404() {
		return new ModelAndView("/error/404code");
	}
	
	/**
	 * 500 ������ �߻��Ͽ��� �� handling�Ѵ�.
	 * @return
	 */
	@RequestMapping("/error/error500")
	public ModelAndView error500() {
		return new ModelAndView("/error/500code");
	}
}
