<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>用戶註冊</title>
<link rel="stylesheet" type="text/css" href="../../css/css-table.css">
<script src="../../js/jquery.min.js"></script>
</head>

<body style="text-align: center;">
	<form action="${pageContext.request.contextPath}/team/doUpdate.do"
		method="post">
		<table>
			<tr>
				<th>用戶名</th>
				<td><input type="text" name="mber" value="${team.mber}">
					<input type="hidden" name="planId" value="${team.planId}"></td>
			</tr>
			<tr>
				<th>密碼</th>
				<td><input type="text" name="pos" value="${team.pos}"></td>
			</tr>
			<tr>
				<th>郵箱</th>
				<td><input type="text" name="skill" value="${team.skill}"></td>
			</tr>
			<tr>
				<th><input type="reset" value="清空"></th>
				<th><input type="submit" value="更新"></th>
			</tr>
		</table>
	</form>

</body>