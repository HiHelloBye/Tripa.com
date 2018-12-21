package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReservationDao;
import service.CommandProcess;

public class ResvPointCheckAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result2 = 0;
		try {
			request.setCharacterEncoding("utf-8");
//			String user_id = "abcabc11";
			String user_id = request.getParameter("user_id");
			int saving_pnt = Integer.parseInt(request.getParameter("saving_pnt"));
			
			int price = Integer.parseInt(request.getParameter("price"));
			if(saving_pnt<1000) {
				result2 = -1;
			} else if(saving_pnt>price) {
				result2 = -2;
			} else {
				ReservationDao ardpro = ReservationDao.getInstance();
				result2 = ardpro.compare(user_id, saving_pnt);
				
				if ( result2 > 0) {
					request.setAttribute("saving_pnt", saving_pnt);
				}
			}
//			request.setAttribute("price", price);
			request.setAttribute("result2", result2);
		}	
		catch(Exception e) {
		}
		return "pointCheckPro.jsp";
	}

}
