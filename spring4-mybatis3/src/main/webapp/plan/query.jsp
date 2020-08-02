<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<script>
	//將radio選取選項，帶回母視窗頁面
	function sendData() {
		var checkedText = document.querySelector('input[type="radio"]:checked').value;
		window.opener.document.getElementById("planId").value = checkedText;
		window.close();
	}
</script>
</head>
<body>
	<table>
		<tr>
			<th>選取</th>
			<th>計劃書名稱</th>
			<th>選取</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="plan" items="${lsts}">
			<tr>
				<td><input type="radio" name="planId" value="${plan.pId}"></td>
				<td>${plan.pName}</td>
				<td><input type="button" value="選取" onclick="sendData()">

				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

