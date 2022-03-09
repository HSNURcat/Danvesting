<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<title>Insert title here</title>
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp" />
			
			<section class="mt-5 d-flex justify-content-center">
				<div class="signUp-box">
				
					<div class="input-box d-flex flex-column align-items-between my-5">
						<div class="col-12 text-center">
							<h3>Please input your password</h3>
						</div>
						
						<div class="d-flex m-3">
							<div class="col-3">
								<span>ID</span>
							</div>
							<div class="col-9">
								<input type="text" class="form-control col-11" value="${userInfo}" disabled="disabled">
							</div>
						</div>
						
						<div class="d-flex m-3">
							<div class="col-3">
								<span>PW</span>
							</div>
							<div class="col-9">
								<input type="password" class="form-control col-11" id="password">
							</div>
						</div>
						
						<div class="d-flex justify-content-end my-3 mx-5">
							<div>
								<button type="button" class="btn btn-info" id="checkMember-btn">Check Member</button>
							</div>
						</div>
					
					</div>
				
				</div>
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
			
		</div>
	</body>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#checkMember-btn").on("click", function() {
				var password = $("#password").val();
				
				if(password=="") {
					alert("Input your password");
					return false;
				}
				
				$.ajax({
					type:"post",
					url:"/user/member/check_member",
					data:{"password":password},
					success:function(data) {
						if(data.result == "success") {
							//회원수정 페이지 이동
							location.href = "/user/member/change_user_info";	
						} else {
							alert("Check your PW.");
						}
					},
					error:function() {
						alert("에러 발생");
					}
				});
				
			});
		});
	
	</script>
	
</html>