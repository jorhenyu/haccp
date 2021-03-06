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
	<form:form  id="form1" name="form1" action="${pageContext.request.contextPath}/ha/doAdd.do"
		method="post">
		<table>
		    <tr>
			<th>專案ID</th>
				<td><input type="text" id="planId"  name="planId" value="${ha.planId}"
					readonly><input id="openWin" name="openWin" type="button" value="選取"></td>
			</tr>
			<tr>
				<th>加工步驟名稱</th>
				<td><textarea name="procStep">${ha.procStep}</textarea></td>
			</tr>			
			<tr>
				<th>潛在危害</th>
				<td>
					<select id="pHa" name="pHa">
						<option value="phy">物理性</option>
						<option value="chem">化學性</option>
						<option value="bio">生物性</option>
				    </select>
				</td>
			</tr>
			<tr>
				<th>潛在危害描述</th>
				<td><textarea name="haDesc">${ha.haDesc}</textarea></td>
			</tr>
			<tr>
				<th>影響產品安全嗎</th>
				<td>
					<select id="issafe" name="issafe">
					    <option value="">--請選擇----</option>
						<option value="Y">Y</option>
						<option value="N">N</option>						
				    </select>					
				</td>
			</tr>
			<tr>
				<th>判定左欄之理由</th>
				<td><textarea name="reason">${ha.reason}</textarea></td>
			</tr>
			<tr>
				<th>預防措施</th>
				<td><textarea name="pMeas">${ha.pMeas}</textarea></td>
			</tr>
			<tr>
				<th></th>
				<th><input id="add" name="add" type="button" value="新增" /></th>
			</tr>
		</table>
	</form:form>

</body>