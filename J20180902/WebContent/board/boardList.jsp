<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="boardStyle.css" />
	<title>Tripa.com</title>

</head>
<body style="overflow-x:hidden;">
		<div class="board-box" >
			<div class="searchResult">
			</div>
			<c:if test="${totCnt > 0 }">
				<c:forEach var="board" items="${list }">
					<div class="gallery">
						<a href='boardContent.do?num=${board.posting_no}&pageNum=${currentPage}'>
							${board.look}
							${board.scrap_cnt}
							<div class="bphoto">
								<img src="${board.photo}" style="width: 100%; height: auto;">
							</div>
							<div class="title">
								${board.board_title} 
							</div>
							<div class="id">
								${board.user_id}
							</div>
							<div>
								${board.hashTag}
							</div>
						</a>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="block" style="text-align: center;">
				<c:if test="${startPage > blockSize }">
					<a href='boardList.do?pageNum=${startPage-blockSize}'>[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href='boardList.do?pageNum=${i}'>[${i}]</a>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href='boardList.do?pageNum=${startPage+blockSize}'>[다음]</a>
				</c:if>
		</div>
</body>
</html>