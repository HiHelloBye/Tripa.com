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
		<style>
		.modal.fade .modal-dialog {
	-webkit-transition: -webkit-transform .3s ease-out;
	-o-transition: -o-transform .3s ease-out;
	transition: transform .3s ease-out;
	-webkit-transform: translate(0, -25%);
	-ms-transform: translate(0, -25%);
	-o-transform: translate(0, -25%);
	transform: translate(0, -25%)
}
.modal.in .modal-dialog {
	-webkit-transform: translate(0, 0);
	-ms-transform: translate(0, 0);
	-o-transform: translate(0, 0);
	transform: translate(0, 0)
}
.modal-dialog {
	position: relative;
	width: auto;
	margin: 10px
}
@media ( min-width :768px) {
	.modal-dialog {
		width: 600px;
		margin: 30px auto
	}
	.modal-content {
		-webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
		box-shadow: 0 5px 15px rgba(0, 0, 0, .5)
	}
	.modal-sm {
		width: 600px
	}
}
.modal-content {
	position: relative;
	background-color: #fff;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	border: 1px solid #999;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 6px;
	outline: 0;
	-webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
	box-shadow: 0 3px 9px rgba(0, 0, 0, .5)
}
.modal-header {
	padding: 15px;
	border-bottom: 1px solid #e5e5e5
}

.modal-header .close {
	margin-top: -2px;  
}
.btn-group-vertical>.btn-group:after, .btn-group-vertical>.btn-group:before,
	.btn-toolbar:after, .btn-toolbar:before, .clearfix:after, .clearfix:before,
	.container-fluid:after, .container-fluid:before, .container:after,
	.container:before, .dl-horizontal dd:after, .dl-horizontal dd:before,
	.form-horizontal .form-group:after, .form-horizontal .form-group:before,
	.modal-footer:after, .modal-footer:before, .modal-header:after,
	.modal-header:before, .nav:after, .nav:before, .navbar-collapse:after,
	.navbar-collapse:before, .navbar-header:after, .navbar-header:before,
	.navbar:after, .navbar:before, .pager:after, .pager:before, .panel-body:after,
	.panel-body:before, .row:after, .row:before {
	display: table;
	content: " "
}

.btn-group-vertical>.btn-group:after, .btn-toolbar:after, .clearfix:after,
	.container-fluid:after, .container:after, .dl-horizontal dd:after,
	.form-horizontal .form-group:after, .modal-footer:after, .modal-header:after,
	.nav:after, .navbar-collapse:after, .navbar-header:after, .navbar:after,
	.pager:after, .panel-body:after, .row:after {
	clear: both
}

.modal-body {
	position: relative;
	padding: 15px
}

.sr-only {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0
}
.sr-only-focusable:active, .sr-only-focusable:focus {
	position: static;
	width: auto;
	height: auto;
	margin: 0;
	overflow: visible;
	clip: auto
}
.has-feedback label.sr-only ~.form-control-feedback {
	top: 0
}
.form-control {
	display: block;
	width: 100%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}
