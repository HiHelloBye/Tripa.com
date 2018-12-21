package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao_Single;
import service.CommandProcess;


public class BoardDeleteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");
			String user_id = request.getParameter("user_id");
			System.out.println("aaaaa--->"+user_id);
			BoardDao_Single bd =BoardDao_Single.getInstance();
			Board board = bd.select(num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);
			request.setAttribute("user_id", user_id);
			request.setAttribute("board", board);
		}catch(Exception e) {System.out.println(e.getMessage());
			
		}
		return "boardDeleteForm.jsp";
	
	}

}
