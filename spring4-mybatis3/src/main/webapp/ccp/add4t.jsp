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
    	window.open("${pageContext.request.contextPath}/ha/query.do", null, "width=600px,height=400px");
    });    

    $("#add").bind('click', function() {
        if($("#planId").val()==""){
            alert("專案ID不存在"); 
            eval("document.form1['planId'].focus()");
        }else{        	 
        	 $('#form1').submit();        	  
        } 
  
      });  
    
    $('#q1').change(function(){
        ChangeQ();
    });

    $('#q2').change(function(){
        ChangeQ();
    }); 
    
    $('#q3').change(function(){
        ChangeQ();
    }); 
    
    $('#q4').change(function(){
        ChangeQ();
    }); 
    
    var q1Id 
    var q2Id 
    var q3Id 
    var q4Id 
    
    function ChangeQ()
    {
        //變動下拉選單
     q1Id = $.trim($('#q1 option:selected').val());
     q2Id = $.trim($('#q2 option:selected').val());
     q3Id = $.trim($('#q3 option:selected').val());
     q4Id = $.trim($('#q4 option:selected').val());

       // alert("q1Id=="+q1Id.length);
        //alert("q2Id=="+ q2Id.length);
       // alert("q3Id=="+q3Id.length);
        //alert("q4Id=="+q4Id.length);
        
        if(q1Id == 'Y')
        {
        	if(q2Id == 'Y'){
        		if(q3Id.length == 0 && q4Id.length == 0){
        			$('#ccp').val("Y");
        		}else{        			
        			$('#ccp').val("N");
        		}        		   		
        	}else{
            	if(q3Id == 'Y'){
                	if(q4Id == 'Y'){
                		$('#ccp').val("N");   		
                	}else{
                		$('#ccp').val("Y");
                	}    		
            	}else{
            		$('#ccp').val("N");
            	} 
        	} 	
        		
        }else{        	
        	$('#ccp').val("N");
        }
    }  
})

</script>
</head>

<body>
	<form:form  id="form1" name="form1" action="${pageContext.request.contextPath}/ccp/doAdd.do"
		method="post">
		<table>
		    <tr>
			<th>加工步驟</th>
				<td><input type="text" id="procStep"  name="procStep" value="${ccp.ha.procStep}"
					readonly>
					<input id="openWin" name="openWin" type="button" value="選取"></td>
					<td><input type="hidden" id="haId" name="haId" value="${ccp.haId}"></td>
					<td><input type="hidden" id="planId" name="planId" value="${ccp.planId}"></td>
					<td><input type="hidden" id="qTb" name="qTb" value="4"></td>					
			</tr>
			<tr>
			<th>Q1</th>
			<th>Q2</th>
			<th>Q3</th>	
			<th>Q4</th>		
			<th>CCP</th>
		</tr>
			<tr>			

				<td>
					<select id="q1" name="q1">
					    <option value="">------</option>
						<option value="Y">Y</option>
						<option value="N">N</option>						
				    </select>
				</td>	
								<td>
					<select id="q2" name="q2">
					<option value="">------</option>
						<option value="Y">Y</option>
						<option value="N">N</option>						
				    </select>
				</td>
				<td>
					<select id="q3" name="q3">
					<option value="">------</option>
						<option value="Y">Y</option>
						<option value="N">N</option>						
				    </select>
				</td>
				<td>
					<select id="q4" name="q4">
					<option value="">------</option>
						<option value="Y">Y</option>
						<option value="N">N</option>						
				    </select>
				</td>
					<td>
					<input type="text" id="ccp"  name="ccp" value="${ccp.ccp}"
					readonly>
				</td>			

			<tr>
				<th></th>
				<td colspan="3"><input id="add" name="add" type="button" value="新增" /></td>
			</tr>
		</table>
	</form:form>

</body>