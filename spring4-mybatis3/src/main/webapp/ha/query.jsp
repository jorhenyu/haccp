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
	//將radio選取選項，帶回母視窗頁面
	function sendData() {
		var checkedText = document.querySelector('input[type="radio"]:checked').value;
		window.opener.document.getElementById("haId").value = checkedText;
	    
	   // window.opener.document.getElementById("txtName").value=name;
	   //var qq = $("input:radio[name=haId][checked]").index();
	   
	   var radioButtons = $("input:radio[name='haId']");
	   var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
	   //alert("selectedIndex="+selectedIndex);
	  // alert($('input[name="lsts['+selectedIndex+'].procStep"]').val());
	   
	   window.opener.document.getElementById("procStep").value=$('input[name="lsts['+selectedIndex+'].procStep"]').val();
	   window.opener.document.getElementById("planId").value=$('input[name="lsts['+selectedIndex+'].planId"]').val();

		//window.close();
	}
	
	   $('#input:radio[name=haId]').click(function(){
		   var index = $('#input:radio[name=haId]').index(this);
		   alert(index);
		 	   
		 });
</script>
</head>
<body>
	<table>
		<tr>
			<th>選取</th>
			<th>流程名稱</th>
			<th>選取</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ha" items="${lsts}" varStatus="status">
			<tr>
				<td><input type="radio" id="haId" name="haId" value="${ha.haId}"></td>
				<td><input type="text" id="pHa" name="lsts[${status.index}].pHa" value="${ha.pHa}"></td>
				<td><input type="text" id="procStep" name="lsts[${status.index}].procStep" value="${ha.procStep}"></td>
				<td><input type="text" id="planId" name="lsts[${status.index}].planId" value="${ha.planId}"></td>
				<td><input type="button" value="選取" onclick="sendData()">

				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

