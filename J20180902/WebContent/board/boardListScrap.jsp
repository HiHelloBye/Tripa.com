<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*" %>
<% 
	String user_id = (String) session.getAttribute("user_id");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Tripa.com</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../boardCSS/assets/css/main.css?ver=2" />
	<link rel="stylesheet" href="../boardCSS/assets/css/boxMain.css?ver=2" />
	<script type="text/javascript">
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
				<li class="active"><a href="#contact" id="writeReiveiw">리뷰쓰기</a></li>
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
	<!-- 글쓰기 팝업 -->
	<div class="modal fade" id="beforeWrite" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
		  <div class="modal-content">
				<div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <h4 class="modaxl-title" id="myModalLabel">새 리뷰만들기</h4>
				</div>
				<div class="modal-body">
				<form action="../board/boardWriteForm.do" method="post" class="form-signin">
				  <input type="hidden" name="user_id" value="${user_id}" /> 
				  <input type="text" name="board_title" id="board_title" class="form-control" placeholder="예)3박4일 제주도여행" required="required" autofocus="autofocus">
				   <label class="sr-only">출발일</label>
				   <input type="date" name="startDate" id="startDate" class="form-control" required="required">
				   <label class="sr-only">도착일</label>
				   <input type="date" name="arriveDate" id="arriveDate" class="form-control" required="required">
				   <select id="select1" name="inout" onchange="itemChange()">
					  <option value="선택">선택</option>
					  <option value="국내">국내</option>
					  <option value="국외">국외</option>
				  </select>
				  <select id="select2" name="area">
					  <option>선택해주세요</option>
				  </select>	
				  <hr>
				   <button type="submit" class="btn btn-lg btn-primary btn-block">새 리뷰 발행</button>
			  </form>
				<br>
				</div>
				<div class="modal-footer">
				  <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			 </div>
		</div>
	</div>
	
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<!-- Banner -->
				<section id="banner">
					<div class="content">
						<div id="boxWrapper">
							<section id="main">
								<!-- Thumbnails -->
								<section class="thumbnails">
										<c:if test="${totCnt > 0}" >
											<div>
												<c:forEach var="board" items="${list1}">
														<a href='boardContent.do?num=${board.posting_no}&pageNum=${currentPage}&thumbnail=${board.photo}'>
														<img src="${board.photo}" alt="" />
														<h3>${board.board_title}</h3>
														<h4>Writer ${board.user_id}</h4>
														<h4>Views ${board.look}  Scraps ${board.scrap_cnt}</h4> 
														<h6>${board.hashTag}</h6>
													</a>
												</c:forEach>
											</div>
											<div>
												<c:forEach var="board" items="${list2}">
														<a href='boardContent.do?num=${board.posting_no}&pageNum=${currentPage}&thumbnail=${board.photo}'>
														<img src="${board.photo}" alt="" />
														<h3>${board.board_title}</h3>
														<h4>Writer ${board.user_id}</h4>
														<h4>Views ${board.look}  Scraps ${board.scrap_cnt}</h4> 
														<h6>${board.hashTag}</h6>
													</a>
												</c:forEach>
											</div>
											<div>
												<c:forEach var="board" items="${list3}">
													<a href='boardContent.do?num=${board.posting_no}&pageNum=${currentPage}&thumbnail=${board.photo}'> 
														<img src="${board.photo}" alt="" />
														<h3>${board.board_title}</h3>
														<h4>Writer ${board.user_id}</h4>
														<h4>Views ${board.look}  Scraps ${board.scrap_cnt}</h4> 
														<h6>${board.hashTag}</h6>
													</a>
												</c:forEach>
											</div>
										</c:if>

									</section>
							</section>
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
								<li><a href="../board/boardList.do">     전체     </a></li>
                    			<li><a href="../board/boardListScrap.do">스크랩순 </a></li>                        
                    			<li><a href="../board/boardListCount.do">조회순   </a></li>
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
		<script src="../boardCSS/assets/js/browser.min.js"></script>
		<script src="../js/jquery.min.js"></script>
		<script src="../boardCSS/assets/js/breakpoints.min.js"></script>
		<script src="../boardCSS/assets/js/util.js"></script>
		<script src="../boardCSS/assets/js/main.js"></script>
		<script src="../boardCSS/assets/js/jquery.poptrox.min.js"></script>
		<script src="../boardCSS/assets/js/skel.min.js"></script>
		
		<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
		<script src="../js/bootstrap.js"></script>
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