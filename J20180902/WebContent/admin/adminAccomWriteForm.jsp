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
				<li><a href="../board/boardList.do">여행리뷰</a></li>
				<li><a href="#">공지사항</a></li>
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
									<a href="adminAccomWriteForm.jsp" class="logo"><strong>숙소관리</strong> by ADMIN</a>
									
								</header>

							<!-- Banner -->
								

							<!-- Section -->
								<section>
									<header class="major">
										<h2>숙소 등록</h2>
									</header>
									<div class="content">
										<form action="adminAccomWritePro.do" enctype= multipart/form-data method="post">
												<input type="hidden" name="pageNum" value="${pageNum}">
												<table>
													<caption><h4>새로운 숙소를 등록합니다</h4></caption>
													<tr align="center"><td style="background-color:#d5b6d6">숙소 이름</td><td><input type="text" class="field" name="accom_name" required="required"
													 value="${accom.accom_name}"></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">숙소 종류</td><td><input type="text" class="field" name="accom_type" required="required" placeholder="호텔:H 펜션:P"
													 value="${accom.accom_type}"></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">지원기능</td><td><input type="text" class="field" name="accom_func" required="required"
														 value="${accom.accom_func}"></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">숙소 주소</td><td><input type="text" class="field" name="accom_addr" required="required"
														 value="${accom.accom_addr}"></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">좌표</td>
														<td>
															<div id="map" style="width:350px;height:350px;"></div>
																<p><em>지도를 클릭해주세요! 좌표를 소수점 5자리까지 입력해주세요.</em></p>
																<p><em>입력 예시) 52.12654, 52.35484</em></p>
																<p id="result"></p>
										
																<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa2f28eb8108bb53f0ec8804381eb18f"></script>
																	<script>
																		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
										    							mapOption = { 
										        						center: new daum.maps.LatLng(36.91511, 127.57276), // 지도의 중심좌표
										        						level: 13 // 지도의 확대 레벨
										    							};
										
																		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
										
																		//일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
																		var mapTypeControl = new daum.maps.MapTypeControl();
										
																		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
																		// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
																		map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
										
																		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
																		var zoomControl = new daum.maps.ZoomControl();
																		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
										
																		// 지도에 클릭 이벤트를 등록합니다
																		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
																		daum.maps.event.addListener(map, 'click', function(mouseEvent) {        
										    
										    								// 클릭한 위도, 경도 정보를 가져옵니다 
										    								var latlng = mouseEvent.latLng;
										    
										    								var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
										    								message += '경도는 ' + latlng.getLng() + ' 입니다';
										    
										    								var resultDiv = document.getElementById('result'); 
										    								resultDiv.innerHTML = message;
																		});
																	</script>
															<input type="text" class="field" name="accom_map" required="required"
														 	value="${accom.accom_map}"></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">내용</td><td><pre><textarea rows="10" cols="40" name="accom_cont"
														required="required" class="field">${accom.accom_cont}</textarea></pre></td></tr>
													
													<tr align="center"><td style="background-color:#d5b6d6">Single 룸 가격</td><td><input type="text" class="field" name="price"/></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">Single 룸 최대인원</td><td><input type="text" class="field" name="num_people"/></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">Double 룸 가격</td><td><input type="text" class="field" name="price2"/></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">Double 룸 최대인원</td><td><input type="text" class="field" name="num_people2"/></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">Family 룸 가격</td><td><input type="text" class="field" name="price3"/></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">Family 룸 최대인원</td><td><input type="text" class="field" name="num_people3"/></td></tr>
													
													<tr align="center"><td style="background-color:#d5b6d6">사진첨부1</td><td><input type="file" name="accom_photo" accep="image/jpeg,image/png" /></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">사진첨부2</td><td><input type="file" name="accom_photo2" accep="image/jpeg,image/png" /></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">사진첨부3</td><td><input type="file" name="accom_photo3" accep="image/jpeg,image/png" /></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">사진첨부4</td><td><input type="file" name="accom_photo4" accep="image/jpeg,image/png" /></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">별점</td><td>등록 시 작성하실 수 없습니다.</td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">국내외</td><td><input type="text" class="field" name="inout" required="required" placeholder="국내:K, 국외:F"
														 value="${accom.inout}"></td></tr>
													<tr align="center"><td style="background-color:#d5b6d6">지역</td><td><input type="text" class="field" name="area" required="required" 
														 value="${accom.area}"></td></tr>
													<tr align="right">
														<td colspan="2">
														<div>
															<input type="reset" value="등록 취소" class="field2" style="background-color:white">
															<input type="submit" value="등록 완료" class="field2"style="background-color:white">
														</div>
														</td>
													</tr>
												</table>
										</form>
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
											<span class="opener">공지관리</span>
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