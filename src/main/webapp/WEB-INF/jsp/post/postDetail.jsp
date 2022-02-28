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
						<span class="font-weight-bold">${postDetail.nickName }</span>
					</div>
					<div class="col-8 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">${postDetail.title }</span>
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">
							<fmt:formatDate value="${postDetail.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</span>
					</div>
				</div>
				
				<%-- 이미지 구역 --%>
				<div class="my-2">
					<%-- 이미지 업로드 박스 --%>
					<div id="imageBox" class="d-flex justify-content-center align-items-center">
						<img alt="content-img" src="${postDetail.imagePath }" class="contentImg">												
					</div>
				</div>
					
				<%-- 텍스트 구역 --%>
				<div class="my-2">
					<%-- 게시물 텍스트 박스 --%>
					<div class="d-flex justify-content-center">
						${postDetail.content }
					</div>
				</div>
				
				<%-- 좋아요 구역 --%>
				<div class="my-2">
				
				</div>
				
				<%--버튼구역 --%>
				<div class="my-5 d-flex justify-content-between">
					<div></div>
					<div>
						<button class="btn btn-info" id="changeBtn">게시물 수정</button>
						<button class="btn btn-danger" id="deleteBtn">게시물 삭제</button>
					</div>
				</div>
				
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp" />
			
		</div>
	</body>
</html>