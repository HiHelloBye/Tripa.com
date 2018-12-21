package service.accom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;


public class ResvCreditProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = 0;
		try {
		int valid_date1 = Integer.parseInt(request.getParameter("valid_date1"));
			String card_no1 = request.getParameter("card_no1");
			String card_no2 = request.getParameter("card_no2");
			String card_no3 = request.getParameter("card_no3");
			String card_no4 = request.getParameter("card_no4");
			String valid_date2 = request.getParameter("valid_date2");
			String cvc_no = request.getParameter("cvc_no");
			String card_passwd = request.getParameter("card_passwd");
				if(valid_date1>0 && valid_date1<13 && card_no1.length()==4 && card_no2.length()==4&& card_no3.length()==4&& card_no4.length()==4
						&& valid_date2.length()==2 && cvc_no.length()==3 && card_passwd.length()==4) {
					result = 1;
				} else {
					result = 0;
				}
				System.out.println("ResvCreditProAction result->"+result);
				request.setAttribute("result", result);

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "resvCreditPro.jsp";
	}

}
