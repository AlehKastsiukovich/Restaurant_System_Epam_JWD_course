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
      <title>Home page</title>
    </head>
    <body>

    <form>
      <select id="language" name="language" onchange="submit()">
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
      </select>
    </form>
      <form action="" method="get">
        <table>
          <tr>
            <td><fmt:message key="label.login" />: </td>
            <td><input type="text" name="login"></td>
          </tr>
          <tr>
            <td><fmt:message key="label.password" />:</td>
            <td><input type="password" name="password"></td>
          </tr>
          <tr>
            <td><input type="submit" name="submit" value="<fmt:message key="label.login" />"></td>
            <td><a href="/register.jsp"><fmt:message key="label.registration" /></a></td>
          </tr>
        </table>
      </form>
  </body>
</html>
