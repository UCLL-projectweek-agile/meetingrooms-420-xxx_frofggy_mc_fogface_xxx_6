<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>

		 
		 <div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
  <h1>Lokalen nu bezet:</h1>
  <c:forEach var="appointment" items="${appointments}">
            <p>${appointment}</p>
        </c:forEach>
  </div>
  <div class="col-md-4"></div>
</div>
		
        
        
 	<div class="panel-footer">
		<p>&copy; Company | Privacy | Terms</p>
	</div>
</body>