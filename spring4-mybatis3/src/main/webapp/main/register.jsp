 <%@ page language="java" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
 <html>
     <head>
         <title>用戶註冊</title>
     </head>
 
     <body style="text-align: center;">
         <form action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">
             <table width="60%" border="1">
                 <tr>
                     <td>用户名</td>
                     <td>
                         <%--使用EL運算式${}提取存儲在request物件中的formbean物件中封裝的表單數據(formbean.userName)以及錯誤提示消息(formbean.errors.userName)--%>
                         <input type="text" name="userName" value="${formbean.userName}">${formbean.errors.userName}
                     </td>
                 </tr>
                 <tr>
                     <td>密码</td>
                     <td>
                         <input type="password" name="userPwd" value="${formbean.userPwd}">${formbean.errors.userPwd}
                     </td>
                 </tr>
                 <tr>
                     <td>确认密码</td>
                     <td>
                         <input type="password" name="confirmPwd" value="${formbean.confirmPwd}">${formbean.errors.confirmPwd}
                     </td>
                 </tr>
                 <tr>
                     <td>邮箱</td>
                     <td>
                         <input type="text" name="email" value="${formbean.email}">${formbean.errors.email}
                     </td>
                 </tr>
                 <tr>
                     <td>生日</td>
                     <td>
                         <input type="text" name="birthday" value="${formbean.birthday}">${formbean.errors.birthday}
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <input type="reset" value="清空">
                     </td>
                     <td>
                         <input type="submit" value="注册">
                     </td>
                 </tr>
             </table>
         </form>
     </body>
