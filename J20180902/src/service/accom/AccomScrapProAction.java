package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Accom_comDao;
import dao.BoardDao_Single;
import dao.Scrap;
import service.CommandProcess;

public class AccomScrapProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			Accom_comDao bd = Accom_comDao.getInstance();	
		try {
			request.setCharacterEncoding("utf-8");
			int accom_no = Integer.parseInt(request.getParameter("accom_no"));			
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");
			Scrap scrap = new Scrap();
			scrap.setUser_id(user_id);
			String scrap_cd="A";
			Scrap scrap1= bd.selectScrap(accom_no);
			int scrap_no= scrap1.getScrap_no();
			int like_check= scrap1.getLike_check();
			scrap.setScrap_cd(scrap_cd);
			scrap.setScrap_no(scrap_no);			
			scrap.setLike_check(like_check);			
			scrap.setAccom_no(accom_no);
	
			
			int result = bd.insertScrap(scrap,user_id);
								
			request.setAttribute("accom_no", scrap.getAccom_no());
			
		} catch(Exception e) { 
			System.out.println(e.getMessage()); 
		}
		return "../board/commDeleteResult.jsp";
	}

}
