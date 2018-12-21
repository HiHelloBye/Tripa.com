<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<% 
	String user_id = (String) session.getAttribute("user_id");
%>
<!DOCTYPE html>
<html>

<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
<style type="text/css">table { width: 80%; }</style> -->
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="icon" href="../../favicon.ico">

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="offcanvas.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 100%;
      height: 800px;
      margin: auto;
  }
  .carousel-control.left{background-image:none;}
  .carousel-control.right{background-image:none;}
  </style>
  <script type="text/javascript">
function addScrap() {	
	var form = document.getElementById("writeCommentForm");
	var accom_no = form.accom_no.value;
    var user_id = form.user_id.value;
	var scrap_cd = form.scrap_cd.value;
	var scrap_no = form.scrap_no.value;
	var like_check = form.like_check.value;	
	var sendData="accom_no="+accom_no+"&scrap_cd="+scrap_cd+"&scrap_no="+scrap_no+"&like_check="+like_check +"&user_id="+user_id;
		$.post('scrapProA.do',
			sendData, 
			function() {
		        location.reload();
	      }
		);
} 

function delScrap() {
	var form = document.getElementById("writeCommentForm");
	var accom_no = form.accom_no.value
    var user_id = form.user_id.value;
	var sendData="accom_no="+accom_no +"&user_id="+user_id;
		$.post('scrapDelA.do',
			sendData, 
			function() {
		        location.reload();
	      }
		);
} 
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
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="../search/searchMain.do">트리콤</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="../board/boardHome.jsp">여행 후기</a></li>
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
    
    <div class="jumbotron" style="background-color: rgb(220, 242, 242)">
      <div class="container">
        <h1>${board1.accom_name}
				<div id="map" style="width:500px;height:500px;float:right;">
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa2f28eb8108bb53f0ec8804381eb18f"></script>
				<script>
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    				mapOption = { 
        			center: new daum.maps.LatLng(${board1.accom_map }), // 지도의 중심좌표
        			level: 5 // 지도의 확대 레벨
    				};

					var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

					// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
					var mapTypeControl = new daum.maps.MapTypeControl();

					// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
					// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
					map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

					// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
					var zoomControl = new daum.maps.ZoomControl();
					map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
						
					// 마커가 표시될 위치입니다 
					var markerPosition  = new daum.maps.LatLng(${board1.accom_map }); 

					// 마커를 생성합니다
					var marker = new daum.maps.Marker({
    							position: markerPosition
					});

					// 마커가 지도 위에 표시되도록 설정합니다
					marker.setMap(map);
				</script>
            </div>
        </h1>
        <p>${board1.accom_addr }</p>
        <p>${board1.accom_func }</p>
        <p>${board1.accom_cont }</p>
        <br><br><br><br>
        <h2>${board1.accom_rat }/5.0</h2>
        <p><a class="btn btn-default" href='../accomCom/accom_comList.do?accom_no=${board1.accom_no}'>더보기</a>
              	<c:if test="${result9==0 }">
		<input style="vertical-align: top;" type="button"
			value="스크랩 (${likeCntBoard })" onclick="addScrap()"class="btn btn-default">
    	</c:if>
    	<c:if test="${result9==1 }">
    	<input style="vertical-align: top; " type="button"
			value="스크랩 취소 (${likeCntBoard })" onclick="delScrap()"class="btn btn-default">
    	</c:if> 
        </p> 

      </div>
    </div>
    
    <hr>
    
    <div class="container">
    <div class="row">
			<div id="myCarousel" class="carousel" data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-tp="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-tp="1"></li>
					<li data-target="#myCarousel" data-slide-tp="2"></li>
					<li data-target="#myCarousel" data-slide-tp="3"></li>
				</ol>
				<div class="carousel-inner">
					<div class="item active">
						<img src='${board1.accom_photo }' style="margin-left: auto; margin-right: auto; display: block;">
						<div class="carousel-caption">
               				<h1>외관 사진</h1>
            			</div>
					</div>
				
					<div class="item">
						<img src='${board2.photo }' style="margin-left: auto; margin-right: auto; display: block;">
						<div class="carousel-caption">
               				<h1>${board2.room_no }</h1>
            			</div>
					</div>
					
					<div class="item">
						<img src='${board3.photo }' style="margin-left: auto; margin-right: auto; display: block;">
						<div class="carousel-caption">
               				<h1>${board3.room_no }</h1>
            			</div>
					</div>
					
					<div class="item">
						<img src='${board4.photo }' style="margin-left: auto; margin-right: auto; display: block;">
						<div class="carousel-caption">
               				<h1>${board4.room_no }</h1>
               			</div>
					</div>
				</div>
				
				<a class="left carousel-control" href="#myCarousel" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left"></span>
				</a>
				<a class="right carousel-control" href="#myCarousel" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>
		</div>
		</div>
		
		<hr>
	
    <div class="container marketing">
      <div style="text-align: center;" class="row">
        <div class="col-lg-4">
          <img class="img-circle" src='${board2.photo }' width="140" height="140">
          <h1>${board2.room_no }</h1>
          <p>${board2.num_people }인실</p>
          <p>${board2.price }원</p>
          <c:if  test="${sessionScope.user_id !=null}"> 
          <p><a class="btn btn-default" href="../reservation/resvWriteForm.do?accom_no=${board1.accom_no }&room_no=${board2.room_no}" role="button">예약하기 &raquo;</a></p>
          </c:if>
        </div>
        <div class="col-lg-4">
          <img class="img-circle" src='${board3.photo }' alt="Generic placeholder image" width="140" height="140">
          <h1>${board3.room_no }</h1>
          <p>${board3.num_people }인실</p>
          <p>${board3.price }원</p>
          <c:if  test="${sessionScope.user_id !=null}"> 
          <p><a class="btn btn-default" href="../reservation/resvWriteForm.do?accom_no=${board1.accom_no }&room_no=${board3.room_no}" role="button">예약하기 &raquo;</a></p>
          </c:if>
        </div>
        <div class="col-lg-4">
          <img class="img-circle" src='${board4.photo }' alt="Generic placeholder image" width="140" height="140">
          <h1>${board4.room_no }</h1>
          <p>${board4.num_people }인실</p>
          <p>${board4.price }원</p>
          <c:if  test="${sessionScope.user_id !=null}"> 
          <p><a class="btn btn-default" href="../reservation/resvWriteForm.do?accom_no=${board1.accom_no }&room_no=${board4.room_no}" role="button">예약하기 &raquo;</a></p>
          </c:if>
        </div>
      </div>
    </div>
    
    <%-- <div class="container">
		<div class="row">
    		<div class="col-sm-8 main">
				<div class="col-sm-7">
          			<h1>${board2.room_no }</h1>
          			<h3><img src='${board2.photo }' width="150" height="150">가격: ${board2.price }</h3>
          			<p><a class="btn btn-default" href='content2.do?accom_no=${board1.accom_no}&pageNum=${currentPage}'>
							${board2.accom_name}크게보기 &raquo;</a></p>
        		</div>
        		
        		<div class="col-sm-3 col-sm-offset-2 outpic">
        			<h2>외관사진</h2>
        			<img src='${board1.accom_photo }' width="250" height="300">
        		</div>
        		
        		<div class="col-sm-7">
          			<h1>${board3.room_no }</h1>
          			<h3><img src='${board3.photo }' width="150" height="150">가격: ${board3.price }</h3>
          			<!-- <p><a class="btn btn-default" href="content2.jsp" role="button">크게보기 &raquo;</a></p> -->
      	 			<p><a class="btn btn-default" href='content2.do?accom_no=${board1.accom_no}&pageNum=${currentPage}'>
							${board2.accom_name}크게보기 &raquo;</a></p>
      	 		</div>
      	 		
        		<div class="col-sm-7">
          			<h1>${board4.room_no }</h1>
          			<h3><img src='${board4.photo }' width="150" height="150">가격: ${board4.price }</h3>
          			<!-- <p><a class="btn btn-default" href="content2.jsp" role="button">크게보기 &raquo;</a></p> -->
        			<p><a class="btn btn-default" href='content2.do?accom_no=${board1.accom_no}&pageNum=${currentPage}'>
							${board2.accom_name}크게보기 &raquo;</a></p>
        		</div>
        	</div> --%><!-- /.main -->

        	<%-- <div class="col-sm-3 col-sm-offset-1 sidebar">
          		<div class="sidebar-module sidebar-module-inset">
            		<h2>주변 지도</h2>
					<div id="map" style="width:100%;height:350px;"></div>

					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa2f28eb8108bb53f0ec8804381eb18f"></script>
					<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    					mapOption = { 
        				center: new daum.maps.LatLng(${board1.accom_map }), // 지도의 중심좌표
        				level: 5 // 지도의 확대 레벨
    					};

						var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

						// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
						var mapTypeControl = new daum.maps.MapTypeControl();

						// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
						// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
						map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

						// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
						var zoomControl = new daum.maps.ZoomControl();
						map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
						
						// 마커가 표시될 위치입니다 
						var markerPosition  = new daum.maps.LatLng(${board1.accom_map }); 

						// 마커를 생성합니다
						var marker = new daum.maps.Marker({
    							position: markerPosition
						});

						// 마커가 지도 위에 표시되도록 설정합니다
						marker.setMap(map);
					</script>
            	</div>
            	
          		<div class="sidebar-module">
            		<h4>별점</h4>
            		<h2>${board1.accom_rat }/5.0</h2>
            		<p><a class="btn btn-default" href='../accomCom/accom_comList.do?accom_no=${board1.accom_no}'>더보기</a></p>
          		</div> --%>
        	</div><!-- /.sidebar -->
      	</div><!-- /.row -->
    </div><!--/.container-->
    
