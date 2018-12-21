package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommandProcess;

public class LogoutAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			try {
				String user_id = request.getParameter("user_id");	
				
		        HttpSession session = request.getSession();
		    	session.setAttribute("user_id", user_id);
		        session.invalidate();
			} catch(Exception e) {	System.out.println(e.getMessage());}

		return "Logout.jsp";
	}
}
