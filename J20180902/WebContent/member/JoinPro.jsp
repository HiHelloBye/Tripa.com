<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<script type="text/javascript">
			alert('회원가입에 성공하였습니다.');
			location.href="../search/searchMain.do";
		</script>
	</c:if>
	
	<c:if test="${result == -1 }">
		<script type="text/javascript">
			alert('회원가입에 실패하였습니다.');
			history.go(-1);
		</script>
	</c:if>

</body>
</html>