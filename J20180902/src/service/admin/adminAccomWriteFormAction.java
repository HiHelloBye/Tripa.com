package service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Accom;
import dao.Board;
import dao.adminAccomDao;
import service.CommandProcess;

public class adminAccomWriteFormAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "adminAccomWriteForm.jsp";
	}
	
}
