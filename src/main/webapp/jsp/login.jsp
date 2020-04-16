
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--  <link rel="stylesheet" href="../css/styles.css">--%>
<%--  <link rel="stylesheet" href="../css/login.css">--%>
  <style>
    <%@ include file="/css/styles.css"%>
    <%@ include file="/css/login.css"%>
  </style>
  <title>login page</title>
</head>

<body>
  <header class="header">
    <div class="wrapper header__wrapper">
      <div class="header__logo-line">
        <a href="${pageContext.request.contextPath}/controller" class="logo">
          <h1 class="logo__text">Restaurant</h1>
        </a>
      </div>
      <div class="header__navigation">
        <ul class="navigation">
          <li class="navigation__item"><a href="#" class="navigation__link">contacts</a></li>
          <li class="navigation__item"><a href="${pageContext.request.contextPath}/jsp/order.jsp" class="navigation__link">cart</a></li>
          <li class="navigation__item"><a id="login" href="${pageContext.request.contextPath}/jsp/login.jsp" class="navigation__link">Sign in</a></li>
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
          <h3 class="modal-title">LOGIN</h3>
        </div>
        <div class="modal-body">
          <form action="${pageContext.request.contextPath}/controller" method="post">
            <input class="modal-body__input" type="text" name="login" placeholder="Username">
            <input class="modal-body__input" type="password" name="password" placeholder="Password">
            <button class="modal-body__input button-login" type="submit" name="command" value="SIGN_IN">Login</button>
<%--        <input class="modal-body__input button-login" type="button" name="command" value="SIGN_IN">--%>
          </form>
        </div>
        <div class="modal-footer">
          <form action="${pageContext.request.contextPath}/jsp/registration.jsp">
<%--          <input class="modal-body__input button-sign-up" type="button" value="Sign up">--%>
            <button class="modal-body__input button-sign-up" type="submit">Sign up</button>
          </form>
        </div>
      </div>
    </div>
  </main>

</body>

</html>
