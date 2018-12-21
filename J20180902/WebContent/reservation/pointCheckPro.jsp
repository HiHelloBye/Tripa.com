<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tripa.com</title>
</head>
<body>
<c:if test="${result2 == 1 }">
	<script type="text/javascript">
		alert("포인트 사용이 가능합니다.");
		history.back();
	</script>
</c:if>
<c:if test="${result2 == 0 }">
	<script type="text/javascript">
		alert("포인트가 부족합니다.");
		history.back();
	</script>
</c:if>
<c:if test="${result2 == -1 }">
	<script type="text/javascript">
		alert("포인트는 1000점부터 사용 가능합니다.");
		history.back();
	</script>
</c:if>
<c:if test="${result2 == -2 }">
	<script type="text/javascript">
		alert("사용할 포인트가 너무 많습니다.");
		history.back();
	</script>
</c:if>
</body>
</html>