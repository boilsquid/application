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

<title>Groups</title>

<!--  include the css files -->
<jsp:include page="css.jsp" />

<!-- extra css files -->
<link
	href="${pageContext.request.contextPath}/resources/css/multi-select.css"
	rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

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
<!-- include multiselect jquery plugin -->
<script
	src='${pageContext.request.contextPath}/resources/js/jquery.multi-select.js'></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#popup').popup({
			outline : true, // optional
			focusdelay : 400, // optional
			vertical : 'top' //optional
		});

		$('#popup2').popup({
			outline : true, // optional
			focusdelay : 400, // optional
			vertical : 'top' //optional
		});

		$('#creategroup').click(function() {

			$('#popup').popup('show');

		});

		$('#deletegroup').click(function() {

			$('#popup2').popup('show');

		});
		
		$('#cancel').click(function() {

			$('#popup').popup('hide');

		});

		$('#cancel2').click(function() {

			$('#popup2').popup('hide');

		});

		//initialize multiselect
		$('#usernames').multiSelect();
		//initialize multiselect
		$('#groupDelete').multiSelect();
		

	});//end document ready
</script>

</head>
<body>


	<!--  include the header -->
	<jsp:include page="header.jsp" />


	<div id="popup" class="row">
		<div id="panel" class="panel panel-primary"
			style="color: #1975FF; background: #D1F0FF;">
			<div class="panel-heading">
				<h3 class="panel-title">Create Group</h3>
			</div>
			<div class="panel-body">
				<form id="createevent"
					action="${pageContext.request.contextPath}/creategroups"
					method="post">
					<fieldset>
						<div class="form-group">
							<label for="student">Pick Group Members</label> <select
								id="usernames" multiple="multiple" name="usernames"
								class="form-control">

								<c:forEach var="user" items="${users}">
									<option value="${user.getUserName()}">${user.getUserName()}
										${user.getRoleId()}</option>

								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label for="groupName">Group Name</label>
							<textarea name="groupName" class="form-control" maxlength="20"
								rows="3" required></textarea>
						</div>


						<button id="create" type="submit" value="create"
							style="width: 45%;" class="btn btn-success">Create</button>
						<button id="cancel" type="button" style="width: 45%;"
							class="btn btn-danger">Cancel</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!-- end create groups popup form -->

	<div id="popup2" class="row">
		<div id="panel" class="panel panel-primary"
			style="color: #1975FF; background: #D1F0FF;">
			<div class="panel-heading">
				<h3 class="panel-title">Detete Group</h3>
			</div>
			<div class="panel-body">
				<form id="createevent"
					action="${pageContext.request.contextPath}/deletegroups"
					method="post">
					<fieldset>
						<div class="form-group">
							<label for="groupDelete">Select Groups</label> <select
								id="groupDelete" multiple="multiple" name="groupDelete"
								class="form-control">

								<c:forEach var="groupDelete" items="${groupsToDelete}">
									<option value="${groupDelete.getGroupId()}">${groupDelete.getGroupName()}</option>

								</c:forEach>
							</select>
						</div>


						<button id="create2" type="submit" value="create"
							style="width: 45%;" class="btn btn-success">Delete Groups</button>
						<button id="cancel2" type="button" style="width: 45%;"
							class="btn btn-danger">Cancel</button>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!-- end delete groups popup form -->


	<section style="margin-top: 100px;">


	<div class="panel panel-primary" style="width: 50%; margin-left: 20%;">
		<div class="panel-heading">
			<h3 class="panel-title">Pick Groups</h3>
		</div>
		<form method="post" role="form"
			action="${pageContext.request.contextPath}/pickgroups"
			commandName="event">
			<c:forEach var="group" items="${groups}">


				<div class="radio" style="margin-left: 20%;">
					<label><input type="radio" name="groupId" required
						value="${group.getGroupId()}"> ${group.getGroupName()}</label>
				</div>


			</c:forEach>
			<div class="panel-footer clearfix">
				<div class="pull-right">
					<button id="creategroup" type="button" class="btn btn-primary">Create
						Groups</button>
					<button id="deletegroup" type="button" class="btn btn-danger">Delete
						Groups</button>
					<button name="choice" value="module" type="submit"
						class="btn btn-success">Pick Group</button>

				</div>
			</div>
			<!-- end panel footer -->
		</form>
	</div>
	<!-- end panel --> </section>
	<!-- end main content -->
	
	<!--  include the footer-->
	<jsp:include page="footer.jsp" />

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