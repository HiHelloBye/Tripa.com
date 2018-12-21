package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.MemberDao;
import service.CommandProcess;

public class adminMemberDeleteFormAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String[] user_id = request.getParameterValues("user_id");
			String pageNum = request.getParameter("pageNum");
			String user_pass = request.getParameter("user_pass");
			MemberDao md = MemberDao.getInstance();	
			int result = 0;
		
			for(int i=0;i<user_id.length;i++){
				System.out.println("accom_no[i]->"+user_id[i]);
				Member member = null;
				result = md.deleteadmin(user_id[i], user_pass); 
				request.setAttribute("result", result);
				request.setAttribute("pageNum", pageNum);
			}
		}catch(Exception e){
				System.out.println(e.getMessage());
		}
		return "adminMemberDeletePro.jsp";
	}

}
