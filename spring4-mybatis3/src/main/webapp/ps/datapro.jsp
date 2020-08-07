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

	$("#exportWord").click(function(){
		   var method =$("input[name='pspsId']:checked").val(); //radio 取值，注意寫法
		   if( typeof(method) == "undefined"){ // 注意檢查完全沒有選取的寫法，這行是精華
		   alert( "請選取操作方式！");
		   return false;
		  }	
		   alert(method);
			  
			   $('#psId').val(method);
			   $('form#myForm').submit();
		   });  
});

</script>
</head>
<body>
	<table>
		<tr>
						
			<th colspan="16" scope="row">
			<form id="myForm" name="myForm" action="${pageContext.request.contextPath }/ps/exportWord.do" method="post">
			<input type="hidden" id="psId" name="psId" value=""> 
			 <input	id="exportWord" name="exportWord" type="button" value="匯出">
			</form>						
			</th>
		</tr>
		<tr>
			
			<th>選取</th>
			<th>專案名稱名稱</th>
			<th>產品類別</th>			
			<th>產品名稱</th>
			<th>主要原料</th>
			<th>其他原料</th>
			<th>食品添加物</th>
			<th>加工助劑</th>
			<th>物料</th>
			<th>產品特性</th>
			<th>加工方式</th>
			<th>包裝方式及說明</th>
			<th>貯存及運輸方法</th>
			<th>架售期</th>
			<th>注意事項</th>
		</tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="ps" items="${lsts}">
			<tr><td>
					<input type="radio" id="pspsId" name="pspsId" value="${ps.psId}">
				</td>
			    <td>${ps.plan.pName}</td>			
				<td><c:forEach items="${options}" var="o">
							<c:choose>
								<c:when test="${ps.cId == o.optionKey}">									
										${o.optionValue}
								</c:when>
							</c:choose>
					</c:forEach></td>
				<td>${ps.pName}</td>
				<td>${ps.matrlM}</td>
				<td>${ps.matrlO}</td>	
				
				<td>${ps.fdAdd}</td>			
				<td>${ps.prcsAids}</td>
				<td>${ps.matrl}</td>
				<td>${ps.pdtFt}</td>
				<td>${ps.pdtMd}</td>	
				
				<td>${ps.pmDesc}</td>			
				<td>${ps.stMd}</td>
				<td>${ps.sLife}</td>
				<td>${ps.notes}</td>					
			</tr>
		</c:forEach>
	</table>

</body>
</html>

