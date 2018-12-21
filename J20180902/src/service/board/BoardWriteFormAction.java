package service.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao_List;
import dao.BoardDao_Single;
import dao.Bphoto;
import service.CommandProcess;
public class BoardWriteFormAction implements CommandProcess {
	public String requestPro(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		// *** 아직 안함 *** //
		// 팝업창에서
		// user_id와 국가, 도시, 체크인, 체크아웃을 받아옴
		// 그 값들을 setAttribute로 지정해주고 
		// boardWriteForm.jsp로 넘겨주어야한다
		
		BoardDao_List bd = BoardDao_List.getInstance();

		try {
		
			request.setCharacterEncoding("utf-8"); 

			int totCnt = bd.getTotalCntWithAdmin();
			System.out.println("????????????????????" + totCnt);
			totCnt += 1;
			
			String user_id 		= request.getParameter("user_id");
			String inout 		= request.getParameter("inout");
			String area 		= request.getParameter("area");			
			String board_title 	= request.getParameter("board_title");
			String start_date	= request.getParameter("startDate");
			String end_date		= request.getParameter("arriveDate");
			
			System.out.println("//////////////////////////"+inout);
			System.out.println("area : "+ area);
			if(inout.equals("국내")) {
				inout = "K";				
				/*switch(area) {
					case "충청도":
						area = "01";	
					break;
					case "전라도":
						area = "02";	
						break;
					case "경상도":
						area = "03";	
						break;
					case "제주도":
						area = "04";	
						break;
					case "강원도":
						area = "05";	
						break;
					default:
				}*/
				area = bd.getArea(inout,area);
			}
			
			else if(inout.equals("국외")) {
				inout = "F";
				/*switch(area) {
					case "일본":
						area = "01";	
						break;
					case "프랑스":
						area = "02";	
						break;
					case "영국":
						area = "03";	
						break;
					case "브라질":
						area = "04";	
						break;
					case "베트남":
						area = "05";	
						break;
					case "중국":
						area = "06";	
						break;
					case "미국":
						area = "07";	
						break;
					case "독일":
						area = "08";	
						break;
					case "네덜란드":
						area = "09";	
						break;
					case "캐나다":
						area = "10";	
						break;
					default:
				
				}*/
				area = bd.getArea(inout,area);
			}
			
			start_date 	= start_date.substring(2);
			start_date = start_date.replaceAll("-", "/");
			end_date 	= end_date.substring(2);
			end_date = end_date.replaceAll("-", "/");

			request.setAttribute("user_id"		, 	user_id);
			request.setAttribute("inout"		, 	inout);
			request.setAttribute("area"			, 	area);
			request.setAttribute("board_title"	, 	board_title);
			request.setAttribute("posting_no"	, 	totCnt);
			request.setAttribute("start_date"	, 	start_date);
			request.setAttribute("end_date"		, 	end_date);
			
			System.out.println("글쓰는 사람은 누구?" + user_id);
			System.out.println("국내? 국외?" 		 + inout);
			System.out.println("어느지역??" 		 + area);
			System.out.println("포스팅 제목??" 		 + board_title);
			System.out.println("포스팅번호??" 		 + totCnt);
			System.out.println("출발일??" 			 + start_date);
			System.out.println("도착일??" 			 + end_date);
			
		}catch(Exception e) {System.out.println(e.getMessage());}
		return "boardWriteForm.jsp";
	}	
}
