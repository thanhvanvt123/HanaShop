<%-- 
    Document   : confirm
    Created on : Jan 13, 2021, 10:56:55 AM
    Author     : AVITA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="container mt-3 border bg-light p-4" style="width: 500px" >
            <font color="red">Thank you for Buy In Our Shop! Your confirm has completed</font>
            <a class="btn btn-success" href="search.jsp">Go shopping</a>
            <c:set var="cart" value="${requestScope.CART}"/>
            <c:set var="mapFood" value="${cart.food}"></c:set>
            </div>
        <c:if test="${not empty mapFood}">
            <table class="container table table-bordered">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>FoodName</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Image</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cart.items}" varStatus="counter" >
                        <tr>
                            <c:set var="foodId" value="${item.key}"/>
                            <c:set var="amount" value="${item.value}"></c:set>
                            <c:set var="foodDto" value="${mapFood.get(foodId)}" ></c:set>

                            <td>${counter.count}</td>
                            <td>${foodDto.foodname}</td>
                            <td>${cart.getPriceDisplay(foodId)}</td>
                            <td>${foodDto.getCategoryname(foodDto.categoriID)}</td>
                            <td>${foodDto.description}</td>
                            <td>${amount}</td>
                            <td><img src="${foodDto.imageLink}" width="150"/></td> 
                            <td>${cart.getPriceOfEachItemDisplay(foodId)}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5"></td>
                        <td>Total Price: ${cart.totalPriceDisplay}</td>
                        <td colspan="2"></td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </body>
    <jsp:include page="footer.jsp"/>
</html>
