package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberBoardDao;
import service.CommandProcess;

public class MemberReviewListProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		       HttpSession session = request.getSession();
		       int posting_no = Integer.parseInt(request.getParameter("posting_no"));
		       String pageNum = request.getParameter("pageNum");
		       MemberBoardDao mbd = MemberBoardDao.getInstance();

		       int result = mbd.ReviewDelete(posting_no);
				
		       if(result == 1){
		    	   session.setAttribute("result", result);
		    	   session.setAttribute("pageNum", pageNum);
		       }
		        
				request.getSession().setAttribute("result", result);
				request.getSession().setAttribute("pageNum", pageNum);
		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "MemberReviewListPro.jsp";
	}
}
