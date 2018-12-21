package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import service.CommandProcess;

public class LoginProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			  String user_id = request.getParameter("user_id");
		      String user_pass = request.getParameter("user_pass");
		      
		      MemberDao md = MemberDao.getInstance();
		      int result = md.login(user_id,user_pass);
		      
		      if(result == 1){
		    	HttpSession session = request.getSession();
		    	session.setAttribute("user_id", user_id);
				System.out.println("LogoutAction.java user_id->" + user_id);

		        } 
			    request.getSession().setAttribute("result", result);
		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "LoginPro.jsp";
	}
}
