<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "dao.*" %>
    <% 
    ReservationDao ardpro = ReservationDao.getInstance();
	String endDate = ardpro.endDate();
	int reser_no = Integer.parseInt(request.getParameter("reser_no"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.fff {
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/httpRequest.js"></script>
<script type="text/javascript">
	function aaa(){
		alert("예약 완료");
		location.href="../search/searchMain.do";
	}
	function aab() {
		var reser_no = frm.ab.value;
		var sendData="reser_no="+reser_no;
			$.post('resvDelete.do',
				sendData, 
				function() {
			        history.back();
		      }
			);
	} 
</script>
<title>Tripa.com</title>
</head>
<body>
<div style="text-align: center; font-size:30px" class="container">
<table border="1" width="650px">
<tr><td>
<h2>무통장 입금</h2>
<div align="center" style="font-size:20px">
<table bgcolor="#C6D3D3" border="1" width="500px" height = "200px">
	<tr><td style="font-weight: bold">은행명</td><td style="text-align: left">신한은행</td></tr>
	<tr><td style="font-weight: bold">계좌 번호</td><td style="text-align: left">110-988-762584</td></tr>
	<tr><td style="font-weight: bold">예금주</td><td style="text-align: left">박지성</td></tr>
	<tr><td style="font-weight: bold">입금기한</td><td style="text-align: left"><%=endDate %></td></tr>
</table>
<br><br>
</div>
	<tr><td><input class="fff" type="button" value="확인" onclick="aaa()">
			<input class="fff" type="button" value="취소" onclick="aab()">
	</td></tr>
</td></tr>
</table>
</div>
 <form name="frm">
	<input type="hidden" name="ab" value=<%=reser_no%>>
</form> 
</body>
</html>