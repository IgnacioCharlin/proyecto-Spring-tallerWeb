<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8" />
<meta name="keywords"
	content="Pool Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link href="css/font-awesome.min.css" rel="stylesheet">
<link
	href="//fonts.googleapis.com/css?family=Oswald:200,300,400,500,600,700"
	rel="stylesheet">
<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>
<c:set  value='<%= session.getAttribute("rolUsuario") %>' var="rol"  />
<c:if test="${empty rol}">
  <c:set  value="null" var="rol"  />
  </c:if>  

<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
</head>
<body class="mw-100">
	<header class="w3l-home">
		<div class="container-fluid">
			<div class="row">
				<div
					class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
					<a href="home" class="h5 text-white text-decoration-none fw-bold">Home</a>
				<c:if test="${ rol == 'admin' }">
					<a href="agregar-profesor"
						class="h5 text-white text-decoration-none fw-bold">Agregar
						Profesor</a>
				</c:if>
					<a href="filtar-profesor"
						class="h5 text-white text-decoration-none fw-bold">Clase Por
						Profesor</a> 
					<a href="clases-disponibles"
						class="h5 text-white text-decoration-none fw-bold">Clases
						Disponibles</a>
				<c:if test="${ rol == 'usuario' }">		
					<a href="clases-inscriptas/${idUsuario}"
						class="h5 text-white text-decoration-none fw-bold">Clases
						incriptas</a>
					<a href="comprarTarjeta/${idUsuario}"
						class="h5 text-white text-decoration-none fw-bold">Comprar
						Tarjetas</a>
				</c:if>
				</div>
				<h1 class="display-5 text-center titulo-home">JUST DO IT</h1>
	</header>
	<div class="col-10">
		<div class="container">
	<c:if test="${ rol == 'admin' }">
			<a href="agregar-clase" class="btn btn-success">Agregar Clase</a>
	</c:if>
			<main class="mt-4 d-flex flex-wrap">

				<c:forEach var="i" items="${clasesMap}">
					<div class="card" style="width: 18rem;">
						<img src="${contextPath}/images/${i.nombre}.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">${i.nombre}</h5>
							<p class="card-text">${i.HorarioYFecha}</p>
							<p class="card-text">Capacidad: ${i.capacidad}</p>
							<a class="btn btn-warning text-white" href="modificar/${i.id}">Modificar</a>
							<a class="btn btn-danger" href="eliminar/${i.id}">Eliminar</a>
						</div>
					</div>
					
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