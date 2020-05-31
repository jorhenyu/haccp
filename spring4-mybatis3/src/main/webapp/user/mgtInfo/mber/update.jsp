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
	<form action="${pageContext.request.contextPath}/user/mber/doUpdate.do"
		method="post">
		<table>
			<tr>
				<th>用戶名</th>
				<td><input type="text" name="uName" value="${user.uName}">
					<input type="hidden" name="uId" value="${user.uId}"> <form:errors
						path="userDetail.uName" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<th>密碼</th>
				<td><input type="password" name="uPw" value="${user.uPw}">
					<form:errors path="userDetail.uPw" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<th>郵箱</th>
				<td><input type="text" name="uEmail" value="${user.uEmail}">
					<form:errors path="userDetail.uEmail" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<th>職位</th>
				<td><input type="text" name="uPosi" value="${user.uPosi}"></td>
			<tr>
				<th><input type="reset" value="清空"></th>
				<th><input type="submit" value="更新"></th>
			</tr>
		</table>
	</form>

</body>