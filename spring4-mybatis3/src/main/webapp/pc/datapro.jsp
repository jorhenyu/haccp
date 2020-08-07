<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<script src="../js/jquery.min.js"></script>
<script>

$(document).ready(function() {  

	$("#exportWord").click(function(){
		   var method =$("input[name='pcpcId']:checked").val(); //radio 取值，注意寫法
		   if( typeof(method) == "undefined"){ // 注意檢查完全沒有選取的寫法，這行是精華
		   alert( "請選取操作方式！");
		   return false;
		  }	
		   alert(method);
			  
			   $('#pcId').val(method);
			   $('form#myForm').submit();
		   });  
});

</script>
</head>
<body>
	<table>
		<tr>
						
			<th colspan="16" scope="row">
			<form id="myForm" name="myForm" action="${pageContext.request.contextPath }/pc/exportWord.do" method="post">
			<input type="hidden" id="pcId" name="pcId" value=""> 
			 <input	id="exportWord" name="exportWord" type="button" value="匯出">
			</form>						
			</th>
		</tr>
		<tr>
			
			<th>選取</th>
			<th>專案名稱名稱</th>
			<th>產品預定用法及用途</th>			
			<th>銷售地點</th>
			<th>消費對象</th>
			<th>注意事項</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="pc" items="${lsts}">
			<tr><td>
					<input type="radio" id="pcpcId" name="pcpcId" value="${pc.pcId}">
				</td>
			    <td>${pc.plan.pName}</td>			
				<td>${pc.pUse}</td>
				<td>${pc.sSpot}</td>
				<td>${pc.cObj}</td>
				<td>${pc.notes}</td>		
			</tr>
		</c:forEach>
	</table>

</body>
</html>

