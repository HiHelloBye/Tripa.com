package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import service.CommandProcess;

public class FindPasswordProAction implements CommandProcess{
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {	             
			request.setCharacterEncoding("utf-8");
	        String user_id = request.getParameter("user_id");
	        String user_email = request.getParameter("user_email");
	        String user_question = request.getParameter("user_question");
	        String user_answer = request.getParameter("user_answer");

		   	System.out.println("FindPasswordFormAction.java user_id->" + user_id);
		   	System.out.println("FindPasswordFormAction.java user_email->" + user_email);
		   	System.out.println("FindPasswordFormAction.java user_question->" + user_question);
		   	System.out.println("FindPasswordFormAction.java user_answer->" + user_answer);
		   	
	        MemberDao md = MemberDao.getInstance();
	        switch(user_question){
        	case "1":
        		user_question="기억에 남는 추억의 장소는?";
        		break;
        	case "2":
        		user_question="자신의 보물 제1호는?";
        		break;
        	case "3":
        		user_question="가장 기억에 남는 선생님 성함은?";
        		break;
        	case "4":
        		user_question="타인이 모르는 자신만의 신체비밀이 있다면?";
        		break;
        	case "5":
        		user_question="추억하고 싶은 날짜가 있다면?";
        		break;
        	case "6":
        		user_question="받았던 선물 중 기억에 남는 독특한 선물은?";
        		break;
        	case "7":
        		user_question="유년시절 가장 생각나는 친구 이름은?";
        		break;
        	case "8":
        		user_question="인상 깊게 읽은 책 이름은?";
        		break;
        	case "9":
        		user_question="자신이 두번째로 존경하는 인물은?";
        		break;
        	case "10":
        		user_question="친구들에게 공개하지 않은 어릴 적 별명이 있다면?";
        		break;
        	case "11":
        		user_question="초등학교 때 기억에 남는 짝궁 이름은?";
        		break;
        	case "12":
        		user_question="다시 태어나면 되고 싶은 것은?";
        		break;
        	case "13":
        		user_question="내가 좋아하는 캐릭터는?";
        		break;
        }
	        System.out.println("switch후에 "+user_question);
	  //     	int result = Integer.valueOf(md.searchPassword(user_id, user_email, user_question, user_answer));
	       	String result = md.searchPassword(user_id, user_email, user_question, user_answer);
		   	
		   	
		   	if(result != null){
	            HttpSession session = request.getSession();
	            session.setAttribute("user_id", user_id);
	            session.setAttribute("user_email", user_email);
	            session.setAttribute("user_question", user_question);
	            session.setAttribute("user_answer", user_answer);
	        } else if (result == null){
	        	return "FindPasswordPro2.jsp";
		   	}
		   	
		   	System.out.println("FindPasswordFormAction.java result->" + result);
	      
			request.setAttribute("user_pass", result);
			request.getSession().setAttribute("user_pass2", result);

		} catch(Exception e) {	System.out.println(e.getMessage());}
		
		return "/member/FindPasswordPro.jsp";
	}
}
