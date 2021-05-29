<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS 
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme 
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
	
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
</head>
<body>
	<div class="container">
		<h1>Bienvenidos a Taller Web 1</h1>
		<a href="agregar-clase" class="btn btn-success">Agregar Clase</a>
		<main class="mt-4 d-flex flex-wrap">
			
					
			 <c:forEach var="i" items="${clasesMap}">
			 <div class="card mx-auto mb-3" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">${i.nombre}</h5> 
    			</div>
				<div class="card-body">
				 <p class="card-text"> ${i.getHorarioYFecha()}</p>
					<p class="card-text">Capacidad: ${i.capacidad}</p>
				</div>
			</div>
			</c:forEach>
			
			<!-- 
			<c:forEach var = "i" begin = "1" end = "5">
				Clases <c:out value = "${nombre}"/><p> 
			</c:forEach>
			-->	
				
		</main>
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