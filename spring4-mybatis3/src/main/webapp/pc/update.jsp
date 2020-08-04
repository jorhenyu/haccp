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
	<form:form  id="form1" name="form1" action="${pageContext.request.contextPath}/pc/doUpdate.do"
		method="post">
		<table>
		    <tr>
			<th>專案名稱</th>
				<td>${pc.plan.pName}
					<input type="hidden" name="pcId" value="${pc.pcId}">
					<input type="hidden" name="planId" value="${pc.planId}">
					</td>
			</tr>
			<tr>
				<th>產品預定用法及用途</th>
				<td><textarea name="pUse">${pc.pUse}</textarea></td>
			</tr>			
			<tr>
				<th>銷售地點</th>
				<td>
					<textarea name="sSpot">${pc.sSpot}</textarea>
				</td>
			</tr>
			<tr>
				<th>消費對象</th>
				<td><textarea name="cObj">${pc.cObj}</textarea></td>
			</tr>
			<tr>
				<th>注意事項</th>
				<td>
					<textarea name="notes">${pc.notes}</textarea>
				</td>
			</tr>
			<tr>
				<th></th>
				<th><input id="add" name="add" type="button" value="更新" /></th>
			</tr>
		</table>
	</form:form>

</body>