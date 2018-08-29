<%@ page import="java.util.Scanner" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../js/utils.js" type="text/javascript">
        <jsp:text/>
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.minus').click(function () {
                var $input = $(this).parent().find('input');
                var count = parseInt($input.val()) - 1;
                count = count < 1 ? 1 : count;
                $input.val(count);
                $input.change();
                return false;
            });
            $('.plus').click(function () {
                var $input = $(this).parent().find('input');
                $input.val(parseInt($input.val()) + 1);
                $input.change();
                return false;
            });
        });

    </script>
    <title>Title</title>
</head>
<body>
<h1>Products Page</h1>
<table class="table table-hover table-dark">
    <caption class="caption">Select your Room!</caption>
    <tr class="stripes">
        <td>Category</td>
        <td>Name</td>
        <td>Price</td>
        <td>On Storage</td>
    </tr>
    <c:forEach var="products" items="${product}">
        <tr class="stripes">
            <td>${products.category}</td>
            <td>${products.name}</td>
            <td>${products.price}$</td>
            <td>
                <div class="number">
                    <button type="submit" class="button minus" value="-"></button>
                    <input type="text" value="1" name="quantity" id="quantity" size="5"/>
                    <button type="button" class="button plus" value="+"></button>
                </div>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/frontController?command=addToBasket&productId=${products.id}&quantity=${products.quantity}">Add
                    To Basket</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
