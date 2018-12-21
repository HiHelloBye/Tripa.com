package service.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Accom_comDao;
import dao.MemberBoardDao;
import dao.Reservation;
import service.CommandProcess;

public class MemberReservationListFormAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
		MemberBoardDao mbd = MemberBoardDao.getInstance();
		Accom_comDao accd = Accom_comDao.getInstance();
		try {
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");
			System.out.println("MemberReservationListForm user_id1->" + user_id);
			int totCnt = mbd.getTotalCnt1(user_id);
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")){pageNum="1";}
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=100,blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;     
			int endRow=startRow+pageSize-1;              
			int startNum = totCnt-startRow+1; 
			//int accom_no = Integer.parseInt(request.getParameter("accom_no"));
			System.out.println("MemberReservationListForm user_id->" + user_id);
			//System.out.println("accom_no->"+accom_no);
			//List<Accom_comDao> com = accd.getList(1,totCnt);

			List<Reservation> list2 = mbd.list2(user_id, startRow, endRow);
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
			int endPage = startPage+blockSize-1;
			if(endPage>pageCnt) endPage=pageCnt;
			

			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list2);
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

		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "MemberReservationListForm.jsp";
	}
}
