<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="validar-login" method="POST"
		modelAttribute="usuario">
		<h3 class="form-signin-heading display-5">EnerGym</h3>
		<hr class="colorgraph">
		<br>

		<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
		<form:input path="email" id="email" type="email"
			class="form-control mb-2" placeholder='Ingresar Mail' />
		<form:input path="password" type="password" id="password"
			class="form-control mb-2" placeholder='Ingresar contraseña' />
		<form:input path="repitePassword" type="password" id="password"
			class="form-control mb-2" placeholder='Repetir contraseña' />
		<button class="btn btn-lg btn-primary btn-block mt-2 w-100"
			Type="Submit" />Login</button>
	</form:form>
</body>
</html>