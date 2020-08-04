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
				onclick="javascript:location.href='${pageContext.request.contextPath}/pc/add.do'"
				value="新增"></input></th>
		</tr>
		<tr>
			<th>修改</th>
			<th>刪除</th>
			<th>專案名稱名稱</th>
			<th>產品預定用法及用途</th>			
			<th>銷售地點</th>
			<th>消費對象</th>
			<th>注意事項</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="pc" items="${lsts}">
			<tr>
				<td>
					<form
						action="${pageContext.request.contextPath }/pc/update.do"
						method="post">
						<input type="hidden" name="pcId" value="${pc.pcId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/pc/del.do"
						method="post">
						<input type="hidden" name="pcId" value="${pc.pcId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
			   <td>${pc.plan.pName}</td>			
				<td><textarea name="pUse" readonly>${pc.pUse}</textarea></td>
				<td><textarea name="sSpot" readonly>${pc.sSpot}</textarea></td>
				<td><textarea name="cObj" readonly>${pc.cObj}</textarea></td>
				<td><textarea name="notes" readonly>${pc.notes}</textarea></td>		
			</tr>
		</c:forEach>
	</table>

</body>
</html>

