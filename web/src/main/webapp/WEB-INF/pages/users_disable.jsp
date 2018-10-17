<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<form action="${pageContext.request.contextPath}/web/users/disable" method="post" class="form-action">
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
                    <th scope="col">IS DISABLE</th>
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
                        <td>${user.disabled}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="disabled" id="exampleRadios1" value="true"
                       checked>
                <label class="form-check-label" for="exampleRadios1">
                    Disable User
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="disabled" id="exampleRadios2"
                       value="false">
                <label class="form-check-label" for="exampleRadios2">
                    Enable User
                </label>
            </div>
            <button class="btn btn-warning" type="submit">Apply</button>
</form>
</body>
<ul class="pagination justify-content-center">
    <c:forEach var="page" begin="1" end="${pages}">
        <li class="page-item"><a class="page-link"
                                 href="${pageContext.request.contextPath}/web/users?page=${page}">${page}</a></li>
    </c:forEach>
</ul>
<jsp:include page="common/footer.jsp"/>
</html>