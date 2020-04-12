<%--
  Created by IntelliJ IDEA.
  User: AlehKastsiukovich
  Date: 3/15/2020
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <style>
        <%@ include file="/css/styles.css"%>
        <%@ include file="/css/login.css"%>
    </style>
</head>
<body>
    <input class="modal-body__input" type="text" name="login" placeholder="Username">
    <input class="modal-body__input" type="password" name="password" placeholder="Password">
    <button class="modal-body__input button-login" type="submit" name="command" value="SIGN_IN">Login</button>
</body>
</html>
