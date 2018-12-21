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
		<link rel="stylesheet" href="../searchCSS/assets/css/main.css?ver=3" />
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
				<h1 id="logo"><a href="searchMain.do">Tripa.com</a></h1>
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
						<h2>LOG IN</h2>
					</header>
					<p>
                        <form action="../member/LoginPro.do" method="POST">
                            <label>ID</label>
                            <input type="text" name="user_id" placeholder="아이디" required="required" autofocus="autofocus">
                            <label>PASSWORD</label>
                            <input type="password" name="user_pass" placeholder="비밀번호" required="required">
                            <br />
                            <button type="submit" class="button primary">로그인</button>
                            <br />
                            <hr>
                            <a href="../member/JoinForm.do" type="button" target=_blank class="button">회원가입</a>
                            <br />
                            <hr>
                            <a href="../member/FindPasswordForm.do" type="button" target=_blank class="button">비밀번호 찾기</a>
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