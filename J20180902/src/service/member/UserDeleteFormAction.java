package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CommandProcess;

public class UserDeleteFormAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "UserDeleteForm.jsp";
	}
}
