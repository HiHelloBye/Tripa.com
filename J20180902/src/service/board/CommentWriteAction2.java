package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao_Single;
import dao.Board_Com;
import dao.Board_ComDao;
import service.CommandProcess;

public class CommentWriteAction2 implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int posting_no = Integer.parseInt(request.getParameter("posting_no"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		String user_id = request.getParameter("user_id");
		String comments3 = request.getParameter("comments3");
		try {
			Board_ComDao dao = Board_ComDao.getInstance();
			BoardDao_Single adpro = BoardDao_Single.getInstance();
			Board_Com board_com = new Board_Com();
			
			String regi_date = dao.getDate();
			int intCommNo = dao.getCommNo()+1;
			int re_step = dao.getReStep(posting_no,re_level)+1;
			int saving_pnt = 0;
			
			board_com.setComm_no(intCommNo); 		
        	board_com.setPosting_no(posting_no);
        	board_com.setUser_id(user_id);
        	board_com.setComments(comments3);
        	board_com.setSaving_pnt(saving_pnt);	
        	board_com.setRegi_date(regi_date);
        	board_com.setRe_level(re_level);
        	board_com.setRe_step(re_step);
        	board_com.setRef(posting_no);
		
        	int result = dao.insertComments(board_com);
        	request.setAttribute("result", result);
 			request.setAttribute("user_id", user_id);
 			request.setAttribute("regi_date", regi_date);
 			request.setAttribute("comments2", comments3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return "commWriteResult2.jsp";
	}

}
