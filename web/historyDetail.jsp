<%-- 
    Document   : confirm
    Created on : Jan 17, 2021, 7:56:55 AM
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
        <div class="container mt-3 border bg-light p-4 mb-5"  >
            <a class="btn btn-success text-center" href="search.jsp">Go shopping</a>
            <c:set var="historyList" value="${sessionScope.ALLHISTORY}"/>
            <c:set var="hisById" value="${sessionScope.DETAILBYID}"/>
        </div>
        <c:if test="${not empty historyList}">
            <table class="container table table-bordered  p-4">
                <thead>
                    <tr>
                        <th>HistoryCode</th>
                        <th>DateBuy</th>
                        <th>Total</th>
                        <th>Food Name</th>
                        <th>Amount</th>>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${historyList}" varStatus="counter" >
                         <c:set var="foodId" value="${hisById.foodId}"/>
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.importedDate}</td>
                            <td>${item.total}</td>
                            <td>${hisById.getFoodName(foodId)}</td>
                            <td>${hisById.amount}</td>                               
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty historyList}">
            <h5 class="alert alert-danger text-center">Not Have History!!!!!</h5>
        </c:if>
    </body>
    <c:if test="${not empty historyList}">
        <jsp:include page="footer.jsp"/>
    </c:if>
</html>
