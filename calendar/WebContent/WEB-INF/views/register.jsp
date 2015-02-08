<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/validation.css"
	rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<title>Registration</title>

</head>
<body>


	<div class="container">
		<div class="row">
			<div
				class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				<img class="ucc_logo" src="${pageContext.request.contextPath}/resources/images/UCC_logo_registration.png"
					alt="The ucc logo" />
			</div>
		</div>
		<div class="row">
			<div
				class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">

				<!-- I'm referring to form by command name "user" in UserController mapping doregister -->
				<!-- user cannot submit form unless the passwords match in the js script passMatch id details is used in script -->
				<sf:form id ="details" method="post"
					action="${pageContext.request.contextPath}/doregister"
					commandName="user">
					<h2>Registration.</h2>
					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<sf:input type="text" path="firstName" name="firstName"
									id="firstName" class="form-control input-lg"
									placeholder="First Name" tabindex="1" />
								<sf:errors path="firstName" cssClass="error"></sf:errors>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<sf:input type="text" path="lastName" name="lastName"
									id="lastName" class="form-control input-lg"
									placeholder="Last Name" tabindex="2" />
								<br />
								<sf:errors path="lastName" cssClass="error"></sf:errors>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">

								<select class="form-control" name="roleId" id="roleId">
									<option value="student">Student</option>
									<option value="lecturer">Lecture</option>
									<!-- need to change database to take more than 8 chars -->

								</select>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">

								<select class="form-control" name="streamId" id="streamId">

									<c:forEach var="stream" items="${streams}">
										<option value="${stream.getStreamId()}">
											${stream.getStream()}</option>
									</c:forEach>

								</select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<sf:input type="text" path="userName" name="userName" id="userId"
							class="form-control input-lg" placeholder="userName" tabindex="1" />
						<br />
						<sf:errors path="userName" cssClass="error"
							cssStyle="color: #ff0000;"></sf:errors>
					</div>


					<div class="form-group">
						<sf:input path="email" type="email" name="email" id="email"
							class="form-control input-lg" placeholder="Email Address"
							tabindex="4" />
						<br />
						<sf:errors path="email" cssClass="error"
							cssStyle="color: #ff0000;"></sf:errors>
					</div>

					<div>
						<div class="form-group">
							<sf:input path="phone" type="phone" name="phone" id="phone"
								class="form-control input-lg" placeholder="Cell Phone"
								tabindex="4" />
							<br />
							<sf:errors path="phone" cssClass="error"
								cssStyle="color: #ff0000;"></sf:errors>
						</div>

					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<sf:input type="password" path="password" name="password"
									id="password" class="form-control input-lg"
									placeholder="Password" tabindex="5" />
								<sf:errors path="password" cssClass="error"
									cssStyle="color: #ff0000;"></sf:errors>
								<div id="matchpass"></div></td><!-- this is for the javascript match password script -->
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="form-group">
								<sf:input type="password" path="passwordConfirmation"
									name="passwordConfirmation" id="passwordConfirmation"
									class="form-control input-lg" placeholder="Confirm Password"
									tabindex="6" />
									
								<sf:errors path="passwordConfirmation" cssClass="error"
									cssStyle="color: #ff0000;"></sf:errors>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4 col-sm-3 col-md-3">
							<span class="button-checkbox">
								<button type="button" class="btn" data-color="info" tabindex="7">I
									Agree</button> <input type="checkbox" name="t_and_c" id="t_and_c"
								class="hidden" value="1">
							</span>
						</div>
						<div class="col-xs-8 col-sm-9 col-md-9">
							By clicking <strong class="label label-primary">Register</strong>,
							you agree to the <a href="#" data-toggle="modal"
								data-target="#t_and_c_m">Terms and Conditions</a> set out by
							this site, including our Cookie Use.
						</div>
					</div>

					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-12 col-md-6">
							<input type="submit" value="Register"
								class="btn btn-primary btn-block btn-lg" tabindex="7">
						</div>
						<div class="col-xs-12 col-md-6">
							<a href="${pageContext.request.contextPath}/login"
							 class="btn btn-success btn-block btn-lg">Sign In</a>
						</div>
					</div>
				</sf:form>
			</div>
		</div>
	</div>


	<!-- include js files here -->
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/hideShowStreams.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/passMatch.js"></script>
</body>
</html>