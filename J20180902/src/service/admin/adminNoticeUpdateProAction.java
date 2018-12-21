package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.adminBoardDao;
import service.CommandProcess;

public class adminNoticeUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			request.setCharacterEncoding("utf-8"); 
	        String pageNum = request.getParameter("pageNum");
	        Board board = new Board();
	        board.setPosting_no(Integer.parseInt(request.getParameter("posting_no")));
	        board.setBoard_title(request.getParameter("board_title"));
	        board.setContent_text(request.getParameter("content_text"));
	        adminBoardDao abd = adminBoardDao.getInstance();
			int result = abd.update(board);
			
			request.setAttribute("result", result); 
			request.setAttribute("posting_no", board);
			request.setAttribute("pageNum", pageNum);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "adminNoticeUpdatePro.jsp";
	}

}
