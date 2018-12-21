package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;

public class IndexAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String user_pass = request.getParameter("user_pass");	
			
	        HttpSession session = request.getSession();
	    	session.setAttribute("user_pass", user_pass);
	        session.invalidate();
	        
		} catch(Exception e) {	System.out.println(e.getMessage());}

		return "index.jsp";
	}
}
