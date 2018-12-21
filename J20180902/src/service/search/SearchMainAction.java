package service.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Accom;
import dao.Board;
import dao.searchBoardDao;
import service.CommandProcess;

public class SearchMainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		searchBoardDao sbd = searchBoardDao.getInstance();
		try{
			int totCnt = sbd.getTotalCnt();   
			
			System.out.println(totCnt);	
//			String pageNum = request.getParameter("pageNum");
			
//			if(pageNum==null||pageNum.equals("")){pageNum="1";}
			
//			int currentPage=Integer.parseInt(pageNum);
//			int pageSize=10,blockSize=10;
//			int startRow=(currentPage-1)*pageSize+1;     //초기화시 1
//			int startNum = totCnt-startRow+1;            //초기화시 38
							 //초기화시          1        10
			
			// best review top 10(1~5)
			List<Board> listBest1 = sbd.listBest(1,5); 
			// best review top 10(1~5)
			List<Board> listBest2 = sbd.listBest(6,10); 
			
			System.out.println("listBest1 -> "+listBest1.size());
			System.out.println("listBest2 -> "+listBest2.size());
//			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			//초기화 시 1
//			int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
			//초기화 시 10
//			int endPage = startPage+blockSize-1;
			//초기화시  4
//			if(endPage>pageCnt) endPage=pageCnt;
			
			request.setAttribute("totCnt", totCnt);
//			request.setAttribute("pageNum", pageNum);
//			request.setAttribute("currentPage", currentPage);
//			request.setAttribute("startNum", startNum);
			request.setAttribute("list1", listBest1);
			request.setAttribute("list2", listBest2);
//			request.setAttribute("blockSize", blockSize);
//			request.setAttribute("PageCnt", pageCnt);
//			request.setAttribute("startPage", startPage);
//			request.setAttribute("endPage", endPage);
			
			int totCnt2 = sbd.getTotalCnt();   
			System.out.println(totCnt2);
		
//			int currentPage2=Integer.parseInt(pageNum);
//			int pageSize2=10,blockSize2=10;
//			int startRow2=(currentPage2-1)*pageSize2+1;     //초기화시 1
//			int endRow2=startRow2+pageSize2-1;              //초기화시 10
//			int startNum2 = totCnt2-startRow2+1;            //초기화시 38
							 //초기화시          1        10
			
			// best accommdation top 10(1~5)
			List<Accom> listAccom1 = sbd.listAccom(1,8); 
			List<Accom> listAccom2 = sbd.listAccom(9,16); 

//			int pageCnt2 = (int)Math.ceil((double)totCnt2/pageSize2);
			//초기화 시 1
//			int startPage2 = (int)(currentPage2-1)/blockSize2*blockSize2+1;
			//초기화 시 10
//			int endPage2 = startPage+blockSize2-1;
			//초기화시  4
//			if(endPage2>pageCnt2) endPage2=pageCnt2;
			
			request.setAttribute("totCnt2", totCnt2);
//			request.setAttribute("currentPage2", currentPage2);
//			request.setAttribute("startNum2", startNum2);
			request.setAttribute("listAccom1", listAccom1);
			request.setAttribute("listAccom2", listAccom2);
//			request.setAttribute("blockSize2", blockSize2);
//			request.setAttribute("PageCnt2", pageCnt2);
//			request.setAttribute("startPage2", startPage2);
//			request.setAttribute("endPage2", endPage2);
			
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "searchMain.jsp";
	}

}
