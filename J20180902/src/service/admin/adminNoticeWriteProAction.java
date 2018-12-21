package service.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.adminBoardDao;
import service.CommandProcess;

public class adminNoticeWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	  
	        request.setCharacterEncoding("utf-8"); 
	        String pageNum = request.getParameter("pageNum");
	        Board board = new Board();
	        
	        board.setBoard_title(request.getParameter("board_title"));
	        
//	        Date from = new Date();
//	        SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd");
//	        String to = transFormat.format(from);
//	        board.setRegi_date(to);
//			board.setSaving_pnt(0);
	        board.setContent_text(request.getParameter("content_text"));
//			board.setUser_id("admin");
//			board.setInout(null);
//			board.setArea(null);
	        adminBoardDao adPro = adminBoardDao.getInstance();//DB 
	        int result = adPro.insert(board);
	        
	        request.setAttribute("result", result);
	        request.setAttribute("pageNum", pageNum);
		} catch(Exception e) { System.out.println(e.getMessage()); }
		return "adminNoticeWritePro.jsp";
	}

}
