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
	<form action="${pageContext.request.contextPath}/team/doAdd.do"
		method="post">
		<table>
			<tr>
				<th>成員</th>
				<td><input type="text" name="mber" value="${team.mber}">					
				<form:errors path="teamDetail.planId" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<th>職稱</th>
				<td><input type="text" name="pos" value="${team.pos}">
					</td>
			</tr>
			<tr>
				<th>專長</th>
				<td><input type="text" name="skill" value="${team.skill}">
					</td>
			</tr>
			<tr>
				<th><input type="reset" value="清空"></th>
				<th><input type="submit" value="新增"></th>
			</tr>
		</table>
	</form>
	<script>
		var selValue = '${user.uPosi}';
		$('#uPosi option[value=' + selValue + ']')
				.attr('selected', true);
	</script>
</body>