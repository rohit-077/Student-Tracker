<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>VIT Student Tracker App</title>

	<link type="text/css" rel="stylesheet" href="CSS/style.css">
	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<div id="heading">
				<img src="https://vtopcc.vit.ac.in/vtop/assets/img/VITLogoEmblem.png" alt="logo" />
				<h2>VIT</h2>
			</div>
			 
		</div>
		<div id="container">
			<div id="content">
				<div id="btns">
					<div id="add">
						<input type="button" value="Add Student" 
						onclick="window.location.href='add_student_form.jsp';return false;"
						class="add_student_button"/>
					</div>
					<div id="search">
						<form action="StudentControllerServlet" method="GET">
	        
		                	<input type="hidden" name="command" value="SEARCH" />
		            
		                	Search student: <input type="text" name="theSearchName" />
		                
		                	<input type="submit" value="Search" class="add-student-button" />
	            
	            		</form>
					</div>
				</div>				
				<table>
					<tr>
						<th>ID</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email address</th>
						<th>Action</th>
					</tr>
					<c:forEach var="tempStudent" items="${student_list}">
					
						<c:url var="tempLink" value="StudentControllerServlet">
							<c:param name="command" value="LOAD" ></c:param>
							<c:param name="studentId" value="${tempStudent.id}"></c:param>
						</c:url>
						
						<c:url var="deleteLink" value="StudentControllerServlet">
							<c:param name="command" value="DELETE" ></c:param>
							<c:param name="studentId" value="${tempStudent.id}"></c:param>
						</c:url>
						
						<tr>
							<td>${tempStudent.id }</td>
							<td>${tempStudent.firstName }</td>
							<td>${tempStudent.lastName}</td>
							<td>${tempStudent.email }</td>
							<td>
								<a href="${tempLink}" >Update</a>
								| 
								<a href="${deleteLink}" 
								onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false" >
								Delete</a>
							</td>
						</tr>
						
					</c:forEach>
						
						
				</table>
			</div>
		</div>
	</div>

</body>
</html>