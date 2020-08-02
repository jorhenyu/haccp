<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示team info</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
</head>
<body>
	<table>
			<tr>
			<th colspan="9" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/team/add.do'"
				value="新增"></input></th>
		</tr>
		<tr>
		    <th>專案名稱</th>
			<th>成員</th>
			<th>職稱</th>
			<th>職責</th>	
			<th>學歷</th>	
			<th>HACCP專業訓練及經驗</th>			
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%--遍歷lstTeams集合中的Team物件 --%>
		<c:forEach var="team" items="${lstTeams}">
			<tr>
			    <td><textarea readonly>${team.plan.pName}</textarea></td>
				<td><textarea readonly>${team.mber}</textarea></td>
				<td><textarea readonly>${team.pos}</textarea></td>
				<td><textarea readonly>${team.duty}</textarea></td>
				<td><textarea readonly>${team.bg}</textarea></td>
				<td><textarea readonly>${team.skill}</textarea></td>
				<td>
					<form action="${pageContext.request.contextPath }/team/update.do"
						method="post">
						<input type="hidden" name="teamId" value="${team.teamId}">
						<input type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/team/del.do"
						method="post">
						<input type="hidden" name="teamId" value="${team.teamId}">
						<input type="submit" value="刪除">
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>

