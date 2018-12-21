package service.search;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.Accom;
import dao.Board;
import dao.searchAccomDao;
import dao.searchBoardDao;
import service.CommandProcess;

public class SearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yy.MM.dd", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );

		searchBoardDao sbd = searchBoardDao.getInstance();
		searchAccomDao sad = searchAccomDao.getInstance();
		
		String keyword 			= "";
		String keywordAccom 	= "";
		String ckeckin 			= "";
		String ckeckout		 	= "";
		String room 			="";
		String reviewsort 		= "";
		
		try{
			request.setCharacterEncoding("utf-8"); 
			
			String radio=request.getParameter("switch");
			keyword = request.getParameter("keyword");
			
//			System.out.println("switch*******************"+ radio);
//			System.out.println("keyword*******************"+ keyword);
			
			/* 
			 * 리뷰 검색
			 */
		    if(radio.equals("review")){
				//검색결과 정렬
				reviewsort = request.getParameter("reviewsort");
				System.out.println("SearchAction reviewsort->"+reviewsort);
	
			}  
			/* 
			 * 숙소 검색
			 */
			else if (radio.equals("accom")) {
				//숙소 검색
				keywordAccom = request.getParameter("keywordAccom");
				
				if(ckeckin == null || ckeckin.equals("")) {
					ckeckin = mTime;
				} else {
					ckeckin = request.getParameter("checkin");
				}

				if(ckeckout == null || ckeckout.equals("")){
					ckeckout = "30/12/31";
				} else{
					ckeckout = request.getParameter("checkout");
				}

				if(room == null || room.equals("")){
					room = "Single";
				} else {
					room = request.getParameter("room");
				}
				
				System.out.println("room 나오는 값: "+room);
				
				//검색결과 정렬
				reviewsort = request.getParameter("reviewsort");
				
				System.out.println("SearchAction reviewsort->"+reviewsort);
				

			} else {
				//검색결과 정렬
				reviewsort = request.getParameter("reviewsort");
				
				System.out.println("reviewsort->"+reviewsort);
				
			}
 
			String inout	=null;
			String area		=null;
			
			List<Board> listBoard1;
			List<Board> listBoard2;

			/*
			 * 리뷰
			 */
			request.setAttribute("keyword", keyword);
			System.out.println("keyword->"+keyword);
			int ceilTotBoardCnt;
			//검색 결과만 리스트 보여주기
			int totCntBoard = (sbd.getTotalCnt(keyword)).size();
			
			if(totCntBoard==1){
				ceilTotBoardCnt = 1;
			}
			else{
				ceilTotBoardCnt = (int)Math.ceil(totCntBoard/2);
			}
			System.out.println("??????????????????????????" + ceilTotBoardCnt);
			System.out.println("totCntBoard->"+totCntBoard);
			
			if(radio.equals("review")){
				
				//국내외코드랑 지역번호 가져오기
				List<String> list=null;
				
				if(keyword.contains("#")){
					if(reviewsort.equals("0")){
						
						listBoard1 = sbd.getHashtag(1, ceilTotBoardCnt,keyword);
						listBoard2 = sbd.getHashtag(ceilTotBoardCnt+1, totCntBoard, keyword);
					
					}else if(reviewsort.equals("1")){
						
						listBoard1 = sbd.listHashtagScrap(1, ceilTotBoardCnt, keyword);
						listBoard2 = sbd.listHashtagScrap(ceilTotBoardCnt+1, totCntBoard, keyword);

					}else if(reviewsort.equals("2")){
						
						listBoard1 = sbd.listHashtagLook(1, ceilTotBoardCnt, keyword);
						listBoard2 = sbd.listHashtagLook(ceilTotBoardCnt+1, totCntBoard, keyword);		

					}else {
						listBoard1 = sbd.getHashtag(1, ceilTotBoardCnt,keyword);
						listBoard2 = sbd.getHashtag(ceilTotBoardCnt+1, totCntBoard, keyword);

					}
				
				} else {
					if(reviewsort.equals("0")){
						
						listBoard1 = sbd.listBoard(1, ceilTotBoardCnt,keyword);
						listBoard2 = sbd.listBoard(ceilTotBoardCnt+1, totCntBoard, keyword);

					}else if(reviewsort.equals("1")){
						
						listBoard1 = sbd.listBoardScrap(1, ceilTotBoardCnt, keyword);
						listBoard2 = sbd.listBoardScrap(ceilTotBoardCnt+1, totCntBoard, keyword);

					}else if(reviewsort.equals("2")){
						
						listBoard1 = sbd.listBoardLook(1, ceilTotBoardCnt, keyword);
						listBoard2 = sbd.listBoardLook(ceilTotBoardCnt+1, totCntBoard, keyword);

					}else {
						
						listBoard1 = sbd.listBoard(1, ceilTotBoardCnt, keyword);
						listBoard2 = sbd.listBoard(ceilTotBoardCnt+1, totCntBoard,keyword);

					}
				}
				
				request.setAttribute("totCnt", totCntBoard);
				request.setAttribute("listBoard1", listBoard1);//목록
				request.setAttribute("listBoard2", listBoard2);//목록
				
				return "searchReviewResult.jsp";
			}
			
			/* 
			 * 숙소
			 */
			else{

				//국내외코드랑 지역번호 가져오기
				List<Accom> listAccom1;
				List<Accom> listAccom2;

				request.setAttribute("keywordAccom", keywordAccom);
				
				int totCntAccom=sad.getTotalCnt(keywordAccom);
			
				
			
				
				int ceilTotAccomCnt = (int)Math.ceil(totCntAccom/2);
				
				
				System.out.println("*******************");
				System.out.println("*checkin->"+ckeckin);
				System.out.println("*checkout->"+ckeckout);
				System.out.println("*ceilTotBoardCnt->"+ceilTotAccomCnt);
				System.out.println("*totCntAccom->"+totCntAccom);
				System.out.println("*******************");
				if(reviewsort.equals("0")){
					listAccom1 = sad.listAccom(keywordAccom, 1, ceilTotAccomCnt, ckeckin, ckeckout, room);
					listAccom2 = sad.listAccom(keywordAccom, ceilTotAccomCnt+1, totCntAccom, ckeckin, ckeckout, room); 

				}else if(reviewsort.equals("1")){
					
					listAccom1 = sad.listAccomLowPrice(keywordAccom, 1, ceilTotAccomCnt, ckeckin, ckeckout,room);
					listAccom2 = sad.listAccomLowPrice(keywordAccom, ceilTotAccomCnt+1, totCntAccom, ckeckin, ckeckout,room);
					
				}else if(reviewsort.equals("2")){
					
					listAccom1 = sad.listAccomRat(keywordAccom, 1, ceilTotAccomCnt, ckeckin, ckeckout,room);
					listAccom2 = sad.listAccomRat(keywordAccom, ceilTotAccomCnt+1, totCntAccom,  ckeckin, ckeckout,room);
					
				}else if(reviewsort.equals("3")){
					
					listAccom1 = sad.listAccomComm(keywordAccom, 1, ceilTotAccomCnt, ckeckin, ckeckout,room);
					listAccom2 = sad.listAccomComm(keywordAccom, ceilTotAccomCnt+1, totCntAccom, ckeckin, ckeckout,room);
					
				}else {
					listAccom1 = sad.listAccom(keywordAccom, 1, ceilTotAccomCnt, ckeckin, ckeckout,room);
					listAccom2 = sad.listAccom(keywordAccom, ceilTotAccomCnt+1, totCntAccom, ckeckin, ckeckout,room);
				}
				

				// int pageCnt = (int)Math.ceil((double)totCnt2/pageSize);
				// //초기화 시 1
				// int startPage = (int)(currentPage-1)/blockSize*blockSize+1;
				// //초기화 시 10
				// int endPage = startPage+blockSize-1;
				// //초기화시  4
				// if(endPage>pageCnt) endPage=pageCnt;
				
				request.setAttribute("checkin", ckeckin);
				request.setAttribute("checkout",ckeckout);
				request.setAttribute("room",room);
				request.setAttribute("totCnt2", totCntAccom);
				request.setAttribute("listAccom1", listAccom1);//목록
				request.setAttribute("listAccom2", listAccom2);//목록


		
				return "searchAccomResult.jsp";
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
