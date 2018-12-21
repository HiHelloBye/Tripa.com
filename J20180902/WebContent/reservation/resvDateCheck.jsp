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
result3 : ${result3 }
<c:if test="${result3 == 1 }">
	<script type="text/javascript">
		alert("예약 가능한 날짜입니다.");
		history.back();
	</script>
</c:if>
<c:if test="${result3 == 2 }">
	<script type="text/javascript">
		alert("Check Out 날짜가 중복됩니다. 다른 날짜를 선택하여 주세요.");
		history.back();
	</script>
</c:if>
<c:if test="${result3 == 3 }">
	<script type="text/javascript">
		alert("Check In 날짜가 중복됩니다. 다른 날짜를 선택하여 주세요.");
		history.back();
	</script>
</c:if>
<c:if test="${result3 == 0 }">
	<script type="text/javascript">
		alert("날짜를 다시 확인해주세요.");
		history.back();
	</script>
</c:if>
<c:if test="${result3 == 4 }">
	<script type="text/javascript">
		alert("날짜가 잘못되었습니다. 다시 입력해주세요.");
		history.back();
	</script>
</c:if>
</body>
</html>