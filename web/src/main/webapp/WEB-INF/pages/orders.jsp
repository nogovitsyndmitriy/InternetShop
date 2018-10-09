<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body class="body">
<div class="col-md-12">
    <jsp:include page="common/header.jsp"/>
    <table class="table table-striped table-dark">
        <form:form action="${pageContext.request.contextPath}/web/orders/change_status"
                   modelAttribute="order" method="post"
                   cssClass="form">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">User</th>
            <th scope="col">Item</th>
            <th scope="col">Quantity</th>
            <th scope="col">Created</th>
            <th scope="col">Status</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <th scope="row"><input type="checkbox" name="ids" value="${order.id}"></th>
                <td>${order.userDto.name}</td>
                <td>${order.itemDto.name}</td>
                <td>${order.quantity}</td>
                <td>${order.created.dayOfMonth}/${order.created.monthValue}/${order.created.year}
                        ${order.created.hour}:${order.created.minute}:${order.created.second}</td>
                <td>${order.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="status" id="exampleRadios1" value="REVIEWING"
               checked>
        <label class="form-check-label" for="exampleRadios1">
            Reviewing
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="status" id="exampleRadios2"
               value="IN_PROGRESS">
        <label class="form-check-label" for="exampleRadios2">
            In Progress
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="status" id="exampleRadios3"
               value="DELIVERED">
        <label class="form-check-label" for="exampleRadios3">
            Delivered
        </label>
    </div>
    <button class="btn btn-warning" type="submit">Change Status</button>
    </form:form>
<security:authorize access="hasAuthority('VIEW_USERS')">
    <ul class="pagination justify-content-center">
        <c:forEach var="page" begin="1" end="${pages}">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/web/orders/orders_admin?page=${page}">${page}</a></li>
        </c:forEach>
    </ul>
</security:authorize>
    <security:authorize access="hasAuthority('VIEW_DOCUMENTS')">
        <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${pages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/web/orders?page=${page}">${page}</a></li>
            </c:forEach>
        </ul>
    </security:authorize>
</div>
</body>
<jsp:include page="common/footer.jsp"/>

</html>
