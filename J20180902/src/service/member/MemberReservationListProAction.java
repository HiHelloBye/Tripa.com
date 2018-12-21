package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberBoardDao;
import service.CommandProcess;

public class MemberReservationListProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
		MemberBoardDao mbd = MemberBoardDao.getInstance();

		try {
	        HttpSession session = request.getSession();
			int reser_no = Integer.parseInt(request.getParameter("reser_no"));
			String pageNum = request.getParameter("pageNum");
			String user_id = (String) session.getAttribute("user_id");
			
			int result9 = mbd.ascUser_Pnt(reser_no, user_id);

	        if(result9 == 1 ){
		        int result = mbd.ReservationDelete(reser_no);
		        if (result == 1) {
			    	   session.setAttribute("result", result);
			    	   session.setAttribute("result9", result9);
			    	   session.setAttribute("pageNum", pageNum);
		        }
	        }
	   
			request.getSession().setAttribute("result9", result9);
			request.getSession().setAttribute("pageNum", pageNum);

		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "MemberReservationListPro.jsp";
	}

}
