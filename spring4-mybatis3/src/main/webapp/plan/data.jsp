<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
</head>
<body>
	<table>
		<tr>
			<th colspan="9" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/plan/add.do'"
				value="新增"></input></th>
		</tr>
		<tr>
			<th>計劃書名稱</th>
			<th>業別</th>
			<th>製作者</th>
			<th>計劃書狀態</th>
			<th>協同作業密碼</th>
			<th>擁有者</th>
			<th>紀錄日期</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="plan" items="${lsts}">
			<tr>
				<td>${plan.pName}</td>
				<td>
						<c:forEach items="${options}" var="o">
							<c:choose>
								<c:when test="${plan.cId == o.optionKey}">									
										${o.optionValue}
								</c:when>

							</c:choose>
						</c:forEach>
				</td>
				<td>${plan.maker}</td>
				
				<td>
						<c:forEach items="${planStatus}" var="o">
							<c:choose>
								<c:when test="${plan.pStatus == o.optionKey}">									
										${o.optionValue}
								</c:when>								
							</c:choose>
						</c:forEach>
				
				
				</td>
				<td>${plan.cwPw}</td>
				<td>${plan.rder}</td>
				<td>${plan.rDate}</td>
				<td>
					<form action="${pageContext.request.contextPath }/plan/update.do"
						method="post">
						<input type="hidden" name="pId" value="${plan.pId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/plan/del.do"
						method="post">
						<input type="hidden" name="pId" value="${plan.pId}"> <input
							type="submit" value="刪除">
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>

