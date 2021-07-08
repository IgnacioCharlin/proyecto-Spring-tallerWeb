
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
				<a href="../../home" class="text-white text-decoration-none fw-bold">Home</a>
				<a href="../../clases-disponibles" class="text-white text-decoration-none fw-bold">Clases Disponibles</a>
				<a href="../../agregar-profesor" class="text-white text-decoration-none fw-bold">Agregar Profesor</a>
				<a href="../../clases-inscriptas/${idUsuario}" class="text-white text-decoration-none fw-bold">Clases incriptas</a>
			</div>
			<div class="col-10">
				<div class="container">
				
					<main class="mt-12 d-flex flex-wrap">
						<h1 class="display-12 text-center col-12 mb-5">Tomar Presente</h1>
 
 							<div class="card mx-auto mb-12 text-center"  >
								<div class="card-body">
									<h5 class="card-title">Listado de alumnos</h5>
								</div>
								
								<div class="card-body">
								
								
		 <c:if test="${not empty msj}">
                <div class="alert alert-warning" role="alert">
                    <p>${msj}</p>
                </div>
            </c:if>	
            
             
            
             
            
            <c:if test="${not empty asistencia}">
								<table>
							<tr>
							<td>Alumno</td>
							<td style="    width: 200px;">Estado</td>
							<td>Accion</td>
 							</tr>
						<c:forEach var="i" items="${asistencia}">
						
	<c:set  value='Ausente' var="asistio"  /> 
  <c:if test="${i.presente==1}">
	<c:set  value='Presente' var="asistio"  /> 
  </c:if>	
						
							<tr>
							<td>${i.usuario.email}</td>
							<td>${asistio}</td>
							<td><a href="../../guardarAsistencia/${i.clase.id}/${i.usuario.id}" class="btn btn-primary">Cambiar</a></td>
 							</tr>
 							
						</c:forEach>
								
						 	</table>
								    </c:if>	
	
	
	   <input type="button" value="Página anterior" class="btn btn-danger btn-block mt-2 w-50" onClick="history.go(-1);">
								
								</div>
							</div>
				 
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