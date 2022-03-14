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
						<span class="font-weight-bold">${columnDetail.writer }</span>
					</div>
					<div class="col-8 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">${columnDetail.title }</span>
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center">
						<span class="font-weight-bold">
							<fmt:formatDate value="${columnDetail.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</span>
					</div>
				</div>
				
				<%-- 칼럼 본문 --%>
				<div>
					${columnDetail.content }
				</div>
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		</div>

	</body>
</html>