<%@page import="java.util.*"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>

	<link href="css/estilo.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>


<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>
<c:if test="${empty rol}">
	<c:set value="null" var="rol" />
</c:if>

<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
 

 <body class="mw-100">
	<div class="container-fluid">
		<div class="row">
			<div class="d-flex flex-column col-2 p-3 mb-2 bg-primary text-white">
				<a href="${contextPath}/home" class="text-white text-decoration-none fw-bold">Home</a>
				<a href="${contextPath}/clases-disponibles" class="text-white text-decoration-none fw-bold">Clases Disponibles</a>
				<c:if test="${ rol == 'admin' }">
				<a href="${contextPath}/agregar-profesor" class="text-white text-decoration-none fw-bold">Agregar Profesor</a>
				</c:if>
				<a href="${contextPath}/filtar-profesor" class="text-white text-decoration-none fw-bold">Clase Por Profesor</a>
				<a href="${contextPath}/clases-inscriptas/${idUsuario}" class="text-white text-decoration-none fw-bold">Clases incriptas</a>
				<a href="${contextPath}/comprarTarjeta/${idUsuario}" class="text-white text-decoration-none fw-bold">Comprar Tarjetas</a>
				<a href="${contextPath}/verMisCompras/${idUsuario}" class="text-white text-decoration-none fw-bold">Ver mis Compras</a>
			</div>
			<div class="col-10">
				<div class="container">
				
					<main class="mt-4 d-flex flex-wrap">
						<h1 class="display-5 text-center col-12 mb-5">Tarjetas Disponibles</h1>
						
						<div class="col-12">
 
 		 <c:if test="${not empty tarjetaComprada}">
                <div class="alert alert-warning" role="alert">
                    <p>Su usuario acaba de comprar ${tarjetaComprada.cantidad} creditos para poder inscribirse en las clases.</p>
                </div>
            </c:if>	
            
            
             		 <c:if test="${not empty msj}">
                <div class="alert alert-success" role="alert">
                    <p>${msj}</p>
                </div>
            </c:if>	
            

         
			 			</div>
			 			
						<c:forEach var="i" items="${tarjetasDisponible}">
							<div class="card mx-auto mb-3 text-center" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">${i.nombre}</h5>
								</div>
								<div class="card-body">
									<p class="card-text">${i.descripcion}</p>
									<p class="card-text">Precio:$ ${i.precio}</p>
									<p class="card-text">Cantidad de Clases: ${i.cantidad}</p> 
									<img src="${contextPath}/images/tarjeta${i.id}.png" alt="${i.nombre}">
 
<c:if test="${i.id==1}">
<script
  src="https://www.mercadopago.com.ar/integrations/v1/web-payment-checkout.js"
  data-preference-id="<%= session.getAttribute("preference1") %>">
</script>
</c:if> 
<c:if test="${i.id==2}">
<script
  src="https://www.mercadopago.com.ar/integrations/v1/web-payment-checkout.js"
  data-preference-id="<%= session.getAttribute("preference2") %>">
</script>
</c:if> 
<c:if test="${i.id==3}">
<script
  src="https://www.mercadopago.com.ar/integrations/v1/web-payment-checkout.js"
  data-preference-id="<%= session.getAttribute("preference3") %>">
</script>
</c:if> 

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
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>