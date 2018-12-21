package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import service.CommandProcess;

public class UserDeleteProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
	        HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");
			String user_pass = request.getParameter("user_pass");
			
	        MemberDao md = MemberDao.getInstance();
	        int result = md.delete(user_id, user_pass);
	        
		      if(result == 1){
		    	  session.setAttribute("user_id", user_id);
		    	  session.setAttribute("user_pass", user_pass);
		          session.invalidate();
		      }
		      
			  request.getSession().setAttribute("result", result);
	        
		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "UserDeletePro.jsp";
	}
}
