<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="Home" name="pageTitle"/>
</jsp:include>


<div class="timetable"><div style="margin:auto"></div></div>
	<script src="js/timetable.js"></script>
	<div id="afspraak">
		<c:forEach var="afspraak" items="${afspraken}">
			<afspraak startYear="${afspraak.startYear }"
				endYear="${afspraak.endYear }" startMonth="${afspraak.startMonth }"
				endMonth="${afspraak.endMonth}" startDay="${afspraak.startDay}"
				endDay="${afspraak.endDay }" startHour="${afspraak.startHour }"
				endHour="${afspraak.endHour }"
				startMinute="${afspraak.startMinute }"
				endMinute="${afspraak.endMinute }" lokaal="${afspraak.lokaal }"
				description="${afspraak.desc }"></afspraak>
		</c:forEach>
	</div>
	<script>
        var timetable = new Timetable();
        
        //set the starttime and the endtime to whatever is specified
        //can update the fist parameter to the hour that is current later
        var date = new Date();
        var hour = date.getHours();
        timetable.setScope(7, 23)
        //this will hold the different spaces -- Name of the river
        //static
       
        timetable.addLocations(['Donau', 'Arno', 'Po', 'Schelde', 'Chao-Praya', 'Douro', 'Maas', 'Ebro', 'Moselle', 'Rhone', 'Sarine', 'Yangtze', 'Thames']);
        //adds the reservetion to the river
        //(Subject, name river, date start, date end, { can add a url if wanted later})
		var x = document.getElementById("afspraak").children
		for (var i = 0; i < x.length; i++) {
       	timetable.addEvent(x[i].getAttribute('description'), x[i].getAttribute('lokaal'), new Date(x[i].getAttribute('startYear'),x[i].getAttribute('startMonth'),x[i].getAttribute('startDay'), x[i].getAttribute('startHour'),x[i].getAttribute('startMinute')), 
       			new Date(x[i].getAttribute('endYear'),x[i].getAttribute('endMonth'),x[i].getAttribute('endDay'), x[i].getAttribute('endHour'),x[i].getAttribute('endMinute')));
		}
        var renderer = new Timetable.Renderer(timetable);
        renderer.draw('.timetable');
        //CopyRight goes to Grible, no site only github: //https://github.com/Grible/timetable.js
    </script>
<div class="container-fluid no-padding">
  <div class="row">
    <div class="col-md-12">
   
 <img class="map img-responsive" src="./images/mapLokalen.PNG" border="0" usemap="#image-maps-2017-12-20-045103" alt="" />
<map name="image-maps-2017-12-20-045103" id="ImageMapsCom-image-maps-2017-12-20-045103">
<area class="${D160}" id="D160" alt="" title="D160" href="#" shape="rect" coords="2,14,151,145" style="outline:none;" target="_self"     />
<area class="${D159}" id="D159" alt="" title="D159" href="#" shape="rect" coords="2,151,191,269" style="outline:none;" target="_self"     />
<area class="${D156}" id="D156" alt="" title="D156" href="#" shape="rect" coords="195,150,383,268" style="outline:none;" target="_self"     />
<area class="${D157}" id="D157" alt="" title="D157" href="#" shape="rect" coords="239,10,339,145" style="outline:none;" target="_self"     />
<area class="${D154}" id="D154" alt="" title="D154" href="#" shape="rect" coords="426,14,574,145" style="outline:none;" target="_self"     />
<area class="${D155}" id="D155" alt="" title="D155" href="#" shape="rect" coords="387,151,574,269" style="outline:none;" target="_self"     />
<area class="${D153}" id="D153" alt="" title="D153" href="#" shape="rect" coords="579,11,764,269" style="outline:none;" target="_self"     />
<area class="${D149}" id="D149" alt="" title="D149" href="#" shape="rect" coords="772,15,920,145" style="outline:none;" target="_self"     />
<area class="${D148}" id="D148" alt="" title="D148" href="#" shape="rect" coords="771,150,959,269" style="outline:none;" target="_self"     />
<area class="${D146}" id="D146" alt="" title="D146" href="#" shape="rect" coords="1007,10,1107,145" style="outline:none;" target="_self"     />
<area class="${D145}" id="D145" alt="" title="D145" href="#" shape="rect" coords="963,151,1150,268" style="outline:none;" target="_self"     />
<area class="${D143}" id="D143" alt="" title="D143" href="#" shape="rect" coords="1195,15,1344,146" style="outline:none;" target="_self"     />
<area class="${D144}" id="D144" alt="" title="D144" href="#" shape="rect" coords="1156,149,1343,268" style="outline:none;" target="_self"     />
<area shape="rect" coords="1420,267,1422,269" alt="Image Map" style="outline:none;" title="Image Map" href="#" />
</map>  
    </div>
  </div>
</div>

<script type="text/javascript">
		$('map').imageMapResize();
</script>

<jsp:include page="footer.jsp"></jsp:include>