<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IMDB Search App</title>
<style type="text/css">

.myButton {
	-moz-box-shadow:inset 0px 1px 0px 0px #fce2c1;
	-webkit-box-shadow:inset 0px 1px 0px 0px #fce2c1;
	box-shadow:inset 0px 1px 0px 0px #fce2c1;
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ffc477), color-stop(1, #fb9e25));
	background:-moz-linear-gradient(top, #ffc477 5%, #fb9e25 100%);
	background:-webkit-linear-gradient(top, #ffc477 5%, #fb9e25 100%);
	background:-o-linear-gradient(top, #ffc477 5%, #fb9e25 100%);
	background:-ms-linear-gradient(top, #ffc477 5%, #fb9e25 100%);
	background:linear-gradient(to bottom, #ffc477 5%, #fb9e25 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffc477', endColorstr='#fb9e25',GradientType=0);
	background-color:#ffc477;
	-moz-border-radius:11px;
	-webkit-border-radius:11px;
	border-radius:11px;
	border:3px solid #eeb44f;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:19px;
	font-weight:bold;
	padding:14px 32px;
	text-decoration:none;
	text-shadow:0px 1px 0px #cc9f52;
	margin-left: 250px;
	margin-top: 30px;
}
.myButton:hover {
	background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #fb9e25), color-stop(1, #ffc477));
	background:-moz-linear-gradient(top, #fb9e25 5%, #ffc477 100%);
	background:-webkit-linear-gradient(top, #fb9e25 5%, #ffc477 100%);
	background:-o-linear-gradient(top, #fb9e25 5%, #ffc477 100%);
	background:-ms-linear-gradient(top, #fb9e25 5%, #ffc477 100%);
	background:linear-gradient(to bottom, #fb9e25 5%, #ffc477 100%);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fb9e25', endColorstr='#ffc477',GradientType=0);
	background-color:#fb9e25;
}
.myButton:active {
	position:relative;
	top:1px;
}
</style>
</head>
<body style="background-color:lightblue; text-align:center;">

<h1 style="margin-top:20px; font-size: 80px; font-family: fantasy; font-weight: bold;">IMDB SEARCH APP</h1>
<div style="background-color: white; width: 85%; margin-left: 100px" align="center">
<h4 style="font-size:17px; font-style:italic;color:red; float: left; margin-left: 40px">Search your favourite actors for their top movies</h4>

	<form action="searchActor">
		<table cellspacing="20">
			<tr>
				<td style="font-family: cursive; font-size: 25px">Enter Actor Name</td>
				<td><input type="text" style="font-size:15px;width:250px; height:30px;" placeholder="Enter Actor name here" name="actor"/></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Seach for Movies" class="myButton" ></td> 
				
			</tr>
		</table>
	</form>
	</div>
</body>
</html>