<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<header>
				<div class="page-header d-flex">
					<%-- 로고 --%>
					<div class="col-4">
						<a href="/main" class="text-decoration-none text-white">
							<span class="display-4 font-weight-bold">DanDanDevelop</span>
						</a>
					</div>
					
					<%-- 검색창 --%>
					<div class="col-4 d-flex justify-content-center align-items-center">
						<div class="col-12 d-flex">
							<input type="text" class="form-control" id="searchObj">
							<button type="button" class="btn btn-light" id="searchBtn">search</button>
						</div>
					</div>
					
					<%-- 회원정보 & 로그아웃 --%>
					<div class="col-4 d-flex justify-content-arount align-items-center">
						<%-- 회원정보 --%>
						<c:choose>
						
						<c:when test="${not empty id }">
							<div class="d-flex flex-column col-6">
								<div class="mr-3 ">
									<a data-toggle="collapse" href="#memberMenu">
										<span class="whiteTitleText font-weight-bold">${nickName }님</span>
									</a>
								</div>
								<div class="collapse" id="memberMenu">
									<div>
										<a href="/user/member/check_member">Change User Info</a>
									</div>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="mr-3 col-6">
								<a href="/user/sign_in_view">
									<span class="whiteTitleText font-weight-bold">로그인</span>
								</a>
							</div>
						</c:otherwise>
						
						</c:choose>
						
						<%-- 로그아웃 --%>
							<div class="col-6">
								<a href="/user/logout">
									<span class="font-weight-bold text-white">로그아웃</span>
								</a>
							</div>
					</div>
					
				</div>
			</header>
			<div class="page-menu mb-5">
				<nav id="menu" class="w-25 h-25">
					<ul class="nav nav-fill d-flex align-items-center justify-content-around">
					<li><a href="/post/column_list" class="nav-text text-dark font-weight-bold">Column</a></li>
						<li><a href="/post/content/board" class="nav-text text-dark font-weight-bold">Board</a></li>
						<li><a href="/post/stock/mylist" class="nav-text text-dark font-weight-bold">My stock list</a></li>
					</ul>
				</nav>
			</div>
			
			<script type="text/javascript">
				$(document).ready(function() {
					
					//검색함수 생성
					function search() {
						var searchObj = $("#searchObj").val();
						var ticker = searchObj.toUpperCase();
						location.href="/post/stock/detail_view?ticker=" + ticker;
					}
					
					//엔터키 눌렀을때 검색 함수 실행
					$(document).keyup(function(e) {
						if(e.keyCode == 13) {
							search();
						}
					})
					
					//검색 버튼 눌렀을때 검색 함수 실행
					$("#searchBtn").on("click", function() {
						search();
					});
				});
				
			</script>