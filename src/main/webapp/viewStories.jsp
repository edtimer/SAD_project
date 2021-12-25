<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Display all stories</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Results</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/SDA/SAgile_demo.jsp">Home</a></li>

				</ul>


			</div>
		</div>
	</nav>
	<h2>Formatted stories</h2>
	<table class="table table-success table-striped">

		<%
		ArrayList<String> accepted = (ArrayList<String>) session.getAttribute("accepted_stories");
		int index = 1;
		for (int i = 0; i < accepted.size(); i++) {
			out.print("<tr>");
			out.print("<td>" + index + "</td>");
			out.print("<td>" + accepted.get(i) + "</td>");
			out.print("</tr>");
			index++;
		}
		%>
	</table>
	<br>
	<br>
	<h2>Unformatted stories</h2>
	<table class="table table-danger table-striped">
		<%
		ArrayList<String> unaccepted = (ArrayList<String>) session.getAttribute("unaccepted_stories");
		int index2 = 1;
		for (int i = 0; i < unaccepted.size(); i++) {
			out.print("<tr>");
			out.print("<td>" + index2 + "</td>");
			out.print("<td>" + unaccepted.get(i) + "</td>");
			out.print("</tr>");
			index2++;
		}
		%>
	</table>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.js"></script>
</body>
</html>