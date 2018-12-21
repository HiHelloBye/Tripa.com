package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Accom;
import dao.adminAccomDao;
import service.CommandProcess;

public class adminAccomUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int accom_no = Integer.parseInt(request.getParameter("accom_no"));
			String pageNum = request.getParameter("pageNum");
			adminAccomDao ad = adminAccomDao.getInstance();
			Accom accom = ad.select(accom_no);  
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("accom", accom);
		}catch(Exception e) {	System.out.println(e.getMessage());	}
		return "adminAccomUpdateForm.jsp";
	}

}
