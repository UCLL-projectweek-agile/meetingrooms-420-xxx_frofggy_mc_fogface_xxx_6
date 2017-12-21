<%@page import="domain.*"%>
<%@page import="db.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp" />

	
		<h2>Lokaal Overview</h2>
		<main>
		<table>
			<tr>
				<th>Naam</th>
				<th>07</th>
				<th>08</th>
				<th>09</th>
				<th>10</th>
				<th>11</th>
				<th>12</th>
				<th>13</th>
				<th>14</th>
				<th>15</th>
				<th>16</th>
				<th>17</th>
				<th>18</th>
				<th>19</th>
				<th>20</th>
				<th>21</th>
				<th>22</th>
				<th>23</th>
			</tr>

			<c:forEach var="afspraak" items="${afspraken}">

				<tr>
					<td>${afspraak.lokaal.lokaalID}</td>
					<td>${afspraak.startHour}</td>
					<td>${afspraak.startMinute}</td>
					<td>${afspraak.endHour}</td>
					<td>${afspraak.endMinute}</td>

					<td></td>
				</tr>
			</c:forEach>
		</table>
		</main>
		<div class="panel-footer">
		<p>&copy; Company | Privacy | Terms</p>
		</div>
	
</body>
</html>