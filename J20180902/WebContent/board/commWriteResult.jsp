<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
    
<%
	int result=(int)request.getAttribute("result");
	String user_id = (String)request.getAttribute("user_id");
	String regi_date = (String)request.getAttribute("regi_date");
	String comments = (String)request.getAttribute("comments");
/* 	request.setAttribute("DatCnt", "2");
	//session.setAttribute("DatCnt", "2");
	if(result==1){
		System.out.println("댓글 입력 성공");
		out.println(regi_date);
	}
	else{
		System.out.println("댓글 입력 실패");
	} */
	
	
    JSONObject obj = new JSONObject();
	
	obj.put("regi_date", regi_date);
	obj.put("DatCnt", 3);
	System.out.println(obj);
	
	out.print(obj);
%>