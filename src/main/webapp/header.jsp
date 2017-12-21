<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://use.fontawesome.com/6a6b824a28.js"></script>
	<script src="https://code.jquery.com/jquery-3.1.1.js" type="text/javascript"></script>
	<script src="./js/jquery.maphilight.js" type="text/javascript"></script>
	<script src="./js/hilight.js" type="text/javascript"></script>
	<script src="./js/imageMapResizer.js" type="text/javascript"></script>
	<title>${param.pageTitle }</title>
	<c:if test="${param.pageTitle == 'Timetable' }">
	<link rel="stylesheet" href="css/demo.css">
	</c:if>

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="Servlet?action=index">Reservatie
					lokalen</a>
			</div>
			<ul class="nav navbar-nav">
				<c:if test="${param.pageTitle == 'Home' }">
					<li class="active"><a href="Servlet?action=index">Home</a></li>
				</c:if>
				<c:if test="${param.pageTitle != 'Home' }">
					<li><a href="Servlet?action=index">Home</a></li>
				</c:if>
				<c:if test="${param.pageTitle == 'Timetable' }">
					<li class="active"><a href="Servlet?action=timetable">Timetable</a></li>
				</c:if>
				<c:if test="${param.pageTitle != 'Timetable' }">
					<li><a href="Servlet?action=timetable">Timetable</a></li>
				</c:if>
			</ul>
		</div>
	</nav>