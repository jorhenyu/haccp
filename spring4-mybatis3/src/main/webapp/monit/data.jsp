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
				onclick="javascript:location.href='${pageContext.request.contextPath}/monit/add.do'"
				value="新增">
				<input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/monit/queryChart.do'"
				value="製作折線圖"></input></th>
		</tr>
		<tr>
				<th colspan="14">
				<form id="form1" name="form1" action="${pageContext.request.contextPath }/monit/queryByparm.do"
				method="post">
				業別:	<select name="qCatId">
				<option value="">---請選擇-----</option>
						<c:forEach var="o" items="${options}">
							<option value="${o.optionKey}">${o.optionValue}</option>
						</c:forEach>
				</select>
			專案名稱:<input type="text" name="qPname" id="qPname" value=""	>
			管制類別:<select id="qtypeReg" name="qtypeReg">
						<option value="ul">上限</option>
						<option value="ll">下限</option>
						<option value="ulAndll">上限與下限</option>
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
			<th>批次編號</th>
			<th>管制類型</th>
			<th>管制上限</th>
			<th>管制下限</th>
			<th>監測值</th>
			<th>單位</th>
			<th>監控狀況</th>
			<th>日期</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="monit" items="${lsts}">
			<tr>
				<td>
					<form
						action="${pageContext.request.contextPath }/monit/update.do"
						method="post">
						<input type="hidden" name="monitId" value="${monit.monitId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/monit/del.do"
						method="post">
						<input type="hidden" name="monitId" value="${monit.monitId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
			   <td>${monit.plan.pName}</td>	
			   		
				<td>${monit.ha.procStep}</td>
				
				<td><c:choose><c:when test="${monit.ha.pHa == 'phy'}">物理性</c:when>
				<c:when test="${monit.ha.pHa == 'chem'}">化學性</c:when>
				<c:otherwise>生物性</c:otherwise></c:choose>
				</td>
				<td>${monit.ha.haDesc}</td>
				
				<td>${monit.bNum}</td>
				<td>
				<c:choose><c:when test="${monit.typeReg == 'ul'}">	
                                            上限				
				</c:when>
				<c:when test="${monit.typeReg == 'll'}">
				下限
				</c:when>
				<c:otherwise>
				上限與下限
				</c:otherwise>
				</c:choose>	
				</td>				
				<td>${monit.ucl}</td>	
				<td>${monit.lcl}</td>	
				<td>${monit.mVal}</td>
				<td>${monit.unit}</td>
				<td>
				<c:choose><c:when test="${monit.typeReg == 'ul'}">					
                    <c:if test="${monit.ucl*1 < monit.mVal}">異常 </c:if>
                    <c:if test="${monit.ucl*1 > monit.mVal}">正常 </c:if>                                                  
				</c:when>
				<c:when test="${monit.typeReg == 'll'}">
				     <c:if test="${monit.lcl*1 < monit.mVal}">正常 </c:if>
                    <c:if test="${monit.lcl*1 > monit.mVal}">異常 </c:if> 
				</c:when>
				<c:otherwise>
					<c:if test="${monit.lcl*1 < monit.mVal && monit.ucl*1 > monit.mVal}">正常 </c:if>
					<c:if test="${monit.lcl*1 > monit.mVal || monit.ucl*1 < monit.mVal}">異常 </c:if>                
				</c:otherwise>
				</c:choose>
					
				</td>
				<td>${monit.rDate}</td>
						
			</tr>
		</c:forEach>
	</table>

</body>
</html>

