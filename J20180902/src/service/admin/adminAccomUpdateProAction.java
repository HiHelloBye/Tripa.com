package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Accom;
import dao.adminAccomDao;
import service.CommandProcess;

public class adminAccomUpdateProAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			request.setCharacterEncoding("utf-8"); 
	        String pageNum = request.getParameter("pageNum");
	        Accom accom = new Accom();
	        System.out.println("여기까지 들어오나 확인PRO");
	        accom.setAccom_no(Integer.parseInt(request.getParameter("accom_no")));
	        accom.setAccom_name(request.getParameter("accom_name"));
	        accom.setAccom_type(request.getParameter("accom_type"));
	        accom.setAccom_addr(request.getParameter("accom_addr"));
	        accom.setAccom_func(request.getParameter("accom_func"));
	        accom.setAccom_map(request.getParameter("accom_map"));
	        accom.setAccom_cont(request.getParameter("content"));
	        accom.setInout(request.getParameter("inout"));
	        accom.setArea(request.getParameter("area"));
			adminAccomDao aad = adminAccomDao.getInstance();
			int result = aad.update(accom);
			
			request.setAttribute("result", result);
			request.setAttribute("accom_no", accom);
			request.setAttribute("pageNum", pageNum);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "adminAccomUpdatePro.jsp";
	}

}
