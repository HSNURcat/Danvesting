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
		<title>post</title>
	</head>
	<body>
		<div id="wrap">
		
			<c:import url="/WEB-INF/jsp/include/header.jsp" />
			
			<%-- 본문 영역 --%>
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
				
				<div class="PostWriterInfo d-flex">
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">${postDetail.post.nickName }</span>
					</div>
					<div class="col-8 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">${postDetail.post.title }</span>
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">
							<fmt:formatDate value="${postDetail.post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</span>
					</div>
				</div>
				
				<%-- 이미지 구역 --%>
				<div class="my-2">
					<%-- 이미지 업로드 박스 --%>
					<div id="imageBox" class="d-flex justify-content-center align-items-center">
						<img alt="content-img" src="${postDetail.post.imagePath }" class="contentImg">												
					</div>
				</div>
					
				<%-- 텍스트 구역 --%>
				<div class="my-2">
					<%-- 게시물 텍스트 박스 --%>
					<div class="d-flex justify-content-center">
						${postDetail.post.content }
					</div>
				</div>
				
				<%-- 좋아요 구역 --%>
				<div class="my-2 d-flex">
					<%-- like부분 --%>
					<div class="d-flex mr-5 align-items-center">
						<%-- like아이콘 --%>
						<div>
							<a id="likePostBtn" data-post-id="${postDetail.post.id }"><i class="bi bi-hand-thumbs-up display-4"></i></a>
						</div>
						
						<%-- like 숫자 --%>
						<div>
							<h1>${postDetail.postLikeCount }</h1>
						</div>
					</div>
					
					<%-- dislike부분 --%>
					<div class="d-flex align-items-center">
						<%-- dislike아이콘 --%>
						<div>
							<a id="dislikePostBtn" data-post-id="${postDetail.post.id }"><i class="bi bi-hand-thumbs-down display-4"></i></a>
						</div>
						
						<%-- dislike 숫자 --%>
						<div>
							<h1>${postDetail.postDislikeCount }</h1>
						</div>
					</div>
					
				</div>
				
				<%--버튼구역 --%>
				<div class="my-5 d-flex justify-content-between">
					<div></div>
					<div>
						<button class="btn btn-info" id="changeBtn">Rewrite Content</button>
						<button class="btn btn-danger" id="deleteBtn">Delete Content</button>
					</div>
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
						<button type="button" class="btn btn-info" id="addCommentBtn" data-post-id="${postDetail.post.id }">Submit</button>
					</div>
				</div>
				
				<%-- 댓글 출력 구역 --%>
				<c:forEach var="postComment" items="${postDetail.comment}">
				<div class="commentArea ">
					<%-- 반복 구역 시작 --%>
					<div class="comment d-flex">
						<div class="col-2">
							${postComment.nickName }
						</div>
						<div class="col-8">
							${postComment.content }
						</div>
						<div class="col-2">
							좋아요/싫어요
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
			
			$("#likePostBtn").on("click", function(e) {
				e.preventDefault();
				
				var postId = $(this).data("post-id");

				$.ajax({
					type:"get",
					url:"/post/content/like",
					data:{"postId":postId},
					success:function(data) {
						location.reload();
					},
					error:function() {
						alert("error");
					}
				});
			});
			
			$("#dislikePostBtn").on("click", function(e) {
				e.preventDefault();
				
				var postId = $(this).data("post-id");

				$.ajax({
					type:"get",
					url:"/post/content/dislike",
					data:{"postId":postId},
					success:function(data) {
						location.reload();
					},
					error:function() {
						alert("error");
					}
				});
			});
			
			$("#addCommentBtn").on("click", function() {
				var postId = $(this).data("post-id");
				var comment = $("#commentText").val();
				
				$.ajax({
					type:"post",
					url:"/post/content/comment/create",
					data:{"commentContent":comment, "postId":postId},
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
			
			
			
			
		});
	</script>
	
	
</html>