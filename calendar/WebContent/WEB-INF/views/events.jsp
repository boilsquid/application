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

<!-- Include jQuery Popup Overlay -->
<script
	src='${pageContext.request.contextPath}/resources/js/jquery.popupoverlay.js'></script>


<script type="text/javascript">

	$(document).ready(function() {
		var startDate;
		var test;
		var newId;
		
		

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
			defaultDate : moment('2015-09-25'),/* default date when calendar opens for testing*/
			editable : true,
			selectable : true,
			selectHelper : true,
			allDay : false,
			defaultView : 'agendaWeek',
			minTime : "08:00:00",
			maxTime : "19:00:00",// only show from 8 am to 7 pm on calender
			weekends : false, //don't show weekends on calender

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

				$("#start").val(startDate);//display this date

				$("#popup").popup('show');

			},

			/* edit function on click*/
			eventClick : function(calEvent, jsEvent, view) {

				newId = calEvent.id;
				alert('Event: ' + newId);
				$("#deletePopup").popup('show');

				$('#delete').click(function() {
					if (confirm('Do you wan\'t to delete this item?')) {
						deleteEvent(calEvent.id);
						$("#deletePopup").popup('hide');
					} else {
						$("#deletePopup").popup('hide');
					}
				});

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

		$('#cancel').click(function() {//hide the popup form when you cancel the form
			$("#popup").popup('hide');
			$("#check").hide();
			$('#recurs').val("no");//reset this value, which resets form to default

			/* enabled fields when user clicks cancel*/
			$("#numWeeks").prop("disabled", false);
			$("#start").prop("disabled", false);
			$("#end").prop("disabled", false);
		});

		$('#create').click(function() {//enable fields before form is submitted

			/* enabled fields when user clicks create*/
			$("#numWeeks").prop("disabled", false);
			$("#start").prop("disabled", false);
			$("#end").prop("disabled", false);
		});
		
		 // Initialize the plugin
	      $('#deletePopup').popup();
	      $('#popup').popup();

	
		$("select").change(function() {
			$("select option:selected").each(function() {// hide and show form values depending if the meeting is recurring

				if ($(this).attr("value") == "no") {
					$("#amount").hide();
					$("#check").hide();
					$("#create").show();
				}
				if ($(this).attr("value") == "yes") {
					$("#amount").show();
					$("#check").show();
					$("#create").hide();
					/*
					do some test to only show create available test is done
					for now will just block it altogether
					 */

				}

			});
		}).change();
	});//end document ready

	$(document).ready(function() {
		$("select").change(function() {
			$("select option:selected").each(function() {

				if ($(this).attr("value") == "tutorial") {
					$("#module").show();
				}
				if ($(this).attr("value") == "meeting") {
					$("#module").hide();
				}
				if ($(this).attr("value") == "personal") {
					$("#module").hide();
				}

			});
		}).change();
	});

	function success(data) {
		alert("This timeslot is available for " + data.allowWeeks);
		$("#numWeeks").val(data.allowWeeks);
		$("#numWeeks").prop("disabled", true);
		$("#start").prop("disabled", true);
		$("#end").prop("disabled", true);/*disable this fields so user cant create bad events*/
		$("#create").show();
	}

	function error(data) {
		alert("error");
	}

	function sendMessage() {

		var numWeeks = $("#numWeeks").val();
		var end = $("#end").val();
		var start = $("#start").val();
		var types = $("#types").val();
		var allowWeeks = 0;

		$.ajax({
			"type" : 'POST',
			"url" : '<c:url value="/setform" />',
			"data" : JSON.stringify({
				"numWeeks" : numWeeks,
				"end" : end,
				"start" : start,
				"types" : types,
				"allowWeeks" : allowWeeks
			}),
			"success" : success,
			"error" : error,
			contentType : "application/json",
			dataType : "json"
		});
	}

	function success2(data) {
		alert("Event deleted ");
	}

	function error2(data) {
		alert("error2");
	}

	function deleteEvent(id) {
		var test = "help";
		var sendId = id;
		$.ajax({
			"type" : 'POST',
			"url" : '<c:url value="/deleteevent" />',
			"data" : JSON.stringify({
				"sendId" : sendId,
				"test" : test
			}),
			"success" : success2,
			"error" : error2,
			contentType : "application/json",
			dataType : "json"
		});
	}

	$(document).ready(function() {
		$("#check2").click(function() {

			sendMessage();

		});

	});
