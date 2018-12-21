package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Reservation;
import dao.ReservationDao;
import service.CommandProcess;

public class ResvTotalCheckAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result2=0;
		int result3=0;
		int result =0;
		try {
			String user_id = request.getParameter("user_id");
			//String user_id = "abcabc11";
			//int accom_no = 1;
			int accom_no = Integer.parseInt(request.getParameter("accom_no"));
			//String room_no="Single";
			String room_no = request.getParameter("room_no");
			String out_date = request.getParameter("out_date");
			String in_date = request.getParameter("in_date");
			int saving_pnt = Integer.parseInt(request.getParameter("saving_pnt"));
			int option1 = Integer.parseInt(request.getParameter("option1"));
			int option2 = Integer.parseInt(request.getParameter("option2"));
			String user_email = request.getParameter("email");
			String res_cont = request.getParameter("res_cont");
			String res_name = request.getParameter("res_name");
			String user_cell = request.getParameter("user_cell");
			int Odate = Integer.parseInt(out_date.substring(2, 4));
			int Idate = Integer.parseInt(in_date.substring(2, 4));
			int Oedate = Integer.parseInt(out_date.substring(4, 6));
			int Iedate = Integer.parseInt(in_date.substring(4, 6));
			ReservationDao ardpro = ReservationDao.getInstance();
			result3 = ardpro.date_compare(out_date,in_date,accom_no,room_no);
			result2 = ardpro.compare(user_id,saving_pnt);
			int reser_no = ardpro.maxReserNo()+1;
			if(out_date.length()<6 || in_date.length()<6) {
				result2=0;
			} else if (Odate>12 || Idate>12 || Oedate>31 || Iedate>31) {
				result2=0;
			}
			
			if (result2 == 1 && result3==1 ) {
				Reservation resv = new Reservation();
				resv.setAccom_no(accom_no);
				resv.setRoom_no(room_no);
				resv.setReser_no(reser_no);
				resv.setOut_date(out_date);
				resv.setIn_date(in_date);
				resv.setUser_email(user_email);
				resv.setRes_cont(res_cont);
				resv.setUser_id(user_id);
				resv.setRes_name(res_name);
				resv.setUser_cell(user_cell);
				resv.setRsaving_pnt(saving_pnt);
				result = ardpro.insert(resv);
				if (result == 1) {
				int result4 = ardpro.descPnt(user_id,saving_pnt);
				}
			}
			request.setAttribute("result", result);
			request.setAttribute("result2", result2);
			request.setAttribute("result3", result3);
			request.setAttribute("option1", option1);
			request.setAttribute("option2", option2);
			request.setAttribute("reser_no", reser_no);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "resvWritePro.jsp";
	}

}
