<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />

	
 

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
.imgTarjeta{

    width: 100px;
    float: left;
    margin-right: 10px;

}</style>
<title>EnerGym</title>
</head>



<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>
<c:if test="${empty rol}">
	<c:set value="null" var="rol" />
</c:if>



<body class="mw-100 w3l-home">
	<div class="container-fluid">
		<div class="row">
			<div
				class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" />

			</div>
			<div class="container">
				<main class="mt-4 d-flex flex-wrap col-8 mx-auto card bg-dark">
					<h1 class="display-5 text-center col-12 mb-4 text-white">
					Aprobar Compras</h1>

 						
						<div class="col-12">
 
            
             		 <c:if test="${not empty msj}">
                <div class="alert alert-success" role="alert">
                    <p>${msj}</p>
                </div>
            </c:if>	
            
			 			</div>
					<table class="table mt-3 table-hover table-dark text-center">
			 			<thead>
			 			<tr>
			 			<th scope="col">Fecha</th>
			 			<th scope="col">Tarjeta</th>
			 			<th scope="col">Cantidad</th>
			 			<th scope="col">Usuario</th>
			 			<th scope="col">Estado</th>
			 			<th scope="col"><i class="fa fa-trash-o" aria-hidden="true"></i></th>
			 			<th scope="col">Aprobar</th>
			 			</tr>
			 			</thead>
			 			<tbody>
			 			
						<c:forEach var="i" items="${comprasPendientes}">
					  <c:set  value="" var="cssClass"  />
					  <c:if test="${i.estado=='Pendiente'}">
					  <c:set  value="table-warning" var="cssClass"  />
					  </c:if>	
					  <c:if test="${i.estado=='Rechazado'}">
					  <c:set  value="table-danger" var="cssClass"  />
					  </c:if>
  
						<tr class="${cssClass}">
						<td><fmt:formatDate value="${i.fecha}" pattern="dd/MM/yyyy" /></td>
						<td>${i.tarjeta.nombre} $ ${i.tarjeta.precio}<img src="${contextPath}/images/tarjeta${i.tarjeta.id}.png" alt="${i.tarjeta.nombre}" class="imgTarjeta"></td>
						<td>${i.cantidad}</td>
						<td>${i.usuario.email}</td>
						<td>
						${i.estado}
						</td>
						<td>
						<a href="${contextPath}/aprobar-compra/${i.id}/${i.usuario.id}/Eliminada/" class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
						</td>
						<td><a href="${contextPath}/aprobar-compra/${i.id}/${i.usuario.id}/Abonada/" class="btn btn-primary">Aprobar</a>
						</td>
						
 					   	</tr>
						</c:forEach>
						</tbody>
						</table>
					</main>
				</div>
			</div>
		</div>
	</div>

              				<jsp:include page="pie.jsp" />

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>