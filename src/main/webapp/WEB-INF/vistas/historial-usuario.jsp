<!DOCTYPE html>
<html>
<head>
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

<style>
    .formulario{
    display: inline;
    width: 33%;
    float: left;
    }
    </style>
</head>

	<c:set  value='<%= session.getAttribute("idUsuario") %>' var="idUsuario"  /> 
  <c:if test="${empty idUsuario}">
  <c:set  value="0" var="idUsuario"  />
  </c:if>
<c:set value='<%=session.getAttribute("rolUsuario")%>' var="rol" />
<c:if test="${empty rol}">
	<c:set value="null" var="rol" />
</c:if>
<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />

<body class="mw-100 w3l-home">
	<div class="container-fluid">
		<div class="row">
			<div class="d-flex flex-column col-2 p-3 mb-2 bg-primary text-white">
			<jsp:include page="menu.jsp" />			
					</div>
			
			<div class="col-10">
				<div class="container">
					<main class="mt-4 d-flex flex-wrap">
						<h1 class="display-5 text-center col-12 mb-5">Clases inscriptas</h1>

						<c:forEach var="i" items="${clasesMap}">
						  <c:set  value="0" var="estrellas"  />
						
							<div class="card mx-auto mb-3 text-center" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">${i.clase.nombre}</h5>
								</div>
								<div class="card-body ">
									<p class="card-text">${i.clase.getHorarioYFecha()}</p>
									<p class="card-text">Capacidad: ${i.clase.capacidad}</p>
							
								
								
							<c:forEach var="calificacion" items="${calificaciones}">
							  <c:if test="${calificacion.clase.id==i.clase.id}">
  						 	  <c:set  value="${calificacion.calificacion}" var="estrellas"  />
							  </c:if>  
							</c:forEach>
							
	
<c:forEach begin="1" step="1" end="3" var="estrella"> 
		<form action="../agregarCalificacion/${i.clase.id}/${idUsuario}/${estrella}" class="formulario">
		
		<c:if test="${estrellas>=estrella}">			
		<input title="boton enviar" alt="Completa" src="../images/estrellaComleta.png" type="image" style=" width: 50px;"/>
 		</c:if> 
		<c:if test="${estrellas<estrella}">			
		<input title="boton enviar" alt="Vacia"    src="../images/estrellaVacia.png" type="image" style=" width: 50px;"/>
		</c:if> 
		
		</form> 
		
</c:forEach>

	
	  
	  
	  
	 
								</div>
							</div>
							
							
				
							
							
							
						</c:forEach>
					</main>
				</div>
			</div>
		</div>
	</div>

 	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 
</body>
</html>