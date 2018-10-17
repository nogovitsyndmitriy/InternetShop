<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<div class="row">
    <div class="col-md-12">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Email</th>
                <th scope="col">FirstName</th>
                <th scope="col">LastName</th>
                <th scope="col">Address</th>
                <th scope="col">Role</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <form:form action="${pageContext.request.contextPath}/web/users/set_discount" method="post">
            <c:forEach items="${users}" var="user">
                <tr>
                    <th scope="row"><input type="checkbox" name="ids" value="${user.id}"></th>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.profileDto.address}</td>
                    <td>${user.roleDto.name}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/web/users/${user.id}"
                           class="btn btn-primary"
                           aria-pressed="true"
                           role="button">UPDATE</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h6>Set discount</h6>

        <c:forEach items="${discounts}" var="discount">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="discountName" id="disc_${discount.id}"
                       value="${discount.name}"
                       checked>
                <label class="form-check-label" for="disc_${discount.id}">
                        ${discount.name}
                </label>
            </div>
        </c:forEach>
        <button class="btn-primary" type="submit">Set discount</button>
        </form:form>
    </div>
</div>
<div class="col-md-2"></div>
<ul class="pagination justify-content-center">
    <c:forEach var="page" begin="1" end="${pages}">
        <li class="page-item"><a class="page-link"
                                 href="${pageContext.request.contextPath}/web/users?page=${page}">${page}</a></li>
    </c:forEach>
</ul>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
