<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head> 
<title>EnerGym</title>	
	<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />


<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
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
	
<body class="w3l-home overflow-hidden">
				<div class="d-flex flex-col w-100 p-3 mb-2 text-white justify-content-around">
				<jsp:include page="menu.jsp" /> 
			</div>
			
 
	 
		<div class = "container">
		<div id="loginbox" class=" col-6 mx-auto text-center pt-2 form-profesor mt-5 bg-dark"
	 style="margin-bottom: 6em;padding: 1em;">
	 
				<form:form action="${contextPath}/modificar/${id}" method="POST" modelAttribute="modificar">
 				<h3 class="text-white form-signin-heading display-5">Modificar Clase</h3>
		
		<hr class="colorgraph">
		<br>


		<form:input path="nombre" id="nombre" type="text" class="form-control mb-2 bg-dark " placeholder="Ingresar Nombre" value="${clase.getNombre()}"/>
		<form:input path="fechaYHora" id="fechaYHora" type="text" class="form-control mb-2 bg-dark " placeholder="Ingresar fecha y hora de la clase" value="${clase.getHorarioYFecha()}"/>
		<form:input path="cupo" type="number" id="cupo" class="form-control mb-2 bg-dark " placeholder="Ingresar capacidad" value="${clase.getCapacidad()}"/>

		<form:select path="idProfesor"  id="idProfesor" class="form-control mb-2 bg-dark "
		 placeholder="Ingresar el Id del profe">
		 <c:forEach var="i" items="${listaProfesores}">
		 <option value="${i.id}">${i.email}</option>
		 </c:forEach>
		 </form:select>
		 		
		
		<button class="btn btn-lg btn-primary btn-block mt-2 " Type="Submit" />Modificar</button>
		
 	</form:form>
				<%--Bloque que es visible si el elemento error no est� vac�o	--%>
				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>	
			</div>

		</div>
	</div>
	<script>
	document.getElementById("idProfesor").value=${clase.profesor.id};
	</script>


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