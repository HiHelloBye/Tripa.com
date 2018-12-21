package service.board;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao_Single;
import dao.Bphoto;
import dao.BphotoDao;
import dao.Hashtag;
import dao.HashtagDao;
import service.CommandProcess;

public class BoardUpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		
		System.out.println("UpdateFormAction시작");
		try{
			int posting_no 	= Integer.parseInt(request.getParameter("posting_no"));
			
			BoardDao_Single bd 	= BoardDao_Single.getInstance();			
			BphotoDao bp 			= BphotoDao.getInstance();
			HashtagDao ht			= HashtagDao.getInstance();
			
			//수정할 게시글에 대한 정보
			Board board = bd.select(posting_no);	
			

			request.setAttribute("posting_no", posting_no);
		
			request.setAttribute("board", board);
			request.setAttribute("content", board.getContent_text());
			request.setAttribute("hashtag", bd.getHashtag(posting_no));
			request.setAttribute("totPhotoCnt", bp.totPhotoCnt(posting_no));
			request.setAttribute("totHashtagCnt", ht.totHashtagCnt(posting_no));
			
			
			//false 일 때 후기작성
			//true 넘어가기
			
			int count = 4;
			int td_row = 0;
			
			int [] first_ = new int[count];

			for(int i =0; i<first_.length; i++) {
				first_[i] = 0;
			}
			
			Map<Integer, Boolean> test = new HashMap<Integer, Boolean>();
			
			
			
			//왔었는지 확인
			if(first_[td_row] == 0) { 
				test.put(td_row, false);
			}
			
			
			if(test.get(td_row) == true) {
				//alert
			}
			else if (test.get(td_row) == false) {
				//후기작성 모달
				test.put(td_row, true);
				first_[td_row] = 1;
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "boardUpdateForm.jsp";
	}

}
