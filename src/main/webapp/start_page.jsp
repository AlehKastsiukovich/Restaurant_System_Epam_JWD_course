<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<!DOCTYPE html>
<html lang="${language}">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css"/>
  <title>My restaurant</title>
  <style>
    <%@ include file="css/styles.css"%>
    <%@ include file="css/login.css"%>
  </style>
</head>

<body>
  <header class="header">
    <div class="wrapper header__wrapper">
      <div class="header__logo-line">
        <a href="#" class="logo">
          <h1 class="logo__text">The restaurant</h1>
        </a>
      </div>
      <div class="header__navigation">
        <ul class="navigation">
          <li class="navigation__item"><a href="#" class="navigation__link">contacts</a></li>
          <li class="navigation__item"><a href="#" class="navigation__link">orders</a></li>
          <li class="navigation__item"><a href="#" id="login" class="navigation__link">Sign in</a></li>
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
      <div class="tags">
        <ul>
          <li>All</li>
          <li>Pizza</li>
          <li>Drinks</li>
          <li>Sushi</li>
        </ul>
      </div>
      <div class="products-list">
        <c:forEach var="list" items="${positionList}" varStatus="status">
        <div class="product">
          <h2 class="product__name">${list.itemName}</h2>
          <img src="data:image/jpg;base64,${list.positionImage}" width="200" height="200"/>
          <div class="product__content">
            <button>add</button>
            <span class="price">${list.itemPrice}$</span>
          </div>
        </div>
        </c:forEach>
      </div>
    </div>
  </main>
  
  <div class="modal-box">
    <div class="modal-overlay">
      <div class="modal-window">
        <div class="modal-header">
          <h3 class="modal-title">LOGIN</h3>
        </div>
        <div class="modal-body">
          <form action="/" method="post">
            <button></button>
            <input class="modal-body__input" type="text" name="login" placeholder="Username">
            <input class="modal-body__input" type="password" name="password" placeholder="Password">
<%--            <input class="modal-body__input button-login" type="button" value="SIGN_IN">--%>
            <button class="modal-body__input button-login" type="submit" name="command" value="SIGN_IN">Login</button>
          </form>
        </div>
        <div class="modal-footer">
          <input class="modal-body__input button-sign-up" type="button" value="Sign up">
        </div>
      </div>
    </div>
  </div>

  <div class="footer">
    <div class="wrapper"></div>
  </div>
  <script><%@include file="/js/index.js"%></script>
</body>

</html>