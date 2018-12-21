<%@page import="java.text.SimpleDateFormat"%>
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
		<link rel="stylesheet" href="../adminCSS/assets/css/main.css?ver=2" />
	</head>
<body class="is-preload">
<%
	SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyMMdd", Locale.KOREA );
	Date currentTime = new Date ();
	String mTime = mSimpleDateFormat.format ( currentTime );
	int intTime = Integer.parseInt(mTime);
%>
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
				<li><a href="../member/MyPage.do">마이페이지</a></li>
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
         			<button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
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
									<a href="MyPage.do" class="logo"><strong>마이페이지</strong> </a>
								</header>

							<!-- Banner -->
								

							<!-- Section -->
								<section>
									<header class="major">
										<h2>스크랩목록</h2>
									</header>
									<div class="content">
											<form method="post" action="MemberReviewListPro.do" >
				<table class="table table-striped" style="text-align: center; boder: 1px solid #ddd">
					<thead>
						<tr> 
							<th style="background-color: #eee; text-align: center;">스크랩 번호</th> 
							<th style="background-color: #eee; text-align: center;">여행 리뷰  제목</th>
							<th style="background-color: #eee; text-align: center;">스크랩 조회</th>
							<th style="background-color: #eee; text-align: center;">스크랩 삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${totCnt > 0 }">
						<c:forEach var="scrap" items="${listReview4 }">
							<tr>
								<td>${scrap.scrap_no}</td>
								<td>${scrap.board_title}</td>
								<td><a href="../board/boardContent.do?num=${scrap.posting_no}&pageNum=${currentPage}">조회</a></td>
								<td><a onclick="return confirm('정말로 삭제하시겠습니까?')" href="MemberScrapListPro.do?scrap_no=${scrap.scrap_no }">삭제</a></td>
							</tr>
							<c:set var="startNum" value="${startNum - 1 }" />
						</c:forEach>
					</c:if>
					<thead>
						<tr> 
							<th style="background-color: #eee; text-align: center;">스크랩 번호</th> 
							<th style="background-color: #eee; text-align: center;">숙소 제목</th>
							<th style="background-color: #eee; text-align: center;">스크랩 조회</th>
							<th style="background-color: #eee; text-align: center;">스크랩 삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${totCnt2 > 0 }">
						<c:forEach var="scrap" items="${listTravel4 }">
							<tr>
								<td>${scrap.scrap_no}</td>
								<td>${scrap.accom_name}</td>
								<td><a href="../accom/content.do?accom_no=${scrap.accom_no}&pageNum=${currentPage}">조회</a></td>
								<td><a onclick="return confirm('정말로 삭제하시겠습니까?')" href="MemberScrapListPro.do?scrap_no=${scrap.scrap_no }">삭제</a></td>
							</tr>
							<c:set var="startNum" value="${startNum - 1 }" />
						</c:forEach>
					</c:if>
					
					<c:if test="${totCnt == 0 }">
						<tr>
							<td colspan=7>예약 데이터가 존재하지 않습니다.</td>
						</tr>
					</c:if>
					</tbody>
						</table>
							</form>
							<div style="text-align: center;">
							<c:if test="${startPage > blockSize }">
								<a href='MemberScrapListForm.do?pageNum=${startPage-blockSize}'>[이전]</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a href='MemberScrapListForm.do?pageNum=${i}'>[${i}]</a>
							</c:forEach>
							
							<c:if test="${endPage < pageCnt }">
								<a href='MemberScrapListFor.do?pageNum=${startPage+blockSize}'>[다음]</a>
							</c:if>
							</div>
						</div>
					</section>
							</div>
					</div>

				<!-- Sidebar -->
					<div id="sidebar">
						<div class="inner">

							<!-- Menu -->
								<nav id="menu">
									<header class="major">
										<h2>Menu</h2>
									</header>
									<ul>
										<li><a href="MemberReservationListForm.do">예약목록</a></li>
										<li><a href="MemberReviewListForm.do">리뷰 & 후기목록</a></li>
										<li><a href="MemberScrapListForm.do">스크랩목록</a></li>
									</ul>
								</nav>
							<!-- Footer -->
								<footer id="footer">
									<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
								</footer>

						</div>
					</div>

			</div>
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
         			<button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
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