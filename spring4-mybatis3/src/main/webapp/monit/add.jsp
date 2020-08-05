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
    	window.open("${pageContext.request.contextPath}/ccp/query.do", null, "width=600px,height=400px");
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
	<form:form  id="form1" name="form1" action="${pageContext.request.contextPath}/monit/doAdd.do"
		method="post">
		<table>
		    <tr>
			<th>重要管制點(CCP)</th>
				<td><input type="text" id="procStep"  name="procStep" value="${monit.ha.procStep}"
					readonly><input id="openWin" name="openWin" type="button" value="選取">
					<input type="hidden" id="haId"  name="haId" value="${monit.haId}">
					<input type="hidden" id="planId"  name="planId" value="${monit.planId}"></td>
			</tr>			
			<tr>
				<th>顯著危害類別</th>
				<td>
                  <textarea id="pHa" name="pHa">${monit.ha.pHa}</textarea>
				</td>
			</tr>
			<tr>
				<th>顯著危害描述</th>
				<td><textarea id="haDesc" name="haDesc">${monit.ha.haDesc}</textarea></td>
			</tr>
			<tr>
				<th>批次編號</th>
				<td><textarea name="bNum">${monit.bNum}</textarea></td>
			</tr>

			<tr>
				<th>管制類型</th>
				<td><textarea name="typeReg">${monit.typeReg}</textarea></td>
			</tr>
			<tr>
				<th>管制上限</th>
				<td><textarea name="ucl">${monit.ucl}</textarea></td>
			</tr>
						<tr>
				<th>管制下限</th>
				<td><textarea name="lcl">${monit.lcl}</textarea></td>
			</tr>
						<tr>
				<th>監測值</th>
				<td><textarea name="mVal">${monit.mVal}</textarea></td>
			</tr>
		    <tr>
				<th>單位</th>
				<td><textarea name="unit">${monit.unit}</textarea></td>
			</tr>
			<tr>			
				<th></th>
				<th><input id="add" name="add" type="button" value="新增" /></th>
			</tr>
		</table>
	</form:form>

</body>