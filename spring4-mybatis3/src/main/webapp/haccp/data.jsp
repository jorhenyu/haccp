<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../css/css-table.css">
</head>
<body>
	<table>
		<tr>
			<th colspan="14" scope="row"><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/haccp/add.do'"
				value="新增"></input><input type="button"
				onclick="javascript:location.href='${pageContext.request.contextPath}/haccp/report.do'"
				value="匯出"></input></th>
		</tr>
  <tr>  
      <th width="50" rowspan="2"><p align="center">修改</p></th>
          <th width="50" rowspan="2"><p align="center">刪除<br></p></th>
          <th width="188" rowspan="2"><p align="center">專案名稱<br></p></th>
    <th width="188" rowspan="2"><p align="center">重要 <br>
      管制點<br>
      (CCP )</p></th>
          <th width="146" rowspan="2"><p align="center">顯著之 <br>
      安全危害類別</p></th>
    <th width="146" rowspan="2"><p align="center">顯著之 <br>
      安全危害 </p></th>
    <th width="209" rowspan="2"><p align="center">管制界限 </p></th>
    <th width="413" colspan="4"><p align="center">監測程序 </p></th>
    <th width="120" rowspan="2"><p align="center">矯正措施 </p></th>
    <th width="120" rowspan="2"><p align="center">紀錄 </p></th>
    <th width="248" rowspan="2"><p align="center">確認程序 </p></th>
  </tr>
  <tr>
    <th width="103"><p align="center">項目 </p></th>
    <th width="103"><p align="center">方法 </p></th>
    <th width="103"><p align="center">頻率 </p></th>
    <th width="103"><p align="center">執行人 </p></th>
  </tr>
		<%--遍歷lstUsers集合中的User物件 --%>
		<c:forEach var="haccp" items="${lsts}">
			<tr>
				<td>
					<form
						action="${pageContext.request.contextPath }/haccp/update.do"
						method="post">
						<input type="hidden" name="haccpId" value="${haccp.haccpId}"> <input
							type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath }/haccp/del.do"
						method="post">
						<input type="hidden" name="haccpId" value="${haccp.haccpId}"> <input
							type="submit" value="刪除">
					</form>
				</td>
			   <td>${haccp.plan.pName}</td>			
				<td>${haccp.ha.procStep}</td>
				
				<td><c:choose><c:when test="${haccp.ha.pHa == 'phy'}">物理性</c:when>
				<c:when test="${haccp.ha.pHa == 'chem'}">化學性</c:when>
				<c:otherwise>生物性</c:otherwise></c:choose>
				</td>
				<td>${haccp.ha.haDesc}</td>
				<td>${haccp.cLimit}</td>	
				<td>${haccp.mItm}</td>
				<td>${haccp.mMd}</td>
				<td>${haccp.mFre}</td>	
				<td>${haccp.mPrin}</td>
				<td>${haccp.cMeas}</td>		
				<td>${haccp.record}</td>
				<td>${haccp.confirm}</td>						
			</tr>
		</c:forEach>
	</table>

</body>
</html>

