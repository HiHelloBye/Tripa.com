package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Accom;
import dao.Member;
import dao.MemberDao;
import service.CommandProcess;
import dao.ReservationDao;

public class ResvWriteFormAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();

			String user_id = (String) session.getAttribute("user_id");  
			int accom_no = Integer.parseInt(request.getParameter("accom_no"));
			String room_no = request.getParameter("room_no");
			
			ReservationDao ard = ReservationDao.getInstance();
			Accom accomInfo = ard.select2(accom_no,room_no);
			MemberDao md = MemberDao.getInstance();
			Member member = md.getMember(user_id);

			int price = accomInfo.getPrice();
			String res_name = member.getUser_name();
			int reser_no = ard.maxReserNo() + 1;
			
			request.setAttribute("accom_no", accom_no);
			request.setAttribute("room_no", room_no);
			request.setAttribute("reser_no", reser_no);
			request.setAttribute("price", price);
			request.setAttribute("user_id", user_id);
			request.setAttribute("res_name", res_name);
			
			System.out.println("accom_no->" + accom_no);
			System.out.println("room_no->" + room_no);
			System.out.println("reser_no->" + reser_no);
			System.out.println("price->" + price);
			System.out.println("user_id->" + user_id);
			System.out.println("res_name->" + res_name);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return "resvWriteForm.jsp";
	}
}
