<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>트리콤</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="../css/bootstrap.min.css?ver=1">
</head>
<body>
<c:if test="${result2 == 1  }">
		<script type="text/javascript">
			alert('작성하신 후기가 삭제되었습니다.');
			location.href="MemberReviewListForm.do"
		</script>
	</c:if>
	
	<c:if test="${result2 == 0 }">
		<script type="text/javascript">
			alert('리뷰 삭제가 실패됐습니다.');
			history.go(-1);
		</script>
	</c:if>
	
</body>
</html>