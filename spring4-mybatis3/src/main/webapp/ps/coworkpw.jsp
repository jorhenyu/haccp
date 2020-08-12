<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
<script src="../js/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$(".tip").hide();
		$("#add").bind('click', function() {
			
			 var cwPwd = $(`#cwPwd`).val();
			 var myCwPw = $(`#myCwPw`).val();
			// alert("==cwPwd==="+cwPwd);
			 //alert("==myCwPw==="+myCwPw);
		        if (cwPwd == "") {		        	
		        	$(".tip").html("密碼不得為空！");
		        	$(".tip").show();
		            return false;
		        } else if (cwPwd != myCwPw) {
		        	$(".tip").html("密碼不正確！");
		        	$(".tip").show();
		            return false;
		        } else if(cwPwd == myCwPw){
		        	$('#form1').submit();
		        } else {
		            return false;
		        }

			
		});
	});

</script>
</head>
<body>
	<table>
		<tr>

			<th>協作密碼驗證</th>
			<th></th>
		</tr>
		<tr>

			<td><form id="form1" name="form1" action="${pageContext.request.contextPath }/ps/update.do"
					method="post">
					<input type="text" id="cwPwd" name="cwPwd" value=""><input id="add" name="add" type="button" value="驗證">
					<input type="hidden" id="myCwPw" name="myCwPw" value="${ps.plan.cwPw}">
					<input type="hidden" id="psId" name="psId" value="${ps.psId}">
					<span class="tip" style="color: red;"></span>					 
				</form></td>
		</tr>
	</table>
</body>
</html>

