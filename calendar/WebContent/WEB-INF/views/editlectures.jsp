<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form  method="post"
					action="${pageContext.request.contextPath}/changelectures"
					commandName="event">
		

	<c:forEach var="lecture" items="${lectures}">
		<p>
			<!-- show a lecturer every lecture in department -->
			<c:if test="${user.getRoleId().equals('lecturer')}">
					<input type="checkbox" name="lectureId"
						value="${lecture.getLectureId()}">
				${lecture.getModuleId()}  ${lecture.getStart()} ${lecture.getDay()}  <br>
			
			</c:if>
			<!--show a student lectures relating to students stream-->
			<c:if test="${user.getRoleId().equals('student')&&lecture.getStreamId().equals(user.getStreamId()) }">

					<input type="checkbox" name="lectureId"
						value="${lecture.getLectureId()}">
				${lecture.getModuleId()}  ${lecture.getStart()} ${lecture.getDay()}  <br>
			</c:if>
		
			
		</p>
	</c:forEach>
		<input type="submit" value="Submit">
	</form>

</body>
</html>