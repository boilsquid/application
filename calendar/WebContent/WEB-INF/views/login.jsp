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
    <link href="${pageContext.request.contextPath}/resources/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">



<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath}/resources/font-awesome-4.1.0/css/font-awesome.min.css"
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


</head>
<body>

	<!--  include the header -->
	<jsp:include page="header.jsp" />
	
	<section style="margin-top: 100px;">
	<div class="container">
		<c:if test="${param.success != null}">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="alert alert-success" role="alert">${param.success}</div>
				</div>
			</div>
		</c:if>
		<c:if test="${param.danger != null}">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="alert alert-danger" role="alert">${param.danger}</div>
				</div>
			</div>
		</c:if>
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<img class="ucc_logo"
					src="${pageContext.request.contextPath}/resources/images/UCC_Logo_RGB_simplified.png"
					alt="The ucc logo" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Log In</h3>
					</div>
					<div class="panel-body">
						<c:if test="${param.error != null}">
							<p class="login-error">Login has failed check if Username and
								Password is Correct.</p>
						</c:if>
						<form name='f'
							action='${pageContext.request.contextPath}/j_spring_security_check'
							method='POST' role="form">
							<fieldset>
								<div class="form-group">
									<input class="form-control" type='text' name='j_username'
										value='' placeholder="Username" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" type='password' name='j_password'
										placeholder="Password" />
								</div>
								<div class="form-group">
									<input type='checkbox' value="remember me"
										name='_spring_security_remember_me' checked="checked" /> Remember me
								</div>
								<div class="form-group">
									<input class="btn btn-lg btn-success btn-block" name="submit"
										type="submit" value="Login" />
								</div>
								<div class="form-group">
									<a href="${pageContext.request.contextPath}/reset" class="btn btn-lg btn-success btn-block">Forgot Password</a>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section> <!--  end main section -->
	
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