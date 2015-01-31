<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>registration</title>
</head>
<body>


	<form role ="form" method="post" action="${pageContext.request.contextPath}/doregister">
		<h2>Registration.</h2>
		<hr class="colorgraph">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">
					<input type="text" name="firstName" id="firstName"
						class="form-control input-lg" placeholder="First Name"
						tabindex="1">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">
					<input type="text" name="lastName" id="lastName"
						class="form-control input-lg" placeholder="Last Name" tabindex="2">
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">
					<input type="text" name="id" id="id" class="form-control input-lg"
						placeholder="student/lecturer id" tabindex="1">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">

					<select class="form-control" id="role">
						<option>Student</option>
						<option>Lecturer</option>

					</select>
				</div>
			</div>
		</div>

		<div class="form-group">
			<input type="email" name="email" id="email"
				class="form-control input-lg" placeholder="Email Address"
				tabindex="4">
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">
					<input type="password" name="password" id="password"
						class="form-control input-lg" placeholder="Password" tabindex="5">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<div class="form-group">
					<input type="password" name="password_confirmation"
						id="password_confirmation" class="form-control input-lg"
						placeholder="Confirm Password" tabindex="6">
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
					data-target="#t_and_c_m">Terms and Conditions</a> set out by this
				site, including our Cookie Use.
			</div>
		</div>

		<hr class="colorgraph">
		<div class="row">
			<div class="col-xs-12 col-md-6">
				<input type="submit" value="Register"
					class="btn btn-primary btn-block btn-lg" tabindex="7">
			</div>
			<div class="col-xs-12 col-md-6">
				<a href="#" class="btn btn-success btn-block btn-lg">Sign In</a>
			</div>
		</div>
	</form>

</body>
</html>