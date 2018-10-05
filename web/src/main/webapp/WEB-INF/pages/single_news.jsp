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
        <div class="comment_author"><i><b>${comment.id}</b></i></div>
    <div class="comment_created"><i>${comment.created.dayOfMonth}/${comment.created.monthValue}/${comment.created.year}
            ${comment.created.hour}:${comment.created.minute}:${comment.created.second}</i></div>
    <div class="comment_text">${comment.content}</div>
    </c:forEach>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
