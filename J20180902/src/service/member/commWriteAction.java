package service.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Accom;
import dao.AccomDao;
import dao.Accom_com;
import dao.Accom_comDao;
import dao.Acphoto;
import dao.Member;
import dao.MemberBoardDao;
import dao.Reservation;
import dao.ReservationDao;
import dao.adminAccomDao;
import service.CommandProcess;

public class commWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//후기 등록
		Accom_com accom_com = new Accom_com();
		Acphoto acphoto = new Acphoto();
		MemberBoardDao mbd = MemberBoardDao.getInstance();
		Accom_comDao accd = Accom_comDao.getInstance();
		Accom accom = new Accom();
		AccomDao ad = AccomDao.getInstance();
		ReservationDao rs = new ReservationDao();
		
		try{
			
			
			HttpSession session = request.getSession();
			int totCnt = mbd.getTotalCnt();
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")){pageNum="1";}
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=10,blockSize=10;
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
			
			
			// String savePath = "D:/Projects/workspace/projectName/WebContent/folderName";
			String savePath = "C:/jspSrc/J20180902/WebContent/image";
			 
			// 파일 크기 15MB로 제한
			int sizeLimit = 1024*1024*15;
			MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			int accom_no = Integer.parseInt(multi.getParameter("accom_no"));
			System.out.println("accom_no->"+accom_no);
			
			
			int reser_no=Integer.parseInt(multi.getParameter("reser_no"));
			rs.updateFlag(reser_no);
			
			accom_com.setAccom_no(accom_no);
			acphoto.setAccom_no(accom_no);
			// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
	        String fileName = multi.getFilesystemName("uploadFile1");
	        // 업로드한 파일의 전체 경로를 DB에 저장하기 위함
	        String m_fileFullPath = "../image/" + fileName;
	        System.out.println("m_fileFullPath->"+m_fileFullPath);
	        acphoto.setPhoto(m_fileFullPath);
	        
	        accom_com.setUser_id(user_id);
	        acphoto.setUser_id(user_id);
	        System.out.println("user_id->"+user_id);
	        
	        String review_cont = multi.getParameter("review_cont");
	        accom_com.setReview_cont(review_cont);
	        System.out.println("review_cont->"+review_cont);
	        
	        String score= multi.getParameter("score");
	        
	        if(score==null || score.equals("")){
	        	score="0.5";
	        }
	        
	     	float score2 = Float.parseFloat(score);	
			accom_com.setAccom_rat(score2);
			
			int maxCom_no = accd.getMaxcom_no(accom_no);
			
			accom_com.setSaving_pnt(100);
			
			accom_com.setCom_no(maxCom_no);
			acphoto.setCom_no(maxCom_no);
			accom.setAccom_no(accom_no);
			accom.setAccom_rat(score2);
			
			accd.insert(acphoto);
			int result0 = ad.update(accom_com);
			
			request.setAttribute("result", result0);
			
		}catch(Exception e) {	
			System.out.println(e.getMessage());
		}
		return "MyPage.jsp";
	}

}
