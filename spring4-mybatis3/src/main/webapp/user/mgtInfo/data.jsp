<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<style type="text/css">
table, td {
	border: 1px solid;
	border-collapse: collapse;
}
</style>
<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery.js"></script>
<script src="../js/jquery-ui.min.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$("#datepicker").datepicker("option", {minDate:-1,maxDate:-2});		
	});
	
 
</script>
</head>
<body>
	<input type="button"
		onclick="javascript:location.href='${pageContext.request.contextPath}/user/add.do'"
		value="新增"></input>
	<table>
		<tr>
			<td>用戶名</td>
			<td>用戶密碼</td>
			<td>用戶郵箱</td>
			<td>用戶生日</td>
			<td>用戶職位</td>
			<td>修改</td>
			<td>刪除</td>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="user" items="${lstUsers}">
			<tr>
				<td>${user.userName}</td>
				<td>${user.userPwd}</td>
				<td>${user.userEmail}</td>
				<td><input type="text" name="userBirthday" id="datepicker"
					value="${user.userBirthday}" readonly></td>
				<td><select id="userPosition" name="${user.userPosition}" disabled>
						<option value="fc">廠長</option>
						<option value="sm">銷售經理</option>
						<option value="qcs">品保主管</option>
						<option value="fm">廠務經理</option>
						<option value="mw" selected>生産員工</option>
				</select>				
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/user/update.do"
						method="post">
						<input type="hidden" name="userId" value="${user.userId}">
						<input type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/user/delete.do"
						method="post">
						<input type="hidden" name="userId" value="${user.userId}">
						<input type="submit" value="刪除">
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
					<script>
					$(function(){
					    $("select").each(function(){					    	
					       $(this).children("option").each(function(){					       
					            var selName = $(this).parent("select").attr("name");
					            var optValue = $(this).attr("value");					           
					            if(selName==optValue)
					               $(this).attr('selected', true);
					        });
					    });
					});
				</script>
</body>
</html>

