package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao_Single;
import service.CommandProcess;

public class ScrapDelAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String user_id = request.getParameter("user_id");
		String user_id = request.getParameter("user_id");
		try {
			BoardDao_Single bd = BoardDao_Single.getInstance();	
			int num = Integer.parseInt(request.getParameter("posting_no"));
			
			int result8 = bd.scrapDel(num,user_id);		
					
			
			
		} catch(Exception e) { 
			System.out.println(e.getMessage()); 
		}
		return "commDeleteResult.jsp";
	}

}
