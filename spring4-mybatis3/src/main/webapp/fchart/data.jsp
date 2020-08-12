<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
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
			<th scope="row" colspan="16" ><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/fchart/add.do'"
				value="新增"></input><br></th>
			
		</tr>
		<tr>
				<th colspan="9">
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/fchart/queryByparm.do"
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
			
					<input id="add" name="add" type="button" value="查詢">
			</form>
		</th>
		</tr>
		<tr>
			<th>修改</th>
			<th>刪除</th>
			<th>專案名稱名稱</th>
			<th>檔案名稱</th>			
			<th>下載</th>
			<th>注意事項</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="file" items="${lsts}">
			<tr>
			<c:choose><c:when test="${file.plan.pStatus == 'fprivate'}">
				<td>
					<form
						action="${pageContext.request.contextPath }/fchart/update.do"
						method="post">
						<input type="hidden" name="fileId" value="${file.fileId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/fchart/del.do"
						method="post">
						<input type="hidden" name="fileId" value="${file.fileId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
				<td>
                <form method="post" action="${pageContext.request.contextPath}/fchart/doUpload.do"
			enctype="multipart/form-data">
			<input type="file" name="file" />
			<input type="hidden" name="fileId" value="${file.fileId}">
			<button type="submit">上傳</button>
		</form>
				</td>
								</c:when>
				<c:when test="${file.plan.pStatus == 'fcowork'}">
								<td></td><td></td></c:when>
				<c:otherwise><td>
					</td><td></td></c:otherwise></c:choose>
			   <td>${file.plan.pName}</td>			
				<td>${file.fileNm}</td>
				<td><a href="${pageContext.request.contextPath }/fchart/download.do?filename=${file.download}">${file.fileNm}</a></td>
				<td>${file.notes}</td>						
			</tr>
		</c:forEach>
	</table>

</body>
</html>

