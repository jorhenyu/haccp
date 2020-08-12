<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>無標題文件</title>
<link rel="stylesheet" type="text/css" href="../../css/css-table.css">
<script src="../../js/jquery.min.js"></script>
</head>

<body>
		<table>
			<tr>
				<th>用戶名</th>
				<td><input type="text" name="uName" value="${user.uName}">
					<form:errors path="userDetail.uName" cssStyle="color:red"></form:errors>
				</td>
			</tr>
			<tr>
				<th>密碼</th>
				<td><input type="password" name="uPw" value="${user.uPw}">
					</td>
			</tr>
			<tr>
				<th>郵箱</th>
				<td><input type="text" name="uEmail" value="${user.uEmail}">
					<form:errors path="userDetail.uEmail" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<th>身分</th>
				<td><select id="uPosi" name="uPosi">
						<option value="mg">管理者</option>
						<option value="mber" selected>會員</option>
				</select></td>
			<tr>
				<th>積分</th>
				<td>${user.rdPot}</td>
			</tr>
		</table>
			<script>
		var selValue = '${user.uPosi}';
		$('#uPosi option[value=' + selValue + ']')
				.attr('selected', true);
	</script>
</body>
</html>
