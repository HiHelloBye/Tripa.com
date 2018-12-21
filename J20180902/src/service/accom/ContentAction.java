package service.accom;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Accom;
import dao.AccomDao;
import dao.Accom_comDao;
import dao.Scrap;
import service.CommandProcess;
public class ContentAction implements CommandProcess{

	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ContentAction start 연결");
		AccomDao bd = AccomDao.getInstance();
		try {
				int accom_no = Integer.parseInt(request.getParameter("accom_no"));
				int totCnt  = bd.getTotalCnt();			
				Accom_comDao bds = Accom_comDao.getInstance();
		        System.out.println("ListAction getTotalCnt");
				String pageNum = request.getParameter("pageNum");	
				HttpSession session = request.getSession();
				String user_id = (String) session.getAttribute("user_id");
				if (pageNum==null || pageNum.equals("")) {	pageNum = "1";	}
				int currentPage = Integer.parseInt(pageNum);
				int pageSize  = 10, blockSize = 10;
				int startRow = (currentPage - 1) * pageSize + 1;
				int endRow   = startRow + pageSize - 1;
				int startNum = totCnt - startRow + 1;
				List<Accom> list = bd.list(startRow, endRow,accom_no);	
				int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
				int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
				int endPage = startPage + blockSize -1;	
				if (endPage > pageCnt) endPage = pageCnt;	
				int result9 = bds.checkScrap(accom_no, user_id);				
				int likeCntBoard = bds.likeCntBoard(accom_no);
				
				String scrap_cd="A";
				Scrap scrap= bds.selectScrap(accom_no);
				int scrap_no= scrap.getScrap_no();
				int like_check= scrap.getLike_check();
				
				request.setAttribute("totCnt", totCnt);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("startNum", startNum);
				request.setAttribute("list", list);
				request.setAttribute("blockSize", blockSize);
				request.setAttribute("pageCnt", pageCnt);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("likeCntBoard", likeCntBoard);
				request.setAttribute("accom_no", accom_no);
				request.setAttribute("scrap_cd", scrap_cd);
				request.setAttribute("scrap_no", scrap_no);
				request.setAttribute("like_check", like_check);
				request.setAttribute("result9", result9);
				request.setAttribute("user_id", user_id);
				
				System.out.println("-----------------------------------------------");  // /ch16/list.do
				System.out.println("startNum-->" + startNum);  // /ch16/list.do
				System.out.println("totCnt-->" + totCnt);  // /ch16/list.do
				System.out.println("currentPage-->" + currentPage);  // /ch16/list.do
				System.out.println("blockSize-->" + blockSize);  // /ch16/list.do
				System.out.println("pageSize-->" + pageSize);  // /ch16/list.do
				System.out.println("pageCnt-->" + pageCnt);  // /ch16/list.do
				System.out.println("startPage-->" + startPage);  // /ch16/list.do
				System.out.println("endPage-->" + endPage);  // /ch16/list.do
			
			
			
			System.out.println("ContentAction getInstance");
			/*bd.readCount(accom_no); */
			Accom board1 = bd.select_accom1(accom_no);
			Accom board2 = bd.select_accom2(accom_no);
			Accom board3 = bd.select_accom3(accom_no);
			Accom board4 = bd.select_accom4(accom_no);
			System.out.println("-----------------------------------------------");  // /ch16/list.do
			System.out.println("ContentAction accom_no-->" + accom_no);  // /ch16/list.do
			System.out.println("ContentAction pageNum-->" + pageNum);  // /ch16/list.do
			System.out.println("ContentAction board-->" + board1);  // /ch16/list.do
			System.out.println("ContentAction board-->" + board2);  // /ch16/list.do
			System.out.println("ContentAction board-->" + board3);  // /ch16/list.do
			System.out.println("ContentAction board-->" + board4);  // /ch16/list.do
			
			request.setAttribute("accom_no", accom_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board1", board1);
			request.setAttribute("board2", board2);
			request.setAttribute("board3", board3);
			request.setAttribute("board4", board4);
			
		} catch(Exception e) { System.out.println(e.getMessage()); }
		return "content.jsp";
	}
}