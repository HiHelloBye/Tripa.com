package service.board;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao_List;
import dao.BoardDao_Single;
import service.CommandProcess;

public class BoardListAction implements CommandProcess {
	@SuppressWarnings("null")
	public String requestPro(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		BoardDao_List bd = BoardDao_List.getInstance();
		
		List<Board> list1 = null;
		List<Board> list2 = null;
		List<Board> list3 = null;
		
		try { 
			    int totCnt  	= bd.getTotalCnt();		
//				String pageNum 	= request.getParameter("pageNum");				
				
//				if (pageNum==null || pageNum.equals("")) 	
//						pageNum = "1";	
				
//				int currentPage = Integer.parseInt(pageNum);	
				
//				int pageSize 	= 5; 
//				int blockSize 	= 10;
//				int startRow 	= (currentPage - 1) * pageSize + 1;	//시작줄
//				int endRow   	= startRow + pageSize - 1;			//마지막줄
//				int startNum 	= totCnt - startRow + 1;			//시작번호
				
//				List<Board> list = bd.list(startRow, endRow);	
//				List<Board> list = bd.list();	
				
//				int pageCnt 	= (int)Math.ceil((double)totCnt/pageSize);
//				int startPage 	= (int)(currentPage-1)/blockSize*blockSize + 1;
//				int endPage 	= startPage + blockSize -1;	
//				if (endPage > pageCnt) 
//						endPage = pageCnt;			//페이징작업
			
				int floorSize = (int)Math.floor(totCnt/3);
			
				list1 = bd.list(1, floorSize);
				list2 = bd.list(floorSize+1, floorSize*2);
				list3 = bd.list((floorSize*2)+1, totCnt);
				
			
				
				request.setAttribute("totCnt", 		totCnt);		//전체개수
//				request.setAttribute("pageNum", 	pageNum);		//페이지번호
//				request.setAttribute("currentPage", currentPage);	//현재페이지
//				request.setAttribute("startNum", 	startNum);		//글번호
//				request.setAttribute("list", 		list);			//목록
				request.setAttribute("list1", 		list1);
				request.setAttribute("list2", 		list2);
				request.setAttribute("list3", 		list3);
//				request.setAttribute("list_size", 	list.size());	//목록
//				request.setAttribute("blockSize", 	blockSize);		//페이지 넘기는 수
//				request.setAttribute("pageCnt", 	pageCnt);		//5.1234면 6페이지로 만드는 것
//				request.setAttribute("startPage", 	startPage);		//시작페이지
//				request.setAttribute("endPage",	 	endPage);		//끝페이지
				
//				 System.out.println("-----------------------------------------------");  // /ch16/list.do
//				 System.out.println("startNum-->" 	+ startNum);  
//				 System.out.println("totCnt-->" 		+ totCnt);
//				 System.out.println("list.size-->" 	+ list.size()); 
//				 System.out.println("currentPage-->" + currentPage); 
//				 System.out.println("blockSize-->" 	+ blockSize); 
//				 System.out.println("pageSize-->"	+ pageSize); 
//				 System.out.println("pageCnt-->" 	+ pageCnt);  
//				 System.out.println("startPage-->" 	+ startPage); 
//				 System.out.println("endPage-->" 	+ endPage); 
//				System.out.println("startRow-->"+startRow);
//				System.out.println("endRow-->"+endRow);
				
			
		} catch(Exception e) { System.out.println(e.getMessage()); }
		return "boardHome.jsp";
	}
}