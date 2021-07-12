<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS 
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme 
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body>
		<div class = "container">
			<div id="loginbox" class="pt-5 col-6 mx-auto text-center">
				<form:form action="agregarClase" method="POST"
		modelAttribute="registrarClase">
		<h3 class="form-signin-heading display-5">EnerGym</h3>
		<hr class="colorgraph">
		<br>

	
		<form:input path="nombre" id="nombre" type="text" class="form-control mb-2" placeholder="Ingresar Nombre"/>
		<form:input path="fechaYHora" id="fechaYHora" type="datetime-local" class="form-control mb-2" />
		<form:input path="cupo" type="number" id="cupo" class="form-control mb-2" placeholder="Ingresar capacidad"/>
		<form:input path="idProfesor" type="number" id="idProfesor" class="form-control mb-2" placeholder="Ingresar el Id del profe"/>
		
				<%--Bloque que es visible si el elemento error no está vacío	--%>
				<c:if test="${not empty error}">
			        <div class="alert alert-danger mt-2" role="alert" >${error}</div>
		
		        </c:if>
		<a href="home" class="btn btn-lg btn-danger btn-block mt-2 w-25">Volver</a>
		<button class="btn btn-lg btn-primary btn-block mt-2 w-100" Type="Submit" />Agregar</button>
		
		
	</form:form>

			</div>

		</div>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>