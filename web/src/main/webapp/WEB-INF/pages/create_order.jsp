<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Order</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<table class="table table-striped table-dark">
<thead>
<tr>
    <th scope="col">#</th>
    <th scope="col">Description</th>
    <th scope="col">Name</th>
    <th scope="col">Price</th>
    <th scope="col">Unique Number</th>
    <th scope="col">Quantity</th>
    <th scope="col">Actions</th>
</tr>
</thead>
<tbody>
<tr>
<td>${item.description}</td>
<td>${item.name}</td>
<td>${item.price}$</td>
<td>${item.uniqueNumber}</td>
<form:form action="${pageContext.request.contextPath}/web/orders/create_order" modelAttribute="order" method="post">
    <td>
    <input type="number" name="quantity" class="form-control" placeholder="Quantity"/>
    <input type="hidden" name="item" value="${item.id}"/>
    </td>
    <td><button type="submit" class="btn-primary">CreateOrder</button></td>
</form:form>
    </tr>
    </tbody>
</table>
</body>
<jsp:include page="common/footer.jsp"/>
</html>