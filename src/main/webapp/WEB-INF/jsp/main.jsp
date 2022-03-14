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
		<title>Insert title here</title>
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>	
			
			<section>
				<%-- 구글 애드센스 --%>
				<div>
				
				</div>
				
				<%-- 칼럼 & 그래프 --%>
				<div class="mb-5">
					<%-- 칼럼 --%>
					<div>
						<div>
							<h3 class="text-dark decoration-none">분석 & 견해</h3>
						</div>
						<div class="boardBar d-flex align-items-center">
							<span class="whiteTitleText font-weight-bold ml-3">Columns</span>
						</div>
						
						<%-- 칼럼 본문들 --%>
						<div>
							<%-- 반복 시작 --%>
							<c:forEach var="column" items="${mainPageData.column}">
							<a href="http://localhost:8080/post/column_detail_view?columnId=${column.id }" class="text-decoration-none text-dark">
							<div class="d-flex my-2">
								<div class="col-2 text-center">
									${column.writer }
								</div>
								<div class="col-8 text-center">
									${column.title}
								</div>
								<div class="col-2 text-center">
									<fmt:formatDate value="${column.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>
							</div>
							</a>
							</c:forEach>
						</div>		
						<div>
							<a href="#">
								View more
							</a>
						</div>
					</div>
					
					<%-- 그래프 --%>
					<div>
					
					</div>
				</div>
				
				<div>
					<div class="boardBar d-flex align-items-center">
						<span class="whiteTitleText font-weight-bold ml-3">Board</span>
					</div>
					
					<%-- 자유게시판 게시물 부분 --%>
					<div>
						<%-- 반복 시작 --%>
						<c:forEach var="post" items="${mainPageData.post}">
						<a href="/post/content/list_detail_view?postId=${post.id }" class="text-decoration-none text-dark">
						<div class="d-flex my-2">
							<div class="col-2 text-center">
								${post.nickName }
							</div>
							<div class="col-8 text-center">
								${post.title}
							</div>
							<div class="col-2 text-center">
								<fmt:formatDate value="${post.updatedAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</div>
						</div>
						</a>
						</c:forEach>
						<%-- 반복 끝 --%>
					</div>
					
					<div>
						<a href="/post/content/board">
							View more 
						</a>
					</div>
				</div>
				
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
		
		</div>
		
	</body>
</html>