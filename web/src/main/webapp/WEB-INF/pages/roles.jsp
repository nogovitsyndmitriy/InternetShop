<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Roles</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<form action="${pageContext.request.contextPath}/web/users/roles" method="post">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-dark">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Email</th>
                    <th scope="col">FirstName</th>
                    <th scope="col">LastName</th>
                    <th scope="col">Role</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th scope="row"><input type="checkbox" name="ids" value="${user.id}"></th>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.roleDto.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:forEach items="${roles}" var="role">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="roleId" id="exampleRadios1" value="${role.id}"
                       checked>
                <label class="form-check-label" for="exampleRadios1">
                        ${role.name}
                </label>
            </div>
            </c:forEach>
            <button class="btn btn-warning" type="submit">Change ROLE</button>
</form>
</body>
<ul class="pagination justify-content-center">
    <c:forEach var="page" begin="1" end="${pages}">
        <li class="page-item"><a class="page-link"
                                 href="${pageContext.request.contextPath}/web/users/roles">${page}</a></li>
    </c:forEach>
</ul>
<jsp:include page="common/footer.jsp"/>
</html>