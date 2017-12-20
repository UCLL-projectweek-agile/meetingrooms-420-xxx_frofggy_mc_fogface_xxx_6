/**
 * 
 */

$(document).ready(function() {
	console.log("test2")
	var data = {};
	$('.map').maphilight();
	data.alwaysOn = true;
	data.fillColor = '00ff00';
	data.fillOpacity = '0.6';
	$('.free').data('maphilight', data).trigger('alwaysOn.maphilight');
});