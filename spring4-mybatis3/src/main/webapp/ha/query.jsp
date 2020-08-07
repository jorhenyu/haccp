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
	   
	   var radioButtons = $("input:radio[name='haId']");
	   var selectedIndex = radioButtons.index(radioButtons.filter(':checked'));
	   
	   window.opener.document.getElementById("procStep").value=$('input[name="lsts['+selectedIndex+'].procStep"]').val();
	   window.opener.document.getElementById("planId").value=$('input[name="lsts['+selectedIndex+'].planId"]').val();

		window.close();
	}
	

</script>
</head>
<body>
	<table>
		<tr>
			<th>選取</th>
			<th>專案名稱</th>
			<th>加工步驟</th>
			<th>危害類別</th>
			<th>危害描述</th>
			<th>選取</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ha" items="${lsts}" varStatus="status">
			<tr>
				<td><input type="radio" id="haId" name="haId" value="${ha.haId}"></td>
				<td>${ha.plan.pName}</td>
				<td><input type="text" id="procStep" name="lsts[${status.index}].procStep" value="${ha.procStep}"></td>
				<td><c:choose><c:when test="${ccp.ha.pHa == 'phy'}">物理性</c:when>
				<c:when test="${ccp.ha.pHa == 'chem'}">化學性</c:when>
				<c:otherwise>生物性</c:otherwise></c:choose></td>
				<td><input type="text" id="pHa" name="lsts[${status.index}].haDesc" value="${ha.haDesc}">
				<input type="hidden" id="planId" name="lsts[${status.index}].planId" value="${ha.planId}"></td>				
				<td><input type="button" value="選取" onclick="sendData()"></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

