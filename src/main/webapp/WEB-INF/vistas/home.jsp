<!DOCTYPE html>
<html>
<head>
<title>EnerGym</title>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8" />


<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="${contextPath}/css/estilo.css" type="text/css" media="all" />
<link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
<link href="${contextPath}/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
	
	


 
<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>
<c:set  value='<%= session.getAttribute("rolUsuario") %>' var="rol"  />
<c:if test="${empty rol}">
  <c:set  value="null" var="rol"  />
  </c:if>  


 
</head>
<body class="mw-100 background-body">
	<header class="w3l-home">
		<div class="container-fluid">
			<div class="row">
				<div
					class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">

				<jsp:include page="menu.jsp" />

				</div>
				<h1 class="display-5 text-center titulo-home">JUST DO IT</h1>
	</header>
	
		<c:if test="${ rol == 'admin' }">
		<div class="col-12" style="
    margin-top: 1em;
">
 <div class="container">
<a href="${contextPath}/agregar-clase" class="btn btn-success" style="    float: right;">Agregar Clase</a>
	</div></div>
	</c:if>
	
	
	<div class="col-12 mx-auto mt-4">
		<div class="container">
			<h1 class="display-5 mb-2">Listado de Clases</h1>
		

	
	
			<main class="mt-4 d-flex flex-wrap justify-content-between">

				<c:forEach var="i" items="${clasesMap}">
					<div class="card text-center bg-dark text-white" style="width: 16rem;">
						<img src="${contextPath}/images/${i.nombre}.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">${i.nombre}</h5>
 							
								<p class="card-text">
									<c:set var="dateParts"
										value="${fn:split(i.HorarioYFecha, ' ')}" />
									<c:set var="dia" value="${fn:split(dateParts[0], '-')}" />
									<c:set var="hora" value="${fn:split(dateParts[1], ':')}" />
									${dia[2]}/${dia[1]}/${dia[0]} ${hora[0]}:${hora[1]}
								</p>
								
								
							<p class="card-text">Capacidad: ${i.capacidad}</p>
							
								<c:if test="${ rol == 'admin' }">
							<a class="btn btn-warning text-white" href="${contextPath}/modificar/${i.id}">Modificar</a>
							<a class="btn btn-danger" href="${contextPath}/eliminar/${i.id}">Eliminar</a>
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
              				<jsp:include page="pie.jsp" />

</html>