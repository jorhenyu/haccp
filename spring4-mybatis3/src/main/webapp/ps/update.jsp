<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>用戶註冊</title>
<link rel="stylesheet" type="text/css" href="../../css/css-table.css">
</head>

<body style="text-align: center;">
	<form action="${pageContext.request.contextPath}/user/cat/doUpdate.do"
		method="post">
		<table>
			<tr>
				<th>類別ID</th>
				<td><input type="hidden" name="cId" value="${cat.cId}">${cat.cId}</td>
			</tr>
			<tr>
				<th>類別名稱</th>
				<td><input type="text" name="cName" value="${cat.cName}"></td>
			<tr>
				<th><input type="reset" value="清空"></th>
				<th><input type="submit" value="更新"></th>
			</tr>
		</table>
	</form>

</body>