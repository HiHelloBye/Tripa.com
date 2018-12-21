package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

@WebServlet("/UserRegisterCheckServlet")
public class UserRegisterCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String user_id = request.getParameter("user_id");
		if(user_id == null || user_id.equals("")) response.getWriter().write("-1");
		response.getWriter().write(new MemberDao().registerCheck(user_id) + "");
	}

}
