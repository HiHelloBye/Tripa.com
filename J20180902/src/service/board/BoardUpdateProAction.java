package service.board;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils; 

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

public class BoardUpdateProAction implements CommandProcess {
	
	public String requestPro(HttpServletRequest request,
	
	HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> imgArr = new ArrayList<String>();
		ArrayList<String> hashArr = new ArrayList<String>();

		BoardDao_Single bd 	= BoardDao_Single.getInstance();	
		BphotoDao bp 		= BphotoDao.getInstance();
		HashtagDao ht		= HashtagDao.getInstance();

		try { 
			request.setCharacterEncoding("utf-8"); 
			
			//원글의 사진과 해시태그 개수
			int totPhotoCnt = Integer.parseInt(request.getParameter("totPhotoCnt"));
			int totHashtagCnt = Integer.parseInt(request.getParameter("totHashtagCnt"));
			
			//수정된 글 내용과 입력된해시태그
			String imgSrc 		= request.getParameter("smarteditor");
			String hashtags 	= request.getParameter("hashtag");

			
			// upload는 폴더 이름 (나중에 변경)
			int updatePhotoCnt = StringUtils.countMatches(imgSrc, "image");
			String srcTmp = imgSrc;
			
			// 사진 경로 뽑는 곳 ------------------------------------- //
			for (int i = 0; i < updatePhotoCnt; i++) {

				String target = "image";
				int target_num = srcTmp.indexOf(target);

				String result = srcTmp.substring(target_num,(srcTmp.substring(target_num).indexOf(".")+4+target_num));

				String src = "../" + result;
				imgArr.add(i, src);
				// 처음으로 만난 upload 단어를 "" 으로 대체
				srcTmp = srcTmp.replaceFirst(target, ""); 
			}
			
//			System.out.println("imgArr 잘 들어가있는지 확인");
//			System.out.println(imgArr);

			// 해시태그 하나씩 뽑는 곳 ----------------------------------- //
			String hashTmp = hashtags;
	
			//문자열 앞 뒤 공백 제거
			String trimHashTmp = StringUtils.trimToEmpty(hashTmp);
			//문자열의 공백을 기준으로 나눔
			String[] split = StringUtils.split(trimHashTmp);

			for(String string : split) {
				if(string.contains("#"))
					hashArr.add(string);
				else 
					hashArr.add("#" + string);
			}

			int updateHashCnt = hashArr.size();
			System.out.println("hashArr 잘 들어가있는지 확인");
			System.out.println(hashArr);
	
			//게시판(Board) 테이블 수정
			System.out.println(request.getParameter("smarteditor"));
//			int tmp = bd.update(request.getParameter("board_title"), request.getParameter("smarteditor"),
//					Integer.parseInt(request.getParameter("posting_no")));
			
			Board board = new Board();
			board.setBoard_title(request.getParameter("board_title"));
			board.setContent_text(request.getParameter("smarteditor"));
			board.setPosting_no(Integer.parseInt(request.getParameter("posting_no")));
			
			int tmp = bd.update(board);
			
			/* 사진(Bphoto) 테이블 수정	*/		
			//수정하는 사진개수가 원래 사진개수보다 많은 경우
			if(totPhotoCnt < updatePhotoCnt) {
				//게시판 사진(Bphoto) 테이블 수정
				//원래있던 사진 수정
				System.out.println("수정하는 사진개수가 원래 사진개수보다 많은 경우");
				System.out.println("<-------테이블 사진 수정update 시작------>");
				Bphoto bphoto = new Bphoto();
				for(int i=0; i<totPhotoCnt; i++) {							
					bp.update(imgArr.get(i),Integer.parseInt(request.getParameter("posting_no")), i+1);
				}
				//추가로 insert 되어야할 사진들
				System.out.println("<-------테이블 사진 수정insert 시작------>");
				for(int i=0; i<updatePhotoCnt-totPhotoCnt;i++) {
					bphoto.setPosting_no(Integer.parseInt(request.getParameter("posting_no")));
					bphoto.setPho_no(totPhotoCnt+1+i);
					bphoto.setPhoto(imgArr.get(totPhotoCnt+i));
									
					bp.insert(bphoto);
				}
			}
			
			//수정하는 사진개수가 원래 사진개수보다 적은 경우
			else if(totPhotoCnt > updatePhotoCnt ) {
				//게시판 사진(Bphoto) 테이블 수정
				//원래있던 사진 수정
				System.out.println("수정하는 사진개수가 원래 사진개수보다 적은 경우");
				System.out.println("<-------테이블 사진 수정update 시작------>");
				
				for(int i=0; i<updatePhotoCnt; i++) {
		
					bp.update(imgArr.get(i),Integer.parseInt(request.getParameter("posting_no")), i+1);
				
				}
				//추가로 delete 되어야할 사진들
				System.out.println("<-------테이블 사진 수정delete 시작------>");
								
				bp.delete(Integer.parseInt(request.getParameter("posting_no")),updatePhotoCnt+1);
				
			}
			
			//수정하는 사진개수가 원래 사진개수보다 같은 경우
			else if(totPhotoCnt == updatePhotoCnt) {
				System.out.println("수정하는 사진개수가 원래 사진개수보다 같은 경우");
				System.out.println("<-------테이블 사진 수정update 시작------>");
								
				for(int i=0; i<updatePhotoCnt; i++) {
									
					bp.update(imgArr.get(i),Integer.parseInt(request.getParameter("posting_no")), i+1);
				}
			}
			
			
			
			/* 해시태그(Hashtag) 테이블 수정	*/	
			System.out.println("원본 해시 : 수정 해시   " + totHashtagCnt + "   " + updateHashCnt );
			//수정하는 해시태그가 원래 해시태그보다 많은 경우
			if(totHashtagCnt < updateHashCnt) {
				//원래있던 해시태그 수정
				System.out.println("수정하는 해시태그가 원래 해시태그보다 많은 경우");
				System.out.println("<-------테이블 해시태그 수정update 시작------>");
				
				Hashtag hashtag = new Hashtag();
				for(int i=0; i<totHashtagCnt; i++) {							
					ht.update(hashArr.get(i),Integer.parseInt(request.getParameter("posting_no")), i+1);
				}
				
				//추가로 insert 되어야할 사진들
				System.out.println("<-------테이블 해시태그 수정insert 시작------>");
				for(int i=0; i<updateHashCnt-totHashtagCnt;i++) {
					hashtag.setPosting_no(Integer.parseInt(request.getParameter("posting_no")));
					hashtag.setHash_no(totHashtagCnt+1+i);
					hashtag.setHashtag(hashArr.get(totHashtagCnt+i));
									
					ht.insert(hashtag);
				}
			}
			
			//수정하는 해시태그 개수가 원래 해시태그 개수 보다 적은 경우
			else if(totHashtagCnt > updateHashCnt ) {
				//게시판 사진(Bphoto) 테이블 수정
				//원래있던 사진 수정
				System.out.println("수정하는 해시태그 개수가 원래 해시태그 개수보다 적은 경우");
				System.out.println("<-------테이블 사진 수정update 시작------>");
				
				for(int i=0; i<updateHashCnt; i++) {
		
					ht.update(hashArr.get(i),Integer.parseInt(request.getParameter("posting_no")), i+1);
				
				}
				//추가로 delete 되어야할 사진들
				System.out.println("<-------테이블 사진 수정delete 시작------>");
								
				ht.delete(Integer.parseInt(request.getParameter("posting_no")),updateHashCnt+1);
				
			}
			
			//수정하는 해시태그 개수가 원래 해시태그 개수와 같은 경우
			else if(totHashtagCnt == updateHashCnt) {
				System.out.println("수정하는 해시태그 개수가 원래 해시태그 개수보다 같은 경우");
				System.out.println("<-------테이블 사진 수정update 시작------>");
								
				for(int i=0; i<totHashtagCnt; i++) {
									
					ht.update(hashArr.get(i),Integer.parseInt(request.getParameter("posting_no")), i+1);
				}
			}
			
			int result = tmp;
			
			request.setAttribute("result", result);

		} catch(Exception e) { 
			System.out.println(e.getMessage()); 				
		}
		return "boardUpdatePro.jsp";
	}
}