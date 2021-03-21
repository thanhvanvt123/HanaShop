<%-- 
    Document   : errorPage
    Created on : Jan 6, 2021, 3:58:21 PM
    Author     : AVITA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <h1 class="container btn btn-danger mt-5 ml-5"> Error Update, Pood Has buying!!</h1>
        <h1 class="container btn btn-success mt-5 ml-5"> <a href="DispatcherController?btAction=Manager"> Manager Foods </a></h1>

    </body>
</html>
