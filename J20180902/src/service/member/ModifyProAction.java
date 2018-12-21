package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member;
import dao.MemberDao;
import service.CommandProcess;

public class ModifyProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
//	        HttpSession session =request.getSession();
//	        request.setCharacterEncoding("utf-8");
//	        Member member = new Member();
//	        member.setUser_id((String) session.getAttribute("user_id"));
//	        member.setUser_pass((String) session.getAttribute("user_pass"));
//	        member.setUser_email((String) session.getAttribute("user_email"));
//	        member.setUser_cell((String) session.getAttribute("user_cell"));
	        Member member = new Member();
	        member.setUser_id(request.getParameter("user_id"));
			member.setUser_pass(request.getParameter("user_pass1"));
	        member.setUser_email(request.getParameter("user_email"));
	        member.setUser_cell(request.getParameter("user_cell"));

	        MemberDao md = MemberDao.getInstance();
	        int result = md.updateMember(member);
	        
		      if(result == 1){
		            HttpSession session = request.getSession();
		            session.setAttribute("member", member);
		      }
		      
			  request.getSession().setAttribute("result", result);

		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "ModifyPro.jsp";
	}
}
