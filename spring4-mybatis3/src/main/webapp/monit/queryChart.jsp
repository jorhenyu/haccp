<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui.min.js"></script>

<script>
$(document).ready(function() {
		$("#startdatepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	
		
		$("#enddatepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});

		
		   $("#add").bind('click', function() {
            	 
	        	 $('#form1').submit();
	      });  
    });
	

</script>
</head>
<body>
	

	<table>

		<tr><th>
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/monit/getColumnLine.do"
				method="post">
				業別:	<select name="qCatId">
						<c:forEach var="o" items="${options}">
							<option value="${o.optionKey}">${o.optionValue}</option>
						</c:forEach>
				</select>
			專案名稱:<input type="text" name="qPname" id="qPname" value=""
				>
			管制類別:<select id="qtypeReg" name="qtypeReg">
						<option value="ul">上限</option>
						<option value="ll">下限</option>
						<option value="ulAndll">上限與下限</option>
				</select><br>
			開始日期:<input type="text" name="rDateStart" id="startdatepicker" value=""
				readonly>
			結束日期:<input type="text" name="rDateEnd" id="enddatepicker" value=""
				readonly>
			
					<input id="add" name="add" type="button" value="製作折線圖">
			</form>
		</th></tr>
		
		


	</table>


</body>
</html>