</script>

</head>

<!-- delete event button will produce a warning before event is deleletd -->

<div id="deletePopup">
	<button id="delete" type="button" class="btn btn-lg btn-success">Delete
		Event</button>
</div>



<body>

	<div id ="popup">
	
				<div class="login-panel panel panel-default">
				
					<div class="panel-heading">
						<h3 class="panel-title">Calendar Entry</h3>
					</div>
					
					<div class="panel-body">
						<form id="createevent"
							action="${pageContext.request.contextPath}/createevent"
							method="post" ">
							<fieldset>
								<div class="form-group">
									<select id="types" name="type"  class="form-control">
										<c:if test="${personalAllow}">
											<!-- only allow to book if allowed -->
											<option value="personal">Personal</option>
										</c:if>
										<c:if test="${type==1}">
											<!-- only allow these options when group view is set -->
											<c:if test="${meetingAllow}">
												<!-- only allow to book if allowed -->
												<option value="meeting">Meeting</option>
											</c:if>
											<c:if test="${tutorialAllow}">
												<!-- only allow to book if allowed -->
												<option value="tutorial">Tutorial</option>
											</c:if>
										</c:if>
									</select>
								</div>



								<div class="form-group">
									<select id="recurs" name="recurring" 
										class="form-control">
										<option id="week" value="no">One week event</option>
										<option id="recur" value="yes">Recurring event</option>

									</select>
								</div>
								<div id="amount" class="form-group">
									<select id="numWeeks" name="recuramount" 
										class="form-control">
										<c:forEach var="i" begin="1" end="${maxWeeks}">
											<option value="${i}">${i}</option>
											<!-- only allow users book for checked availability of free weeks -->
										</c:forEach>

									</select>
								</div>

								<div class="form-group">
									<input class="form-control"  id="start"
										value="formatStart" name="start" type="text" value="start">
								</div>
								<div class="form-group">
									<select id="end" name="end"  class="form-control">
										<option value="15">15 minutes</option>
										<option value="30">30 minutes</option>
										<option value="60">1 Hour</option>
										<option value="120">2 Hours</option>
									</select>
								</div>
								<div class="form-group">
									<label>Title</label>
									<textarea name="title" class="form-control" rows="3"></textarea>
								</div>

								<div class="form-group" id="check">
									<button type="button" id="check2" value="check"
										class="btn btn-lg btn-success">Check Avaiibility</button>
								</div>

								<button id="create" type="submit" value="create"
									class="btn btn-lg btn-success">Create</button>
								<button id="cancel" type="button" class="btn btn-lg btn-success">Cancel</button>
							</fieldset>
						</form>
				
			</div>
		</div>
	</div>
	<!-- /.col-lg-12 -->

	<div id="wrapper">


		<!-- buttons to select deselect events type like lectures meeting in calender feed -->
		<form method="post" action="eventselector">
			<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
			<!--  <button name="choice" value="all" type="submit" class="btn btn-primary">All</button>-->

			<!-- Indicates a successful or positive action -->
			<button name="choice" value="module" type="submit"
				class="btn btn-success">Modules</button>

			<!-- Contextual button for informational alert messages -->
			<button name="choice" value="meeting" type="submit"
				class="btn btn-info">Meetings</button>

			<!-- Indicates caution should be taken with this action -->
			<button name="choice" value="personal" type="submit"
				class="btn btn-warning">Personal</button>
		</form>
		<!-- end button selectors -->





		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">

					<li><a class=""
						href="${pageContext.request.contextPath}/setpersonalview"><i
							class="fa fa-calendar-o fa-fw"></i>Personal Calender</a></li>
					<li><a class="" href="meeting.html"><i
							class="fa fa-comments-o fa-fw"></i>Pick Group</a></li>
					<li><a class=""
						href="${pageContext.request.contextPath}/setgroupview"><i
							class="fa fa-calendar-o fa-fw"></i>Show availability of Group</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side -->
		</nav>

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