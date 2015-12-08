<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   
${data}
<br>

Hello World
<table style="  border: 1px solid black; border-collapse: collapse;">
 <tr>
    <th>First Name</th>
    <th>Last Name</th>		
    <th>Points</th>
  </tr>
  <tr>
    <td rowspan="3">Jill</td>
    <td rowspan="3">Smith</td>		
    <td>50</td>
  </tr>
<tr>
<td>55</td>
</tr>
<tr>
<td>55</td>
</tr>
  <tr>
    <td>Eve</td>
    <td>Jackson</td>		
    <td>94</td>
  </tr>
  <tr>
    <td>John</td>
    <td>Doe</td>		
    <td>80</td>
  </tr>
</table>
</body>
</html>