package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberBoardDao;
import service.CommandProcess;

public class MemberReviewListProAction2 implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		       HttpSession session = request.getSession();
		       int accom_no = Integer.parseInt(request.getParameter("accom_no"));
		       int com_no = Integer.parseInt(request.getParameter("com_no"));
		       String pageNum = request.getParameter("pageNum");
		       MemberBoardDao mbd = MemberBoardDao.getInstance();

		       int result2 = mbd.ReviewDelete2(accom_no, com_no);
				System.out.println("zxdv"+result2);
		       if(result2 == 1){
		    	   session.setAttribute("result", result2);
		    	   session.setAttribute("pageNum", pageNum);
		       }
		        
				request.getSession().setAttribute("result2", result2);
				request.getSession().setAttribute("pageNum", pageNum);
		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "MemberReviewListPro2.jsp";
	}
}
