
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
<%@ page import="java.util.*" %>

<%@ page import="java.text.SimpleDateFormat"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 
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
  
   
<body class="mw-100">
	<div class="container-fluid">
		<div class="row">
			<div class="d-flex flex-column col-2 p-3 mb-2 bg-primary text-white">
				<jsp:include page="menu.jsp" />
			
			</div>
			<div class="col-10">
				<div class="container">
				
					<main class="mt-4 d-flex flex-wrap">
						<h1 class="display-5 text-center col-12 mb-5">Clases disponibles</h1>
						
						
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