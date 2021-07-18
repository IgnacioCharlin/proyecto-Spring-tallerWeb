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
	

</head>
<%@page import="java.util.*"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  

<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>

<c:set  value='<%= session.getAttribute("rolUsuario") %>' var="rol"  />
<c:if test="${empty rol}">
  <c:set  value="null" var="rol"  />
  </c:if>  
   
   

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

<body class="mw-100 w3l-home">
		<div
				class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" />

			</div>
			
	<div class="container-fluid">
		<div class="row">
	 
			<div class="col-12">
				<div class="container">
				
	<main class="mt-4 d-flex flex-wrap col-12 mx-auto card bg-dark">
 		 <h1 class="display-5 text-center col-12 mb-4 text-white">Tarjetas Disponibles</h1>
							<hr class="colorgraph" style="    color: white;">
		<br>
						<div class="col-12">
 
 		 <c:if test="${not empty tarjetaComprada}">
                <div class="alert alert-warning" role="alert">
                    <p style="color: #000;">Su usuario acaba de comprar ${tarjetaComprada.cantidad} creditos para poder inscribirse en las clases.</p>
                </div>
            </c:if>	
            
            
             		 <c:if test="${not empty msj}">
                <div class="alert alert-success" role="alert">
                    <p style="color: #000;">${msj}</p>
                </div>
            </c:if>	
            

         
			
			 			
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
 <p style="margin-top: 1em;">
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
</c:if> </p>

								</div>
							</div>
							
						</c:forEach>
						 			</div>
					</main>
				</div>
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