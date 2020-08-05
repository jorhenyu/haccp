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
			<th colspan="11" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ccp/add.do?jt=3'"
				value="新增3個問題判定表">
				<input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ccp/add.do?jt=4'"
				value="新增4個問題判定表"></input></th>
		</tr>
		<tr>
			<th>修改</th>
			<th>刪除</th>
			<th>專案名稱名稱</th>
			<th>加工步驟名稱</th>			
			<th>潛在危害</th>
			<th>潛在危害描述</th>
			<th>Q1</th>
			<th>Q2</th>
			<th>Q3</th>
			<th>Q4</th>
			<th>CCP</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ccp" items="${lsts}">
			<tr>
				<td>
					<form
						action="${pageContext.request.contextPath }/ccp/update.do"
						method="post">
						<input type="hidden" name="ccpId" value="${ccp.ccpId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/ccp/del.do"
						method="post">
						<input type="hidden" name="ccpId" value="${ccp.ccpId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
			   <td>${ccp.plan.pName}</td>			
				<td><textarea name="procStep" readonly>${ccp.ha.procStep}</textarea></td>
				<td><c:choose><c:when test="${ccp.ha.pHa == 'phy'}">物理性</c:when>
				<c:when test="${ccp.ha.pHa == 'chem'}">化學性</c:when>
				<c:otherwise>生物性</c:otherwise></c:choose>
				</td>
				<td><textarea name="haDesc" readonly>${ccp.ha.haDesc}</textarea></td>
				<td>${ccp.q1}</td>	
				<td>${ccp.q2}</td>
				<td>${ccp.q3}</td>	
				<td>${ccp.q4}</td>	
				<td>${ccp.ccp}</td>					
			</tr>
		</c:forEach>
	</table>

</body>
</html>

