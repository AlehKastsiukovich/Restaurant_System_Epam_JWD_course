<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<html lang="${language}">
<head>
    <title>Register page</title>
</head>
<body>

<script>
    <%@include file="/js/confirm_password.js"%>
</script>

<form action="/" method="post">
    <table>
        <tr>
            <td><fmt:message key="label.login"/>:</td>
            <td><input type="text" name="login" required></td>
        </tr>
        <tr>
            <td><fmt:message key="label.firstName"/>:</td>
            <td><input type="text" name="firstName"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.lastName"/>:</td>
            <td><input type="text" name="lastName"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.email"/>:</td>
            <td><input type="email" pattern="^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.]{2,6})$" name="email" required></td>
        </tr>
        <tr>
            <td><fmt:message key="label.phoneNumber"/>:</td>
            <td><input type="text" pattern="^\+375(17|29|33|44)[0-9]{7}$" name="phoneNumber" required></td>
        </tr>
        <tr>
            <td><fmt:message key="label.password"/>:</td>
            <td><input type="password" name="password" id="password" onkeyup='check()' required></td>
        </tr>
        <tr>
            <td><fmt:message key="label.confirmPassword"/>:</td>
            <td><input type="password" name="password_repeat" id="confirm_password" onkeyup='check();' required></td>
            <td><span id='message'></span></td>
        </tr>
        <tr>
            <td><button type="submit" name="command" value="REGISTRATION"><fmt:message key="label.register"/></button></td>
        </tr>
    </table>
</form>
</body>
</html>
