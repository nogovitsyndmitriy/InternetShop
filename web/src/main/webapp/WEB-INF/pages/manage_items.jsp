<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Items</title>
</head>
<body class="body">
<jsp:include page="common/header.jsp"/>
<form:form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/web/items/upload">
    <input type="file" name="file" accept="text/xml">
    <button type="submit" class="btn btn-primary">upload</button>
</form:form>
<a href="${pageContext.request.contextPath}/web/items/create_item" class="btn-primary">Create Item</a>
<div class="col-md-12">
    <table class="table table-striped table-dark">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Description</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Unique Number</th>
        </tr>
        </thead>
        <tbody>
        <form:form action="${pageContext.request.contextPath}/web/items/remove"
                   modelAttribute="item" method="post"
                   cssClass="form">
        <c:forEach items="${items}" var="item">
            <tr>
                <th scope="row"><input type="checkbox" name="ids" value="${item.id}"></th>
                <td>${item.description}</td>
                <td>${item.name}</td>
                <td>${item.price}$</td>
                <td>${item.uniqueNumber}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="status" id="exampleRadios1" value="remove"
               checked>
        <label class="form-check-label" for="exampleRadios1">
            Remove
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="status" id="exampleRadios2"
               value="add">
        <label class="form-check-label" for="exampleRadios2">
            Add
        </label>
    </div>
    <button class="btn btn-warning" type="submit">Change Status</button>
    </form:form>
</div>
<ul class="pagination justify-content-center">
    <c:forEach var="page" begin="1" end="${pages}">
        <li class="page-item"><a class="page-link"
                                 href="${pageContext.request.contextPath}/web/items?page=${page}">${page}</a></li>
    </c:forEach>
</ul>

<jsp:include page="common/footer.jsp"/>
</body>
</html>
