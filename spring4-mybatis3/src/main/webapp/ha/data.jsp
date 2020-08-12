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
				onclick="javascript:location.href='${pageContext.request.contextPath}/ha/add.do'"
				value="新增"></input><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ha/report.do'"
				value="匯出"></input></th>
		</tr>
				<tr>
				<th colspan="10">
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/ha/queryByparm.do"
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
			<th>修改</th>
			<th>刪除</th>
			<th width="107" valign="top">專案名稱</th>
			<th width="107" valign="top"><p align="center">加工步驟名稱 </p></th>
    <th><p align="center">潛在危害</p></th>
      <th width="161" valign="top"><p align="center">潛在危害描述</p></th>
    <th width="156" valign="top"><p>影響產品安全嗎</p></th>
    <th width="213" valign="top"><p align="center">判定左欄之理由</p></th>
    <th width="226" valign="top"><p align="center">預防措施 </p></th>
    <th width="127" valign="top"><p align="center">本步驟是 <br>
      重要管制點(CCP#)</p></th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ha" items="${lsts}">
			<tr>
			<c:choose><c:when test="${ha.plan.pStatus == 'fprivate'}">
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
					</c:when>
				<c:when test="${ha.plan.pStatus == 'fcowork'}">
								<td>
					<form action="${pageContext.request.contextPath }/ha/cowork.do"
						method="post">
						<input type="hidden" name="haId" value="${ha.haId}"> <input
							type="submit" value="協作">
					</form>
				</td><td></td></c:when>
				<c:otherwise><td>
					</td><td></td></c:otherwise></c:choose>
			   <td>${ha.plan.pName}</td>			
				<td>${ha.procStep}</td>
				<td><c:choose><c:when test="${ha.pHa == 'phy'}">物理性</c:when>
				<c:when test="${ha.pHa == 'chem'}">化學性</c:when>
				<c:otherwise>生物性</c:otherwise></c:choose>
				</td>
				<td>${ha.haDesc}</td>
				<td>${ha.issafe}</td>	
				<td>${ha.reason}</td>
				<td>${ha.pMeas}</td>	
				<td>${ha.ccp.ccp}</td>		
			</tr>
		</c:forEach>
	</table>
</body>
</html>

