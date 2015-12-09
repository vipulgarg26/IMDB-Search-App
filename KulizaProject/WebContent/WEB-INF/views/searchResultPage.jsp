<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>IMDB Search App</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
	padding-top: 10px;
	margin-left: 50px;
	margin-right: 50px;
	height: 20px;
}

th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

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
	margin-left: 450px;
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
<body style="background-color: lightblue; text-align: center;">

	<h1
		style="margin-top: 20px; font-size: 80px; font-family: fantasy; font-weight: bold; width: 100%">IMDB
		SEARCH APP</h1>
	<div
		style="background-color: white; width: 85%; margin-left: 100px; padding-bottom: 10px"
		align="left">

		<c:choose>
			<c:when test="${empty movieList}">
			<h4
					style="font-size: 25px; font-style: italic; color: red; float: left; margin-left: 40px; width: 100%">You entered an invalid name. Please try again with a correct name</h4>
					

				<button class="myButton" onclick="location.href='/KulizaProject/'">Try Again</button>
			</c:when>
			<c:otherwise>
				<h4
					style="font-size: 25px; font-style: italic; color: red; float: left; margin-left: 40px; width: 100%">${actor}'s
					Top Movies</h4>

				<br>
				<br>
				<table cellpadding="10">
					<tr>
						<th style="width: 100px">Movie Display Pic</th>
						<th style="width: 100px">Name (along with year, runtime etc.)</th>
						<th style="width: 60px">Rating</th>
						<th style="width: 350px">Top Reviews</th>
					</tr>

					<c:forEach var="movielist" items="${movieList}" step="1">
						<tr>
							<td align="center"><img style="" alt="${movielist.title}"
								src="${movielist.urlPoster}"></td>
							<td style="margin-bottom: 10px"><a
								href="${movielist.urlIMDB}" target="_blank">${movielist.title}</a>
								(${movielist.year}) ${movielist.rated} | ${movielist.runtime} |
								<c:forEach var="genre" items="${movielist.genres }" step="1">
   							 ${genre },</c:forEach> | ${movielist.globalRelease} (
								${movielist.country} ) | ${movielist.otherRelease }</td>
							<td style="margin-bottom: 10px">${movielist.rating}/10from
								${movielist.votes} users</td>

							<td>
								<ol>
									<c:forEach var="review" items="${movielist.reviews }" step="1">
										<br>
										<li><strong style="font-size: 20px">${review.heading }....</strong>
											&nbsp; <strong style="font-weight: lighter;">( by
												${review.userInfo } ):</strong> &nbsp; ${review.data }</li>

									</c:forEach>
								</ol>

							</td>
						</tr>

					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>