<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示team info</title>
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
				onclick="javascript:location.href='${pageContext.request.contextPath}/team/add.do'"
				value="新增"></input>	<input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/team/queryPro.do'"
				value="匯出"></input></th>
		</tr>
		<tr>
				<th colspan="9">
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/team/queryByparm.do"
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
		    <th>專案名稱</th>
			<th>成員</th>
			<th>職稱</th>
			<th>職責</th>	
			<th>學歷</th>	
			<th>HACCP專業訓練及經驗</th>			

		</tr>
		<%--遍歷lstTeams集合中的Team物件 --%>
		<c:forEach var="team" items="${lstTeams}">
			<tr>
				<td>
					<form action="${pageContext.request.contextPath }/team/update.do"
						method="post">
						<input type="hidden" name="teamId" value="${team.teamId}">
						<input type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/team/del.do"
						method="post">
						<input type="hidden" name="teamId" value="${team.teamId}">
						<input type="submit" value="刪除">
					</form>
				</td>
			    <td><textarea readonly>${team.plan.pName}</textarea></td>
				<td><textarea readonly>${team.mber}</textarea></td>
				<td><textarea readonly>${team.pos}</textarea></td>
				<td><textarea readonly>${team.duty}</textarea></td>
				<td><textarea readonly>${team.bg}</textarea></td>
				<td><textarea readonly>${team.skill}</textarea></td>


			</tr>
		</c:forEach>
	</table>

</body>
</html>

