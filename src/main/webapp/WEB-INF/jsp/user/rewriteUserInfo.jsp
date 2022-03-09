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
						<%-- ID부분 --%>
						<div class="m-3">
							<%-- ID input부분 --%>
							<div class="d-flex">
								<div class="col-3">
									<span>ID</span>
								</div>
								<div class="col-9">
									<input type="text" class="form-control col-11" value="${userInfo }" disabled="disabled">
								</div>
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
						
						<div class="d-flex m-3">
							<div class="col-3">
								<span>PW Confirm</span>
							</div>
							<div class="col-9">
								<input type="password" class="form-control col-11" id="password-confirm">
							</div>
						</div>
						
						<div class="d-flex m-3">
							<div class="col-3">
								<span>name</span>
							</div>
							<div class="col-9">
								<input type="text" class="form-control col-11" id="name">
							</div>
						</div>

						<div class="d-flex m-3">
							<div class="col-3">
								<span>NickName</span>
							</div>
							<div class="col-9">
								<input type="text" class="form-control col-11" id="nickname">
							</div>
						</div>
						
						<div class="d-flex justify-content-between my-3 mx-5">
							<div></div>
							<div>
								<button type="button" class="btn btn-info" id="change-btn">Change</button>
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
			$("#change-btn").on("click", function() {
				var password = $("#password").val();
				var passwordConfirm = $("#password-confirm").val();
				var name = $("#name").val();
				var nickname = $("#nickname").val();
				
				if(password == "") {
					alert("Please input password");
					return false;
				}
				
				if(passwordConfirm == "") {
					alert("Please input password-confirm");
					return false;
				}
				
				if(name == "") {
					alert("Please input name");
					return false;
				}
				
				if(nickname == "") {
					alert("Please input nickname");
					return false;
				}
				
				if(password != passwordConfirm) {
					alert("Please check password sameness");
					return false;
				}
				
				$.ajax({
					type:"post",
					url:"/user/member/rewrite_user_info",
					data:{"password":password, "name":name, "nickName":nickname},
					success:function(data) {
						if(data.result == "success") {
							//로그아웃, 로그인 화면으로 이동
							location.href = "/user/sign_out";	
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