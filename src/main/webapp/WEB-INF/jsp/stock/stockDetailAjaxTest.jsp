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
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<script type="text/javascript">
		
			google.charts.load('current', {'packages':['corechart']});
		
			$(document).ready(function() {
				//url에서 티커추출
				var parameter = location.search;
				var param = parameter.split("=")
				var ticker = param[1]
				
				var stockBar;
				
				var unixTimestamps = new Array();
				var c = new Array(); // 설정기간 동안의 종가
				var h = new Array(); // 설정기간 동안 가장 높은 가격
				var l = new Array(); // 설정기간 동안 가장 낮은 가격
				var o = new Array(); // 시장 시작 가격
				var v = new Array(); // 거래량
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
					data.addColumn("number", "l");
					data.addColumn("number", "o");
					data.addColumn("number", "c");
					data.addColumn("number", "h");
					
					
					for(let n = 0; n < resultsCount; n++) {
						data.addRow([dates[n], l[n], o[n], c[n], h[n]]);
						console.log(data[n]);
					}
					
					var options = {
						legend:'none',
						hAxis:{slantedText: true, slantedTextAngle: 45},
						candlestick: {
							fallingColor:{fill: "blue", strokeWidth:0.5, stroke:'blue'},
							risingColor:{fill:"red", strokeWidth:0.5, stroke:'red'}
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
					data.addColumn("number", "v");
					
					for(let n = 0; n < resultsCount; n++) {
						data.addRow([dates[n], v[n]]);
					}
					
					var options = {
						legend:'none',
						title: "Trading Volume",
					};
					
					var chart = new google.visualization.ColumnChart(document.getElementById('trading_chart_div'));
					chart.draw(data, options);					
				}
				
				var searchFromDate = "2020-02-01";
				
				$.ajax({
					type:"get",
					url:"/post/stock/detail_price",
					data:{"ticker":ticker, "search_from_date":searchFromDate },
					success:function(data) {
						stockBar = data.stockBar;
						priceBar(stockBar);
						tradingVolume(stockBar);
					}
				});
				
				
			});
		
		</script>
		
	</head>
	<body>
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp" />
			
			<section>

			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		</div>
		
		<div id="chart_div" style="width: 900px; height: 500px;"></div>
		<div id="trading_chart_div" style="width: 900px; height: 400px;"></div>
	</body>
</html>