.form-control2 {
	display: block;
	width: 25%;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.form-control:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

.form-control::-moz-placeholder {
	color: #999;
	opacity: 1
}

.form-control:-ms-input-placeholder {
	color: #999
}

.form-control::-webkit-input-placeholder {
	color: #999
}

.form-control::-ms-expand {
	background-color: transparent;
	border: 0
}

.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control
	{
	background-color: #eee;
	opacity: 1
}

.form-control[disabled], fieldset[disabled] .form-control {
	cursor: not-allowed
}

</style>
<style>
.starR1{
    background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat -52px 0;
    background-size: auto 100%;
    width: 15px;
    height: 30px;
    float:left;
    text-indent: -9999px;
    cursor: pointer;
}
.starR2{
    background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
    background-size: auto 100%;
    width: 15px;
    height: 30px;
    float:left;
    text-indent: -9999px;
    cursor: pointer;
}
.starR1.on{background-position:0 0;}
.starR2.on{background-position:-15px 0;}
</style>
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
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="false">
  		<div class="modal-dialog modal-sm" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modaxl-title" id="myModalLabel">숙소 후기</h4>        			    		
      			</div>
      			<div class="modal-body">
	      			<form action="commWrite.do" method="post" class="form-signin" enctype="multipart/form-data">
	      				<input type="hidden" name="user_id" value="${user_id}" >
	      				<input type="text" name="reser_no" id="reser_no" readonly>												
						<input type="hidden" name="accom_no" id="accom_num" >    
						<input type="hidden" name="score" id="scoreResult">		         			
	         			<label class="sr-only">내용</label><h3>내용</h3>
	         			<textarea rows="10" cols="30" name="review_cont" class="form-control"  required="required" autofocus="autofocus" maxlength="100" id="review_cont"style="resize: none;"></textarea> 
	         			<label class="sr-only">사진첨부</label><h3>사진첨부</h3>
	         			<input type="file" name="uploadFile1" class="form-control">	         			
	         			<label class="sr-only">별점</label><h3>별점</h3>
	         			<div class="starRev" id="starRev">
  							<span class="starR1 on" name="starRev" value="0.5" onclick="getScore(this)">0.5</span>
  							<span class="starR2" name="starRev" value="1.0" onclick="getScore(this)">1.0</span>
							<span class="starR1" name="starRev" value="1.5" onclick="getScore(this)">1.5</span>
							<span class="starR2" name="starRev" value="2.0" onclick="getScore(this)">2.0</span>
							<span class="starR1" name="starRev" value="2.5" onclick="getScore(this)">2.5</span>
							<span class="starR2" name="starRev" value="3.0" onclick="getScore(this)">3.0</span>
							<span class="starR1" name="starRev" value="3.5" onclick="getScore(this)">3.5</span>
							<span class="starR2" name="starRev" value="4.0" onclick="getScore(this)">4.0</span>
							<span class="starR1" name="starRev" value="4.5" onclick="getScore(this)">4.5</span>
							<span class="starR2" name="starRev" value="5.0" onclick="getScore(this)">5.0</span>
						</div>
	      				
						<script src="../js/jquery-1.11.3.min.js"></script>
						<script src="../js/star.js"></script>
						<br />
	         			<button type="submit" class="btn btn-lg button primary btn-block" onclick="insertBoard()">완료</button>
	      			</form>
			      	<br />				      	
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="button primary btn-default" data-dismiss="modal">닫기</button>
      			</div>
   			</div>
  		</div>
	</div>
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
										<h2>예약목록</h2>
									</header>
									<div class="content">
											<form id="frm">
				
												<table class="table table-striped" id="table_Res" style="text-align: center; boder: 1px solid #ddd">
													<thead>
														<tr> 
															<th style="background-color: #eee; text-align: center;">번호</th>
															<th style="background-color: #eee; text-align: center;">예약번호</th> 
															<th style="background-color: #eee; text-align: center;">숙소번호</th> 
															<th style="background-color: #eee; text-align: center;">숙소명</th>
															<th style="background-color: #eee; text-align: center;">방 종류</th>
															<th style="background-color: #eee; text-align: center;">체크인</th>
															<th style="background-color: #eee; text-align: center;">체크아웃</th>
															<th style="background-color: #eee; text-align: center;">숙소 확인</th>
															<th style="background-color: #eee; text-align: center;">예약 취소</th>
															<th style="background-color: #eee; text-align: center;">후기 작성</th>
															
														</tr>
													</thead>
													<tbody>
													<c:if test="${totCnt > 0 }">
														<c:forEach  var="reservation"  items="${list }" varStatus="status">
															
															<tr>
																
															    <td>${status.count}</td>
																<td>${reservation.reser_no}</td>
																<td>${reservation.accom_no}</td>
																<td>${reservation.accom_name}</td>
																<td>${reservation.room_no}</td>
																<td>${reservation.in_date}</td>
																<td class="outdate">${reservation.out_date}</td>
																<td><a href="../accom/content.do?accom_no=${reservation.accom_no }">확인</a></td>
																<td><a onclick="return confirm('정말로 취소하시겠습니까?')" href="MemberReservationListPro.do?reser_no=${reservation.reser_no }&user_id=${sessionScope.user_id }">취소</a></td>
																<td><a href="#" class="login2" onclick="getRese_no(this,${status.count},${reservation.flag})">작성</a></td>
																
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
				<a href='MemberReservationListForm.do?pageNum=${startPage-blockSize}'>[이전]</a>
			</c:if>
			
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href='MemberReservationListForm.do?pageNum=${i}'>[${i}]</a>
			</c:forEach>
			
			<c:if test="${endPage < pageCnt }">
				<a href='MemberReservationListForm.do?pageNum=${startPage+blockSize}'>[다음]</a>
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
			
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	
	<script type="text/javascript">
	 
  	 function getRese_no(num,count,flag){
  		
  		if(flag==1){			
  			alert('이미 후기를 작성하였습니다.');
  		}else if(flag==0){
  			
  			var tmp = num.parentElement.parentElement.rowIndex;
    	 	var accom_no = document.getElementsByTagName('tr')[tmp].getElementsByTagName('td')[2].childNodes[0].nodeValue;
    	 	var reser_no = document.getElementsByTagName('tr')[tmp].getElementsByTagName('td')[1].childNodes[0].nodeValue;
    	 	var str = document.getElementsByTagName('tr')[tmp].getElementsByTagName('td')[6].childNodes[0].nodeValue;
    	 
    	 	str= Number(str);
    	 	
    	 	document.getElementById("accom_num").value = parseInt(accom_no);
    	 	document.getElementById("reser_no").value=reser_no;
         	if(str <= (<%= intTime%>)){
    	  		event.preventDefault();
    	  		$('#myModal2').modal('show');
    	 	}else{
    	    	alert('체크아웃 후 작성해주십시오.');
    	 	}
  		}
	 }
	</script>	
	<script>
	$(document).ready(function(){			
		$('#review_cont').keyup(function(){
			if($(this).val().length > $(this).attr('maxlength')){
				alert('글자수를 초과하였습니다.');
				$(this).val($(this).val().substr(0, $(this).attr('maxlength')));
			}
		});
	});	
	</script>
	<script>
	$('.starRev span').click(function(){
	  $(this).parent().children('span').removeClass('on');
	  $(this).addClass('on').prevAll('span').addClass('on');
	  return false;
	});
</script>
<script>
	function getScore(num){
		document.getElementById("scoreResult").value=event.target.innerHTML;
	}
</script>
			
	</body>
</html>