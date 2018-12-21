package service.accomCom;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Accom;
import dao.Accom_comDao;
import dao.Accom_com;
import service.CommandProcess;

public class Accom_comListAction implements CommandProcess {
	public String requestPro(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		Accom_comDao bd = Accom_comDao.getInstance();
		try { 
				int accom_no = Integer.parseInt(request.getParameter("accom_no"));
			    int totCnt  = bd.getTotalCnt();		// 38	
				String pageNum = request.getParameter("pageNum");				
				if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
				int currentPage = Integer.parseInt(pageNum);	
				int pageSize  = 100, blockSize = 10;
				int startRow = (currentPage - 1) * pageSize + 1;//시작줄:(1-1)*10+1
				int endRow   = startRow + pageSize -1;//startRow + pageSize - 1;//마지막줄:1+10-1
				int startNum = totCnt - startRow + 1;//시작번호
				List<Accom_com> list = bd.listAccom_com(startRow, endRow,accom_no);	
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				int endPage = startPage + blockSize -1;	
				if (endPage > pageCnt) endPage = pageCnt;	//페이징작업				
				Accom accom = bd.select(accom_no);	
				
			
				request.setAttribute("totCnt", totCnt);//조회수
				request.setAttribute("pageNum", pageNum);//페이지번호
				request.setAttribute("currentPage", currentPage);//현재페이지
				request.setAttribute("startNum", startNum);//글번호
				request.setAttribute("list", list);//목록
				request.setAttribute("list_size", list.size());//목록
				request.setAttribute("blockSize", blockSize);//페이지 넘기는 수
				request.setAttribute("pageCnt", pageCnt);//5.1234면 6페이지로 만드는 것
				request.setAttribute("startPage", startPage);//시작페이지
				request.setAttribute("endPage", endPage);//끝페이지
				request.setAttribute("accom", accom);
				
				System.out.println("-----------------------------------------------");  // /ch16/list.do
				System.out.println("startNum-->" + startNum);  // /ch16/list.do
				System.out.println("totCnt-->" + totCnt);  // /ch16/list.do
				System.out.println("list.size-->" + list.size());  // /ch16/list.do
				System.out.println("currentPage-->" + currentPage);  // /ch16/list.do
				System.out.println("blockSize-->" + blockSize);  // /ch16/list.do
				System.out.println("pageSize-->" + pageSize);  // /ch16/list.do
				System.out.println("pageCnt-->" + pageCnt);  // /ch16/list.do
				System.out.println("startPage-->" + startPage);  // /ch16/list.do
				System.out.println("endPage-->" + endPage);  // /ch16/list.do
				System.out.println("startRow-->"+startRow);
				System.out.println("endRow-->"+endRow);
				System.out.println("endRow-->"+accom);
				System.out.println("endRow-->"+accom_no);
			
		} catch(Exception e) { System.out.println(e.getMessage()); }
		return "accom_comList.jsp";
	}
}