<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 <%--為了避免在jsp頁面中出現java代碼，這裡引入jstl標籤庫，利用jstl標籤庫提供的標籤來做一些邏輯判斷處理 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE HTML>
 <html>
   <head>
     <title>使用者登入</title>
   <script type="text/javascript">
         function doLogout(){
             //訪問LogoutServlet註銷當前登錄的用戶
             window.location.href="${pageContext.request.contextPath}/servlet/LogoutServlet";
         }
         
 
     </script>
   </head>
   
   <body>
     <form action="${pageContext.request.contextPath }/index.do" method="post" name="form1">
         帳號：<input type="text" name="userName" value="test">
         <form:errors path="userDetail.userName" cssStyle="color:red"></form:errors><br/>
         密碼：<input type="password" name="userPwd" value="test">
         <form:errors path="userDetail.userPwd" cssStyle="color:red"></form:errors><br/>
         <input type="submit" value="登入">         
     </form>
        <hr/>
     <c:if test="${user==null}">
         <a href="${pageContext.request.contextPath}/user/add.do">註冊</a>
         <a href="${pageContext.request.contextPath}/LoginUIServlet">訪客</a>
     </c:if>
   </body>
 </html>