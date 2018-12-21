<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<% 
	String user_id = (String) session.getAttribute("user_id");
%>
<!DOCTYPE html>
<html>
<head>
	<title>Tripa.com</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="../css/bootstrap.min.css?ver=2">
	<link rel="stylesheet" href="../css/search.css"> 
	<link rel="stylesheet" href="../css/btn.css">
	<script type="text/javascript" src="../js/search.js"></script>
	<script type="text/javascript">
	function passwordCheckFunction() {
		var user_pass1 = $('#user_pass1').val();
		var user_pass2 = $('#user_pass2').val();
		if(user_pass1 != user_pass2) {
			$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
		} else {
			$('#passwordCheckMessage').html('');
		}
	}
	function display1(box){
		container1.style.display = 'block';
		container2.style.display = 'none';
		}
		
	function display2(box){
		container1.style.display = 'none';
		container2.style.display = 'block';
		}
		
		window.onload=display2
	</script>
</head>
<body>
	<nav style="width: 130px;" class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="../search/searchMain.do">Tripa.com</a>
		</div>
	</nav>
		<nav class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="../search/searchMain.do">트리콤</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">여행후기</a></li>
				<li><a href="#">공지사항</a></li>
			</ul>
	       	<%
			if(user_id == null ) {
			%>
			<ul class="nav navbar-nav navbar-right">
	           	<li><a href="#contact" id="login">로그인</a></li>
				<li><a href="JoinForm.do">회원가입</a></li>
			</ul>
			<%
				} else if(user_id.equals("admin")){
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="active">
		 	    	<a><span class="glyphicon glyphicon-user" aria-hidden="true">
		 	    	</span><%=user_id%>님 환영합니다<span class="caret"></span></a>
				</li>
					<li><a href="Logout.do">로그아웃</a></li>
					<li><a href="../admin/adminMain.do">관리자 페이지</a></li>
			</ul>
		     <% } else { %>
		 	<ul class="nav navbar-nav navbar-right">
		 	    <li class="active">
		 	    	<a><span class="glyphicon glyphicon-user" aria-hidden="true">
		 	    	</span><%=user_id%>님 환영합니다<span class="caret"></span></a>
				</li>
				<li><a href="Logout.do">로그아웃</a></li>
				<li><a href="MyPage.do">회원정보</a></li>
			</ul> 
			<%
				}
			%>
		</div>
	</nav>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog modal-sm" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modaxl-title" id="myModalLabel">로그인을 하세요</h4>
      			</div>
      			<div class="modal-body">
      			<form action="LoginPro.do" method="post" class="form-signin">
         			<label class="sr-only">아이디</label>
         			<input type="text" name="user_id" class="form-control" placeholder="아이디" required="required" autofocus="autofocus">
         			<label class="sr-only">비밀번호</label>
         			<input type="password" name="user_pass" class="form-control" placeholder="비밀번호" required="required">
         			<hr>
         			<button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
      			</form>
		      	<br>
		      	<a href="JoinForm.do" type="button" target=_blank class="btn btn-success">회원가입</a>
		      	<a href="FindPasswordForm.do" type="button" target=_blank class="btn btn-info">비밀번호 찾기</a>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      			</div>
   			</div>
  		</div>
	</div>
	<div class="container">
		<form method="post" action="ModifyPro.do" >
			<table class="table table-bordered table-hover" style="text-align: center; background-color:white;  border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" ><h4>회원 정보 수정</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input class="form-control" id="user_id" name="user_id" maxlength="20" value="<%=user_id%>" readonly></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호 변경</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();" class="form-control" id="user_pass1" type="password" name="user_pass1" maxlength="20" placeholder="비밀번호 변경"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호 변경</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();" class="form-control" id="user_pass2" type="password" name="user_pass2" maxlength="20" placeholder="비밀번호  변경 확인"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일 변경</h5></td>
						<td colspan="2"><input class="form-control" id="user_email" type="email" name="user_email" maxlength="20" placeholder="이메일 변경"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>전화번호</h5></td>
						<td colspan="2"><input class="form-control" id="user_cell" type="number" name="user_cell" maxlength="20" placeholder="전화번호 변경"></td>
					</tr>
					<tr>	
						<td style="text-align: left;" colspan="3"><h5 style="color: red;" id="passwordCheckMessage"></h5><input class="btn btn-primary pull-right" type="submit" value="등록"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
		<script type="text/javascript">
		$('#login').click(function(event){
		   event.preventDefault();
		   $('#myModal').modal('show');
		});
	</script>		
</body>
</html>