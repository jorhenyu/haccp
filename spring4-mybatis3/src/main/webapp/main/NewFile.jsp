<%@ page language="java" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 
 <%--為了避免在jsp頁面中出現java代碼，這裡引入jstl標籤庫，利用jstl標籤庫提供的標籤來做一些邏輯判斷處理 --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>顯示使用者資訊</title>
        <style type="text/css">
            table,td{
                border: 1px solid;
                border-collapse: collapse;
            }
        </style>
            <script type="text/javascript">
         function doLogout(){
             //訪問LogoutServlet註銷當前登錄的用戶
             window.location.href="${pageContext.request.contextPath}/servlet/LogoutServlet";
         }
     </script>
    </head>
    <body>
<a href ="<%=basePath%>UserServlet" target ="showframe">Frame a</a><br />
<a href ="/example/html/frame_b.html" target ="showframe">Frame b</a><br />
<a href ="/example/html/frame_c.html" target ="showframe">Frame c</a>

   <hr/>
     <c:if test="${user==null}">
         <a href="${pageContext.request.contextPath}/RegisterUIServlet">註冊</a>
         <a href="${pageContext.request.contextPath}/LoginUIServlet" target="showframe">登陸</a>
     </c:if>
     <c:if test="${user!=null}">
            歡迎您：${user.userName}
            <input type="button" value="退出登陸" onclick="doLogout()">
     </c:if>
     <hr/>

    </body>
</html>

