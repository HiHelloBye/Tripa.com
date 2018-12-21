package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao_Single;
import dao.Scrap;
import service.CommandProcess;

public class ScrapProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			BoardDao_Single bd = BoardDao_Single.getInstance();
		try {
			request.setCharacterEncoding("utf-8");
			//String user_id = request.getParameter("user_id");	
			
			String user_id=request.getParameter("user_id");
			Scrap scrap = new Scrap();
			scrap.setUser_id(user_id);
			scrap.setScrap_cd(request.getParameter("scrap_cd"));
			scrap.setScrap_no(Integer.parseInt(request.getParameter("scrap_no")));
			scrap.setPosting_no(Integer.parseInt(request.getParameter("posting_no")));
			scrap.setLike_check(Integer.parseInt(request.getParameter("like_check")));					
			
			int result = bd.insertScrap(scrap,user_id);
					
			request.setAttribute("posting_no", scrap.getPosting_no());
			
		} catch(Exception e) { 
			System.out.println(e.getMessage()); 
		}
		return "commDeleteResult.jsp";
	}

}
