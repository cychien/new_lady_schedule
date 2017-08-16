$(document).ready(function() {
	$("#sidebar").niceScroll({
		cursorcolor : '#53619d',
		cursorwidth : 4,
		cursorborder : 'none'
	});

	$('#sidebarCollapse').on('click', function() {
		$('#sidebar, #content').toggleClass('active');
		$('.collapse.in').toggleClass('in');
		$('a[aria-expanded=true]').attr('aria-expanded', 'false');
	});
});