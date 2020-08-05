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
	<form:form  id="form1" name="form1" action="${pageContext.request.contextPath}/haccp/doUpdate.do"
		method="post">
		<table>
		    <tr>
			<th>重要管制點(CCP)</th>
				<td><input type="text" id="procStep"  name="procStep" value="${haccp.ha.procStep}"
					readonly><input id="openWin" name="openWin" type="button" value="選取">
					<input type="hidden" id="haId"  name="haId" value="${haccp.haId}">
					<input type="hidden" id="planId"  name="planId" value="${haccp.planId}">
					<input type="hidden" id="haccpId"  name="haccpId" value="${haccp.haccpId}"></td>
			</tr>			
			<tr>
				<th>顯著危害類別</th>
				<td>
                  <textarea id="pHa" name="pHa">${haccp.ha.pHa}</textarea>
				</td>
			</tr>
			<tr>
				<th>顯著危害描述</th>
				<td><textarea id="haDesc" name="haDesc">${haccp.ha.haDesc}</textarea></td>
			</tr>
			<tr>
				<th>管制界限</th>
				<td><textarea name="cLimit">${haccp.cLimit}</textarea></td>
			</tr>

			<tr>
				<th>監測項目</th>
				<td><textarea name="mItm">${haccp.mItm}</textarea></td>
			</tr>
			<tr>
				<th>監測方法</th>
				<td><textarea name="mMd">${haccp.mMd}</textarea></td>
			</tr>
						<tr>
				<th>監測頻率</th>
				<td><textarea name="mFre">${haccp.mFre}</textarea></td>
			</tr>
						<tr>
				<th>監測執行人</th>
				<td><textarea name="mPrin">${haccp.mPrin}</textarea></td>
			</tr>
		    <tr>
				<th>矯正措施</th>
				<td><textarea name="cMeas">${haccp.cMeas}</textarea></td>
			</tr>
									<tr>
				<th>紀錄</th>
				<td><textarea name="record">${haccp.record}</textarea></td>
			</tr>
									<tr>
				<th>確認</th>
				<td><textarea name="confirm">${haccp.confirm}</textarea></td>
			</tr>
			<tr>
				<th></th>
				<th><input id="add" name="add" type="button" value="更新" /></th>
			</tr>
		</table>
	</form:form>

</body>