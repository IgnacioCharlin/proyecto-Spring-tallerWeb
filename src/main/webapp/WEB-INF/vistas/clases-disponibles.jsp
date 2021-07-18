
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link href="css/font-awesome.min.css" rel="stylesheet">
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<%@ page import="java.util.*"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
Date dNow = new Date();
SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");
String hoy = ft.format(dNow);
%>
<c:set value='<%=hoy%>' var="hoy" />



<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>
<c:set value='<%=session.getAttribute("rolUsuario")%>' var="rol" />
<c:if test="${empty rol}">
	<c:set value="null" var="rol" />
</c:if>
<c:set value="<%=request.getContextPath()%>" var="contextPath" />


<body class="mw-100 w3l-home">
	<div class="container-fluid">
		<div class="row">
			<div
				class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" />

			</div>
			<div class="container">
				<main class="mt-4 d-flex flex-wrap col-8 mx-auto card bg-dark">
					<h1 class="display-5 text-center col-12 mb-4 text-white">Clases
						disponibles</h1>

					<div class="col-10 mx-auto">
						<form class="d-flex justify-content-center align-items-end flex-row mb-4"
							action="filtar-clase">
							<div class="d-flex flex-row justify-content-between">
								<div class="col-6">
									<label id="desde" class="text-white">Desde</label> <input
										type="date" name="desde" class="form-control">
								</div>
								<div class="col-6">
									<label id="hasta" class="text-white">Hasta</label> <input
										type="date" name="hasta" class="form-control">
								</div>
							</div>
							<div class="">
								<button type="submit" class="btn btn-primary w-100"><i class="fa fa-search" aria-hidden="true"></i></button>
							</div>
						</form>
					</div>
					
					<table class="table mt-3 table-hover table-dark text-center">
						<thead>
							<tr>
								<th scope="col">Nombre</th>
								<th scope="col">Fecha</th>
								<th scope="col">Capacidad</th>
								<th scope="col">Inscriptos</th>
								<th scope="col">Disponibilidad</th>
								<c:if test="${ rol == 'admin' || rol == 'profesor' }">
									<th scope="col">Tomar Asistencia</th>
									<c:if test="${ rol == 'admin' }">
										<th scope="col">Modificar</th>
										<th scope="col">Eliminar</th>
									</c:if>
								</c:if>
								<c:if test="${ rol == 'usuario' }">
									<th scope="col">Inscribirse</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${clasesMap}">
								<tr>
									<th scope="row">${i.nombre}</th>
									<td>${i.HorarioYFecha}</td>
									<td>${i.capacidad}</td>
									<td>${i.inscriptos}</td>
									<td>${i.capacidad - i.inscriptos}</td>
									<td><c:if test="${ rol == 'admin' || rol == 'profesor' }">
											<a class="btn btn-success text-white"
												href="tomarPresente/${i.id}/${idUsuario}"><i
												class="fa fa-check" aria-hidden="true"></i></a>
										</c:if> <c:if test="${ rol == 'usuario' }">
											<c:if test="${ i.capacidad>i.inscriptos}">
												<a class="btn btn-success text-white w-100"
													href="inscribirseclase/${i.id}/${idUsuario}"><i
													class="fa fa-plus" aria-hidden="true"></i></a>
											</c:if>
										</c:if></td>
									<c:if test="${ rol == 'admin' }">
										<td><a class="btn btn-warning text-white "
										href="${contextPath}/modificar/${i.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
										<td><a class="btn btn-danger"
										href="${contextPath}/eliminar/${i.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>				
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<!-- 
					<c:forEach var="i" items="${clasesMap}">
						<div class="card mx-auto mb-3 text-center" style="width: 18rem;">
							<div class="card-body">
								<h5 class="card-title">${i.nombre}</h5>
							</div>
							<div class="card-body">
								<p class="card-text">
									<c:set var="dateParts"
										value="${fn:split(i.HorarioYFecha, ' ')}" />
									<c:set var="dia" value="${fn:split(dateParts[0], '-')}" />
									<c:set var="hora" value="${fn:split(dateParts[1], ':')}" />
									${dia[2]}/${dia[1]}/${dia[0]} ${hora[0]}:${hora[1]}
								</p>
								<p class="card-text">Capacidad: ${i.capacidad}</p>
								<p class="card-text">Inscriptos: ${i.inscriptos}</p>
								<p class="card-text">Disponibilidad: ${i.capacidad - i.inscriptos}</p>
								<c:if test="${ rol == 'admin' || rol == 'profesor' }">
									<p>
										<a class="btn btn-primary text-white w-100"
											href="tomarPresente/${i.id}/${idUsuario}">Tomar
											Asistencia</a>
									</p>
								</c:if>

								<c:if test="${ rol == 'admin' }">
									<a class="btn btn-warning text-white"
										href="${contextPath}/modificar/${i.id}">Modificar</a>
									<a class="btn btn-danger"
										href="${contextPath}/eliminar/${i.id}">Eliminar</a>
								</c:if>

								<c:if test="${ rol == 'usuario' }">
									<c:if
										test="${ i.capacidad>i.inscriptos && i.HorarioYFecha>hoy }">
										<p>
											<a class="btn btn-success text-white w-100"
												href="inscribirseclase/${i.id}/${idUsuario}">Inscribirse</a>
										</p>
									</c:if>
								</c:if>
							</div>
						</div>
					</c:forEach>
				 -->
				</main>
			</div>
		</div>
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