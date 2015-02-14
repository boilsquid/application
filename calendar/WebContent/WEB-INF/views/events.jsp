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

<title>SB Admin 2 - Bootstrap Admin Theme</title>

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

<!-- Custom Fonts -->
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
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
<script type="text/javascript">
	$(document).ready(function() {
		var startDate;
		/*
		// page is now ready, initialize the calendar...
		function getJsonData() {
		$.getJSON("<c:url value="/getdata"/>", useJsonData);
		}
		function useJsonData(data) {
			alert(data[0].title);
			
		}
		getJsonData();
		 */
		$('#calendar').fullCalendar({
			editable : true,
			selectable : true,
			selectHelper : true,
			ignoreTimezone : false,
			allDayDefault : false,
			defaultView : 'agendaWeek',
			//defaultView : agendaWeek,
			//controls for calendar show by day, week, month etc
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay',
				border : 0
			},

			dayClick : function(date, jsEvent, view) {

				startDate = date.format();

				$("#start").val(startDate);

				$("#popup").show();

			},

			eventSources : [

			// your event source
			{
				url : '<c:url value="/getdata"/>', // use the `url` property
				color : 'green', // an option!
				textColor : 'white' // an option!
			}

			// any other sources...

			]
		//end event sources

		})//end fullcalendar

		$('#cancel').click(function() {
			$("#popup").hide();
		});

	});//end document ready

	function onLoad() {
		$("#popup").hide();

	}

	$(document).ready(onLoad)
</script>

</head>

<body>
	<div class="row" id="popup">
		<div class="col-lg-12">
			<div class="col-md-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Calendar Entry</h3>
					</div>
					<div class="panel-body">
						<form action="${pageContext.request.contextPath}/createevent" role="form">
							<fieldset>
								<div class="form-group">
									<select name="title" form="title">
										<option value="volvo">cs333</option>
										<option value="saab">cs333</option>
										<option value="opel">cs3344</option>
										<option value="audi">vs2005</option>
									</select>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="location"
										name="location" type="text" value="">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Start Time" id="start"
										name="start" type="text" value="">
								</div>
								<div class="form-group">
									<select name="end" form="Duration">
										<option value="volvo">15</option>
										<option value="saab">30</option>
										<option value="opel">1 Hour</option>
										<option value="audi">2 Hours</option>
									</select>
								</div>
								<div class="form-group">
									<label>Description</label>
									<textarea class="form-control" rows="3"></textarea>
								</div>

								<button type="submit" class="btn btn-lg btn-success">Create</button>
								<button id="cancel" type="button" class="btn btn-lg btn-success">Cancel</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.col-lg-12 -->


	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">UCCTimeTabler</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="profile.html"><i class="fa fa-user fa-fw"></i>
							User Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
					<li class="divider"></li>
					<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li><a class="active" href="index.html"><i
							class="fa fa-calendar fa-fw"></i>Calendar</a></li>
					<li><a class="" href="${pageContext.request.contextPath}/setgroupview"><i
							class="fa fa-calendar-o fa-fw"></i>Show availability of Group
					<li><a class="" href="meeting.html"><i
							class="fa fa-comments-o fa-fw"></i>Requset Meeting</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12"></div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div id='calendar'></div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js"></script>
	<script>
		$(document).ready(function() {
			$('#dataTables-example').dataTable();
		});
	</script>
</body>
</html>