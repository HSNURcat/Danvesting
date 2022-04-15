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
		
		<!-- bootstrap_icon-1.8.1 -->
		<link rel="stylesheet" href="/static/bootstrap_icons-1.8.1/font/bootstrap-icons.css">
		
		<!-- jQuery 3.6.0 -->
		<script src="/static/js/jquery-3.6.0.min.js"></script>

		<link rel="stylesheet"  href="/static/css/style.css" type="text/css">
		
		<meta charset="UTF-8">
		<title>write post</title>
	</head>
	<body>
		<div id="wrap">
		
			<c:import url="/WEB-INF/jsp/include/header.jsp"></c:import>	
			
			<section class="w-100 d-flex justify-content-center mb-5">
				<div class="contentBox">
					<%-- 제목 구역 --%>
					<div>
						<input type="text" class="form-control" placeholder="Input title...." id="postTitle">
					</div>
					
					<%-- 이미지 구역 --%>
					<div class="my-2">
						<%-- 이미지 업로드 박스 --%>
						<div id="imageBox" class="d-flex justify-content-center align-items-center">
							<%-- 업로드 이전 --%>
							<i class="bi bi-camera-fill display-4" id="beforeUpload"></i>
							
							<%-- 업로드 이후 --%>
							<img alt="upload-img" src="#" class="d-none" id="image-preview">
							
							<input type="file" id="input-file" class="d-none">
						</div>
					</div>
					
					<%-- 텍스트 구역 --%>
					<div class="my-2">
						<%-- 게시물 텍스트 박스 --%>
						<div id="textBox" class="d-flex justify-content-center align-items-center">
							<textarea class=" form-control" placeholder="Input text...." id="postText"></textarea>
						</div>
					</div>
					
					<%--버튼구역 --%>
					<div class="d-flex justify-content-between">
						<div>
							<button class="btn btn-danger" id="cancelBtn">Cancel</button>
						</div>
						
						<div>
							<button class="btn btn-info" id="submitBtn">Submit</button>
						</div>
					</div>
				</div>	
			</section>
		
			<c:import url="/WEB-INF/jsp/include/footer.jsp"></c:import>
		</div>
	</body>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			// 카메라 아이콘 클릭시 파일업로드 창 클릭
			$("#beforeUpload").on("click", function() {
				$("#input-file").click();
			});
			
			//input-file에 뭔가 변화(업로드)가 있을때 실행
			$("#input-file").on("change", function() {
				//파일 형식 추출
				uploadedFileFormat = $(this).val().split(".").pop().toLowerCase();
				console.log(uploadedFileFormat);
				//input-file에 업로드 된 값(파일 이름)<.val()>을 "."으로 나눠서 배열로 저장하고<.split(".")>, 
				//마지막 배열을 제거한 후, 제거한 배열 값을 반환<.pop()>, 반환된 배열값을 소문자로 변환<.toLowerCase>
				
				fileFormats = ["gif", "png", "jpg", "jpeg"];
				
				//fileFormats 배열에 uploadedFileFormat 값이 있는지 확인 
				if($.inArray(uploadedFileFormat, fileFormats) == -1) {
					//값이 없을 때,
					resetFormElement($(this)); //폼 초기화
					alert("이미지 파일이 아닙니다.(gif, png, jpg, jpeg)만 업로드 가능");
				} else {
					$("#beforeUpload").addClass("d-none");
					$("#image-preview").removeClass("d-none");
					
					imgFile = $("#input-file").prop("files")[0];
					blobURL = window.URL.createObjectURL(imgFile);
					$("#image-preview").attr('src', blobURL);
					$('imageBox').slideDown(); //업로드한 이미지 미리보기 
                    $(this).slideUp(); //파일 양식 감춤
				}
			});
			
			$("#submitBtn").on("click", function() {
				let postTitle = $("#postTitle").val();
				let postText = $("#postText").val();
				let file = $("#input-file").val().trim();
				
				if(postTitle == "") {
					alert("제목을 입력해주세요");
					return;
				}
				
				if(file == "") {
					alert("이미지를 첨부해주세요");
					return;
				}
				
				if(postText == "") {
					alert("게시물을 입력해주세요");
					return;
				}
				
				var formData = new FormData();
				formData.append("postTitle", postTitle);
				formData.append("postText", postText);
				formData.append("file", $("#input-file")[0].files[0]);
				
				$.ajax({
					type:"post",
					url:"/post/content/create",
					data:formData,
					enctype:"multipart/form-data", //파일 업로드 필수
					processData:false, //파일 업로드 필수
					contentType:false, //파일 업로드 필수
					success:function(data) {
						if(data.result == "success") {
							location.href="/post/content/board";
						} else {
							alert("글쓰기 실패");
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