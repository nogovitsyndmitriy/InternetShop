<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/web/news/create" modelAttribute="newsDto" method="post" cssClass="form">
        <form:errors path="*" cssClass="errorblock" element="div"/>
        <div class="form group">
            <form:label path="title">Title</form:label>
            <form:input path="title" placeholder="title"/>
        </div>
        <div class="form group">
            <form:label path="content">Content</form:label>
            <form:input path="content" placeholder="Content"/>
        </div>
        <button type="submit" class="btn btn-primary">Create News</button>
    </form:form>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
</html>
