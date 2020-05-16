<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="${language}">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orders.css">
    <title>login page</title>
    <script>
        <%@include file="/js/confirm_password.js"%>
        function validateForm() {
            var password = document.getElementById('password').value;
            var password_repeat = document.getElementById('password_repeat').value;
            var login = document.getElementById('user_login').value;
            var loginRegex = '^[A-Za-z0-9_-]{3,16}$';
            var email = document.getElementById('user_email');
            var emailRegex = '^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.]{2,6})$';
            var phone = document.getElementById('user_phone').value;
            var phoneRegex = '^\+375(17|29|33|44)[0-9]{7}$';

            if (!(login.match(loginRegex))) {
                document.getElementById('messageLogin').style.color = 'red';
                document.getElementById('messageLogin').innerHTML = 'Login must contain 3 or more letter or number';
                return false;
            }

            if (!(email.match(emailRegex))) {
                document.getElementById('messageEmail').style.color = 'red';
                document.getElementById('messageEmail').innerHTML = 'Wrong email';
                return false;
            }
        }

        var check = function () {
            if (document.getElementById('password').value ==
                document.getElementById('password_repeat').value) {
                document.getElementById('message').style.color = 'green';
                document.getElementById('message').innerHTML = 'correct';
            } else {
                document.getElementById('message').style.color = 'red';
                document.getElementById('message').innerHTML = 'please repeat your password';
            }
        }

        function validateLogin() {
            var login = document.getElementById('user_login').value;
            var loginRegex = '^[A-Za-z0-9_-]{3,16}$';

            if (login.match(loginRegex)) {
                document.getElementById('messageLogin').style.color = 'green';
                document.getElementById('messageLogin').innerHTML = 'correct';
                return true;
            } else {
                document.getElementById('messageLogin').style.color = 'red';
                document.getElementById('messageLogin').innerHTML = 'Login must contain 3 or more letter or number';
                return false;
            }
        }

        function validateEmail() {
            var email = document.getElementById('user_email');
            var emailRegex = '^\S+@\S+$';

            if (email.match(emailRegex)) {
                alert('true');
                document.getElementById('messageEmail').style.color = 'green';
                document.getElementById('messageEmail').innerHTML = 'ok';
                return true;
            } else {
                alert('false');
                document.getElementById('messageEmail').style.color = 'red';
                document.getElementById('messageEmail').innerHTML = 'Wrong email';
                return false
            }

            return false;
        }
    </script>
</head>

<body>
<header class="header">
    <div class="wrapper header__wrapper">
        <div class="header__logo-line">
            <a href="${pageContext.request.contextPath}/jsp/start_page.jsp" class="logo">
                <h1 class="logo__text"><fmt:message key="label.restaurant"/></h1>
            </a>
        </div>
        <div class="header__navigation">
            <ul class="navigation">
                <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/contacts.jsp"
                                                class="navigation__link"><fmt:message key="label.contacts"/></a></li>
                <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/order.jsp"
                                                class="navigation__link"><fmt:message key="label.cart"/></a></li>
                <c:if test="${user.role.roleId == 2}">
                    <li class="navigation__item">
                        <form class="header__form" method="get" action="${pageContext.request.contextPath}/controller">
                            <button style="cursor: pointer; border: none; color: rgb(255, 255, 255);
                                background: rgb(0, 0, 0);" class="navigation__link" type="submit" value="PROFILE"
                                    name="command"><fmt:message key="label.profile"/>
                            </button>
                        </form>
                    </li>
                </c:if>
                <li class="navigation__item">
                    <c:choose>
                        <c:when test="${user.role.roleId == 2}">
                            <form method="get" action="${pageContext.request.contextPath}/controller">
                                <button style="cursor: pointer" class="navigation__link" type="submit" value="LOGOUT"
                                        name="command"/>
                                <fmt:message key="label.logout"/>
                                </button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/jsp/login.jsp" id="login"
                               class="navigation__link"><fmt:message key="label.sign_in"/></a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <form>
                <select id="language" name="language" onchange="submit()">
                    <option value="ru" ${language == 'ru' ? 'selected' : ''}>ru</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>en</option>
                </select>
            </form>
        </div>
    </div>
</header>

<main class="main">
    <div class="wrapper main__wrapper">
        <div class="modal-window">
            <div class="modal-header">
                <h3 class="modal-title"><fmt:message key="label.registration"/></h3>
            </div>
            <div class="modal-body">
                <form id="reg" onsubmit="return(validateEmail())" action="${pageContext.request.contextPath}/controller" method="post">
                    <input class="modal-body__input" id="user_login" type="text" name="login"
                           placeholder="<fmt:message key="label.username"/>">
                    <span id='messageLogin' style="font-size: 12px"></span>
                    <input class="modal-body__input" type="text" name="firstName"
                           placeholder="<fmt:message key="label.firstName"/>">
                    <input class="modal-body__input" type="text" name="lastName"
                           placeholder="<fmt:message key="label.lastName"/>">
                    <input class="modal-body__input" id="user_email" name="email"
                           placeholder="<fmt:message key="label.email"/> ">
                    <span id='messageEmail' style="font-size: 12px"></span>
                    <input class="modal-body__input" id="user_phone" type="text" name="phoneNumber"
                           placeholder="<fmt:message key="label.place_holder_phone_number"/>"
                           pattern="^\+375(17|29|33|44)[0-9]{7}$" required>
                    <input class="modal-body__input" type="password" id="password" name="password"
                            pattern="^[A-Za-z0-9_-]{6,18}$"
                           placeholder="<fmt:message key="label.password"/>" onkeyup='check();' required>
                    <input class="modal-body__input" type="password" id="password_repeat" name="password_repeat"
                           placeholder="<fmt:message key="label.confirmPassword"/>" onkeyup='check();' required>
                    <span style="font-size: 12px" id='message'></span>
                    <button class="modal-body__input button-login" type="submit" name="command" value="REGISTRATION">
                        <fmt:message key="label.register"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</main>

<footer class="footer">
    <div class="wrapper footer__wrapper">
    </div>
</footer>

</body>

</html>