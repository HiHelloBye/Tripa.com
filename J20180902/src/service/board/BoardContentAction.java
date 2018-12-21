package service.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Board;
import dao.BoardDao_Single;
import dao.Board_Com;
import dao.Bphoto;
import dao.Scrap;
import service.CommandProcess;



public class BoardContentAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String A = "";
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			
			HttpSession session = request.getSession();
			String user_id 		= (String) session.getAttribute("user_id");
			BoardDao_Single bd 	= BoardDao_Single.getInstance();	
			
			int likeCntBoard = bd.likeCntBoard(num);
			bd.readCount(num); 
			Board board = bd.select(num);	
			
			int Pnum = bd.select2(num);
			String B = bd.select3(1, 1);
			String location = bd.loc(num);
			
			List<Board_Com> comList = bd.listComment(num);
			int comListCnt  = comList.size();
			
			for(int a=1; a<=Pnum; a++) {
				 B = bd.select3(num,a);
				 A = A + B;
			}
			int result9 = bd.checkScrap(num, user_id);
			
			String scrap_cd="B";
			Scrap scrap= bd.selectScrap(num);
			int scrap_no= scrap.getScrap_no();
			int like_check= scrap.getLike_check();		
			String tripDate = bd.tripDate(num);

			String thumbnail = request.getParameter("thumbnail");
			
			request.setAttribute("scrap_cd", scrap_cd);
			request.setAttribute("scrap_no", scrap_no);
			request.setAttribute("like_check", like_check);
			request.setAttribute("result9", result9);
			request.setAttribute("tripDate", tripDate);
			request.setAttribute("location", location);
			request.setAttribute("A", A);
			request.setAttribute("board", board);
			request.setAttribute("thumbnail", thumbnail);
			request.setAttribute("comList", comList);
			request.setAttribute("comListCnt", comListCnt);
			request.setAttribute("likeCntBoard", likeCntBoard);
		} catch(Exception e) { System.out.println(e.getMessage()); }
		return "boardContent.jsp";
	}
}