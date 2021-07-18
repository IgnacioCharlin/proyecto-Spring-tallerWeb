<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta charset="ISO-8859-1">
<title>EnerGym</title>


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
	
	
	
</head>
<body class="w3l-home overflow-hidden">
			<div class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" /> 
			</div>
			
				<div class="col-12 mx-auto mt-4">
		<div class="container">
		
	<div id="loginbox" class=" col-6 mx-auto text-center pt-2 form-profesor mt-5"
	 style="margin-bottom: 6em;padding: 1em;">
	<h1 class="display-5 mb-2">Agregar Profesor</h1>
		<hr class="colorgraph">
		<br>
	
						 <c:if test="${not empty msj}">
			                <div class="alert alert-warning" role="alert">
			                    <p>${msj}</p>
			                </div>
			            </c:if>	
			            
			            
	<form:form action="agregarProfesor" method="POST" 
	modelAttribute="registrarProfesor"  >
		<form:input path="email" id="email" type="mail"
			class="form-control mb-2" placeholder="Ingresar email" />
		<form:input path="password" id="password" type="password"
			placeholder="Ingresar Contraseña" class="form-control mb-2" />
		<form:input path="rol" type="text" id="rol" class="form-control mb-2"
			placeholder="Ingresar rol" />

		<c:if test="${not empty error}">
			<div class="alert alert-danger mt-2" role="alert">${error}</div>
		</c:if>
		<div class="d-flex justify-content-around">
			<button class="btn btn-lg btn-primary btn-block mt-2 "
				Type="Submit"  >Agregar</button>
		</div>
 		
	</form:form>

	</div>
	
	</div></div>
	              				<jsp:include page="pie.jsp" />
	
</body>
</html>