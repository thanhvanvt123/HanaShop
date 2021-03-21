<%-- 
    Document   : admin
    Created on : Jun 10, 2020, 7:45:36 PM
    Author     : AVITA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <h2 class="container bg-light text-center mt-5"> Manager Products Food</h2>
        <c:set var="products" value="${sessionScope.AllPRODUCT}"/>
        <div class="container mt-5" style="width: 1150px">
            <c:if test="${not empty products}">
                <table class=" table table-bordered">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Food Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Description</th>
                            <th>CreateDate</th>
                            <th>Category</th>
                            <th>Image</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${products}" varStatus="counter">
                            <tr>
                                <td>${counter.count} </td>
                                <td>${dto.foodname}</td>
                                <td>${dto.foodprice}</td>
                                <td>${dto.quantity}</td>
                                <td>${dto.description}</td> 
                                <td>${dto.createDate}</td>
                                <td>${dto.getCategoryname(dto.categoriID)}</td>
                                <td>
                                    <img class="border rounded" src="${dto.imageLink}" width="150" height="180"/>
                                </td>
                                <td>
                                    <c:if test="${not empty sessionScope.USER || sessionScope.USER.role.name == 'Admin'}">
                                        <form action="DispatcherController" method="POST">
                                            <c:url var="editUrl" value="DispatcherController?btAction=Edit">
                                                <c:param name="ID" value="${dto.foodId}"> </c:param>
                                                <c:param name="pageNum" value="${param.pageNum}"> </c:param>
                                            </c:url>
                                            <a class="btn btn-success" href="${editUrl}">Edit</a>
                                            <c:url var="deleteUrl" value="DispatcherController?btAction=Delete">
                                                <c:param name="ID" value="${dto.foodId}"> </c:param>
                                                <c:param name="pageNum" value="${param.pageNum}"> </c:param>
                                            </c:url>
                                            <a class="btn btn-danger" href="${deleteUrl}" onclick="return confirm('Are you sure to Delete Food?');">Delete</a>
                                        </form>
                                    </c:if>
                                </td>
<!--                                <td>
                                    <input type="checkbox" name="chkItem" value="${dto.foodId}"/>
                                </td>-->
                            </tr>
                        </c:forEach>
<!--                    <td>
                        <input type="submit" value="Remove Selected Books" />
                    </td>-->
                    </tbody>
                </table>
                <c:if test="${not empty products}">
                    <div class="form-row mb-4">
                        <c:if test="${not empty param.pageNum}">
                            <p>   Page  [ ${param.pageNum} ]: </p>
                        </c:if>
                        <c:if test="${empty param.pageNum}">
                            <p>   Page  [ 1 ]: </p>
                        </c:if>
                        <c:forEach begin="1" end="${requestScope.PAGENUMBER}" varStatus="counter" step="1">
                            <form action="ManagerFoodServlet" method="POST">
                                <input type="hidden" name="pageNum" value="${counter.count}" />
                                <input id="page" class="btn btn-primary ml-5" type="submit" value="${counter.count}"/>
                            </form>
                        </c:forEach>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${not empty param.txtSearchCategory && empty products}">
                <h5 class="alert alert-danger text-center">Not Found!!!!!</h5>
            </c:if>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
