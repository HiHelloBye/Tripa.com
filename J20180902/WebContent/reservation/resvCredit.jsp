<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% int reser_no = Integer.parseInt(request.getParameter("reser_no")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<title>Tripa.com</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/search.css"> 
	<link rel="stylesheet" href="../css/btn.css">
	<link rel="stylesheet" href="../css/bestreview.css"> 
	
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/httpRequest.js"></script>
<style>
	@media ( min-width :768px) {
	.modal-dialog2 {
		width: 600px;
		margin: 30px auto
	}
	.modal-content2 {
		-webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
		box-shadow: 0 5px 15px rgba(0, 0, 0, .5)
	}
	.modal-sm99 {
		width: 680px
	}
}
.do {
	font-size : 18px;
	font-weight : bold;
}
.do2{
	background-color : #C6D3D3;
	width : 650px;
}
.do3{
  width:500px;
  height: 200px;
  background-color : #C6D3D3;
}
.form-control2 {
	display: block;
	width: 1600px;
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
.form-contro {
	display: inline-block;
	width: 140px;
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
.form-control3 {
	display: block;
	width: 1500px;
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
</style>
<script type="text/javascript">
function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
     object.value = object.value.slice(0, object.maxLength);
      }
   }
function aab() {
	var reser_no = frm.ab.value;
	var sendData="reser_no="+reser_no;
		$.post('resvDelete.do',
			sendData, 
			function() {
		        history.back();
	      }
		);
} 
</script>
</head>

<body>

 	<div class="modal-dialog modal-sm99" role="document">
   		<div class="modal-content">
     		<div class="modal-header">
       			<h4 class="modaxl-title" id="myModalLabel">신용카드 결제</h4>
     		</div>
     		<div class="modal-body">
     			<form action="resvCredit.do" method="post" class="form-signin">
        			<label class="do">카드 회사 선택</label>
					<select class = "form-control" name="code">
						<option value = "신한은행">신한은행</option>
						<option value = "국민은행">국민은행</option>
						<option value = "농협은행">농협은행</option>
						<option value = "하나은행">하나은행</option>
						<option value = "우리은행">우리은행</option>
						<option value = "씨티은행">씨티은행</option>
						<option value = "외한은행">외한은행</option>
						<option value = "기업은행">기업은행</option>
						<option value = "제주은행">제주은행</option>
						<option value = "우체국은행">우체국은행</option>
						<option value = "SC은행">SC은행</option>
						<option value = "수협은행">수협은행</option>
						<option value = "새마을은행">새마을은행</option>
						<option value = "산협은행">산협은행</option>
						<option value = "산업은행">산업은행</option>
						<option value = "카카오뱅크">카카오뱅크</option>
					</select>
					<label class="do">카드 번호</label><br>
						<input class = "form-contro" type="number" max="9999" maxlength="4" oninput="maxLengthCheck(this)" name="card_no1"/>-
						<input class = "form-contro" type="number" max="9999" maxlength="4" oninput="maxLengthCheck(this)" name="card_no2"/>-
						<input class = "form-contro" type="number" max="9999" maxlength="4" oninput="maxLengthCheck(this)" name="card_no3"/>-
						<input class = "form-contro" type="number" max="9999" maxlength="4" oninput="maxLengthCheck(this)" name="card_no4"/>
					<label class="do">유효 날짜</label>
						<input class = "form-control" type="number" max="99" maxlength="2" oninput="maxLengthCheck(this)" placeholder="month" name="valid_date1"/>
						<input class = "form-control" type="number" max="99" maxlength="2" oninput="maxLengthCheck(this)" placeholder="year" name="valid_date2"/>
					<label class="do">cvc 번호</label>
						<input class = "form-control" type="number" max="999" maxlength="3" oninput="maxLengthCheck(this)" name="cvc_no"/>
					<label class="do">카드 비밀번호</label>
						<input class = "form-control" type = "password" name="card_passwd" maxlength=6 required="required">
					<hr>
        			<button type="submit" class="btn btn-lg btn-primary btn-block">입력 완료</button>
        			<button type="button" class="btn btn-lg btn-primary btn-block" onclick="aab()">취소</button>
     			</form>
     		</div>
  		</div>
 	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="../js/bootstrap.js"></script>
		<script type="text/javascript">
		$('#card').click(function(event){
		   event.preventDefault();
		   $('#myModal2').modal('show');
		});
	</script>
	 <form name="frm">
	<input type="hidden" name="ab" value=<%=reser_no%>>
</form> 

</body>
</html>