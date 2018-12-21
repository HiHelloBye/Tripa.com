package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReservationDao;
import service.CommandProcess;

public class RescDateCheckAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result3 = 0;

		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("RescDateCheckAction Start..." );

			int accom_no = Integer.parseInt(request.getParameter("accom_no"));
			String room_no = request.getParameter("room_no");
			String out_date = request.getParameter("out_date");
			String in_date = request.getParameter("in_date");
			if(out_date.length()<6 || in_date.length()<6) {
				result3 = 4;
			} else {
				int Odate = Integer.parseInt(out_date.substring(2, 4));
				int Idate = Integer.parseInt(in_date.substring(2, 4));
				int Oedate = Integer.parseInt(out_date.substring(4, 6));
				int Iedate = Integer.parseInt(in_date.substring(4, 6));
				
				if (Odate>12 || Idate>12 || Oedate>31 || Iedate>31) {
				result3 = 4;
				} else {
					ReservationDao ardpro = ReservationDao.getInstance();
					result3 = ardpro.date_compare(out_date, in_date, accom_no, room_no);
					if(result3==1) {
						request.setAttribute("out_date", out_date);
						request.setAttribute("in_date", in_date);
					}
				}
			}
			System.out.println("result3====>"+result3);
			request.setAttribute("result3", result3);
		}	
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "resvDateCheck.jsp";
	}

}
