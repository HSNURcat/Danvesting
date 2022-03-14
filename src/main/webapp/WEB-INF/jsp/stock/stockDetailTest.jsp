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
	
		<script type="text/javascript">
			var unixTimestamps = new Array();
			var c = new Array(); // 설정기간 동안의 종가
			var h = new Array(); // 설정기간 동안 가장 높은 가격
			var l = new Array(); // 설정기간 동안 가장 낮은 가격
			var o = new Array(); // 시장 시작 가격
			var i = 0;
		</script>
		
		<div id="wrap">
			<c:import url="/WEB-INF/jsp/include/header.jsp" />
			
			<section>
				${stockBar.ticker }<br>
				${stockBar.status }<br>
				${stockBar.queryCount }<br>
				${stockBar.resultsCount }<br>
				${stockBar.adjusted }<br>
				<br>
				${stockBar.results }<br>
				<br>
				<c:forEach var="result" items="${stockBar.results }">
					<script type="text/javascript">
						unixTimestamps[i] = ${result.t };
						c[i] = ${result.c };
						h[i] = ${result.h };
						l[i] = ${result.l };
						o[i] = ${result.o };
						i++;
					</script>
				</c:forEach>
			</section>
			
			<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		</div>
		
		<div id="chart_div" style="width: 1200px; height: 500px;"></div>
		
	</body>

	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		var dates = new Array();
		var j = 0;
	
		for(let unixTimestamp of unixTimestamps) {
			 date = new Date(unixTimestamp);
			 dates[j] = date.getFullYear() + "-" + ('0' + (date.getMonth() + 1)).slice(-2) + "-" + ('0' + date.getDate()).slice(-2);
			 j++
		}
	
	 	google.charts.load('current', {'packages':['corechart']});
	    google.charts.setOnLoadCallback(drawChart);
	    
	    function drawChart() {
	        let data = google.visualization.arrayToDataTable([
	          [dates[0], l[0], o[0], c[0], h[0]],
	          [dates[1], l[1], o[1], c[1], h[1]],
	          [dates[2], l[2], o[2], c[2], h[2]],
	          [dates[3], l[3], o[3], c[3], h[3]],
	          [dates[4], l[4], o[4], c[4], h[4]],
	          [dates[5], l[5], o[5], c[5], h[5]],
	          [dates[6], l[6], o[6], c[6], h[6]],
	          [dates[7], l[7], o[7], c[7], h[7]],
	          [dates[8], l[8], o[8], c[8], h[8]],
	          [dates[9], l[9], o[9], c[9], h[9]],
	          [dates[10], l[10], o[10], c[10], h[10]],
	          [dates[11], l[11], o[11], c[11], h[11]],
	          [dates[12], l[12], o[12], c[12], h[12]],
	          [dates[13], l[13], o[13], c[13], h[13]]
	          // Treat first row as data as well.
	        ], true);

	        let options = {
	          legend:'none'
	        };

	        let chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));

	        chart.draw(data, options);
	      }
	</script>
</html>