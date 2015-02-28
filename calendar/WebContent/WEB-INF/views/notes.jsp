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

<title>Events</title>

<!--  include the css files -->
<jsp:include page="css.jsp" />


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
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						
						/* the submit button on from is disabled unless one checkbox is selected
						* the submitButt ia a button
						*/
						var checkboxes = $("input[type='checkbox']"), submitButt = $("button[type='submit']");
						checkboxes.click(function() {
							submitButt.attr("disabled", !checkboxes
									.is(":checked"));
						});

					});//end doc ready
</script>
</head>
<body>

	<!--  include the header -->
	<jsp:include page="header.jsp" />

	<section id="main" style="margin-top: 100px;">
	<h3>New Events</h3>


	<form method="post" id="deletenotes"
		action="${pageContext.request.contextPath}/deletenotes">
		<div class="table-responsive">
			<table class="table table-hover" id="table">
				<thead style="background-color: #66A3FF;">
					<tr>
						<th>From</th>
						<th>Event</th>
						<th>Time</th>
						<th></th>
					</tr>
				</thead>
				<tbody style="background-color: ##C2FFC2;">

					<c:forEach var="note" items="${notes}">
						<tr>
							<td>${note.getCreatedBy()}</td>
							<td>You are invited to <b>${note.getEventName()}</b></td>
							<td>${note.getTime()}</td>
							<td><input id="check" type="checkbox" name="notesId"
								value="${note.getId()}"></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<button id="butt" type="submit" disabled style="float: right;"
				class="btn btn-info">Delete Note</button>
		</div>
	</form>

	</section>
	<!-- end main content -->


	<!--  include the footer-->
	<jsp:include page="footer.jsp" />

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

</body>
</html>