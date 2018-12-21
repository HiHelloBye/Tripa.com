<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<% 
	String user_id = (String) session.getAttribute("user_id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tripa.com</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../css/bootstrap.min.css">
		<link rel="stylesheet" href="../member/assets/css/main.css?ver=2" />
		<link rel="stylesheet" href="reservation.css?ver=1" />
	<script type="text/javascript">

	function saving_pnt_check(){
	    location.href="resvPointCheck.do?saving_pnt=" + frm.saving_pnt.value + "&user_id=" + frm.use_id.value + "&price=" + frm.price.value;
	}
	
	function resv_date_check(){
	 //   location.href="resvDateCheck.do?out_date=" + frm.out_date.value;
	   location.href="resvDateCheck.do?out_date=" + frm.out_date.value + "&in_date=" + frm.in_date.value + "&accom_no=" + frm.accom_no.value + "&room_no=" + frm.room_no.value;
	}
	function tot_cnt(){
		var a = document.getElementById("price").value;
		var b = document.getElementById("savin_pnt").value;
 		if(b == null || b<1000){
 			b = 0;
 		}
		document.getElementById("result").value = parseInt(a) - parseInt(b);
	}
	
	function checkDisable(frm)
	{
	    if( frm.chkbox.checked == false ){
	    	frm.textbox.disabled = true;
		} else {
			frm.textbox.disabled = false;
		}
	}
	
	function checkDisable2(frm)
	{
	    if( frm.chkbox2.checked == false ){
	    	frm.textbox2.disabled = true;
		} else {
			frm.textbox2.disabled = false;
		}
	}
    function maxLengthCheck(object){
        if (object.value.length > object.maxLength){
         object.value = object.value.slice(0, object.maxLength);
          }
       }
     function resv_total_check(){
 		var saving_pnt = frm.saving_pnt.value;
 		var out_date = frm.out_date.value;	
 		var in_date = frm.in_date.value;
        var option1=$("input[type=radio][name=card]:checked").val();   
        var option2=$("input[type=radio][name=cardx]:checked").val();  
        var result2 = 0;
        var result3 = 0;
 		if(saving_pnt == null || saving_pnt<1000){
 			saving_pnt = 0;
 		}
 		if(option2==""||option2==null) {
 			option2 = 0;
 		}
   		if(out_date==""||out_date==null|| in_date==""|| in_date==null) {
         	 alert("날짜를 입력 해주세요.")
         	 return false;
          } else {   
        	  location.href="totalCheck.do?saving_pnt=" + saving_pnt+ "&option1=" + option1 + "&option2=" + option2  + "&res_name=" + frm.res_name.value + "&out_date=" + frm.out_date.value + "&accom_no=" + frm.accom_no.value + "&room_no=" + frm.room_no.value + "&in_date="+frm.in_date.value+"&user_cell="+frm.user_cell.value+"&email="+frm.user_eamil.value+"&res_cont="+frm.res_cont.value+ "&user_id=" + frm.use_id.value;
       	}
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
									<a href="#" class="logo"><strong>숙소예약</strong> by Tripa.com</a>

								</header>

							<!-- Banner -->
								

							<!-- Section -->
								<section>
									<header class="major">
										<h2>숙소예약</h2>
									</header>
									<div class="content">
										<form method="post" name="frm">
			<input type="hidden" name = "use_id" value="${sessionScope.user_id}">
			<table class="table table-bordered table-hover" style="text-align: center; background-color:white;  border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" ><h4>숙소 예약</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>숙소번호</h5></td>
						<td colspan="2"><input class="form-control" name="accom_no" value="${accom_no}" readonly></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>방 명칭</h5></td>
						<td colspan="2"><input class="form-control" name="room_no" value="${room_no}" readonly></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>예약 번호</h5></td>
						<td colspan="2"><input class="form-control" value="${reser_no }" readonly></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>Check In</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="in_date" name="in_date" maxlength="6" required="required" placeholder="체크인 날짜 yy/mm/dd입력"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>Check Out</h5></td>
						<td colspan="2">
						<div class="form-group" style="margin: 0 auto;">
									<ul class="nav navbar-nav">
							           	<li><input class="form-control2" type="text" id="out_date" name="out_date" maxlength="6" required="required" placeholder="체크아웃 날짜 yy/mm/dd입력"></li>
									</ul>
									<button class="button primary pull-right" onclick="resv_date_check()" type="button">예약 가능 여부</button>
							</div>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>가격</h5></td>
						<td colspan="2"><input class="form-control" id="price" name="price" value="${price}" readonly></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>적립금 사용</h5></td>
						<td colspan="2">
							<div class="form-group" style="margin: 0 auto;">
								<ul class="nav navbar-nav">
							        <li><input class="form-control3" type="number" id="savin_pnt"  name="saving_pnt" maxlength="10" placeholder="적립금 사용 입력"></li>
								</ul>
									<button class="button primary" onclick="saving_pnt_check()" type="button">적립금 확인</button>
									<button class="button primary" value="=" onclick="tot_cnt();" type="button">결제 금액 보기</button>
							</div>
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>결제 금액</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="result" ></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td colspan="2"><input class="form-control" name="user_id" value="${user_id}" readonly></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>예약자</h5></td>
						<td colspan="2"><input class="form-control" name="res_name" value="${res_name}" readonly></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>연락처</h5></td>
						<td colspan="2"><input class="form-control" type="text" name="user_cell" maxlength="14" required="required" placeholder="연락처 입력"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td colspan="2"><input class="form-control" type="email" name="user_eamil" maxlength="30" required="required" placeholder="이메일 입력"></td>
					</tr>
					<tr>
                    <td style="width: 110px;"><h5>결제 방법</h5></td>
                    <td colspan="2">
                       <div class="form-group" style="text-align: center; margin: 0 auto;">
                          <div class="btn-group" data-toggle="buttons">
                             <label class="btn btn-primary active">
                             	<input class="button primary" type="radio" name="card" id="card" value="1" checked>신용카드
                             </label>
	                          <label class="btn btn-primary">
	                              <input class="button primary" type="radio" name="cardx"  id="cardx" value="2">무통장 입금
	                           </label>
	                        </div>   
	                     </div>
	                  </td>
	               </tr>
					<tr>
						<td style="width: 110px;"><h5>내용</h5></td>
						<td colspan="2"><textarea class="form-control"placeholder="글 내용" name="res_cont" maxlength="2048" style="height: 350px;"></textarea></td>
					</tr>
					<tr>	
						<td style="text-align: center;" colspan="3"><h5 style="color: red;"></h5>
							<input class="button primary" type="button" onclick="resv_total_check()" value="예약">
							<input class="button primary" type="button" onclick="hisory.back()" value="취소">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
									</div>
								</section>
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