<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- bootstrap4.6.1 -->
		<script src="/static/js/jquery-3.6.0.min.js"></script>
		<link rel="stylesheet"  href="/static/bootstrap-4.6.1-dist/css/bootstrap.css" type="text/css">
		<script src="/static/bootstrap-4.6.1-dist/js/bootstrap.js"></script>
		
		<!-- bootstrap_icon-1.8.1 -->
		<link rel="stylesheet" href="/static/bootstrap_icons-1.8.1/font/bootstrap-icons.css">
		
		<!-- jQuery 3.6.0 -->
		<script src="/static/js/jquery-3.6.0.min.js"></script>

		<link rel="stylesheet"  href="/static/css/style.css" type="text/css">
	
		<meta charset="UTF-8">
		<title>column</title>
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp" />
			
			<section>
				<%-- 제목 영역 --%>
				<header class="postDetailHeader d-flex">
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="whiteTitleText font-weight-bold">Writer</span>
					</div>
					<div class="col-8 d-flex justify-content-center align-items-center">
						<span class="whiteTitleText font-weight-bold">Title</span>
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="whiteTitleText font-weight-bold">Date</span>
					</div>
				</header>
				
				<%-- 칼럼 정보 --%>
				<div class="PostWriterInfo d-flex">
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">${columnDetail.column.writer }</span>
					</div>
					<div class="col-8 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">${columnDetail.column.title }</span>
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">
							<fmt:formatDate value="${columnDetail.column.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</span>
					</div>
				</div>
				
				<%-- 칼럼 본문 --%>
				<div>
					${columnDetail.column.content }
				</div>
				
				<%-- 댓글구역 --%>
				<div class="commentLine d-flex align-items-center">
					<span class="whiteTitleText font-weight-bold ml-3">Comment</span>
				</div>
				
				<%-- 댓글 입력구역 --%>
				<div class="mb-3 d-flex bg-light">
					<%-- 댓글 텍스트 박스 --%>
					<div class="col-11 d-flex justify-content-center align-items-center mt-3 mb-4">
						<textarea class=" form-control" placeholder="Input text...." id="commentText"></textarea>
					</div>
					<div class="col-1 d-flex justify-content-center align-items-center mb-3">
						<button type="button" class="btn btn-info" id="addCommentBtn" data-column-id="${columnDetail.column.id }">Submit</button>
					</div>
				</div>
				
				<%-- 댓글 출력 구역 --%>
				<c:forEach var="columnCommentDetail" items="${columnDetail.columnCommentDetailList}" >
				<div class="commentArea ">
					<%-- 반복 구역 시작 --%>
					<div class="comment d-flex align-items-center my-3">
						<%-- 댓글 작성자 --%>
						<div class="col-2">
							<h3>${columnCommentDetail.columnComment.nickName }</h3>
						</div>
						
						<%-- 댓글 내용 --%>
						<div class="col-8">
							${columnCommentDetail.columnComment.content }
						</div>
						
						<%-- 댓글 좋아요/싫어요 부분 --%>
						<div class="col-2 d-flex">
							<%-- like부분 --%>
							<div class="d-flex mr-5 align-items-center">
								<%-- like아이콘 --%>
								<div>
									<a class="likeCommentBtn" data-column-id="${columnDetail.column.id }" data-comment-id="${columnCommentDetail.columnComment.id }">
									<c:choose>
										<c:when test="${columnCommentDetail.columnCommentLike.commentLike}">
										<h3><i class="bi bi-hand-thumbs-up-fill text-danger"></i></h3>
										</c:when>
										
										<c:otherwise>
										<h3><i class="bi bi-hand-thumbs-up text-dark"></i></h3>
										</c:otherwise>
									</c:choose>
									</a>
								</div>
								
								<%-- like 숫자 --%>
								<div>
									<h3>${columnCommentDetail.columnCommentLike.likeCount}</h3>
								</div>
							</div>
							
							<%-- dislike부분 --%>
							<div class="d-flex align-items-center">
								<%-- dislike아이콘 --%>
								<div>
									<a class="dislikeCommentBtn" data-column-id="${columnDetail.column.id }" data-comment-id="${columnCommentDetail.columnComment.id }">
									<c:choose>
										<c:when test="${columnCommentDetail.columnCommentLike.commentDislike}">
										<h3><i class="bi bi-hand-thumbs-down-fill text-primary"></i></h3>
										</c:when>
										
										<c:otherwise>
										<h3><i class="bi bi-hand-thumbs-down text-dark"></i></h3>
										</c:otherwise>
									</c:choose>
									</a>
								</div>
								
								<%-- dislike 숫자 --%>
								<div>
									<h3>${columnCommentDetail.columnCommentLike.dislikeCount}</h3>
								</div>
							</div>
					</div>
				</div>
				</c:forEach>
				
				
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		</div>

	</body>
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		$("#addCommentBtn").on("click", function() {
			let columnId = $(this).data("column-id");
			let comment = $("#commentText").val();
			
			$.ajax({
				type:"post",
				url:"/post/column/create_opinion",
				data:{"columnId":columnId, "commentContent":comment},
				success:function(data) {
					if(data.result == "success") {
						location.reload();
					} else {
						alert("댓글추가 실패");
					}
				},
				error:function() {
					alert("에러");
				}
			});
		});
		
		//좋아요 버튼 클릭시
		$(".likeCommentBtn").on("click", function() {
			let columnId = $(this).data("column-id");
			let commentId = $(this).data("comment-id");
			
			$.ajax({
				type:"get",
				url:"/post/column/opinion/like",
				data:{"columnId":columnId, "commentId":commentId},
				success:function(data) {
					location.reload();
				},
				error:function() {
					alert("error");
				}
			});
			
		});
		
		//싫어요 버튼 클릭시
		$(".dislikeCommentBtn").on("click", function() {
			let columnId = $(this).data("column-id");
			let commentId = $(this).data("comment-id");
			
			$.ajax({
				type:"get",
				url:"/post/column/opinion/dislike",
				data:{"columnId":columnId, "commentId":commentId},
				success:function(data) {
					location.reload();
				},
				error:function() {
					alert("error");
				}
			});
			
		});
		
	});
	</script>
	
	
</html>