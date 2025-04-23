<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body align="center">
<h1>Search Flight</h1>

<form action="searchFlight" method="post">
	<pre>
		From:	  <input type="text" name="from">
		<br>
		To:	  <input type="text" name="to">
		<br>
		Date:           <input type="date" name="date">
		<br>
		Search:   <input type="submit" value="Search">
	</pre>
</form>

</body>
</html>