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
				onclick="javascript:location.href='${pageContext.request.contextPath}/ps/add.do'"
				value="新增"></input><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ps/queryPro.do'"
				value="匯出"></input></th>
		</tr>
		<tr>
			<th>修改</th>
			<th>刪除</th>
			<th>專案名稱名稱</th>
			<th>產品類別</th>			
			<th>產品名稱</th>
			<th>主要原料</th>
			<th>其他原料</th>
			<th>食品添加物</th>
			<th>加工助劑</th>
			<th>物料</th>
			<th>產品特性</th>
			<th>加工方式</th>
			<th>包裝方式及說明</th>
			<th>貯存及運輸方法</th>
			<th>架售期</th>
			<th>注意事項</th>

		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ps" items="${lsts}">
			<tr>
				<td>
					<form
						action="${pageContext.request.contextPath }/ps/update.do"
						method="post">
						<input type="hidden" name="psId" value="${ps.psId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/ps/del.do"
						method="post">
						<input type="hidden" name="psId" value="${ps.psId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
			   <td>${ps.plan.pName}</td>
				<td>
						<c:forEach items="${options}" var="o">
							<c:choose>
								<c:when test="${ps.cId == o.optionKey}">									
										${o.optionValue}
								</c:when>
							</c:choose>
						</c:forEach>
				</td>
				
				<td><textarea name="pName" readonly>${ps.pName}</textarea></td>
				<td><textarea name="matrlM" readonly>${ps.matrlM}</textarea></td>
				<td><textarea name="matrlO" readonly>${ps.matrlO}</textarea></td>
				<td><textarea name="fdAdd" readonly>${ps.fdAdd}</textarea></td>
				<td><textarea name="prcsAids" readonly>${ps.prcsAids}</textarea></td>
				<td><textarea name="matrl" readonly>${ps.matrl}</textarea></td>
				<td><textarea name="pdtFt" readonly>${ps.pdtFt}</textarea></td>
				<td><textarea name="pdtMd" readonly>${ps.pdtMd}</textarea></td>
				<td><textarea name="pmDesc" readonly>${ps.pmDesc}</textarea></td>
				<td><textarea name="stMd" readonly>${ps.stMd}</textarea></td>
				<td><textarea name="sLife" readonly>${ps.sLife}</textarea></td>
				<td><textarea name="notes" readonly>${ps.notes}</textarea></td>
				



			</tr>
		</c:forEach>
	</table>

</body>
</html>

