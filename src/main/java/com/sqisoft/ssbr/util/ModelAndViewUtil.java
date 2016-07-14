package com.sqisoft.ssbr.util;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

public class ModelAndViewUtil {
	
	//http://wisebee.tistory.com/7
	public static ModelAndView getMessageView(final String msg, final String script) {
		View view = new AbstractView() {
	        @Override
	        protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	            response.setContentType("text/html; charset=EUC-KR");
	            response.setCharacterEncoding("EUC-KR");
	            ServletOutputStream outs = response.getOutputStream();
	            outs.println("<script type=\"text/javascript\">");
	            outs.println("alert(\"" + new String(msg.getBytes(), "ISO_8859_1") + "\");");
	            outs.println(new String(script.getBytes(), "ISO_8859_1"));
	            //outs.println("location.href = \"/login\"/>\";");
	            outs.println("</script>");
	            outs.flush();
	        }
	        
	    };
	    return new ModelAndView(view);
	}
	
}
