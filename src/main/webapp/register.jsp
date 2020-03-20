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
<form action="Register">
    <table>
        <tr>
            <td><fmt:message key="label.login" />:</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.name" />: </td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td><fmt:message key = "label.phoneNumber" />:</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.password" />:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.password" />:</td>
            <td><input type="password" name="password_repeat"></td>
        </tr>
        <tr>
            <td><input type="submit" name="submit" value= "<fmt:message key="label.register" />"/></td>
        </tr>
    </table>
</form>
</body>
</html>
