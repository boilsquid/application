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

<title>Edit lectures</title>

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

<link
	href="${pageContext.request.contextPath}/resources/css/checkbox.css"
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
	
	
	<div class="panel panel-primary" style="width: 50%; margin-left: 20%;">
    <div class="panel-heading">
        <h3 class="panel-title">Pick Lectures</h3>
    </div>
	<form  method="post" role="form" 
					action="${pageContext.request.contextPath}/changelectures"
					commandName="event">
	<c:forEach var="lecture" items="${lectures}">
		
			<!-- show a lecturer every lecture in department -->
			<c:if test="${user.getRoleId().equals('lecturer')}">
				<div class="checkbox" style="margin-left: 20%;"> 
					<label><input type="checkbox" name="lectureId"
						value="${lecture.getLectureId()}">
						${lecture.getModuleId()}  ${lecture.getStart()} ${lecture.getDay()}</label>
				 </div>
			
			</c:if>
			<!--show a student lectures relating to students stream-->
			<c:if test="${user.getRoleId().equals('student')&&lecture.getStreamId().equals(user.getStreamId()) }">
				<div class="checkbox" style="margin-left: 20%;"> 
					<label><input type="checkbox" name="lectureId"
						value="${lecture.getLectureId()}">
					${lecture.getModuleId()}  ${lecture.getStart()} ${lecture.getDay()}</label>
				</div>
			</c:if>
	</c:forEach>
		<div class="panel-footer clearfix">
        <div class="pull-right">
			<button name="choice" value="module" type="submit"
				class="btn btn-success">Submit</button>
		</div>
		</div><!-- end panel footer -->
	</form>
	</div><!-- end panel -->
	
	</section><!-- end main content -->
	
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