<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/styles.css">
    <link rel="stylesheet" href="../css/orders.css">
    <title>restaurant</title>
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
        <div class="orders">
            <ul class="orders__list">
                <li class="order__item">
                    <span class="order__text">item 1</span>
                    <div class="buttons">
                        <button class="order__button button_danger">-</button>
                        <span class="order__quantity">1</span>
                        <button class="order__button button_success">+</button>
                    </div>
                </li>
                <li class="order__item">
                    <span class="order__text">item 2</span>
                    <div class="buttons">
                        <button class="order__button button_danger">-</button>
                        <span class="order__quantity">1</span>
                        <button class="order__button button_success">+</button>
                    </div>
                </li>
                <li class="order__item">
                    <span class="order__text">item 3</span>
                    <div class="buttons">
                        <button class="order__button button_danger">-</button>
                        <span class="order__quantity">1</span>
                        <button class="order__button button_success">+</button>
                    </div>
                </li>
                <li class="order__item">
                    <span class="order__text">item 4</span>
                    <div class="buttons">
                        <button class="order__button button_danger">-</button>
                        <span class="order__quantity">1</span>
                        <button class="order__button button_success">+</button>
                    </div>
                </li>
                <li class="order__item">
                    <span class="order__text">item 5</span>
                    <div class="buttons">
                        <button class="order__button button_danger">-</button>
                        <span class="order__quantity">1</span>
                        <button class="order__button button_success">+</button>
                    </div>
                </li>
            </ul>
            <button class="confirm-button">Order</button>
        </div>
    </div>
</main>

<script src="../js/index1.js"></script>
</body>

</html>