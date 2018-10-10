<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users page</title>
</head>
<body class="body">
<jsp:include page="common/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/web/users/delete" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/web/registration" class="btn btn-primary"
                           aria-pressed="true"
                           role="button">ADD</a>
                        <a href="${pageContext.request.contextPath}/web/users/roles" class="btn btn-primary"
                                                   aria-pressed="true"
                                                   role="button">ROLES</a>
                        <button type="submit" class="btn btn-primary">DELETE</button>
                    </div>
                </div>
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
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<ul class="pagination justify-content-center">
    <c:forEach var="page" begin="1" end="${pages}">
        <li class="page-item"><a class="page-link"
                                 href="${pageContext.request.contextPath}/web/users?page=${page}">${page}</a></li>
    </c:forEach>
</ul>
<jsp:include page="common/footer.jsp"/>
</body>
</html>