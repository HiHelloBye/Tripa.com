<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<% 
	String user_id = (String) session.getAttribute("user_id");
%>
<!DOCTYPE HTML>
<!--
	Twenty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Tripa.com</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../searchCSS/assets/css/main.css?ver=1" />
		<link rel="stylesheet" href="../css/btn.css?ver=1">
		<noscript><link rel="stylesheet" href="../searchCSS/assets/css/noscript.css" /></noscript>
		<!-- <link rel="stylesheet" href="../searchCSS/modal_assets/css/main.css?ver=1" />
		<noscript><link rel="stylesheet" href="../searchCSS/modal_assets/css/noscript.css" /></noscript> -->
		<!-- <link rel="stylesheet" href="../css/bootstrap.min.css"> -->
	<script type="text/javascript">
	function display1(box){
		container1.style.display = 'block';
		container2.style.display = 'none';
		best1.style.display='block';
		best2.style.display='none';
		}
		
	function display2(box){
		container1.style.display = 'none';
		container2.style.display = 'block';
		best1.style.display='none';
		best2.style.display='block';
	}
		
	window.onload=display2

	function itemChange(){
	
		var k = ["충청도","전라도","경상도","제주도","강원도"];
		var f = ["일본","프랑스","영국","브라질","베트남","중국","미국","독일","네덜란드","캐나다"];
		
		var selectItem = $("#select1").val();
		
		var changeItem;
		
		if(selectItem == "국내"){
			changeItem = k;
		}
		else if(selectItem == "국외"){
			changeItem = f;
		}
		
		$('#select2').empty();
		
		for(var count = 0; count < changeItem.length; count++){                
			if(selectItem == "국외") {
				var option = $("<option value="+f[count]+">" + changeItem[count] + "</option>");
				$('#select2').append(option);
			}
			else {
				var option = $("<option value="+k[count]+">" + changeItem[count] + "</option>");
				$('#select2').append(option);
			}
		}
	}	
	</script>
	</head>
	<body class="index is-preload">
		<div id="page-wrapper">
			<!-- Header -->
			<header id="header" class="alt">
				<h1 id="logo"><a href="searchMain.do">Tripa.com</a></h1>
					<nav id="nav">
						<ul>
							<li class="current"><a href="../board/boardList.do">여행리뷰</a></li>
							<li class="current"><a href="../admin/NoticeList.do">공지사항</a></li>
							
							<%
								if(user_id == null) {
							%>
							<li><a href="searchMainLoginForm.jsp" class="button primary">Log In</a></li>
							<li><a href="../member/JoinForm.do" class="button primary">Sign Up</a></li>
							<%
								} else if(user_id.equals("admin")) {
							%>
							<li class="current"><a><%=user_id%>님 환영합니다</a></li>
							<li class="current"><a href="../member/Logout.do">Log out</a></li>
							<li class="current"><a href="../admin/adminMain.jsp">Admin Page</a></li>
							<%  } else { %>
							<li class="current"><a><%=user_id%>님 환영합니다</a></li>
							<li class="current"><a href="../member/Logout.do">Log out</a></li>
							<li class="current"><a href="../member/MyPage.do">My Page</a></li>
							<li class="current"><a href="../board/boardWriteModalForm.jsp">Write Review</a></li>
							<%
								}
							%>
						</ul>
					</nav>
				</header>
			<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<header>
						<h2>TRIPA</h2>
					</header>
					<p>
						<div>
							<form style="text-align:center;" action="search.do" method="post">
								<input type="hidden" name="reviewsort" value="0">
								<a class="btn-switch">		
									<input type="radio" id="yes"  value ="review" name="switch" class="btn-switch__radio btn-switch__radio_yes" onclick="display1()"/>
									<input type="radio" checked id="no" value="accom" name="switch" class="btn-switch__radio btn-switch__radio_no" onclick="display2()"/>	
									<label for="yes" class="btn-switch__label btn-switch__label_yes">
										<span class="btn-switch__txt">리뷰검색</span></label>								  
									<label for="no" class="btn-switch__label btn-switch__label_no">
										<span class="btn-switch__txt">숙소검색</span></label>	 						
								</a>
								<!-- 리뷰 검색 -->
								<div class="formdesign">
									<fieldset class="field-container" id="container1">
										<br />
										<input type="text" name="keyword" placeholder="Search..." class="field" autocomplete="off" />
										<br />
										<input type="submit" value="검색" class="button fit scrolly" >
										<div class="icons-container">
											<div class="icon-search"></div>
											<div class="icon-close">
												<div class="x-up"></div>
												<div class="x-down"></div>
											</div>
										</div>
									</fieldset>
								</div>
								<!-- 숙소 검색 -->
								<br />
								<div class="formdesign">
									<fieldset class="field-container" id="container2" >
										<input type="text" name="keywordAccom" placeholder="Search..." class="field" autocomplete="off"  />
										<div class="icons-container">
										<div class="icon-search"></div>
											<div class="icon-close">
												<div class="x-up"></div>
												<div class="x-down"></div>
											</div>
										</div>
										<br />
										<strong>체크인</strong> 
										<br />
										<input type="date" name="checkin" class="field">
										<hr />
										<strong>체크아웃</strong> 
										<br />
										<input type="date"  name="checkout" class="field">
										<br />
										<hr>
										<input type="radio" id="Single" name="room" checked>
																<label for="Single">싱글룸</label>
										<input type="radio" id="Double" name="room" >
																<label for="Double">더블룸</label>	
										<input type="radio" id="Family" name="room" >
																<label for="Family">더블룸</label>												
										<br />
										<hr>
										<input type="submit" value="검색" class="button fit scrolly">
									</fieldset>
								</div>
							</form>
						</div>
					</p>
				</div>	
			</section>
		</div>
		<!-- Best top 10 -->
		<article id="main">
			<section class="wrapper style3 container special">
				<div class="best1" id="best1">
					<c:if test="${totCnt > 0 }">
						<header class="major">
							<h2>Best <strong>Review</strong> Top 10</h2>
						</header>
						<div class="row">
							<c:forEach var="board" items="${list1}">	
									<div class="col-6 col-12-narrower">
										<section>
											<a href="../board/boardContent.do?num=${board.posting_no}&thumbnail=${board.photo }&pageNum=${currentPage}" class="image featured"><img src="${board.photo}" alt="" /></a>
											<header>
												<h3>${board.board_title}</h3>
											</header>
											<p>
												Views ${board.look}
											</p>
										</section>
									</div>
							</c:forEach>
							<c:forEach var="board" items="${list2}">
								<div class="col-6 col-12-narrower">
									<section>
										<a href="../board/boardContent.do?num=${board.posting_no}&pageNum=${currentPage}" class="image featured"><img src="${board.photo}" alt="" /></a>
										<header>
											<h3>${board.board_title}</h3>
										</header>
										<p>
											Views ${board.look}
										</p>
									</section>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>
				<div class="best2" id="best2">
					<c:if test="${totCnt2 > 0 }">
						<header class="major">
							<h2>Best <strong>Accommodation</strong> Top 10</h2>
						</header>
						<div class="row">
							<c:forEach var="accom" items="${listAccom1}">	
								<div class="col-6 col-12-narrower">
									<section>
										<a href="../accom/content.do?accom_no=${accom.accom_no}&pageNum=${currentPage}" class="image featured"><img src="${accom.photo}" alt="" /></a>
										<header>
											<h3>${accom.accom_name}</h3>
										</header>
										<p>
											Average Rate ${accom.accom_rat}
										</p>
									</section>
								</div>
							</c:forEach>
							<c:forEach var="accom" items="${listAccom2}">	
								<div class="col-6 col-12-narrower">
									<section>
										<a href="../accom/content.do?accom_no=${accom.accom_no}&pageNum=${currentPage}" class="image featured"><img src="${accom.photo}" alt="" /></a>
										<header>
											<h3>${accom.accom_name}</h3>
										</header>
										<p>
											Average Rate ${accom.accom_rat}
										</p>
									</section>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</section>
		</article>
		<!-- Scripts -->
		<script src="../searchCSS/assets/js/jquery.min.js"></script>
		<script src="../searchCSS/assets/js/jquery.dropotron.min.js"></script>
		<script src="../searchCSS/assets/js/jquery.scrolly.min.js"></script>
		<script src="../searchCSS/assets/js/jquery.scrollex.min.js"></script>
		<script src="../searchCSS/assets/js/browser.min.js"></script>
		<script src="../searchCSS/assets/js/breakpoints.min.js"></script>
		<script src="../searchCSS/assets/js/util.js"></script>
		<script src="../searchCSS/assets/js/main.js"></script>
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">

			$('#login').click(function(event){
			   event.preventDefault();
			   $('#myModal').modal('show');
			});
	
			$('#writeReiveiw').click(function(event){
			   event.preventDefault();
			   $('#beforeWrite').modal('show');
			});
		</script>
	</body>
</html>