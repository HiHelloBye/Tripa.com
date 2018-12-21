package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board_Com;
import dao.Board_ComDao;
import service.CommandProcess;

public class CommDeleteFormAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			int posting_no = Integer.parseInt(request.getParameter("posting_no"));
			int re_level = Integer.parseInt(request.getParameter("re_level"));
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			System.out.println(re_level);
			Board_ComDao bd =Board_ComDao.getInstance();
			int result5 = bd.deleteCom(posting_no,re_level, re_step);
			
			request.setAttribute("result5", result5);
		}catch(Exception e) {System.out.println(e.getMessage());
			
		}
		return "commDeleteResult.jsp";
	
	}

}
