package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao_Single;
import service.CommandProcess;



public class BoardDeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try { 
	        String pageNum = request.getParameter("pageNum");
	        String user_id = request.getParameter("user_id");
			int num= Integer.parseInt(request.getParameter("num")); 	        
			//String user_pass =request.getParameter("user_pass");
			System.out.println("aaaaa--->"+num);
			BoardDao_Single bd = BoardDao_Single.getInstance();
			Board board = bd.select(num);
			int saving_pnt = 500;
			
			int result2 = bd.ascPnt(user_id, saving_pnt);
			if(result2 == 1) {
			int result = bd.delete(user_id, num);
			request.setAttribute("result", result);
			}
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
			request.setAttribute("user_id", user_id);
		} catch(Exception e) { 
			System.out.println(e.getMessage()); 
		}
		return "boardDeletePro.jsp";
	}

}
