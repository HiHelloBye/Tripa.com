package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.adminBoardDao;
import service.CommandProcess;

public class adminReviewDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String[] posting_no = request.getParameterValues("posting_no");
			String pageNum = request.getParameter("pageNum");
			String user_pass = request.getParameter("user_pass");
			adminBoardDao abd = adminBoardDao.getInstance();	
			int result = 0;
		
			for(int i=0;i<posting_no.length;i++){
				System.out.println("accom_no[i]->"+posting_no[i]);
				Board board = null;
				result = abd.delete(posting_no[i], user_pass); 
				request.setAttribute("result", result);
				request.setAttribute("pageNum", pageNum);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "adminReviewDeletePro.jsp";
	}

}
