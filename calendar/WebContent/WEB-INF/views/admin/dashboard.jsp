<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Administration</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/plugins/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<link
	href="${pageContext.request.contextPath}/resources/css/sortable-theme-bootstrap.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/resources/css/fullcalendar.css' />
<script
	src='${pageContext.request.contextPath}/resources/lib/jquery.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/lib/moment.min.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/js/fullcalendar.js'></script>

<!-- Include jQuery Popup Overlay -->
<script
	src='${pageContext.request.contextPath}/resources/js/jquery.popupoverlay.js'></script>
<!-- include validation jquery plugin -->
<script
	src='${pageContext.request.contextPath}/resources/js/jquery.validate.js'></script>
<script
	src='${pageContext.request.contextPath}/resources/js/sortable.js'></script>
<!-- Bootstrap Core JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js"></script>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		<jsp:include page="../layouts/navigation/side_nav.jsp" />
		<div id="page-wrapper">
			<div id="main-content">
				<div class="row">
					<div class="col-xs-12">
						<div id="dashboard-heading">
							<h3>Calendar Dashboard</h3>
						</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="panel panel-primary user-graph-panel">
							<div class="panel-heading">Recent Users</div>
							<div id="userchart" style="height: 250px; max-width: 1000px;"></div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="panel panel-green user-graph-panel">
							<div class="panel-heading">Recent Meetings</div>
							<div id="meetingchart" style="height: 250px; max-width: 1000px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer> 
	<script type="text/javascript">
		new Morris.Line({
			// ID of the element in which to draw the chart.
			element : 'userchart',
			// Chart data records -- each entry in this array corresponds to a point on
			// the chart.
			data : [ {
				day : '2015-02-21',
				value : 20
			}, {
				day : '2015-02-22',
				value : 10
			}, {
				day : '2015-02-23',
				value : 5
			}, {
				day : '2015-02-24',
				value : 5
			}, {
				day : '2015-02-25',
				value : 20
			} ],
			// The name of the data record attribute that contains x-values.
			xkey : 'day',
			// A list of names of data record attributes that contain y-values.
			ykeys : [ 'value' ],
			// Labels for the ykeys -- will be displayed when you hover over the
			// chart.
			labels : [ 'Value' ]
		});
		new Morris.Line({
			// ID of the element in which to draw the chart.
			element : 'meetingchart',
			// Chart data records -- each entry in this array corresponds to a point on
			// the chart.
			data : [ {
				day : '2015-02-21',
				value : 7
			}, {
				day : '2015-02-22',
				value : 10
			}, {
				day : '2015-02-23',
				value : 30
			}, {
				day : '2015-02-24',
				value : 1
			}, {
				day : '2015-02-25',
				value : 17
			} ],
			// The name of the data record attribute that contains x-values.
			xkey : 'day',
			// A list of names of data record attributes that contain y-values.
			ykeys : [ 'value' ],
			// Labels for the ykeys -- will be displayed when you hover over the
			// chart.
			labels : [ 'Value' ]
		});
	</script> </footer>
</body>
</html>