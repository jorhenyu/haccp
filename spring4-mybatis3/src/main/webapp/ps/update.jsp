<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>用戶註冊</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<script src="../js/jquery.min.js"></script>

<script>

$(document).ready(function() {
	
    $("#openWin").bind('click', function() {    	
    	window.open("${pageContext.request.contextPath}/plan/query.do", null, "width=600px,height=400px");
    });    

    $("#add").bind('click', function() {
        if($("#planId").val()==""){
            alert("專案ID不存在"); 
            eval("document.form1['planId'].focus()");
        }else{        	 
        	 $('#form1').submit();        	  
        } 
  
      });  
  
})

</script>
</head>

<body>
	<form:form  id="form1" name="form1" action="${pageContext.request.contextPath}/ps/doUpdate.do"
		method="post">
		<table>
		    <tr>
			<th>專案名稱</th>
				<td>${ps.plan.pName}
					<input type="hidden" name="psId" value="${ps.psId}">
					<input type="hidden" name="planId" value="${ps.planId}">
					</td>
			</tr>
			<tr>
				<th>產品類別</th>
				<td>					
                  <select name="cId">
						<c:forEach items="${options}" var="o">
							<c:choose>
								<c:when test="${ps.cId == o.optionKey}">
									<option value="${o.optionKey}" selected="selected">
										${o.optionValue}</option>
								</c:when>
								<c:otherwise>
									<option value="${o.optionKey}">${o.optionValue}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th>產品名稱</th>
				<td><textarea name="pName">${ps.pName}</textarea></td>
			</tr>			
			<tr>
				<th>主要原料</th>
				<td>
					<textarea name="matrlM">${ps.matrlM}</textarea>
				</td>
			</tr>
			<tr>
				<th>其他原料</th>
				<td><textarea name="matrlO">${ps.matrlO}</textarea></td>
			</tr>
			<tr>
				<th>食品添加物</th>
				<td>
					<textarea name="fdAdd">${ps.fdAdd}</textarea>
				</td>
			</tr>
			<tr>
				<th>加工助劑</th>
				<td><textarea name="prcsAids">${ps.prcsAids}</textarea></td>
			</tr>
			<tr>
				<th>物料</th>
				<td>
					<textarea name="matrl">${ps.matrl}</textarea>
				</td>
			</tr>
			<tr>
				<th>產品特性</th>
				<td><textarea name="pdtFt">${ps.pdtFt}</textarea></td>
			</tr>
			<tr>
				<th>加工方式</th>
				<td>
					<textarea name="pdtMd">${ps.pdtMd}</textarea>
				</td>
			</tr>
			<tr>
				<th>包裝方式及說明</th>
				<td><textarea name="pmDesc">${ps.pmDesc}</textarea></td>
			</tr>
			<tr>
				<th>貯存及運輸方法</th>
				<td>
					<textarea name="stMd">${ps.stMd}</textarea>
				</td>
			</tr>
			<tr>
				<th>架售期</th>
				<td><textarea name="sLife">${ps.sLife}</textarea></td>
			</tr>
			<tr>
				<th>注意事項</th>
				<td>
					<textarea name="notes">${ps.notes}</textarea>
				</td>
			</tr>
			<tr>
				<th></th>
				<th><input id="add" name="add" type="button" value="更新" /></th>
			</tr>
		</table>
	</form:form>

</body>