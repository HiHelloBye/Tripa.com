package service.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Accom;
import dao.adminAccomDao;
import service.CommandProcess;

public class adminAccomDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("adminAccomDeleteFormAction Start...");
		try {
			String[] accom_no = request.getParameterValues("accom_no");
			String pageNum = request.getParameter("pageNum");
			String user_pass = request.getParameter("user_pass");
			adminAccomDao ad = adminAccomDao.getInstance();	
			int result = 0;
		
			for(int i=0;i<accom_no.length;i++){
				System.out.println("accom_no[i]->"+accom_no[i]);
				Accom accom = null;
				result = ad.delete(accom_no[i], user_pass); 
				request.setAttribute("result", result);
				request.setAttribute("pageNum", pageNum);
			}
		}catch(Exception e) {	System.out.println(e.getMessage());	}
		return "adminAccomDeletePro.jsp";
	}

}
