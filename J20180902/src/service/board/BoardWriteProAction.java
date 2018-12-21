package service.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils; 
import dao.Board;
import dao.BoardDao_Single;
import dao.Bphoto;
import dao.BphotoDao;
import dao.Hashtag;
import dao.HashtagDao;
import service.CommandProcess;

/* --------------------------------------------------------
			입력받은 form을 보드로 넣는 곳
			smarteditor에 들은 값 확인
   --------------------------------------------------------*/

public class BoardWriteProAction implements CommandProcess {
	public String requestPro(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
			ArrayList<String> imgArr = new ArrayList<String>();
			ArrayList<String> hashArr = new ArrayList<String>();

			BoardDao_Single bd 	= BoardDao_Single.getInstance();	
			BphotoDao bp 		= BphotoDao.getInstance();
			HashtagDao ht		= HashtagDao.getInstance();
			
			try {
				request.setCharacterEncoding("utf-8");
//				System.out.println("************************************");
//				System.out.println("에디터결과");
//				System.out.println(request.getParameter("smarteditor"));
//				System.out.println(request.getParameter("board_title"));
//				System.out.println(request.getParameter("user_id"));
				
				String imgSrc	= request.getParameter("smarteditor");
				String hashtags 	= request.getParameter("hashtag");
				
				
				System.out.println("pro action 에서 posting_no?????"+Integer.parseInt(request.getParameter("posting_no")));
				// upload는 폴더 이름 (나중에 변경)
				int count = StringUtils.countMatches(imgSrc, "image");
				String srcTmp = imgSrc;
				
				// 사진 경로 뽑는 곳 ------------------------------------- //
				for (int i = 0; i < count; i++) {

					String target = "image";
					int target_num = srcTmp.indexOf(target);

					String result = srcTmp.substring(target_num,(srcTmp.substring(target_num).indexOf(".")+4+target_num));

					String src = "../" + result;
					imgArr.add(i, src);
					// 처음으로 만난 upload 단어를 "" 으로 대체
					srcTmp = srcTmp.replaceFirst(target, ""); 
				}
				System.out.println("imgArr 잘 들어가있는지 확인");
				System.out.println(imgArr);
				// -------------------------------------------------------//

				// 해시태그 하나씩 뽑는 곳 ----------------------------------- //
				int count2 = StringUtils.countMatches(hashtags, " ");
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

				System.out.println("hashArr 잘 들어가있는지 확인");
				System.out.println(hashArr);
				// -------------------------------------------------------//

				
				//게시판(Board) 테이블
				Board board = new Board();
				String area = request.getParameter("area");
				System.out.println("area 뭐인지 확인: "+area);
				String newarea = null;
				board.setUser_id(request.getParameter("user_id"));
				board.setSaving_pnt(500);
				board.setBoard_title(request.getParameter("board_title"));
				board.setContent_text(request.getParameter("smarteditor"));
				board.setLook(0);
				board.setScrap_cnt(0);
				board.setInout(request.getParameter("inout"));
				newarea = bd.getArea(area);
				board.setArea(newarea);
				board.setStart_date(request.getParameter("start_date"));
				board.setEnd_date(request.getParameter("end_date"));
				
				System.out.println("#########################33");
				
				int result = bd.insert(board);
				System.out.println("******************************8");
				// 적립금 추가
				bd.ascUser_Pnt(request.getParameter("user_id"));
				
				//게시판 사진(Bphoto) 테이블
				Bphoto bphoto = new Bphoto();

				for(int i=0; i<imgArr.size(); i++) {
					
					bphoto.setPosting_no(Integer.parseInt(request.getParameter("posting_no")));
					bphoto.setPho_no(i+1);
					bphoto.setPhoto(imgArr.get(i));
									
					bp.insert(bphoto);
				}
				
				//해시태그(Hashtag) 테이블
				Hashtag hashtag = new Hashtag();
				
				for(int i=0; i<hashArr.size(); i++) {
					hashtag.setPosting_no(Integer.parseInt(request.getParameter("posting_no")));
					hashtag.setHash_no(i+1);
					hashtag.setHashtag(hashArr.get(i));
					
					ht.insert(hashtag);
				}
				
				request.setAttribute("result", result);
			
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}return "boardWritePro.jsp";
		}
}
