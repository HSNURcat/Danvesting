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
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
		
		google.charts.load('current', {'packages':['corechart']});
		
		$(document).ready(function() {
			//오늘날짜 도출
			let date = new Date();
			let year = date.getFullYear();
			let month = date.getMonth() + 1;
			let day = date.getDate();
			document.getElementById("current_date").innerHTML = year + "-" + month + "-" + day;
			
			//데이트 피커
			$( "#datepicker" ).datepicker({
				changeYear:true, // 년도 셀렉트 박스
		        changeMonth:true, // 월 셀렉트 박스
		        maxDate:0, // 오늘 이후 날짜 선택 불가
				dateFormat : "yy-mm-dd" //날짜를 어떤 방식으로 표기할지("y-mm-dd","mm-dd-yy", "mm-dd-y")
			});
			//데이트 피커 기본값 설정(1달 전)
			$( "#datepicker" ).datepicker("setDate", "today-1M");
			
			//url에서 티커추출
			let parameter = location.search;
			let param = parameter.split("=")
			var ticker = param[1];
			
			//HTML에서 클래스 이름으로 티커 표기 가능하도록
			document.getElementById("ticker").innerHTML = ticker;
			
			//기본 시작 날짜 도출
			var defaultFromDate = $("#datepicker").val();
			
			$.ajax({
				type:"get",
				url:"/post/stock/detail_price",
				data:{"ticker":ticker, "search_from_date":defaultFromDate },
				success:function(data) {
					//RestController로부터 결과값 받아옴
					stockBar = data.stockBar;
					
					//Data가공 후 price그래프 그리는 함수 실행
					priceBar(stockBar);
					
					//Data가공 후 거래량 그래프 그리는 함수 실행
					tradingVolume(stockBar);
				}
			});
			
			//데이트 피커 날짜 변경시 이벤트
			$("#datepicker").change(function() {
				var searchFromDate = $(this).val();
				
				$.ajax({
					type:"get",
					url:"/post/stock/detail_price",
					data:{"ticker":ticker, "search_from_date":searchFromDate },
					success:function(data) {
						//RestController로부터 결과값 받아옴
						stockBar = data.stockBar;
						
						//Data가공 후 price그래프 그리는 함수 실행
						priceBar(stockBar);
						
						//Data가공 후 거래량 그래프 그리는 함수 실행
						tradingVolume(stockBar);
					}
				});
			});
			
			var stockBar;
			
			var unixTimestamps = new Array();
			var c = new Array(); // 설정기간 동안의 종가
			var h = new Array(); // 설정기간 동안 가장 높은 가격
			var l = new Array(); // 설정기간 동안 가장 낮은 가격
			var o = new Array(); // 시장 시작 가격
			var v = new Array(); //설정 기간동안의 거래량
			var i = 0;
			
			function priceBar(stockBar) {
				var i = 0;
				var resultsCount = stockBar.resultsCount;
				
				//results 데이터를 각각 분리하는 작업
				for(var result of stockBar.results) {
					 unixTimestamps[i] = result.t
					 c[i] = result.c;
					 h[i] = result.h;
					 l[i] = result.l;
					 o[i] = result.o;
					 i++;
				 }
			
				//unixTimestamp를 날짜형식으로 가공
				var dates = new Array();
				var j = 0;
				
				for(let unixTimestamp of unixTimestamps) {
					 date = new Date(unixTimestamp);
					 dates[j] = date.getFullYear() + "-" + ('0' + (date.getMonth() + 1)).slice(-2) + "-" + ('0' + date.getDate()).slice(-2);
					 j++;
				}
				
				//분리한 데이터 배열에 넣음
				var arr = new Array();
				
				var data = new google.visualization.DataTable();
				data.addColumn("string", "date");
				data.addColumn("number", "Lowest-Price");
				data.addColumn("number", "Open-Price");
				data.addColumn("number", "Close-Price");
				data.addColumn("number", "Highest-Price");
				
				
				for(let n = 0; n < resultsCount; n++) {
					data.addRow([dates[n], l[n], o[n], c[n], h[n]]);
					console.log(data[n]);
				}
				
				var options = {
					legend:'none',
					hAxis:{slantedText: true, slantedTextAngle: 45},
					candlestick: {
						fallingColor:{fill: "blue", stroke:'blue'},
						risingColor:{fill:"red", stroke:'red'}
					}
				};
				
				var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));
				chart.draw(data, options);					
			}
			
			
			function tradingVolume(stockBar) {
				var i = 0;
				var resultsCount = stockBar.resultsCount;
				
				//results 데이터를 각각 분리하는 작업
				for(var result of stockBar.results) {
					 unixTimestamps[i] = result.t
					 v[i] = result.v;//거래량
					 
					 i++;
				 }
			
				//unixTimestamp를 날짜형식으로 가공
				var dates = new Array();
				var j = 0;
				
				for(let unixTimestamp of unixTimestamps) {
					 date = new Date(unixTimestamp);
					 dates[j] = date.getFullYear() + "-" + ('0' + (date.getMonth() + 1)).slice(-2) + "-" + ('0' + date.getDate()).slice(-2);
					 j++;
				}
				
				//분리한 데이터 배열에 넣음
				var arr = new Array();
				
				var data = new google.visualization.DataTable();
				data.addColumn("string", "date");
				data.addColumn("number", "Trading-Volume");
				
				for(let n = 0; n < resultsCount; n++) {
					data.addRow([dates[n], v[n]]);
				}
				
				var options = {
					legend:'none',
					hAxis:{slantedText: true, slantedTextAngle: 45},
					title: "Trading Volume",
				};
				
				var chart = new google.visualization.ColumnChart(document.getElementById('trading_chart_div'));
				chart.draw(data, options);					
			}
			
		});
		</script>
		
		<meta charset="UTF-8">
		<title>Stock Detail</title>
		
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp" />
			
			<section>
				<div class="mb-2">
					<h1>Ticker : <span id="ticker"></span></h1>
				</div>
				<div class="text-center">
					<h3>Search From <input type="text" id="datepicker"> To today(<span id="current_date"></span>).</h3>
				</div>
				<div class="borderBox align-items-center">
					<div class="text-center"> <h3>PriceChart</h3> </div>
					<div id="chart_div" style="width: 100%; height: 500px;"></div>
					<div id="trading_chart_div" style="width: 100%; height: 400px;"></div>
				</div>
				
				
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		</div>
		
	</body>
	
	
</html>