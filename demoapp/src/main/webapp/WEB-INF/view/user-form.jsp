<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<title>Save User</title>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
		  
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/user-form-style.css">
	
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<title>jQuery UI Datepicker - Default functionality</title>
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<link rel="stylesheet" href="/resources/demos/style.css">
  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	<script>
  	$( function() {
    	$( "#datepicker" ).datepicker();
  	} );
  	</script>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - User Relationship Manager</h2>
		</div>
	</div>
	
	<div id="wrapper">
		<h3>Save Customer</h3>
		
		<form:form action="saveUser" modelAttribute="user" method="POST">
		
		<!-- associate data with customer id -->
		<form:hidden path="id" />
		
		<table>
			<tbody>
				<tr>
					<td><label>First name:</label></td>
					<td><form:input path="firstName" /></td>
					<td><form:errors path="firstName"/>
				</tr>
				
				<tr>
					<td><label>Last name:</label></td>
					<td><form:input path="lastName" /></td>
					<td><form:errors path="lastName"/>
				</tr>
				
				<tr>
					<td><label>Username:</label></td>
					<td><form:input path="username" /></td>
					<td><form:errors path="username"/>
				</tr>
				
				<tr>
					<td><label>Password:</label></td>
					<td><form:input path="password" /></td>
					<td><form:errors path="password"/>
				</tr>
				
				<tr>
					<td><label>Enabled:</label></td>
					<td><form:input path="enabled" /></td>
					<td><form:errors path="enabled"/>
				</tr>
				
				<tr>
					<td><label>Work Address:</label></td>
					<td><form:input path="workAddress" /></td>
				</tr>
				
				<tr>
					<td><label>Home Address:</label></td>
					<td><form:input path="homeAddress" /></td>
				</tr>
				
				<tr>
				<td><label>Gender:</label></td>
				<td>
                  <form:select path = "gender">
                     <form:option value = "NONE" label = "Select"/>
                     <form:options items = "${genderList}" />
                  </form:select>     	
                </td>
                <td><form:errors path="gender"/>
				</tr>
				
				<tr>
				<td><label>Date of Birth:</label>
				<td><form:input type="date" id="datepicker" path="birthDate"/></td>
				<td><form:errors path="birthDate"/>
				</tr>
				
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>
				
			</tbody>
		</table>
		
		</form:form>
		
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/users/manageUsers">Back to List</a>
		</p>
	</div>

</body>

</html>