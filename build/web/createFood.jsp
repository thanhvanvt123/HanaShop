<%-- 
    Document   : createNewFood
    Created on : Jan 5, 2021, 12:45:48 PM
    Author     : AVITA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Food</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <jsp:include page="navbar.jsp"/>
    <body>
        <div class="container mt-3 border bg-light p-4" style="width: 500px" >
            <h1 class="text-center">Create New Food</h1>
            <form action="DispatcherController?btAction=Add New Food" method="POST" enctype="multipart/form-data">
                <c:set var="errors" value="${requestScope.CREATEERROR}"></c:set>
                Food Name*: <input class="form-control" type="text" name="txtFoodname" value="${param.txtFoodname}" />  (2 - 70 characters)</br>
                <c:if test="${not empty errors.foodnameErr}">
                    <font color="red">${errors.foodnameErr}</font></br>     
                </c:if>
                Price*: <input class="form-control" type="text" name="txtPrice" value="${param.txtPrice}" /></br>
                <c:if test="${not empty errors.foodpriceErr}">
                    <font color="red">${errors.foodpriceErr}</font></br>     
                </c:if>
                Quantity*: <input class="form-control" type="text" name="txtQuantity" value="${param.txtQuantity}" /></br>
                <c:if test="${not empty errors.quantityErr}">
                    <font color="red">${errors.quantityErr}</font></br>     
                </c:if>
                Category*: 
                <select class="form-control form-control-line" name="txtCategory">
                    <c:set var="listCate" value="${sessionScope.LISTCATE}"/>
                    <c:forEach var="cate" items="${listCate}">
                        <option value="${cate.id}"
                                <c:if test="${param.txtSearchCategory eq cate.id}">
                                    selected="selected"
                                </c:if>>Category : ${cate.name}</option>
                    </c:forEach>
                </select>
                <c:if test="${not empty errors.categoriIDErr}">
                    <font color="red">${errors.categoriIDErr}</font></br>     
                </c:if>
                Description*: <input class="form-control" type="text" name="txtDes" value="${param.txtDes}" /> (2 - 300 characters)</br>
                <c:if test="${not empty errors.descriptionErr}">
                    <font color="red">${errors.descriptionErr}</font></br>     
                </c:if>
                Image*: <input type="file" name="fileImage" value="${param.fileImage}" /></br>
                <c:if test="${not empty errors.imageErr}">
                    <font color="red">${errors.imageErr}</font></br>     
                </c:if>
                <input class="btn btn-success mt-2" type="submit" name="btAction" value="Add New Food" />
                <input class="btn-light btn border mt-2" type="reset" value="Reset" />
            </form>
        </div>
    </body>
    <jsp:include page="footer.jsp"/>    
</html>
