<%@ page import= "dao.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Tripa.com</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
<!--  	<link rel="stylesheet" href="./css/custom.css"> -->
</head>
<body>
비밀번호 : ${user_pass}

		<% 
           String user_pass2 = (String) session.getAttribute("user_pass2");

		   	System.out.println("FindPasswordPro.jsp user_pass2->" + user_pass2);
	   	
		%>
		<script type="text/javascript">
			alert('비밀번호는 ${user_pass} 입니다.');
			location.href="index.do";
		</script>
			
</body>
</html>