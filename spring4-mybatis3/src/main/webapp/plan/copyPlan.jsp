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
	        $('#form1').submit();			
		});
	});

</script>
</head>
<body>
	<table>
		<tr>

			<th>複製專案</th>
			<th></th>
		</tr>
		<tr>

			<td><form id="form1" name="form1" action="${pageContext.request.contextPath }/plan/doCopy.do"
					method="post">
					<label>複製公開專案,將產生該專案的表單(食品安全管制小組名單、產品特性及貯運方式
                                                       產品用途及消費對象、產品加工流程圖、危害分析工作表、重要管制點判定表、重要管制點計畫表)，
                                                       並將扣點數五點，您的點數目前為${user.rdPot}</label><br>
					<input id="add" name="add" type="button" value="複製">					
					<input type="hidden" id="pId" name="pId" value="${plan.pId}">									 
				</form></td>
		</tr>
	</table>
</body>
</html>

