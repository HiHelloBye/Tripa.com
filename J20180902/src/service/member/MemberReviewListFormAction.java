package service.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Accom_com;
import dao.Board;
import dao.MemberBoardDao;
import service.CommandProcess;

public class MemberReviewListFormAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		MemberBoardDao mbd = MemberBoardDao.getInstance();
		
		try {
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");  
			int totCnt = mbd.getTotalCnt2(user_id);			
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")){pageNum="1";}
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=100,blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;     
			int endRow=startRow+pageSize-1;              
			int startNum = totCnt-startRow+1;      
			List<Board> list3 = mbd.list3(startRow, endRow, user_id);
						
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
			int endPage = startPage+blockSize-1;
			if(endPage>pageCnt) endPage=pageCnt;
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list3", list3);			
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("PageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("user_id", user_id);
			
			
			System.out.println("------------------------------------------------------------------------");
			System.out.println("startRow->"+startRow);
			System.out.println("endRow->"+endRow);
			System.out.println("startNum->"+startNum);
			System.out.println("totCnt->"+totCnt);
			System.out.println("currentPage->"+currentPage);
			System.out.println("blockSize->"+blockSize);
			System.out.println("pageSize->"+pageSize);
			System.out.println("pageCnt->"+pageCnt);
			System.out.println("startPage->"+startPage);
			System.out.println("endPage->"+endPage);
			
			
			int totCntAccom = mbd.getTotalCnt4(user_id);		
			int currentPage2=Integer.parseInt(pageNum);
			int pageSize2=100,blockSize2=10;
			int startRow2=(currentPage-1)*pageSize2+1;     
			int endRow2=startRow2+pageSize2-1;              
			int startNum2 = totCntAccom-startRow2+1;    
			List<Accom_com> list4 = mbd.list4(startRow2, endRow2, user_id);					
			int pageCnt2 = (int)Math.ceil((double)totCntAccom/pageSize2);
			int startPage2 = (int)(currentPage2-1)/blockSize2*blockSize2+1;
			int endPage2 = startPage2+blockSize2-1;
			if(endPage2>pageCnt2) endPage2=pageCnt2;			
			
			request.setAttribute("list4", list4);
			request.setAttribute("user_id", user_id);
			request.setAttribute("currentPage2", currentPage2);
			request.setAttribute("startNum2", startNum2);
			request.setAttribute("blockSize2", blockSize2);
			request.setAttribute("pageSize2", pageSize2);
			request.setAttribute("PageCnt2", pageCnt2);
			request.setAttribute("startPage2", startPage2);
			request.setAttribute("endPage2", endPage2);
			request.setAttribute("totCntAccom", totCntAccom);
			
			System.out.println("totCntAccom->"+totCntAccom);
		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "MemberReviewListForm.jsp";
	}
}
