package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReservationDao;
import service.CommandProcess;

public class ResvDeleteAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		int reser_no = Integer.parseInt(request.getParameter("reser_no"));
		ReservationDao ard = ReservationDao.getInstance();
		int a = ard.ReservationDelete(reser_no);
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "deleteDone.jsp";
	}

}
