package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member;
import dao.MemberDao;
import service.CommandProcess;

public class JoinProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
	        request.setCharacterEncoding("utf-8");
	        
	        Member member = new Member();
	        member.setUser_id(request.getParameter("user_id"));
	        member.setUser_pass(request.getParameter("user_pass1"));
	        member.setUser_name(request.getParameter("user_name"));
	        member.setUser_gen(request.getParameter("user_gen"));
	        member.setUser_email(request.getParameter("user_email"));
	        member.setUser_question((request.getParameter("user_question")));
	        member.setUser_answer(request.getParameter("user_answer"));
	        member.setUser_cell(request.getParameter("user_cell"));

	        MemberDao md = MemberDao.getInstance();
	        int result = md.register(member);
	        
		      if(result == 1){
		            HttpSession session = request.getSession();
		            session.setAttribute("member", member);
		        }
		      
		    request.setAttribute("result", result);

		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "JoinPro.jsp";
	}
}
