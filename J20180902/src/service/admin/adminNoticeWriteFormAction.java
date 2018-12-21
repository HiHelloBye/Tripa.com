package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Board;
import dao.adminBoardDao;
import service.CommandProcess;

public class adminNoticeWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			int posting_no = 0;
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null) pageNum = "1";
		//	if (request.getParameter("accom_no") != null) {
				posting_no = Integer.parseInt(request.getParameter("posting_no"));
				adminBoardDao aad = adminBoardDao.getInstance();
				Board board = aad.select(posting_no);
		//	}
			request.setAttribute("posting_no", posting_no);
			request.setAttribute("pageNum", pageNum);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "adminNoticeWriteForm.jsp";
	}

}
