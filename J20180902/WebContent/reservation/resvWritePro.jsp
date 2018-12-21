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
<c:if test="${result>0 }">
	<c:if test="${option2==2 }">
		<script type="text/javascript">
			location.href="resvMootong.jsp?reser_no="+${reser_no};
		</script>
	</c:if>
	<c:if test="${option2==0 }">
		<script type="text/javascript">
			location.href="resvCredit.jsp?reser_no="+${reser_no};
		</script>
	</c:if>
</c:if>
<c:if test="${result3 != 1 }">
	<script type="text/javascript">
		alert("예약 가능 날짜를 다시 확인하여주세요.");
		history.back();
	</script>
</c:if>
<c:if test="${result2 == 0 }">
	<script type="text/javascript">
		alert("적립금을 다시 확인하여 주세요.");
		history.back();
	</script>
</c:if>
<c:if test="${result3 == 1&&result2 == 1&&result==0 }">
	<script type="text/javascript">
		alert("예약이 되지 않았습니다. 입력한 정보를 다시 확인해주세요.");
		history.back();
	</script>
</c:if>
</body>
</html>
