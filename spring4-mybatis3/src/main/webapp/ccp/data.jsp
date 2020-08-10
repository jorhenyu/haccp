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
			<th colspan="11" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ccp/add.do?jt=3'"
				value="新增3個問題判定表"></input>
				<input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ccp/add.do?jt=4'"
				value="新增4個問題判定表"></input>	<input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/ccp/report.do'"
				value="匯出"></input></th>
		</tr>
		<tr>
				<th colspan="11">
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/ccp/queryByparm.do"
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
			<c:choose><c:when test="${ccp.plan.pStatus == 'fprivate'}">
				<td>
					<form
						action="${pageContext.request.contextPath }/ccp/update.do"
						method="post">
						<input type="hidden" name="ccpId" value="${ccp.ccpId}"> <input
							type="submit" value="修改"><br>
					</form>									
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/ccp/del.do"
						method="post">
						<input type="hidden" name="ccpId" value="${ccp.ccpId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
				</c:when>
				<c:when test="${ccp.plan.pStatus == 'fcowork'}">
								<td>
					<form action="${pageContext.request.contextPath }/ccp/cowork.do"
						method="post">
						<input type="hidden" name="ccpId" value="${ccp.ccpId}"> <input
							type="submit" value="協作">
					</form>
				</td><td></td></c:when>
				<c:otherwise><td>
					</td><td></td></c:otherwise></c:choose>
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

