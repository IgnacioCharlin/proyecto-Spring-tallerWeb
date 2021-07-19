<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <!DOCTYPE html>
<html>
<head>
<title>EnerGym</title>	

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


 
<%
   Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("yyyy-MM-dd hh:mm");
   String hoy = ft.format(dNow);
%> 
<c:set  value='<%= hoy %>' var="hoy"  /> 

 
 
<c:set  value='<%= session.getAttribute("idUsuario") %>' var="idUsuario"  /> 
  <c:if test="${empty idUsuario}">
  <c:set  value="0" var="idUsuario"  />
  </c:if>	
<c:set  value='<%= session.getAttribute("rolUsuario") %>' var="rol"  />
<c:if test="${empty rol}">
  <c:set  value="null" var="rol"  />
  </c:if>  
  <c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
 
  <style>
.card-text{
color:#000;
}
.mx-auto {
    margin-right: 1.5em !important;
    margin-left: 1.5em !important;
    float: left !important;
}
</style>
</head>
   <body class="w3l-home overflow-hidden">
				<div class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" /> 
			</div>
			
			
			
		<div class = "container">
 
	 
	<main class="mt-4 d-flex flex-wrap col-12  card bg-dark">
 				 <h1 class="display-5 text-center col-12 mb-4 text-white">Notificaciones</h1>
				 
							<hr class="colorgraph" style="    color: white;">
		<br>
						<div class="col-12">	
						
						<c:forEach var="i" items="${clases}">
							<div class="card mx-auto mb-3 text-center" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">${i.nombre}</h5>
								</div>
								<div class="card-body">
								<p class="card-text">Tu clase comienza pronto </p>
									<p class="card-text">
 <c:set var="dateParts" value="${fn:split(i.HorarioYFecha, ' ')}" />
 <c:set var="dia" value="${fn:split(dateParts[0], '-')}" />
 <c:set var="hora" value="${fn:split(dateParts[1], ':')}" />
 		 ${dia[2]}/${dia[1]}/${dia[0]} ${hora[0]}:${hora[1]}
									 </p>
									
								
									<p>
									<a class="btn btn-primary text-white w-100" href="${contextPath}/notificaciones/leerNotificacion/${i.id}">notificado</a>
									</p>
												
								<c:if test="${ rol == 'usuario' }">
									   <c:if test="${ i.capacidad>i.inscriptos && i.HorarioYFecha>hoy }">
									<p>	<a class="btn btn-success text-white w-100" href="inscribirseclase/${i.id}/${idUsuario}">Inscribirse</a>
  									  </p>
  									  </c:if>
    							</c:if>
								</div>
							</div>
						</c:forEach>
						</div>
					</main>
				</div>
	 

              				<jsp:include page="pie.jsp" />

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