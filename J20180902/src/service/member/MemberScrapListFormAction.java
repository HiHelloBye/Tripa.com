package service.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberBoardDao;
import dao.Scrap;
import service.CommandProcess;

public class MemberScrapListFormAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		MemberBoardDao mbd = MemberBoardDao.getInstance();

		try {
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");  
			int totCnt = mbd.getTotalCnt3(user_id);
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")){pageNum="1";}
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=100,blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;     
			int endRow=startRow+pageSize-1;              
			int startNum = totCnt-startRow+1;      
			List<Scrap> listReview4 = mbd.listReview4(startRow, endRow, user_id);
			
			
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
			int endPage = startPage+blockSize-1;
			if(endPage>pageCnt) endPage=pageCnt;
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("listReview4", listReview4);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("PageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("user_id", user_id);
			
			int totCnt2 = mbd.getTotalCnt();   
			System.out.println(totCnt2);
			int currentPage2=Integer.parseInt(pageNum);
			int pageSize2=100,blockSize2=10;
			int startRow2=(currentPage2-1)*pageSize2+1;     
//			int endRow2=startRow2+pageSize2-1;             
			int startNum2 = totCnt2-startRow2+1;            
			List<Scrap> listTravel4 = mbd.listTravel4(startRow, endRow, user_id);
			int pageCnt2 = (int)Math.ceil((double)totCnt2/pageSize2);
			int startPage2 = (int)(currentPage2-1)/blockSize2*blockSize2+1;
			int endPage2 = startPage+blockSize2-1;
			if(endPage2>pageCnt2) endPage2=pageCnt2;
			
			request.setAttribute("totCnt2", totCnt2);
			request.setAttribute("currentPage2", currentPage2);
			request.setAttribute("startNum2", startNum2);
			request.setAttribute("listTravel4", listTravel4);
			request.setAttribute("blockSize2", blockSize2);
			request.setAttribute("PageCnt2", pageCnt2);
			request.setAttribute("startPage2", startPage2);
			request.setAttribute("endPage2", endPage2);
//			
		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "MemberScrapListForm.jsp";
	}
}
