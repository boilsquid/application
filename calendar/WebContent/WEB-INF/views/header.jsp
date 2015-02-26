<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<header>
	<!-- Navigation -->

	<sec:authentication var="principal" property="principal" />
	
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation"
		style="background-color: #E9E9E9;">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">UCC CALENDER</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<li>
				<security:authorize access="isAuthenticated()">
    				<b  style="color:#8D8D80;">${pageContext.request.userPrincipal.name}</b>
				</security:authorize>
			</li>

			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="${pageContext.request.contextPath}/"><i class="fa fa-home fa-fw"></i> Home
							</a></li>
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="${pageContext.request.contextPath}/groups"><i class="fa fa-users fa-fw"></i> Groups
							</a></li>
					<li><a href="${pageContext.request.contextPath}/events"><i class="fa fa-calendar-o fa-fw"></i> Calendar
							</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/dashboard"><i class="fa fa-gear fa-fw"></i> Admin</a>
					</li>
					<li class="divider"></li>
					<li><a href="<c:url value='/j_spring_security_logout'/>">
							<i class="fa fa-sign-out fa-fw"></i> Logout
					</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->
	</nav>
</header>
<!-- end header-->