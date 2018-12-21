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
		<link rel="stylesheet" href="../searchCSS/assets/css/main.css?ver=5" />
		<link rel="stylesheet" href="../css/btn.css">
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
				<h1 id="logo"><a href="../search/searchMain.do">Tripa.com</a></h1>
					<nav id="nav">
						<ul>
							<li class="current"><a href="../board/boardList.do">여행리뷰</a></li>
							<li class="current"><a href="../admin/NoticeList.do">공지사항</a></li>
							
							<%
								if(user_id == null) {
							%>
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
							<li class="current"><a href="#" id="writeReiveiw">Write Review</a></li>
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
						<h2>Write<br />Review</h2>
					</header>
					<p>
                       <form class="frm" action="../board/boardWriteForm.do" method="post" class="form-signin">
						  <input type="hidden" name="user_id" value="${user_id}" /> 
						  <input type="text" name="board_title" id="board_title"  placeholder="예)3박4일 제주도여행" required="required" autofocus="autofocus">
						   <br />
						   <p> 출발일 </p>
						   <input type="date" name="startDate" id="startDate" required="required"><br /><br />
						   <p> 도착일 </p>
						   <input type="date" name="arriveDate" id="arriveDate" required="required"><br /><br />
						   <select id="select1" name="inout" style="color:#272b2a"onchange="itemChange()">
						   	  <option value="선택">선택</option>
							  <option value="국내">국내</option>
							  <option value="국외">국외</option>
						  </select>
						  <select id="select2" style="color: #272b2a" name="area">
							  <option>선택해주세요</option>
						  </select>	
						  <hr>
						   <button type="submit" class="button primary">새 리뷰 발행</button>
					  </form>
					</p>
				</div>	
			</section>
		</div>
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