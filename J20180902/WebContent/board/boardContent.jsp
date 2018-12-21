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
<title>트리콤</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="boardStyle.css" />
	<link rel="stylesheet" href="../boardCSS/assets/css/main.css?ver=5" />
	<link rel="stylesheet" href="../boardCSS/assets/css/boxMain.css?ver=4" />
	<link rel="stylesheet" href="../css/bootstrap.min.css?ver=4">

	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/httpRequest.js"></script>
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

<style type="text/css">
table .type09{
	width: 100%;
	height: auto;
}

th {
	text-align: center;
}
table.type09 {
	
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;

}
table.type09 thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: middle;
    color: #369;
    border-bottom: 3px solid #036;
}
table.type09 tbody th {
    /*width: 80px;  */
    padding: 10px;
    font-weight: bold;
    vertical-align: middle;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
table.type09 td {
   	/*  width: 230px; */
    padding: 10px;
    vertical-align: middle;
    border-bottom: 1px solid #ccc;
}
</style>
<script text="text/javascript">	
function paragraph_toggle(obj) {
	if (obj.style.display == 'none') 
	    obj.style.display = 'block';
	else if (obj.style.display == 'block')
	    obj.style.display = 'none';
}

// 답글쓰기
function commDelete(id1, id2) {
	var form 		= document.getElementById("writeCommentForm");
	var posting_no 	= form.posting_no.value
	var re_level 	= document.getElementById(id1).value;
	var re_step		= document.getElementById(id2).value;
	var id 			= form.user_id.value;
		
	var sendData="posting_no="+posting_no+"&re_level="+re_level+"&user_id="+id+ "&re_step="+re_step;
	
	$.post('commDeleteForm.do',
		sendData, 
		function() {
		    location.reload();
	    }
	);
} 

function commUpdate(id3,id4, index) { 
    var comments2 = $('#comments2'+index).val()
	// console.log("comments2->"+comments2); 
    
	var form = document.getElementById("writeCommentForm");
    
	var re_level 	= document.getElementById(id3).value;
    var re_step 	= document.getElementById(id4).value;
    
	var posting_no 	= form.posting_no.value;
    var id 			= form.user_id.value;
    
	if(!comments2)
     {
         alert("내용을 입력하세요.");
         return false;
     }
     else
     {  
        var sendData="posting_no="+posting_no+"&comments2="+comments2+"&re_level="+re_level+"&re_step="+re_step+"&user_id="+id;
		
		$.post('commUpdateForm.do',
			sendData, 
			function(msgObj) {
					location.reload();
			}
		);
     }
}

function writeCmt()
{
    var form = document.getElementById("writeCommentForm");
         
    var posting_no = form.posting_no.value
    var id = form.user_id.value
  		//alert("writeCmt posting_no->"+posting_no);
	 var comments = form.comments.value;
		// alert("writeCmt comments->"+comments);
    if(id=="" || id==null) {
	    alert("로그인을 해주세요")
	    return false;
	}
    
	if(!comments){
        alert("내용을 입력하세요.");
        return false;
    }
	else {   
        var sendData="posting_no="+posting_no+"&comments="+comments+"&user_id="+id;
                 
 		$.post('CommentWriteAction.do',
			sendData, 
			function(msgObj) {
				location.reload();
			}
		);
	}
}
     
function commWrite2(id2, index) { 
	var comments3 = $('#commentsI2'+index).val()
    // console.log("comments3->"+comments3); 
	//alert(comments3);
	var form 		= document.getElementById("writeCommentForm");
	var re_level 	= document.getElementById(id2).value;
	var posting_no 	= form.posting_no.value;
	var id 			= form.user_id.value;
	     
	    
	    	 
	if(!comments3) {
	    alert("내용을 입력하세요.");
	    return false;
	}
	else {  
		var sendData="posting_no="+posting_no+"&comments3="+comments3+"&re_level="+re_level+"&user_id="+id;
		$.post('commentWriteAction2.do',
			sendData, 
			function(msgObj) {
				location.reload();
			}
		);
	}
}

// 스크랩하기
function addScrap() {
    var form = document.getElementById("writeCommentForm");
    var posting_no 	= form.posting_no.value
    var user_id 	= form.user_id.value;
    var scrap_cd 	= form.scrap_cd.value;
    var scrap_no 	= form.scrap_no.value;
    var like_check 	= form.like_check.value;
	
	var sendData="posting_no="+posting_no+"&scrap_cd="+scrap_cd+"&scrap_no="+scrap_no+"&like_check="+like_check +"&user_id="+user_id;
	
	$.post('scrapPro.do',
    	sendData, 
    	function() {
    		location.reload();
    	}
    );
} 

// 스크랩지우기    
function delScrap() {
    var form = document.getElementById("writeCommentForm");
    var posting_no = form.posting_no.value
    var user_id = form.user_id.value;
    var sendData="posting_no="+posting_no +"&user_id="+user_id;
	
	$.post('scrapDel.do',
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
							<header class=main>
								<h2 id="content">${board.board_title}</h1>
							</header>
							<h3>${board.user_id}</h3>
							<h4>${location}</h4>
							<h4>${tripDate}</h4>
							<p>
								${A}
							</p>
							<!-- 스크랩 -->
							<div style="float:left">
								<span>
									<c:if test="${sessionScope.user_id !=null}">
										<c:if test="${result9==0 }">
											<input style="vertical-align: top; float: right" type="button"
												value="스크랩 (${likeCntBoard })" onclick="addScrap()"class="button primary small">
											<c:if test="${sessionScope.user_id == board.user_id}">		
												<input style="vertical-align: bottom; float: right" type="button" value="수정"
													onclick="location.href='boardUpdateForm.do?posting_no=${board.posting_no}&pageNum=${pageNum }&user_id=${board.user_id}'"class="button small">
												<input style="vertical-align: bottom; float: right"
													type="button" value="삭제" onclick="location.href='boardDeletePro.do?num=${board.posting_no}&pageNum=${pageNum }&user_id=${board.user_id}'"class="button small">
											</c:if>		
										</c:if>
										<c:if test="${result9==1 }">
											<input style="vertical-align: top; float: right" type="button"
												value="스크랩 취소 (${likeCntBoard })" onclick="delScrap()"class="button small">
											<c:if test="${sessionScope.user_id == board.user_id}">		
												<input style="vertical-align: bottom; float: right" type="button" value="수정"
													onclick="location.href='boardUpdateForm.do?posting_no=${board.posting_no}&pageNum=${pageNum }&user_id=${board.user_id}'"class="button small">
												<input style="vertical-align: bottom; float: right"
													type="button" value="삭제" onclick="location.href='boardDeletePro.do?num=${board.posting_no}&pageNum=${pageNum }&user_id=${board.user_id}'"class="button small">
											</c:if>		
										</c:if>							
									</c:if>
								</span>
							</div>
						</div>
						<span class="image object">
							<img src="${thumbnail}" alt="" />
						</span>
					</section>	
					<!-- 글 내용 구간 -->
					<section>
						<p>${board.content_text}</p>
					</section>
					<!-- 글 댓글 -->
					<!-- <section> -->
							<form id="writeCommentForm" name="frm">
								<input type="hidden" name="posting_no" value="${board.posting_no}"> 
								<input type="hidden" name="scrap_cd" value="${scrap_cd}">	
								<input type="hidden" name="scrap_no" value="${scrap_no}">
								<input type="hidden" name="like_check" value="${like_check}">
								<input type="hidden" name = "user_id" value="${sessionScope.user_id}">										
											
								<table class="type09">										 
									<tr>
										<td style="width:120;vertical-align: middle;"><h4>${sessionScope.user_id}</h4></td>											
										<td>
											<textarea name="comments" rows="4" cols="100" style="resize: none;"></textarea>
										</td>								
										<!-- 댓글 등록 버튼 -->						
										<td id="btn" style="width:120; text-align: center;">
											<p>
												<input type="button" onclick="writeCmt()"class="button" value="댓글쓰기"></a>
											</p>
										</td>
									</tr>					
									<c:forEach var="cnt" begin="1" end="10" step="1">
										<span id="msg"+${cnt }></span>
									</c:forEach>											
									<c:forEach var="comList" items="${comList }" varStatus="status">
										<input type="hidden" id="${comList.re_level-1 }" name="re_level" value="${comList.re_level }">
										<input type="hidden" id="${comList.re_step-1 }" name="re_step" value="${comList.re_step }">			
										<tr>
											<td>
												<p><h4>${comList.user_id}</h4></p>
												<p style="color:rgb(93,211,207);">(${comList.regi_date})</p>
											</td>  
											<td colspan="2">
												<c:if test="${comList.re_step > 0}">
													<img src='../icon/arrow.png' alt="" width="15" >		
												</c:if>
												${comList.comments}
												<c:if test="${sessionScope.user_id!=null }">
													<!-- 답글 div -->
													<div>
														<div id="hOpen02${status.index}" style="float:right; display: block;">
															<input type="button" onclick="paragraph_toggle(document.getElementById('hOpen02${status.index}')); 
																							paragraph_toggle(document.getElementById('hClose02${status.index}')); return false;" value="답글" class="button small">	
														</div>
														<span>
															<div id="hClose02${status.index}" style="display: none;">
																<textarea name="comments3" id="commentsI2${status.index}"rows="3" cols="100" style="resize:none;"></textarea>
																<div style="float:right">
																	<input type="button" value="완료" onclick="commWrite2(${comList.re_level-1 }, ${status.index} )"class="button small">
																	<a onclick="paragraph_toggle(document.getElementById('hOpen02${status.index}')); 
																				paragraph_toggle(document.getElementById('hClose02${status.index}')); return false;" href="#">
																		<input type="button" value="취소"class="button small">
																	</a>
																</div>
															</div>
														</span>
													</div>
													<c:if test="${comList.user_id == sessionScope.user_id}">
														<input type="button" value="삭제" style="float:right" onclick="commDelete(${comList.re_level-1 },${comList.re_step-1} )" class="button small">
														
														<!-- 수정 div -->
														<span>
															<div>
																<div id="hOpen01${status.index}" style="float:right; display: block;">
																	<a onclick="paragraph_toggle(document.getElementById('hOpen01${status.index}')); 
																				paragraph_toggle(document.getElementById('hClose01${status.index}')); return false;" href="#">
																		<input type="button" class="button small" value="수정" class="icon fa-twitter">
																	</a>
																</div>
																<div  id="hClose01${status.index}" style="display: none;">
																	<hr class="major">
																	<textarea name="comments2" id="comments2${status.index}"rows="4" cols="100" style="resize: none;"></textarea>
																	<div style="float: right;">
																		<ul class="actions small">
																			<li><input type="button" value="수정"	onclick="commUpdate(${comList.re_level-1 }, ${comList.re_step-1 }, ${status.index})"></li>
																			<li>
																				<a onclick="paragraph_toggle(document.getElementById('hOpen01${status.index}')); 
																							paragraph_toggle(document.getElementById('hClose01${status.index}')); return false;" href="#">
																					<input type="button" value="취소"class="button samll">
																				</a>
																			</li>
																		</ul>
																	</div>		
																</div>
															</div>
														</span>	
													</c:if>
													
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
							</form>
					<!-- </section> -->
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