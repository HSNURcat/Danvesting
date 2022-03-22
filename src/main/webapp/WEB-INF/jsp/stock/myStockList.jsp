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
		
		<!-- datepicker -->
		<link rel="stylesheet" href="/static/jquery-ui-1.13.1.custom/jquery-ui.css">
		<script src="/static/jquery-ui-1.13.1.custom/jquery-ui.js"></script>
   
		<link rel="stylesheet"  href="/static/css/style.css" type="text/css">
		
		<meta charset="UTF-8">
		<title>My stocks</title>
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp" />
			
			<section class="d-flex justify-content-center">
				<%-- 리스트 부분 --%>
				<div class="col-12">
					<%-- 컬럼표기 --%>
					<div class="d-flex col-12 bg-light text-center">
						<div class="col-6"> <h4>Name</h4> </div>
						<div class="col-6"> <h4> Add date</h4> </div>
					</div>
					<%-- 리스트 내용영역 --%>
					<div>
						<%-- 리스트 --%>
						<%-- 반복구역 시작 --%>
						<c:forEach var="stock" items="${stockList}" >
						<a href="/post/stock/detail_view?ticker=${stock.ticker }">	
							<div class="d-flex col-12 text-center">
								<div class="col-6">
									<span class="text-dark font-weight-bold">${stock.ticker }</span>
								</div>
								<div class="col-6">
									<span class="text-dark font-weight-bold">${stock.createdAt }</span>
								</div>
							</div>
						</a>
						</c:forEach>
					</div>
				</div>
			
			</section>
		
			<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		</div>

	</body>
</html>