<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page import="model.Dao"%>
<%@ page import="java.util.*"%>
<%@ page session="true"%>
<%@ page pageEncoding="UTF-8"%>

<title>Ma vignette</title>
</head>
<body>

	<h1>
		Vignette :
		<%=request.getParameter("n")%>
		:
		<%=request.getAttribute("n")%>
	</h1>


</body>
</html>