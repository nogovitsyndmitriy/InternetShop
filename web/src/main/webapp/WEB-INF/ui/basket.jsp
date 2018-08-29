<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Basket</h1>
<table class="table table-hover table-dark">
    <caption class="caption">Select your Room!</caption>
    <tr class="stripes">
        <td>Name</td>
        <td>Quantity</td>
        <td>Order Id</td>
        <td>Product Id</td>
        <td>Price</td>
    </tr>
    <c:forEach var="items" items="${item}">
        <tr class="stripes">
            <td>${items.productName}</td>
            <td>${items.quantity}</td>
            <td>${items.orderId}</td>
            <td>${items.userId}</td>
            <td>${items.price}</td>
            <td><a href="${pageContext.request.contextPath}/frontController?command=addToBasket&productId=${products.id}&quantity=${products.quantity}">Remove Item</a></td>
        </tr>
    </c:forEach>
</table>
<a href="#">Create Order</a>
</body>
</html>
