<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="Error.jsp"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tripa.com</title>
</head>
<body>
<c:if test="${result>0}">
	<script type="text/javascript">
	alert("입력완료");
	// location.href="boardList.do?pageNum=${pageNum}";
	// 메인으로 돌아가는게 좋을 것 같음
	location.href="boardList.do";
	</script>
</c:if>	
<c:if test="${result==0}">
	<script type="text/javascript">
	alert("입력오류");
	location.href="boardWriteForm.do?num=${board.posting_no}&pageNum=${pageNum}";	
	</script>
</c:if>
</body>
</html>