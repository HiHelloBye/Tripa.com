package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Accom_comDao;
import service.CommandProcess;

public class AccomScrapDelAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		try {
			Accom_comDao bd = Accom_comDao.getInstance();	
			int accom_no = Integer.parseInt(request.getParameter("accom_no"));
			int resultScrap = bd.scrapDel(accom_no, user_id);	
					
			
			
		} catch(Exception e) { 
			System.out.println(e.getMessage()); 
		}
		return "../board/commDeleteResult.jsp";
	}

}
