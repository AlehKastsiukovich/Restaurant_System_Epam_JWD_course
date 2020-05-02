<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orders.css">
    <title>address</title>
</head>

<body>
<header class="header">
    <div class="wrapper header__wrapper">
        <div class="header__logo-line">
            <a href="${pageContext.request.contextPath}/jsp/start_page.jsp" class="logo">
                <h1 class="logo__text">Restaurant</h1>
            </a>
        </div>
        <div class="header__navigation">
            <ul class="navigation">
                <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/contacts.jsp" class="navigation__link">contacts</a></li>
                <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/order.jsp"
                                                class="navigation__link">cart</a></li>
                <c:if test="${user.role.roleId == 2}">
                    <li class="navigation__item">
                        <form class="header__form" method="get" action="${pageContext.request.contextPath}/controller">
                            <input style="cursor: pointer; border: none; color: rgb(255, 255, 255);
                                background: rgb(0, 0, 0);" class="navigation__link" type="submit" value="VIEW_PROFILE"
                                   name="command"/>
                        </form>
                    </li>
                </c:if>
                <li class="navigation__item">
                    <c:choose>
                        <c:when test="${user.role.roleId == 2}">
                            <form method="get" action="${pageContext.request.contextPath}/controller">
                                <input style="cursor: pointer; border: none; color: rgb(255, 255, 255);
                                background: rgb(0, 0, 0);" class="navigation__link" type="submit" value="LOGOUT"
                                       name="command"/>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/jsp/login.jsp" id="login"
                               class="navigation__link">Sign in</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <select name="language">
                <option value="en" selected>en</option>
                <option value="ru">ru</option>
            </select>
        </div>
    </div>
</header>

<main class="main">
    <div class="wrapper main__wrapper">
        <div class="modal-window">
            <div class="modal-header">
                <h3 class="modal-title">Enter your address</h3>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input class="modal-body__input" type="text" name="street" placeholder="Street">
                    <input class="modal-body__input" type="text" name="build" placeholder="Build number">
                    <input class="modal-body__input" type="text" name="number" placeholder="Apartment number">
                    <button class="modal-body__input button-login" type="submit" name="command" value="CONFIRM_ORDER">Confirm</button>
                </form>
            </div>
        </div>
    </div>
</main>

</body>

</html>