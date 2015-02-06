<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/validation.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<title>Login Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<img class="ucc_logo" src="${pageContext.request.contextPath}/resources/images/UCC_Logo_RGB_simplified.png"
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
									<input class="form-control" type='text' name='j_username' value='' placeholder="Username" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" type='password' name='j_password' placeholder="Password"/>
								</div>
								<div class="form-group">
									<input class="btn btn-lg btn-success btn-block" name="submit"
										type="submit" value="Login" />
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>