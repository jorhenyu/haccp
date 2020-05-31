<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>顯示使用者資訊user</title>
<link rel="stylesheet" type="text/css" href="../../css/css-table.css">
</head>
<body>
<table>
  <tbody>
    <tr>
      <th colspan="2" scope="row">管理介面</th>
    </tr>
<tr>
      <th><input type="button"  value="類別管理" onclick="javascript:location.href='${pageContext.request.contextPath}/user/cat/index.do'"></th>
      <th><input type="button" value="會員管理" onclick="javascript:location.href='${pageContext.request.contextPath}/user/mber/index.do'"></th>
    </tr>
  </tbody>
</table>
</body>
</html>

