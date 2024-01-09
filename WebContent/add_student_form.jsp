<!DOCTYPE html>
<html>
<head>
	<title>
		Add Student
	</title>
	<link type="text/css" rel="stylesheet" href="CSS/style.css">
	<link type="text/css" rel="stylesheet" href="CSS/add_student_style.css">
	
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
			<h3>Add Student</h3>
			<form action="StudentControllerServlet" method="POST">
				
				<input type="hidden" name="command" value="ADD"/>
				<table>
					<tbody>
						<tr>
							<td><label>First Name: </label></td>
							<td><input type="text" name="firstName"/></td>
						</tr>
						<tr>
							<td><label>Last Name: </label></td>
							<td><input type="text" name="lastName"/></td>
						</tr>
						<tr>
							<td><label>Email: </label></td>
							<td><input type="text" name="email"/></td>
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
