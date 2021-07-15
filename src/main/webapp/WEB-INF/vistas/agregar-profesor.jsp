<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>
<body class="w3l-home overflow-hidden">
	<div id="loginbox" class=" col-6 mx-auto text-center pt-2 form-profesor mt-5">
	<h1 class="display-5 mb-2">Agregar Profesor</h1>
	
						 <c:if test="${not empty msj}">
			                <div class="alert alert-warning" role="alert">
			                    <p>${msj}</p>
			                </div>
			            </c:if>	
			            
			            
	<form:form action="agregarProfesor" method="POST" 
	modelAttribute="registrarProfesor" class="p-4 w-75 mx-auto">
		<form:input path="email" id="email" type="mail"
			class="form-control mb-2" placeholder="Ingresar email" />
		<form:input path="password" id="password" type="password"
			placeholder="Ingresar Contraseña" class="form-control mb-2" />
		<form:input path="rol" type="text" id="rol" class="form-control mb-2"
			placeholder="Ingresar rol" />

		<c:if test="${not empty error}">
			<div class="alert alert-danger mt-2" role="alert">${error}</div>
		</c:if>
		<div class="d-flex justify-content-around">
			<a href="home" class="btn btn-lg btn-danger btn-block mt-2 w-25">Volver</a>
			<button class="btn btn-lg btn-primary btn-block mt-2 w-25"
				Type="Submit" />Agregar</button>
		</div>
	</form:form>

	</div>
</body>
</html>