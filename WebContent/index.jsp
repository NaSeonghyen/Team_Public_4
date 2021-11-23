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
			$('getData').click(function(){
				$.ajax({
					url: 'TouristicWeatherServlet.do',
					type: 'post',
				    dataType: 'json',
				    success: function(msg){
				    	var myItem = data.response.body.items.item;
				           
				          for(var i=0; myItem.length; i++){
				              var output = '';
				              console.log(myItem.length);
				              output += '<h3>'+ i + '번째 서울 축제 데이터' +'</h3>';
				              output += '<h4>'+myItem[i].spotAreaName+'</h4>';
				              output += '<h4>'+myItem[i].courseAreaId+'</h4>';
				              output += '<h4>'+myItem[i].courseAreaName+'</h4>';
				              document.body.innerHTML += output;
				          }
				    	//$('#addr').val(msg.data.response.body.items.item[0].spotAreaName);
				    }	
				});
			});
		});
	</script>
</head>
<body>
<!--  
	addr:<input type="text" id="addr"/><br>
	
-->
<input type="button" id="getData" value="Get Data"/>
<form action="TouristicWeatherServlet.do" method="post">
<input type="submit" value="데이터보기"/>
</form>

</body>
</html>