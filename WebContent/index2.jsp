<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공공데이터 활용</title>
	<script type="text/javascript" src="jquery.js"></script>
	<script>
		$(document).ready(function(){
			$.ajax({
				url: 'BusPositionServlet.do',
				type: 'post',
			    dataType: 'json',
			    success: function(data){
			    	resultHtml(data);
			    }
			});
		});
		function resultHtml(data){
			var html = "<table border = '1'>";
			html += "<tr>";
			html += "<th>차량번호</th>";
			html += "</tr>";
			
			var ROW_COUNT = $(data).find("ROW_COUNT").text();
			//alert(ROW_COUNT);
			var LINE = $(data).find("LINE");
			//alert(LINE.text());
			var BusInfo = "";
			
			for(var i = 0; i < ROW_COUNT; i++){
				BusInfo = LINE.eq(i);
				html += "<tr align = 'center'>";
				html += "<td>" + data.find("plainNo").text() + "</td>";
				html += "</tr>"; 
			}
				
			html += "</table>";
			$("#display").empty();
			$("#display").append(html);
		}	
	</script>
</head>
<body>
<button id="btn">버스 노선정보</button>
<br/><br/>
<div id="display">
여기에 버스 노선정보가 출력됩니다.
</div>
</body>
</html>