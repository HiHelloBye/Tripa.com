<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<% 
	String user_id = (String) session.getAttribute("user_id");
%>
<!DOCTYPE HTML>
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Tripa.com</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../css/bootstrap.min.css?ver=2">
		<link rel="stylesheet" href="../member/assets/css/main.css?ver=1" />
	</head>
<body class="is-preload">
	<nav style="width: 130px;" class="navbar navbar-default">
		<div class="navbar-header">
			<a class="navbar-brand" href="../search/searchMain.do">Tripa.com</a>
		</div>
	</nav>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="../search/searchMain.do">트리콤</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="../board/boardList.do">여행 후기</a></li>
				<li><a href="../admin/NoticeList.do">공지사항</a></li>
			</ul>
	           <%
			if(user_id == null ) {
			%>
			<ul class="nav navbar-nav navbar-right">
	           	<li><a href="#contact" id="login">로그인</a></li>
				<li><a href="../member/JoinForm.do">회원가입</a></li>
			</ul>
			<%
				} else if(user_id.equals("admin")){
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="active">
		 	    	<a><span class="glyphicon glyphicon-user" aria-hidden="true">
		 	    	</span><%=user_id%>님 환영합니다<span class="caret"></span></a>
				</li>
					<li><a href="../member/Logout.do">로그아웃</a></li>
					<li><a href="../admin/adminMain.jsp">관리자 페이지</a></li>
			</ul>
		     <% } else { %>
		 	<ul class="nav navbar-nav navbar-right">
		 	    <li class="active">
		 	    	<a><span class="glyphicon glyphicon-user" aria-hidden="true">
		 	    	</span><%=user_id%>님 환영합니다<span class="caret"></span></a>
				</li>
				<li><a href="../member/Logout.do">로그아웃</a></li>
				<li><a href="../member/MyPage.do">회원정보</a></li>
			</ul> 
			<%
				}
			%>
       </div>
	</nav>
	<!-- 반응형 하기 --> 
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog modal-sm" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modaxl-title" id="myModalLabel">로그인을 하세요</h4>
      			</div>
      			<div class="modal-body">
      			<form action="../member/LoginPro.do" method="post" class="form-signin">
         			<label class="sr-only">아이디</label>
         			<input type="text" name="user_id" class="form-control" placeholder="아이디" required="required" autofocus="autofocus">
         			<label class="sr-only">비밀번호</label>
         			<input type="password" name="user_pass" class="form-control" placeholder="비밀번호" required="required">
         			<hr>
         			<button type="submit" class="button primary fit">로그인</button>
      			</form>
		      	<br>
		      	<a href="../member/JoinForm.do" type="button" target=_blank class="btn btn-success">회원가입</a>
		      	<a href="../member/FindPasswordForm.do" type="button" target=_blank class="btn btn-info">비밀번호 찾기</a>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      			</div>
   			</div>
  		</div>
	</div>
		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="FindPasswordForm.jsp" class="logo"><strong>비밀번호 찾기</strong> Tripa.com</a>
									
								</header>

							<!-- Banner -->
								

							<!-- Section -->
								<section>
									<header class="major">
										<h2>비밀번호 찾기</h2>
									</header>
									<div class="content">
										<form method="post" action="FindPasswordPro.do" >
											<table class="table table-bordered table-hover" style="text-align: center; background-color:white;  border: 1px solid #dddddd">
												<thead>
													<tr>
														<th colspan="3"><h4>비밀번호찾기 폼을 입력하십시오</h4></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td style="width: 110px;"><h5>아이디</h5></td>
														<td><input class="form-control" type="text" id="user_id" name="user_id" maxlength="20" placeholder="아이디를 입력하세요."></td>
													</tr>
													<tr>
														<td style="width: 110px;"><h5>이메일</h5></td>
														<td colspan="2"><input class="form-control" id="user_email" type="email" name="user_email" maxlength="20" placeholder="이메일을 입력하세요."></td>
													</tr>
													<tr>
														<td style="width: 110px;"><h5>비밀번호 찾기 질문</h5></td>
														<td colspan="2">
															<div class="form-group" style="text-align: center; margin: 0 auto;">
																<div class="col-sm-10">
																	<select name="user_question" id="user_question" class="form-control">
																		<option value="1">기억에 남는 추억의 장소는?</option>
																		<option value="2">자신의 보물 제1호는?</option>
																		<option value="3">가장 기억에 남는 선생님 성함은?</option>
																		<option value="4">타인이 모르는 자신만의 신체비밀이 있다면?</option>
																		<option value="5">추억하고 싶은 날짜가 있다면?</option>
																		<option value="6">받았던 선물 중 기억에 남는 독특한 선물은?</option>
																		<option value="7">유년시절 가장 생각나는 친구 이름은?</option>
																		<option value="8">인상 깊게 읽은 책 이름은?</option>
																		<option value="9">자신이 두번째로 존경하는 인물은?</option>
																		<option value="10">친구들에게 공개하지 않은 어릴 적 별명이 있다면?</option>
																		<option value="11">초등학교 때 기억에 남는 짝궁 이름은?</option>
																		<option value="12">다시 태어나면 되고 싶은 것은?</option>
																		<option value="13">내가 좋아하는 캐릭터는?</option>
																	</select>
																</div>
															</div>
														</td>
													</tr>
													<tr>
														<td style="width: 110px;"><h5>비밀번호 찾기 답변</h5></td>
														<td colspan="2"><input class="form-control" id="user_answer" type="text" name="user_answer" maxlength="20" placeholder="답변을 입력하세요."></td>
													</tr>
													<tr>	
														<td style="text-align: left;" colspan="3"><input class="btn btn-primary pull-right" style="color: #000000;" type="submit" value="비밀번호 찾기"></td>
													</tr>
												</tbody>
											</table>
										</form>
									</div>
								</section>

						

						</div>
					</div>
			</div>

		<!-- Scripts -->
			<script src="../adminCSS/assets/js/jquery.min.js"></script>
			<script src="../adminCSS/assets/js/browser.min.js"></script>
			<script src="../adminCSS/assets/js/breakpoints.min.js"></script>
			<script src="../adminCSS/assets/js/util.js"></script>
			<script src="../adminCSS/assets/js/main.js"></script>
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