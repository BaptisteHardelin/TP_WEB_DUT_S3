<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page import="model.Dao"%>
<%@ page import="java.util.*"%>
<%@ page session="true"%>
<%@ page pageEncoding="UTF-8"%>
<title>Ma liste</title>
</head>
<body>

	<h1>
		Liste : <%=request.getAttribute("liste")%>
	</h1>
</body>
</html>