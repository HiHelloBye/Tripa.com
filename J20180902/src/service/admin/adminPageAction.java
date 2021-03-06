package service.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member;
import dao.MemberBoardDao;

import service.CommandProcess;

public class adminPageAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		MemberBoardDao mbd = MemberBoardDao.getInstance();
		try {
			HttpSession session = request.getSession();
			int totCnt = 1;
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")){pageNum="1";}
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=100,blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;     
			int endRow=startRow+pageSize-1;              
			int startNum = totCnt-startRow+1; 
			String user_id = (String) session.getAttribute("user_id");  

			List<Member> list = mbd.list(startRow, endRow, user_id);
			
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
			int endPage = startPage+blockSize-1;
			if(endPage>pageCnt) endPage=pageCnt;
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
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
			
		} catch(Exception e) { System.out.println(e.getMessage()); }
		return "adminPage.jsp";
	}
}

