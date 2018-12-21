package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Board_Com;
import dao.Board_ComDao;
import service.CommandProcess;

public class CommUpdateFormAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			Board_Com board_com = new Board_Com();
			int posting_no = Integer.parseInt(request.getParameter("posting_no"));
			int re_level = Integer.parseInt(request.getParameter("re_level"));
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			String comments = request.getParameter("comments2");
			Board_ComDao bd =Board_ComDao.getInstance();
			
			board_com.setPosting_no(posting_no);
			board_com.setRe_level(re_level);
			board_com.setRe_step(re_step);
			board_com.setComments(comments);
			
			int result6 = bd.updateCom(board_com);
			request.setAttribute("result6", result6);
		}catch(Exception e) {System.out.println(e.getMessage());
			
		}
		return "commUpdateResult.jsp";
	}

}
