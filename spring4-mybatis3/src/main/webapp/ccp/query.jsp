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
	   window.opener.document.getElementById("haDesc").value=$('input[name="lsts['+selectedIndex+'].haDesc"]').val();
	   window.opener.document.getElementById("pHa").value=$('input[name="lsts['+selectedIndex+'].pHa"]').val();

		window.close();
	}
	

</script>
</head>
<body>
	<table>
	<tr><th>專案名稱</th>
		<th colspan="16" scope="row">					
		          <form
						action="${pageContext.request.contextPath }/ccp/query.do"
						method="post">
						<input type="text" name="pName" value=""> <input
							type="submit" value="查詢">
					</form></th>
		</tr>
		<tr>
			<th>選取</th>
			<th>專案名稱</th>
			<th>加工步驟</th>
			<th>危害類別</th>
			<th>危害描述</th>
			<th>選取</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ccp" items="${lsts}" varStatus="status">
			<tr>
				<td><input type="radio" id="haId" name="haId" value="${ccp.haId}"></td>
				<td>${ccp.plan.pName}</td>
				<td><input type="text" id="procStep" name="lsts[${status.index}].procStep" value="${ccp.ha.procStep}"></td>
				<td><c:choose><c:when test="${ccp.ha.pHa == 'phy'}"><input type="text" id="pHa" name="lsts[${status.index}].pHa" value="物理性"></c:when>
				<c:when test="${ccp.ha.pHa == 'chem'}"><input type="text" id="pHa" name="lsts[${status.index}].pHa" value="化學性"></c:when>
				<c:otherwise><input type="text" id="pHa" name="lsts[${status.index}].pHa" value="生物性"></c:otherwise></c:choose></td>
				<td><input type="text" id="pHa" name="lsts[${status.index}].haDesc" value="${ccp.ha.haDesc}">
				<input type="hidden" id="planId" name="lsts[${status.index}].planId" value="${ccp.planId}"></td>				
				<td><input type="button" value="選取" onclick="sendData()"></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

