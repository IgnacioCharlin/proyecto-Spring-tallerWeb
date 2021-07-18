<!DOCTYPE html>
<html>
<head>
<title>EnerGym</title>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	


<style>
    .formulario{
    display: inline;
    width: 33%;
    float: left;
    }
    </style>
</head>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 
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
			<div
				class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" />

			</div>
			<div class="container">
				<main class="mt-4 d-flex flex-wrap col-8 mx-auto card bg-dark">
					<h1 class="display-5 text-center col-12 mb-4 text-white">Clases inscriptas</h1>
					 
						 
			
			
					<table class="table mt-3 table-hover table-dark text-center">
						<thead>
							<tr>
								<th scope="col">Nombre</th>
								<th scope="col">Fecha</th>
								<th scope="col">Calificacion</th>
						
							</tr>
						</thead>
						<tbody>
 
						<c:forEach var="i" items="${clasesMap}">
							<tr>
						  <c:set  value="0" var="estrellas"  />
						
							<th scope="row">${i.clase.nombre}</th>
							<th >${ i.clase.getHorarioYFecha()}
									
								 </th>
								
							<th >	
							<c:forEach var="calificacion" items="${calificaciones}">
							  <c:if test="${calificacion.clase.id==i.clase.id}">
  						 	  <c:set  value="${calificacion.calificacion}" var="estrellas"  />
							  </c:if>  
							</c:forEach>
							
	
					<c:forEach begin="1" step="1" end="3" var="estrella"> 
							<form action="${contextPath}/agregarCalificacion/${i.clase.id}/${idUsuario}/${estrella}" class="formulario">
							
							<c:if test="${estrellas>=estrella}">			
							<input title="boton enviar" alt="Completa" src="${contextPath}/images/ellena.png" type="image" style=" width: 50px;"/>
					 		</c:if> 
							<c:if test="${estrellas<estrella}">			
							<input title="boton enviar" alt="Vacia"    src="${contextPath}/images/evacia.png" type="image" style=" width: 50px;"/>
							</c:if> 
							
							</form> 
							
					</c:forEach></th>
							
							</tr>
						</c:forEach>
								</tbody>
					</table>
					</main>
				</div>
			</div>
		</div>
 

 	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
               				<jsp:include page="pie.jsp" />
 
</body>
</html>