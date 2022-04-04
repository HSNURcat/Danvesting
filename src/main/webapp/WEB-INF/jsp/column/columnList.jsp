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
		<title>Column</title>
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>	
			
			<section>
				<div>
					<h1>Column</h1>
				</div>
				<%-- 칼럼 리스트 부분 --%>
				<div>
					<header class="postListHeader d-flex">
						<div class="col-2 d-flex justify-content-center align-items-center">
							<span class="whiteTitleText font-weight-bold">Writer</span>
						</div>
						<div class="col-7 d-flex justify-content-center align-items-center">
							<span class="whiteTitleText font-weight-bold">Title</span>
						</div>
						<div class="col-3 d-flex justify-content-center align-items-center">
							<span class="whiteTitleText font-weight-bold">Date</span>
						</div>
					</header>
					<section>
					
						<div class="my-3">
						<%-- 반복할 부분 시작 --%>
						<c:forEach var="column" items="${columns}">
						<a href="/post/column_detail_view?columnId=${column.id }" class="text-decoration-none text-dark">
							<div class="postDetail d-flex">
								<%--칼럼 작성자 --%>
								<div class="col-2 d-flex justify-content-center align-items-center">
									<span class="font-weight-bold">${column.writer }</span>
								</div>
										
								<%-- 칼럼 제목 --%>
								<div class="col-7 d-flex justify-content-center align-items-center">
									<span class="font-weight-bold">${column.title}</span>
								</div>
										
								<%-- 게시물 작성날짜 --%>
								<div class="col-3 d-flex justify-content-center align-items-center">
									<span class="font-weight-bold">
										<fmt:formatDate value="${column.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</div>
							</div>
						</a>
						</c:forEach>
						<%-- 반복할 부분 끝 --%>
						</div>
					</section>
					
				</div>
				
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
		</div>

	</body>
</html>