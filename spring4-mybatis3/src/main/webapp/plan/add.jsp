<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>用戶註冊</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<script src="../js/jquery.min.js"></script>
</head>

<body style="text-align: center;">
	<form action="${pageContext.request.contextPath}/plan/doAdd.do"
		method="post">
		<table>
			<tr>
				<th>計劃書名稱</th>
				<td><input type="text" name="pName" value="${plan.pName}">
				</td>
			</tr>
			<tr>
				<th>業別</th>
				<td><select name="cId">
						<c:forEach var="o" items="${options}">
							<option value="${o.optionKey}">${o.optionValue}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>製作者</th>
				<td><input type="text" name="maker" value="${plan.maker}">
				</td>
			</tr>
			<tr>
				<th>計劃書狀態</th>
				<td>				
				<select name="pStatus">
						<c:forEach var="o" items="${planStatus}">
							<option value="${o.optionKey}">${o.optionValue}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>協同作業密碼</th>
				<td><input type="text" name="cwPw" value="${plan.cwPw}">
				</td>
			</tr>
			<tr>
				<th><input type="reset" value="清空"></th>
				<th><input type="submit" value="新增"></th>
			</tr>
		</table>
	</form>

</body>