<%-- <table border="1">
	<caption><h2>게시판 상세내역</h2></caption>
	<tr><td width="50">번호</td><td>${board1.accom_no}</td></tr>
	<tr><td>이름</td><td>${board1.accom_name}</td></tr>
	<tr><td>주소</td><td>${board1.accom_addr}</td></tr>
	<tr><td>기능</td><td>${board1.accom_func}</td></tr>
	<tr><td>지도</td><td>${board1.accom_map}</td></tr>
	<tr><td>국내외</td><td>${board1.inout}</td></tr>
	<tr><td>별점</td><td>${board1.accom_rat}</td></tr>
	<tr><td>내용</td><td><pre>${board1.accom_cont}</pre></td></tr>
	<tr><td>지역</td><td>${board1.area }</td></tr>
	<tr><td>사진</td><td>${board1.accom_photo }</td></tr>
	<tr><td>1방이름</td><td>${board2.room_no }</td></tr>
	<tr><td>가격</td><td>${board2.price }</td></tr>
	<tr><td>사진</td><td>${board2.photo }</td></tr>
	<tr><td>2방이름</td><td>${board3.room_no }</td></tr>
	<tr><td>가격</td><td>${board3.price }</td></tr>
	<tr><td>사진</td><td>${board3.photo }</td></tr>
	<tr><td>3방이름</td><td>${board4.room_no }</td></tr>
	<tr><td>가격</td><td>${board4.price }</td></tr>
	<tr><td>사진</td><td>${board4.photo }</td></tr>
</table> --%> <%--데이터 읽어오기 테스트용 --%>


<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

    <script src="offcanvas.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script type="text/javascript">

		$('#login').click(function(event){
		   event.preventDefault();
		   $('#myModal').modal('show');
		});
	</script>
	      	<form id="writeCommentForm" name="frm">  
      	<input type="hidden" name="accom_no" value="${board1.accom_no}">
		<input type="hidden" name="scrap_cd" value="${scrap_cd}">	
		<input type="hidden" name="scrap_no" value="${scrap_no}">
		<input type="hidden" name="like_check" value="${like_check}">
		<input type="hidden" name = "user_id" value="${sessionScope.user_id}">	
		</form>
  </body>
</html>
