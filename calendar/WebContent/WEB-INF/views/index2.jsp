<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Home</title>

	    <!--css files-->
	    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
	    <!--<link href="css/customnav.css" rel="stylesheet">-->
	    <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

		<link href="${pageContext.request.contextPath}/resources/css/validation.css" rel="stylesheet" type="text/css" />
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
  
  <body>
    <header>
	    <div class="container-fluid">
	        <div class="row">
	            <div class="col-xs-4 col-md-4">
		            <img id="headerText" src="UI/images/UCC_Logo_RGB_simplified.png" />
	            </div>
			    <div id="signup" class="col-xs-3 col-md-2 col-xs-offset-5 col-md-offset-6">
				    <a href="login.jsp" class="btn btn-success btn-block btn-lg">Sign In</a>
			    </div>
		    </div>
	    </div>
	</header>

	
	<footer class="footer">
		<div id="foot1" class="container-fluid">
            <a href="#"><small class="text-muted">FAQ</small></a><br>
			<a href="#"><small class="text-muted">Support</small></a><br>
			<a href="#"><small class="text-muted">Terms of use</small></a><br>
        </div>
        <div id="foot2" class="container-fluid">
            <small class="text-muted">@Meetz.inc 2015</small>
        </div>
    </footer>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../../resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/hideShowStreams.js"></script>
  </body>
</html>