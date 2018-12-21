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
		<link rel="stylesheet" href="../css/bootstrap.min.css?ver=6">
		<link rel="stylesheet" href="../adminCSS/assets/css/main.css?ver=4" />
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
									<a href="adminNoticeWriteForm.jsp" class="logo"><strong>공지관리</strong> by ADMIN</a>
									
								</header>

							<!-- Banner -->
								

							<!-- Section -->
								<section>
									<header class="major">
										<h2>공지 수정</h2>
									</header>
									<div class="content">
										<table>
										<caption><h4>기존의 공지사항을 수정합니다</h4></caption>
											<tr style="background-color:#d5b6d6">
												<th>글 번호</th><th>글 제목</th><th>등록일</th><th>작성 ID</th><th>조회수</th>
											</tr>
											<c:if test="${totCnt > 0 }">
												<c:forEach var="board" items="${list }">
													<tr >
														<td onclick="location.href='adminNoticeUpdateForm.do?posting_no=${board.posting_no}&pageNum=${pageNum}'">${board.posting_no}</td>
														<td class="left" width=200 onclick="location.href='adminNoticeUpdateForm.do?posting_no=${board.posting_no}&pageNum=${pageNum}'">${board.board_title}</td>
														<td>${board.regi_date}</td>
														<td>${board.user_id}</td>
														<td>${board.look}</td>
													</tr>
													<c:set var="startNum" value="${startNum - 1 }" />
												</c:forEach>
												
											</c:if>
											<c:if test="${totCnt == 0 }">
												<tr>
													<td colspan=7>공지사항 데이터가 존재하지 않습니다.</td>
												</tr>
											</c:if>
										</table>
										
										<div style="text-align: center;">
											<c:if test="${startPage > blockSize }">
												<a href='adminNoticeList.do?pageNum=${startPage-blockSize}'>[이전]</a>
											</c:if>
											
											<c:forEach var="i" begin="${startPage}" end="${endPage}">
												<a href='adminNoticeList.do?pageNum=${i}'>[${i}]</a>
											</c:forEach>
											
											<c:if test="${endPage < pageCnt }">
												<a href='adminNoticeList.do?pageNum=${startPage+blockSize}'>[다음]</a>
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
										<li>
											<span class="opener">공지사항</span>
											<ul>
												<li><a href="adminNoticeWriteForm.jsp">공지사항 등록</a></li>
												<li><a href="adminNoticeList.do">공지사항 수정</a></li>
											</ul>
										</li>
										<li>
											<span class="opener">숙소관리</span>
											<ul>
												<li><a href="adminAccomWriteForm.jsp">숙소등록</a></li>
												<li><a href="updateList.do">숙소수정</a></li>
												
											</ul>
										</li>
										
										<li><a href="adminReviewList.do">리뷰관리</a></li>
										<li><a href="adminPage.do">관리정보</a></li>
									</ul>
								</nav>
							<!-- Footer -->
								<footer id="footer">
									<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
								</footer>

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