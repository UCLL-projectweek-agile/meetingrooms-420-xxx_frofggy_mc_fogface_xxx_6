<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/demo.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

    <div class="timetable"></div>

    <script src="js/timetable.js"></script>
    <div id="afspraak">
	<c:forEach var="afspraak" items="${afspraken}">
		<afspraak startYear="${afspraak.startYear }" 
		endYear="${afspraak.endYear }" 
		startMonth="${afspraak.startMonth }" 
		endMonth="${afspraak.endMonth}" 
		startDay="${afspraak.startDay}" 
		endDay="${afspraak.endDay }"
		startHour="${afspraak.startHour }"
		endHour="${afspraak.endHour }"
		startMinute="${afspraak.startMinute }"
		endMinute="${afspraak.endMinute }"
		lokaal="${afspraak.lokaal }"
		description="${afspraak.desc }"></afspraak>
	</c:forEach>
	</div>
    <script>
        var timetable = new Timetable();
        
        //set the starttime and the endtime to whatever is specified
        //can update the fist parameter to the hour that is current later
        timetable.setScope(7, 23)
        //this will hold the different spaces -- Name of the river
        //static
       
        timetable.addLocations(['Donau', 'Arno', 'Po', 'Schelde', 'Chao-Praya', 'Douro', 'Maas', 'Ebro', 'Moselle', 'Rhone', 'Sarine', 'Yangtze', 'Thames']);
        //adds the reservetion to the river
        //(Subject, name river, date start, date end, { can add a url if wanted later})
		var x = document.getElementById("afspraak").children
		console.log(x);
		for (var i = 0; i < x.length; i++) {
       	timetable.addEvent(x[i].getAttribute('description'), x[i].getAttribute('lokaal'), new Date(x[i].getAttribute('startYear'),x[i].getAttribute('startMonth'),x[i].getAttribute('startDay'), x[i].getAttribute('startHour'),x[i].getAttribute('startMinute')), 
       			new Date(x[i].getAttribute('endYear'),x[i].getAttribute('endMonth'),x[i].getAttribute('endDay'), x[i].getAttribute('endHour'),x[i].getAttribute('endMinute')));
		}
        var renderer = new Timetable.Renderer(timetable);
        renderer.draw('.timetable');
        //CopyRight goes to Grible, no site only github: //https://github.com/Grible/timetable.js
    </script>
</body>
</html>