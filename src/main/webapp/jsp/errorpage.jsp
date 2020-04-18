<%@ page import="by.epam.javatraining.restaurant.entity.Position" %>
<%@ page import="by.epam.javatraining.restaurant.util.PositionCash" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: AlehKastsiukovich
  Date: 3/15/2020
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <%PositionCash cash = PositionCash.getInstance();
    List<Position> list = cash.getPositionList();
    Position position = list.get(5);%>

    <h1><%position.toString();%></h1>
</body>
</html>
