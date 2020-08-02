<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>用戶註冊</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<script src="../js/jquery.min.js"></script>
</head>

<body style="text-align: center;">
	<form action="${pageContext.request.contextPath}/team/doUpdate.do"
		method="post">
		<table>
			<tr>
				<th>專案名稱</th>
				<td>${team.plan.pName}</td>
			</tr>
			<tr>
				<th>成員</th>
				<td><textarea name="mber">${team.mber}</textarea>
					<input type="hidden" name="planId" value="${team.planId}">
					<input type="hidden" name="teamId" value="${team.teamId}"></td>
			</tr>
			<tr>
				<th>職稱</th>
				<td><textarea name="pos">${team.pos}</textarea></td>
			</tr>
			<tr>
				<th>職責</th>
				<td><textarea name="duty">${team.duty}</textarea></td>
			</tr>
			<tr>
				<th>學歷</th>
				<td><textarea name="bg">${team.bg}</textarea></td>
			</tr>
			<tr>
				<th>HACCP專業訓練及經驗</th>
				<td><textarea name="skill">${team.skill}</textarea></td>
			</tr>
			<tr>
				<th><input type="reset" value="清空"></th>
				<th><input type="submit" value="更新"></th>
			</tr>
		</table>
	</form>

</body>