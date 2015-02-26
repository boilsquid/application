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
<script type="text/javascript">
$(document).ready(function() {
    
} );
</script>
</head>

<!-- delete event button will produce a warning before event is deleletd -->
<body>
	<div id="wrapper">
		<!--  include the header -->
		<jsp:include page="../../header.jsp" />
		<section>
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a class="" href="#"> <i class="fa fa-users"></i>
							Users
					</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12"></div>
			</div>
			<div class="row">
				<div class="col-lg-12 main-content">
					<div class="panel panel-default admin-users-panel">
						<!-- Default panel contents -->
						<div class="panel-heading">Users</div>

						<!-- Table -->
						<table class="table" data-sortable>
							<thead>
								<tr>
									<th>Username</th>
									<th>Email</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Role</th>
									<th>Stream</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="UserVal" items="${users}">
									<tr>
										<td>${UserVal.getUserName()}</td>
										<td>${UserVal.getEmail()}</td>
										<td>${UserVal.getFirstName()}</td>
										<td>${UserVal.getLastName()}</td>
										<td>${UserVal.getRoleId()}</td>
										<td>${UserVal.getStreamId()}</td>
										<td><a class="btn btn-success btn-xs">View</a> <a class="btn btn-info btn-xs">Edit</a> <a class="btn btn-danger btn-xs">Delete</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		</section>
		<!-- end main section -->
	</div>
	<!-- /#wrapper -->
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