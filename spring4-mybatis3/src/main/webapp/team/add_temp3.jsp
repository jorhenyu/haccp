<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring MVC Multipe Row Submit</title>
    
<script type="text/javascript">
$(function() {
    $("#add").bind('click', function() {
        var count = $("#contactList tr").length;
        count = count - 1;

        $("#contactList tbody")
            .append('<tr>')
            .append('<td align="center">' + (count+1) + '</td>')
            .append('<td><input name="contacts[' + count + '].name" value=""/></td>')
            .append('<td><input name="contacts[' + count + '].gender" value=""/></td>')
            .append('<td><input name="contacts[' + count + '].email" value=""/></td>')
            .append('<td><input name="contacts[' + count + '].phone" value=""/></td>')
            .append('</tr>');
    });
});
</script>
</head>
<body>

<form:form method="post" action="/DemoSite/contact/save.do" modelAttribute="contactForm">
    <table id="contactList">
    <thead>
    <tr>
        <th>No.</th>
        <th>姓名</th>
        <th>性別</th>
        <th>電子郵件</th>
        <th>電話</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${contactForm.contacts}" var="contact" varStatus="status">
            <tr>
                <td align="center">${status.count}</td>
                <td><input name="contacts[${status.index}].name" value="${contact.name}"/></td>
                <td><input name="contacts[${status.index}].gender" value="${contact.gender}"/></td>
                <td><input name="contacts[${status.index}].email" value="${contact.email}"/></td>
                <td><input name="contacts[${status.index}].phone" value="${contact.phone}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>    
<br/>
<input id="add" name="add" type="button" value="add" />&nbsp;<input type="submit" value="Save" />
    
</form:form>
</body>
</html>