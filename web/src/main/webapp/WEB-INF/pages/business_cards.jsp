<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Business Cards</title>
</head>
<jsp:include page="common/header.jsp"/>
<body class="body">
<div class="col-md-12">
    <table class="table table-striped table-dark">
        <thead>

        <tr>
            <th scope="col">Title</th>
            <th scope="col">Full Name</th>
            <th scope="col">Phone Number</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cards}" var="card">
        <tr>
            <td>${card.title}</td>
            <td>${card.fullName}</td>
            <td>${card.workingTelephone}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<form:errors path="*"/>
<div class="reg_form text-center">
    <form:form action="${pageContext.request.contextPath}/web/users/cards/create" modelAttribute="card" method="post" cssClass="form">
        <form:errors path="*" cssClass="error" element="div" />
        <div class="form group">
            <form:label path="title">Title</form:label>
            <form:input path="title" placeholder="Title"/>
        </div>
        <div class="form group">
            <form:label path="fullName">Full Name</form:label>
            <form:input path="fullName" placeholder="Full Name"/>
        </div>
        <div class="form group">
            <form:label path="workingTelephone">Phone</form:label>
            <form:input path="workingTelephone" placeholder="Phone"/>
        </div>
        <button type="submit" class="btn btn-primary">Create User</button>
    </form:form>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>

