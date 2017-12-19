<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<footer>
	&copy; Webontwikkeling 3, UC Leuven-Limburg
	<form method="post" action="Controller?action=SwitchColour" novalidate="novalidate">
	<input type="text" id="page" name="page" value = "<c:out value='${paginawaarden}'/>" required value="">
	<input type="submit" id="Switch Colour" value="Switch Colour">
	</form>
</footer>