<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Read News</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<div class="news-area">
    <div class="news_title"><h3>${news.title}</h3></div>
    <div class="news_content">${news.content}</div>
    <div class="news_created"><i>${news.created.dayOfMonth}/${news.created.monthValue}/${news.created.year}
        ${news.created.hour}:${news.created.minute}:${news.created.second}</i></div>
    <div class="news_author"><i>Created by ${news.user.name} ${news.user.surname}</i></div>
</div>
<div class="comment_area">
    <c:forEach items="${comments}" var="comment">
        <div class="comment_author"><i><b>${comment.userDto.name}</b></i></div>
        <div class="comment_created">
            <i>${comment.created.dayOfMonth}/${comment.created.monthValue}/${comment.created.year}
                    ${comment.created.hour}:${comment.created.minute}:${comment.created.second}</i></div>
        <div class="comment_text">${comment.content}</div>
    </c:forEach>
</div>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/web/news/comment/${news.id}" modelAttribute="comment"
               method="post" cssClass="form">
    <div class="form group">
        <form:label path="content">Type your comment</form:label>
        <form:input path="content" placeholder="content"/>
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
    </form:form>
</body>
<jsp:include page="common/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</html>
