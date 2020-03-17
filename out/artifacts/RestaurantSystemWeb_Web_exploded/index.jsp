<%--
  Created by IntelliJ IDEA.
  User: AlehKastsiukovich
  Date: 3/11/2020
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Home page</title>
</head>
<body>
<form action="/controller" method="post">
  <table>
    <tr>
      <td>Login: </td>
      <td><input type="text" name="login"></td>
    </tr>
    <tr>
      <td>Password:</td>
      <td><input type="password" name="password"></td>
    </tr>
    <tr>
      <td><input type="submit" name="submit" value="Login"></td>
      <td><a href="/register.jsp">Registration</a></td>
    </tr>
  </table>
</form>
</body>
</html>
