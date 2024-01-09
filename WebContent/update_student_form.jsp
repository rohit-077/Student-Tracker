<!DOCTYPE html>
<html>
<head>
	<title>
		Update Student
	</title>
	<link type="text/css" rel="stylesheet" href="CSS/style.css">
	<link type="text/css" rel="stylesheet" href="CSS/add_student_style.css">
	
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>VIT UNIVERSITY</h2>
		</div>
		<div id="container">
			<h3>Update Student</h3>
			<form action="StudentControllerServlet" method="GET">
				
				<input type="hidden" name="command" value="UPDATE"/>
				
				<input type="hidden" name="studentId" value="${the_student.id}"/>
				
				<table>
					<tbody>
						<tr>
							<td><label>First Name: </label></td>
							<td><input type="text" name="firstName" value="${the_student.firstName}" /></td>
						</tr>
						<tr>
							<td><label>Last Name: </label></td>
							<td><input type="text" name="lastName" value="${the_student.lastName}"/></td>
						</tr>
						<tr>
							<td><label>Email: </label></td>
							<td><input type="text" name="email" value="${the_student.email}"/></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit" name="submit" value="Save" /></td>
							
						</tr>
					</tbody>
				</table>			
			</form>
			
			<div style = "clear: both;"></div>
			
			<p><a href="StudentControllerServlet">Go Back</a></p>
		</div>
	</div>
</body>
</html>