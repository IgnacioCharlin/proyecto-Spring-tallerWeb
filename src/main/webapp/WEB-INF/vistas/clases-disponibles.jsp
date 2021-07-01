
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
</head>


<c:set  value='<%= session.getAttribute("idUsuario") %>' var="idUsuario"  /> 
  <c:if test="${empty idUsuario}">
  <c:set  value="0" var="idUsuario"  />
  </c:if>	
  
  
  
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
				
					<main class="mt-4 d-flex flex-wrap">
						<h1 class="display-5 text-center col-12 mb-5">Clases disponibles</h1>
						
						<div class="col-12">
						<form class="d-flex col-6 mx-auto flex-column mb-4" action="filtar-clase">
			 					<div class="d-flex flex-row justify-content-between">
			 						<div class="col-4">
			 							<label id="desde" class="text-secondary">Desde</label>
			 							<input type="date" name="desde" class="form-control">
			 						</div>
			 						<div class="col-4">
			 							<label id="hasta" class="text-secondary">Hasta</label>
			 							<input type="date" name="hasta" class="form-control">
			 						</div>
			 					</div>
			 					<div class="">
			 						<input type="submit" value="Buscar" class="btn btn-primary w-100 mt-4"> 
			 					</div>
			 			</form>
			 			</div>
						<c:forEach var="i" items="${clasesMap}">
							<div class="card mx-auto mb-3 text-center" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">${i.nombre}</h5>
								</div>
								<div class="card-body">
									<p class="card-text">${i.HorarioYFecha}</p>
									<p class="card-text">Capacidad: ${i.capacidad}</p>
									<p class="card-text">Inscriptos: ${i.inscriptos}</p> 
									<p class="card-text">Disponibilidad: ${i.capacidad - i.inscriptos}</p>
									
									
									   <c:if test="${ i.capacidad>i.inscriptos}">
										<a class="btn btn-success text-white w-100" href="inscribirseclase/${i.id}">Inscribirse</a>
  									  </c:if>
    
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