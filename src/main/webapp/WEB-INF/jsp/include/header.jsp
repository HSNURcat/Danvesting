<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<header>
				<div class="page-header d-flex">
					<%-- 로고 --%>
					<div class="col-4">
						<span class="display-4 font-weight-bold">Danvesting</span>
					</div>
					
					<%-- 검색창 --%>
					<div class="col-4 d-flex justify-content-center align-items-center">
						<div class="col-12 d-flex">
							<input type="text" class="form-control" id="searchObj">
							<button type="button" class="btn btn-light" id="searchBtn">search</button>
						</div>
					</div>
					
					<%-- 회원정보 & 로그아웃 --%>
					<div class="col-4">
					
					</div>
				</div>
			</header>
			<div class="page-menu mb-5">
				<nav id="menu" class="w-25 h-25">
					<ul class="nav nav-fill d-flex align-items-center justify-content-around">
						<li><a href="#" class="nav-text text-dark font-weight-bold">지수</a></li>
						<li><a href="#" class="nav-text text-dark font-weight-bold">자유게시판</a></li>
						<li><a href="#" class="nav-text text-dark font-weight-bold">내 관심 종목</a></li>
					</ul>
				</nav>
			</div>