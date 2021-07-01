<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS 
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme 
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	-->
	<link href="css/estilo.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set  value='<%= session.getAttribute("idUsuario") %>' var="idUsuario"  /> 
  <c:if test="${empty idUsuario}">
  <c:set  value="0" var="idUsuario"  />
  </c:if>	
  
</head>
<body class="mw-100">
	<div class="container-fluid">
		<div class="row">
			<div class="d-flex flex-column col-2 p-3 mb-2 bg-primary text-white">
				<a href="home" class="text-white text-decoration-none fw-bold">Home</a>
				<a href="clases-disponibles" class="text-white text-decoration-none fw-bold">Clases Disponibles</a>
				<a href="agregar-profesor" class="text-white text-decoration-none fw-bold">Agregar Profesor</a>
				<a href="clases-inscriptas/${idUsuario}" class="text-white text-decoration-none fw-bold">Clases incriptas</a>
			</div>
			<div class="col-10">
				<div class="container">
					<h1 class="display-5 text-center">Bienvenidos a Taller Web 1</h1>
					<a href="agregar-clase" class="btn btn-success">Agregar Clase</a>
					<main class="mt-4 d-flex flex-wrap">


						<c:forEach var="i" items="${clasesMap}">
							<div class="card mx-auto mb-3 text-center" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">${i.nombre}</h5>
								</div>
								<div class="card-body">
									<p class="card-text">${i.HorarioYFecha}</p>
									<p class="card-text">Capacidad: ${i.capacidad}</p>
									<a class="btn btn-warning text-white" href="modificar/${i.id}">Modificar</a>
									<a class="btn btn-danger" href="eliminar/${i.id}">Eliminar</a>
								</div>
							</div>
						</c:forEach>

					</main>
				</div>
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