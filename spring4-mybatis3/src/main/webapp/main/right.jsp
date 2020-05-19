<%@ page language="java" pageEncoding="UTF-8"%>
<%--引入JSTL核心標籤庫 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>顯示使用者資訊main</title>
        <style type="text/css">
            table,td{
                border: 1px solid;
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <table>
            <tr>
                <td>main用戶ID</td>
                <td>用戶名</td>
                <td>用戶生日</td>
                <td>工資</td>
            </tr>
            <%--遍歷lstUsers集合中的User物件 --%>
            <c:forEach var="user" items="${lstUsers}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.userBirthday}</td>
                    <td>${user.userSalary}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

