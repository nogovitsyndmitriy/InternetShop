<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="icon" href="../../utils/favicon%20.ico" type="image/x-icon">
<style>
    <%@include file="../../utils/styles.css"%>
</style>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/web/users/current">Update Info<span
                                class="sr-only">(current)</span></a>
                    </li>
                </security:authorize>
                <security:authorize access="hasAuthority('VIEW_USERS')">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/web/users">Users<span
                                class="sr-only">(current)</span></a>
                    </li>
                </security:authorize>
                <security:authorize access="hasAuthority('VIEW_ITEMS')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/web/items">Items</a>
                    </li>
                </security:authorize>
                <security:authorize access="hasAuthority('UPLOAD_ITEM')">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/web/items/manage">Manage Items</a>
                    </li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/web/news">News</a>
                </li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <security:authorize access="hasAuthority('CHANGE_STATUS')">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/web/orders/admin">Orders</a>
                        </li>
                    </security:authorize>
                    <security:authorize access="hasAuthority('SHOW_PERSONAL_ORDERS')">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/web/orders">Orders</a>
                        </li>
                    </security:authorize>
                    <security:authorize access="hasAuthority('MANAGE_BUSINESS_CARD')">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/web/users/cards">Cards</a>
                        </li>
                    </security:authorize>
                    <security:authorize access="hasAuthority('DELETE_COMMENTS')">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/web/comment">Comments</a>
                        </li>
                    </security:authorize>
                    <li><span>Hi, <security:authentication property="principal.username"/>!</span></li>
                </security:authorize>
            </ul>
            <security:authorize access="isAnonymous()">
                <a href="${pageContext.request.contextPath}/web/registration">
                    <button class="btn btn-primary">Registration</button>
                </a>
                <a href="${pageContext.request.contextPath}/web/login">
                    <button class="btn btn-primary">Login</button>
                </a>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/web/logout">
                    <button class="btn btn-primary">LogOut</button>
                </a>
            </security:authorize>
        </div>
    </nav>
</header>