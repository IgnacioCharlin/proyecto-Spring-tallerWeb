
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
					<h1 class="display-5 text-center col-12 mb-5 text-white">Clases
						Eliminadas</h1>

					<div class="col-12"></div>
						<table class="table table-hover table-dark text-center">
							<thead>
								<tr>
									<th scope="col">Nombre</th>
									<th scope="col">Horario Y Fecha</th>
									<th scope="col">Capacidad</th>
									<th scope="col">Inscriptos</th>
									<th scope="col">Disponibilidad</th>
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
								</tr>
							</tbody>
						</table>
					
					
					
					<!-- 
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


							</div>
						</div>
						 -->
					</c:forEach>
				</main>
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