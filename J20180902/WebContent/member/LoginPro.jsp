<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<c:if test="${result == 1 }">
	<% 
	   String user_id = (String) session.getAttribute("user_id");
	   System.out.println("user_id->" + user_id);
     %>
		<script type="text/javascript">
			location.href="../search/searchMain.do";
		</script>
	</c:if>
	
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert('비밀번호가 틀립니다.');
			history.go(-1);
		</script>
	</c:if>
	
	<c:if test="${result == -1 }">
		<script type="text/javascript">
			alert('존재하지 않는 아이디입니다.');
			history.go(-1);

		</script>
	</c:if>
	
	<c:if test="${result == -2 }">
		<script type="text/javascript">
			alert('데이터 베이스 오류가 발생했습니다.');
			history.go(-1);

		</script>
	</c:if>
</body>
</html>