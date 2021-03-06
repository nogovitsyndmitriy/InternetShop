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
        <form:form action="${pageContext.request.contextPath}/web/orders/status"
                   modelAttribute="order" method="post"
                   cssClass="form">
        <thead>
        <tr>
            <security:authorize access="hasAuthority('CHANGE_STATUS')">
                <th scope="col">#</th>
            </security:authorize>
            <th scope="col">User</th>
            <th scope="col">Item</th>
            <th scope="col">Quantity</th>
            <th scope="col">Created</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <security:authorize access="hasAuthority('CHANGE_STATUS')">
                    <th scope="row"><input type="checkbox" name="ids" value="${order.id}"></th>
                </security:authorize>
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
    <security:authorize access="hasAuthority('CHANGE_STATUS')">
        <c:forEach items="${statuses}" var="status">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="status" id="exampleRadios1" value="${status}"
                       checked>
                <label class="form-check-label" for="exampleRadios1">
                        ${status}
                </label>
            </div>
        </c:forEach>
        <button class="btn btn-warning" type="submit">Change Status</button>
    </security:authorize>
    </form:form>

    <security:authorize access="hasAuthority('VIEW_USERS')">
        <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${pages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/web/orders/admin?page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </security:authorize>
    <security:authorize access="hasAuthority('VIEW_DOCUMENTS')">
        <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${pages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/web/orders?page=${page}">${page}</a>
                </li>
            </c:forEach>
        </ul>
    </security:authorize>
</div>
</body>
<jsp:include page="common/footer.jsp"/>

</html>