package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Board;
import dao.adminBoardDao;
import service.CommandProcess;

public class adminNoticeUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int posting_no = Integer.parseInt(request.getParameter("posting_no"));
			String pageNum = request.getParameter("pageNum");
			adminBoardDao ad = adminBoardDao.getInstance();
			Board board = ad.select(posting_no);  
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
		}catch(Exception e) {	System.out.println(e.getMessage());	}
		return "adminNoticeUpdateForm.jsp";
	}

}
