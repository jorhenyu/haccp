<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--為了避免在jsp頁面中出現java代碼，這裡引入jstl標籤庫，利用jstl標籤庫提供的標籤來做一些邏輯判斷處理 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>使用者登入</title>
<link rel="stylesheet" type="text/css" href="css/login.css" />

</head>
<body>

	

	<form action="${pageContext.request.contextPath }/index.do"
		method="post">
        
		<div class="container">
			<label for="uname"><b>名稱</b></label> <input type="text"
				placeholder="Enter Username" name="uName" required> <label
				for="psw"><b>密碼</b></label> <input type="password"
				placeholder="Enter Password" name="uPw" required>
			<form:errors path="userDetail.uName" cssStyle="color:red"></form:errors>
			<button type="submit">登入</button>
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="otherbtn"
				onclick="javascript:location.href='${pageContext.request.contextPath}/user/mber/add.do'">註冊</button>
			<button type="button" class="otherbtn"
				onclick="javascript:location.href='${pageContext.request.contextPath}/index.do?uName=guest'">訪客</button>

		</div>
	</form>

</body>
</html>