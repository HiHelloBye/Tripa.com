<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.util.*" %>
<% 
	String user_id = (String) session.getAttribute("user_id");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tripa.com</title>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/bootstrap.min.css?ver=4">
	<link rel="stylesheet" href="../boardCSS/assets/css/main.css?ver=2" />
	<link rel="stylesheet" href="../boardCSS/assets/css/boxMain.css?ver=2" />
	<!-- 부가적인 테마 -->

<script type="text/javascript" src="../js/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script> 
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/httpRequest.js"></script>
<script type="text/javascript" src="../smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	//해시태그 개수 검사(고쳐야함)
	function hashtagCheck(frm) {
		var f = frm.hashtag.value;
		var pattern = /\s/g;
	
		if ( f.match(pattern)) {
			alert("해시태그 개수가 10개 이상입니다");
			return;
		}
	}

	$(function() {
		//전역변수선언
		var editor_object = [];
		// Editor Setting
		nhn.husky.EZCreator.createInIFrame({
		oAppRef : editor_object, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "../smarteditor/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 필수값 Check
	function validation(){
		var contents = $.trim(editor_object[0].getContents());
		if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			editor_object.getById['smarteditor'].exec('FOCUS');
			return false;
		}

		return true;
	}
	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		//if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		//}
	})
});
</script>
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
<style>
	.countsort{
			position : relative;
			width : 100%;
			height : 0;
			padding-bottom : 56.25%;
		}
</style>
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
	<div class="modal fade" id="beforeWrite" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="false">
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
	<form action="boardWritePro.do" id="frm" method="post">
		<!-- 팝업에서 받아온 값을 변경없이 보냄 -->
		<input type="hidden" name="user_id" value="${user_id}">
		<input type="hidden" name="inout" 	value="${inout}">
		<input type="hidden" name="area" 	value="${area}">
		<input type="hidden" name="posting_no" value="${posting_no}"> 
		<input type="hidden" name="start_date" value="${start_date}">
		<input type="hidden" name="end_date" value="${end_date}">
		
		<div id="wrapper">
			<div id="main">
				<div class ="inner">
					<section id="banner">
						<div class="content">
							<h1>
								<input type="text"	name="board_title" value="${board_title}" placeholder="제목" rows="1" style="resize: none;" required ></textarea>
							</h1>
						</div>
					</section>
					<!-- Content_text -->
					<section>
						<div style="margin: auto" class="col-6 col-12-small">
							<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;"></textarea>
						</div>
					</section>
					<!-- Hashtag -->
					<hr class="major">
						<span>
							<p>태그</p> 
							<input type="text" name="hashtag" style="width:550px;" placeholder="예) 제주도 제주도여행기 입력(최대10개) 공백으로 구분됩니다">
						</span>
					<!-- 발행하기 -->
					<hr class="major">
					<div class="features">
						<article>
							<span></span>
							<div class="content">
								<p>
									<input type="button" class="button fit small" id="savebutton" value="발행">
								</p>
							</div>
						</article>
						<article></article>
					</div>
					
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
	</form>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../boardCSS/assets/js/browser.min.js"></script>
	<script src="../boardCSS/assets/js/breakpoints.min.js"></script>
	<script src="../boardCSS/assets/js/util.js"></script>
	<script src="../boardCSS/assets/js/main.js"></script>
	<script src="../boardCSS/assets/js/jquery.poptrox.min.js"></script>
	<script src="../boardCSS/assets/js/skel.min.js"></script>

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