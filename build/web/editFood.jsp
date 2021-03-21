<%-- 
    Document   : updateFood
    Created on : Jan 9, 2021, 12:45:48 PM
    Author     : AVITA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Food</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <jsp:include page="navbar.jsp"/>
    <body>
        <div class="container mt-3 border bg-light p-4 mb-3" style="width: 500px" >
            <h1 class="text-center">Update Food</h1>
            <form action="DispatcherController?btAction=Update" method="POST" enctype="multipart/form-data">
                <c:set var="dto" value="${requestScope.FOODDTO}"></c:set>
                <c:set var="errors" value="${requestScope.CREATEERROR}"></c:set>
                <input type="hidden" name="txtFoodId" value="${dto.foodId}" />
                Food Name*: <input class="form-control" type="text" name="txtFoodname" value="${dto.foodname}" /> 
                <c:if test="${not empty errors.foodnameErr}">
                    <font color="red">${errors.foodnameErr}</font></br>     
                </c:if>
                Price*: <input class="form-control" type="text" name="txtPrice" value="${dto.foodprice}" />
                <c:if test="${not empty errors.foodpriceErr}">
                    <font color="red">${errors.foodpriceErr}</font></br>     
                </c:if>
                Quantity*: <input class="form-control" type="text" name="txtQuantity" value="${dto.quantity}" />
                <c:if test="${not empty errors.quantityErr}">
                    <font color="red">${errors.quantityErr}</font></br>     
                </c:if>
                Category*: 
                <select class="form-control form-control-line" name="txtCategory">
                    <c:set var="listCate" value="${sessionScope.LISTCATE}"/>
                    <c:forEach var="cate" items="${listCate}">
                        <option value="${cate.id}"
                                <c:if test="${dto.categoriID eq cate.id}">
                                    selected="selected"
                                </c:if>>Category : ${cate.name}</option>
                    </c:forEach>
                </select>
                Description*: <input class="form-control" type="text" name="txtDes" value="${dto.description}" /></br>
                <c:if test="${not empty errors.descriptionErr}">
                    <font color="red">${errors.descriptionErr}</font></br>     
                </c:if>
                <input type="hidden" name="txtImage" value="${dto.imageLink}" />
                Image: <img class="border rounded" src="${dto.imageLink}" width="350" height="220"/>
                Change Image: <input class="mt-2" type="file" name="fileImage" value="${param.fileImage}" /></br>
                <c:if test="${not empty errors.imageErr}">
                    <font color="red">${errors.imageErr}</font></br>     
                </c:if>
                <a class="btn btn-info mt-2" href="search.jsp">Back</a>
                <input class="btn btn-success mt-2 " type="submit" name="btAction" value="Update" />
            </form>
        </div>
    </body>
    <jsp:include page="footer.jsp"/>    
</html>
