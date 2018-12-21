<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tripa.com</title>
</head>
<body>
<h2>삭제하려면 암호를 입력하세요</h2>
<form action="boardDeletePro.do">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="num" value="${board.posting_no}">
	<input type="hidden" name="user_id" value="${board.user_id}">
	암호:<input type="password" name="user_pass"><p>
	<input type="submit" value="확인">
</form>
</body>
</html>