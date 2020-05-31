<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../../css/css-table.css">
</head>
<body>
	<table>
		<tr>
			<th colspan="6" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/user/mber/add.do'"
				value="新增"></input></th>
		</tr>
		<tr>
			<th>用戶名</th>
			<th>用戶密碼</th>
			<th>用戶郵箱</th>
			<th>用戶職位</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="user" items="${lstUsers}">
			<tr>
				<td>${user.uName}</td>
				<td>${user.uPw}</td>
				<td>${user.uEmail}</td>
				<td>${user.uPosi}</td>

				<td>
					<form
						action="${pageContext.request.contextPath }/user/mber/update.do"
						method="post">
						<input type="hidden" name="uId" value="${user.uId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/user/mber/del.do"
						method="post">
						<input type="hidden" name="uId" value="${user.uId}"> <input
							type="submit" value="刪除">
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>

