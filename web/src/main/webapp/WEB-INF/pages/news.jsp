<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>News</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<security:authorize access="hasAuthority('CREATE_NEWS')">
    <div class="row">
        <form action="${pageContext.request.contextPath}/web/news/delete" method="post">
            <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/web/news/create" class="btn btn-primary"
                   aria-pressed="true"
                   role="button">Create News</a>
                <button type="submit" class="btn btn-primary">DELETE</button>
            </div>
    </div>
</security:authorize>
<div class="col-md-12">
    <h1 align="center">News</h1>
    <table class="table table-striped table-dark col-md-7" align="center">
        <thead>
        <tr align="center">
            <th>#</th>
            <th>Title</th>
            <th>Author</th>
            <th>Created</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${news}" var="news">
            <tr align="center">
                <th scope="row"><input type="checkbox" name="ids" value="${news.id}"></th>
                <td>${news.title}</td>
                <td>${news.user.name} ${news.user.surname}</td>
                <td>${news.created.dayOfMonth}-${news.created.monthValue}-${news.created.year}
                        ${news.created.hour}:${news.created.minute}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/web/news/${news.id}" class="btn btn-primary"
                       aria-pressed="true"
                       role="button">Read</a>
                </td>
                <security:authorize access="hasAuthority('UPDATE_NEWS')">
                    <td>
                        <a href="${pageContext.request.contextPath}/web/news/${news.id}/update"
                           class="btn btn-primary"
                           aria-pressed="true"
                           role="button">UPDATE</a>
                    </td>
                </security:authorize>
            </tr>
        </c:forEach>
        </tbody>
        </form>
    </table>
    <ul class="pagination justify-content-center">
        <c:forEach var="page" begin="1" end="${pages}">
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/web/news?page=${page}">${page}</a></li>
        </c:forEach>
    </ul>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
