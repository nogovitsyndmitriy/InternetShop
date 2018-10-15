<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments</title>
</head>
<body class="body">
<jsp:include page="common/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/web/comment/delete" method="post">
                <div class="row">
                    <div class="col-md-12">
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
                                <th scope="col">Content</th>
                                <th scope="col">Created</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${comments}" var="comment">
                                <tr>
                                    <th scope="row"><input type="checkbox" name="ids" value="${comment.id}"></th>
                                    <td>${comment.userDto.email}</td>
                                    <td>${comment.content}</td>
                                    <td>${comment.created}</td>
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
                                 href="${pageContext.request.contextPath}/web/comment?page=${page}">${page}</a></li>
    </c:forEach>
</ul>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
