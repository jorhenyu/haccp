<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示team info</title>
<link rel="stylesheet" type="text/css" href="../../css/css-table.css">
</head>
<body>
	<table>
		<tr>
			<th>成員</th>
			<th>職稱</th>
			<th>專長</th>
			<th>新增</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%--遍歷lstTeams集合中的Team物件 --%>
		<c:forEach var="team" items="${lstTeams}">
			<tr>
				<td>${team.mber}</td>
				<td>${team.pos}</td>
				<td>${team.skill}</td>
				<td>
					<form action="${pageContext.request.contextPath }/team/add.do"
						method="post">
						<input type="hidden" name="planId" value="${team.planId}">
						<input type="submit" value="新增">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/team/update.do"
						method="post">
						<input type="hidden" name="planId" value="${team.planId}">
						<input type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/team/del.do"
						method="post">
						<input type="hidden" name="planId" value="${team.planId}">
						<input type="submit" value="刪除">
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>

