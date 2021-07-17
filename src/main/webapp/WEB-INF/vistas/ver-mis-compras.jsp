<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>

	<link href="css/estilo.css" rel="stylesheet">
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.imgTarjeta{

    width: 100px;
    float: left;
    margin-right: 10px;

}</style>
</head>
<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />


<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>
<c:set  value='<%= session.getAttribute("rolUsuario") %>' var="rol"  />
<c:if test="${empty rol}">
  <c:set  value="null" var="rol"  />
  </c:if>  
   


<body class="mw-100 w3l-home">
	<div class="container-fluid">
		<div class="row">
			<div class="d-flex flex-column col-2 p-3 mb-2 bg-primary text-white">
				 
				 <jsp:include page="menu.jsp" />
				 
				
						</div>
			<div class="col-10">
				<div class="container">
				
					<main class="mt-4 d-flex flex-wrap">
						<h1 class="display-5 text-center col-12 mb-5">Historial de compras realizadas</h1>
						
						<div class="col-12">
 
            
             		 <c:if test="${not empty msj}">
                <div class="alert alert-success" role="alert">
                    <p>${msj}</p>
                </div>
            </c:if>	
            
            
			 			</div>
			 			<table class="table"> 
			 			<thead>
			 			<tr>
			 			<th>Fecha</th>
			 			<th>Tarjeta</th>
			 			<th>Cantidad</th>
			 			<th>Estado</th>
			 			</tr>
			 			</thead>
			 			<tbody>
			 			
						<c:forEach var="i" items="${comprasRealizadas}">
						
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
						<td>${i.estado}</td>
						
 					   	</tr>
						</c:forEach>
						</tbody>
						</table>
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