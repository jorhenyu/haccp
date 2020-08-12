<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>mydata</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script>
$(document).ready(function() {
		$("#startdatepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	
		
		$("#enddatepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});

		
		   $("#add").bind('click', function() {
            	 
	        	 $('#form1').submit();
	      });  
    });
	

</script>
</head>
<body>
	<table>
		<tr>
			<th colspan="9" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/plan/add.do'"
				value="新增"></input></th>
				</tr>
				<tr>
				<th colspan="9">
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/plan/queryByparm.do"
				method="post">
				業別:	<select name="qCatId">
				<option value="">---請選擇-----</option>
						<c:forEach var="o" items="${options}">
							<option value="${o.optionKey}">${o.optionValue}</option>
						</c:forEach>
				</select>
			專案名稱:<input type="text" name="qPname" id="qPname" value=""	>
			專案狀態:<select name="qPstatus">									
						<c:forEach var="o" items="${planStatus}">
							<option value="${o.optionKey}">${o.optionValue}</option>
						</c:forEach>			
				</select><br>
			
			開始日期:<input type="text" name="rDateStart" id="startdatepicker" value=""
				readonly>
			結束日期:<input type="text" name="rDateEnd" id="enddatepicker" value=""
				readonly>
			<br>第<input type="text" name="pageNum" id="pageNum" value="${pageInfo.pageNum == null?1:pageInfo.pageNum}">頁
				一頁<input type="text" name="pageSize" id="pageSize" value="${pageInfo.pageSize == null?5:pageInfo.pageSize}">筆
			
					<input id="add" name="add" type="button" value="查詢">
			</form>
		</th>
		</tr>
		<tr>
			<th></th>
			<th></th>
			<th>計劃書名稱</th>
			<th>業別</th>
			<th>製作者</th>
			<th>計劃書狀態</th>

		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="plan" items="${lsts}">
			<tr>			
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
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>

