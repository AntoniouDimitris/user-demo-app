<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<title>User Management</title>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">

<body>

	<div id="wrapper">
		<div id="header">
			<h2>User Management Interface</h2>
		</div>
	</div>
	
	<div id="container">
	  <div id="content">
		
		<input type="button" value="Add User"
			   onclick="window.location.href='showAddUserForm'; return false;"
			   class="add-button" />
		
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Username</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var="tempUser" items="${users}">
				
				<c:url var="updateLink" value="/users/showFormForUpdate">
					<c:param name="userId" value="${tempUser.id}" />
				</c:url>
				
				<c:url var="deleteLink" value="/users/deleteUser">
					<c:param name="userId" value="${tempUser.id}" />
				</c:url>
				
				<tr>
					<td> ${tempUser.firstName} </td>
					<td> ${tempUser.lastName} </td>
					<td> ${tempUser.username} </td>
					
					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
						|
						<!-- display the update link -->
						<a href="${deleteLink}"
						   onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
		
		</table>			   
		
	  </div>
	</div>
	
	<a href="${pageContext.request.contextPath}/">Back to Home Page</a>

</body>

</html>