<%-- 
    Document   : login
    Created on : Jan 7, 2021, 9:39:18 AM
    Author     : AVITA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container mt-5 border bg-light p-4" style="width: 400px" >
            <h1 class="text-center">Login Page</h1>
            <form action="DispatcherController" method="POST">
                Username: <input class="form-control" type="text" name="txtUsername" value="${param.txtUsername}" /></br>
                Password: <input class="form-control" type="password" name="txtPassword" value="" /></br>
                <input class="btn btn-success mt-2 px-4" type="submit" name="btAction" value="Login" />
                <input class="btn btn-light border mt-2 px-4" type="reset" value="Reset" />
            </form>
                <c:set var="error" value="${requestScope.LOGINFAIL}"/>
            <c:if test="${not empty error}" >
                <div class="container mt-5 border bg-light p-4" style="width: 350px" >
                    <h2 class="text-danger">${error}</h2>
                </div>
            </c:if>
        </div>
    </body>
</html>
