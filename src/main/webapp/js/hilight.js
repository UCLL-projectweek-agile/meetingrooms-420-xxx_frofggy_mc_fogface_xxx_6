/**
 * 
 */

$(document).ready(function() {
	
	var data = {};
	$('.map').maphilight();
	data.alwaysOn = true;
	data.fillColor = '00ff00';
	data.fillOpacity = '0.6';
	$('.green').data('maphilight', data).trigger('alwaysOn.maphilight');
});

$(document).ready(function() {
	var data = {};
	$('.map').maphilight();
	data.alwaysOn = true;
	data.fillColor = 'ff0000';
	data.fillOpacity = '0.6';
	$('.red').data('maphilight', data).trigger('alwaysOn.maphilight');
});