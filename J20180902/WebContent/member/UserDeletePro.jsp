<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Tripa.com</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="../css/bootstrap.min.css?ver=1">
</head>
<body>
<c:if test="${result == 1 }">
	<% 
		String user_id = (String) session.getAttribute("user_id");
		String user_pass = (String) session.getAttribute("user_pass");
	   	System.out.println("LoginPro.jsp user_id->" + user_id);
     %>
		<script type="text/javascript">
			alert('탈퇴 성공');
			location.href="../search/searchMain.do";
		</script>
	</c:if>
	
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert('탈퇴 불가능');
			history.go(-1);
		</script>
	</c:if>
	
</body>
</html>