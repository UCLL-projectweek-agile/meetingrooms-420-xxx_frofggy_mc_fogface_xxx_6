<!DOCTYPE html>
<%@page import="domain.*"%>
<%@page import="db.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <title>Lokalen</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://use.fontawesome.com/6a6b824a28.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Reservatie lokalen</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="#">Home</a></li>
      <li class = "active"><a href="Servlet?action=current">Overzicht</a></li>
      <li><a href="#">Reserveer</a></li>
      <li><a href="#">Registreer</a></li>
    </ul>
  </div>
</nav>
		 <h1>Lokalen nu bezet:</h1>
		<c:forEach var="appointment" items="${appointments}">
            <p>${appointment}</p>
        </c:forEach>
 	<div class="panel-footer">
 
        <p>&copy; Company | Privacy | Terms</p>
 
      </div>
</body>