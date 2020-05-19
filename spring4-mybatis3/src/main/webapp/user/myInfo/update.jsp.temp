<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用戶註冊</title>

<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui.min.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
	

</script>
</head>

<body style="text-align: center;">
	<form action="${pageContext.request.contextPath}/user/doUpdate.do"
		method="post">
		<table width="60%" border="1">
			<tr>
				<td>用戶名</td>
				<td>
					<%--使用EL運算式${}提取存儲在request物件中的formbean物件中封裝的表單數據(formbean.userName)以及錯誤提示消息(formbean.errors.userName)--%>
					<input type="text" name="userName" value="${user.userName}">
					<input type="hidden" name="userId" value="${user.userId}">
					<form:errors path="userDetail.userName" cssStyle="color:red"></form:errors>
				</td>
			</tr>
			<tr>
				<td>密碼</td>
				<td><input type="password" name="userPwd"
					value="${user.userPwd}"> <form:errors
						path="userDetail.userPwd" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<td>郵箱</td>
				<td><input type="text" name="userEmail"
					value="${user.userEmail}"> <form:errors
						path="userDetail.userEmail" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<td>生日</td>
				<td><input type="text" name="userBirthday" id="datepicker"
					value="${user.userBirthday}"> <form:errors
						path="userDetail.userBirthday" cssStyle="color:red"></form:errors>
				</td>
			</tr>
			<tr>
				<td>職位</td>
				<td><select id="userPosition" name="userPosition">				        
						<option value="fc">廠長</option>
						<option value="sm">銷售經理</option>
						<option value="qcs">品保主管</option>
						<option value="fm">廠務經理</option>
						<option value="mw" selected>生産員工</option>
				</select></td>
			<tr>
				<td><input type="reset" value="清空"></td>
				<td><input type="submit" value="更新"></td>
			</tr>
		</table>
	</form>
	<script>
	var selValue = '${user.userPosition}';
	$('#userPosition option[value='+ selValue +']').attr('selected', true);
</script>
</body>
