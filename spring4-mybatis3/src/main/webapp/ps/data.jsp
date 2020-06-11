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
				onclick="javascript:location.href='${pageContext.request.contextPath}/user/cat/add.do'"
				value="新增"></input></th>
		</tr>
		<tr>
			<th>業別id</th>
			<th>業別名稱</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="cat" items="${lstCats}">
			<tr>
				<td>${cat.cId}</td>
				<td>${cat.cName}</td>

				<td>
					<form
						action="${pageContext.request.contextPath }/user/cat/update.do"
						method="post">
						<input type="hidden" name="cId" value="${cat.cId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/user/cat/del.do"
						method="post">
						<input type="hidden" name="cId" value="${cat.cId}"> <input
							type="submit" value="刪除">
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>

