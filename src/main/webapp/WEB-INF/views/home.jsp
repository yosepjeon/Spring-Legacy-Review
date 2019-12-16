<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<%@include file="include/header.jsp" %>
	<title>Home</title>
</head>
<body>
<h1>
	file I/O By S3
</h1>

<form action="${path}/uploadAjax" enctype="multipart/form-data" method="POST">
	<input type="file" name="file">
	<input type="submit">
</form>
</body>
</html>
