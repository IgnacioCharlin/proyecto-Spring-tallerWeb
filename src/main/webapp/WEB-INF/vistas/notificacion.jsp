<!DOCTYPE html>
<html>
<head>
<title>EnerGym</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

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

	

</head>



<body class="mw-100 w3l-home">
			<div
				class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" />

			</div>
			
<div class="col-12 mx-auto mt-4">
<div class="container">
 
	
 

<c:set  value='<%= session.getAttribute("idUsuario") %>' var="idUsuario"  /> 
  <c:if test="${empty idUsuario}">
  <c:set  value="0" var="idUsuario"  />
  </c:if>	
  
  
	<div class="container-fluid">
					<main class="pt-5 col-6 mx-auto text-center bg-dark">
						<div class=""  style="margin-bottom: 7em;"> 
    	 				<h3 class="text-white form-signin-heading display-5">Notificación</h3>
 
 	<hr class="colorgraph">
						<div style="    padding-bottom: 2em;">
			
					 <c:if test="${not empty msj}">
			                <div class="alert alert-warning" role="alert">
			                    <p style="
    color: #0a0a0a;
">${msj}</p>
			                </div>
			            </c:if>	
            
            	 <c:if test="${not empty json}">

				${json}
 
			         
			            </c:if>	
			             
            			<a onClick="location.href = document.referrer;" class="btn btn-danger btn-block mt-2 " style="    color: white;">Volver</a>
						</div></div>
						
	</main>
	</div>


</div></div>
              				<jsp:include page="pie.jsp" />


<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>