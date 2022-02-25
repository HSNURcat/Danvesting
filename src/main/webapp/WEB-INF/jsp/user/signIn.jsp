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
		<title>Sign-In</title>
	</head>
	<body>
		<div id="wrap">
			<header class="page-header d-flex mb-5">
				<div class="col-4">
					<span class="display-4 font-weight-bold">Danvesting</span>
				</div>
				<div class="col-8">
				
				</div>
			</header>
			
			<section class="mt-5 d-flex justify-content-center">
				<div class="signUp-box">
				
					<div class="input-box d-flex flex-column align-items-between my-5">
						
						<div class="d-flex m-3">
							<div class="col-3">
								<span>ID</span>
							</div>
							<div class="col-9">
								<input type="text" class="form-control col-11" id="userId">
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
						
						<div class="d-flex justify-content-between my-3 mx-5">
							<div>
								<a href="/user/sign_up_view">회원가입</a>
							</div>
							<div>
								<button type="button" class="btn btn-info" id="signIn-btn">로그인</button>
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
			$("#signIn-btn").on("click",function() {
				var userId = $("#userId").val();
				var password = $("#password").val();
				
				if(userId == "") {
					alert("Please input ID");
					return false;
				}
				
				if(!(userId.includes("@") && userId.includes("."))) {
					alert("The ID is unavailable. ID must follow email address format");
					return false;
				}
				
				$.ajax({
					type:"post",
					url:"/user/sign_in",
					data:{"loginId":userId, "password":password},
					success:function(data) {
						if(data.result == "success") {
							//로그인 화면으로 이동
							location.href = "/main";	
						} else {
							alert("Check your ID & PW.");
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