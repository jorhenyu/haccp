<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<script type="text/javascript" src="../js/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用戶註冊</title>
 <link rel="stylesheet" type="text/css" href="../css/css-table.css">
<script>


$(document).ready(function() {
	
    $("#openWin").bind('click', function() {
    	window.open("${pageContext.request.contextPath}/plan/query.do", null, "width=600px,height=400px");
    });
    

    $("#save").bind('click', function() {
        if($("#planId").val()==""){
            alert("專案ID不存在"); 
            eval("document.form1['planId'].focus()");
        }else{        	 
        	 $('#form1').submit();        	  
        } 
  
      });
	
	
var tag = 0;
  $("#add").click(function(){
      $('#teamList tbody')//只能寫一列，才有反應
   .append('<tr><td align="center">'+(tag+1)+'</td><td><textarea name="teams[' + tag + '].mber"></textarea></td><td><textarea name="teams[' + tag + '].pos"></textarea>"</td><td><textarea name="teams[' + tag + '].duty"></textarea></td><td><textarea name="teams[' + tag + '].bg"></textarea></td><td><textarea name="teams[' + tag + '].skill"></textarea></td></tr>');
    tag++;
   });
  $("#del").click(function(){	  
      $('#teamList tbody tr:last').remove();
      tag--;
  });

  
  
})

</script>
</head>
<body>
<form:form id="form1" name="form1"  method="post" action="${pageContext.request.contextPath}/team/doAdd.do" modelAttribute="teamForm">
    <table id="teamList">
    <thead>
    	  <tr>
			<th>專案ID</th>
				<td colspan="6"><input type="text" id="planId"  name="planId" value="${team.planId}"
					readonly><input id="openWin" name="openWin" type="button" value="選取"></td>
			</tr>
    <tr>
        <th>No.</th>
        <th>成員</th>
        <th>職稱</th>
		<th>職責</th>	
		<th>學歷</th>	
		<th>HACCP專業訓練及經驗</th>   
		    
    </tr>
    </thead>
    <tbody>    
         <c:forEach items="${teamForm.teams}" var="team" varStatus="status">
            <tr>
                <td align="center">${status.count}</td>
                <td><textarea name="teams[${status.index}].mber">${team.mber}</textarea></td>
                <td><textarea name="teams[${status.index}].pos">${team.pos}</textarea></td>
                <td><textarea name="teams[${status.index}].duty">${team.duty}</textarea></td>
                <td><textarea name="teams[${status.index}].bg">${team.bg}</textarea></td>
                <td><textarea name="teams[${status.index}].skill">${team.skill}</textarea></td>                
            </tr>
        </c:forEach>  
    </tbody>
    </table>    
<br/>
<input id="add" name="add" type="button" value="新增欄位" />&nbsp;
<input id="del" name="del" type="button" value="刪除欄位" />&nbsp;
<input id="save" name="save" type="button" value="儲存" />
    
</form:form>
</body>
</html>

