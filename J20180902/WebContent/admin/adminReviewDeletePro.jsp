<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tripa.com</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("삭제가 완료되었습니다! ");
		location.href="adminReviewList.do";
	</script>
</c:if>	
<c:if test="${result <= 0 }">
	<script type="text/javascript">
		alert("암호를 다시 입력하십시오");
		location.href="adminReviewList.do";
	</script>
</c:if>	
</body>
</html>