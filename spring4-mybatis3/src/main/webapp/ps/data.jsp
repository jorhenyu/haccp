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
			<th colspan="16" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ps/add.do'"
				value="新增"></input><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ps/queryPro.do'"
				value="匯出"></input></th>
		</tr>
				<tr>
				<th colspan="9">
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/ps/queryByparm.do"
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
			<th></th>
			<th></th>
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
				<c:choose><c:when test="${ps.plan.pStatus == 'fprivate'}">
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
				</c:when>
				<c:when test="${ps.plan.pStatus == 'fcowork'}">
								<td>
					<form action="${pageContext.request.contextPath }/ps/cowork.do"
						method="post">
						<input type="hidden" name="psId" value="${ps.psId}"> <input
							type="submit" value="協作">
					</form>
				</td><td></td></c:when>
				<c:otherwise><td>
					</td><td></td></c:otherwise></c:choose>
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

