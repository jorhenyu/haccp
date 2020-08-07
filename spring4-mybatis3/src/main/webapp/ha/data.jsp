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
			<th colspan="16" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ha/add.do'"
				value="新增"></input><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ha/report.do'"
				value="匯出"></input></th>
		</tr>
		<tr>
			<th>修改</th>
			<th>刪除</th>
			<th>專案名稱名稱</th>
			<th>加工步驟名稱</th>			
			<th>潛在危害</th>
			<th>潛在危害描述</th>
			<th>影響產品安全嗎</th>
			<th>判定左欄之理由</th>
			<th>預防措施</th>
			<th>本步驟是否為重要管制點</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ha" items="${lsts}">
			<tr>
				<td>
					<form
						action="${pageContext.request.contextPath }/ha/update.do"
						method="post">
						<input type="hidden" name="haId" value="${ha.haId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/ha/del.do"
						method="post">
						<input type="hidden" name="haId" value="${ha.haId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
			   <td>${ha.plan.pName}</td>			
				<td><textarea name="procStep" readonly>${ha.procStep}</textarea></td>
				<td><c:choose><c:when test="${ha.pHa == 'phy'}">物理性</c:when>
				<c:when test="${ha.pHa == 'chem'}">化學性</c:when>
				<c:otherwise>生物性</c:otherwise></c:choose>
				</td>
				<td><textarea name="haDesc" readonly>${ha.haDesc}</textarea></td>
				<td><textarea name="issafe" readonly>${ha.issafe}</textarea></td>	
				<td><textarea name="reason" readonly>${ha.reason}</textarea></td>
				<td><textarea name="pMeas" readonly>${ha.pMeas}</textarea></td>	
				<td><textarea name="ccp" readonly>${ha.ccp.ccp}</textarea>
					</form></td>		
			</tr>
		</c:forEach>
	</table>

</body>
</html>

