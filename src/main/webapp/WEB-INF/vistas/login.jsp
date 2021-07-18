<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
<!DOCTYPE html>
	<head>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<link rel="stylesheet" href="css/main.css" type="text/css" media="all" />
	<link rel="stylesheet" href="css/main.css" type="text/css" media="all" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body class="w3l-login">
		<div class = "container" style="    padding-top: 5em;">
			<div id="loginbox" class="pt-5 col-6 mx-auto text-center form-section">
				<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
				<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
				<form:form action="validar-login" method="POST" modelAttribute="usuario">
			    	<h3 class="form-signin-heading display-5 text-white"><img src="${contextPath}/images/logo1.png"></h3>

					<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
					<form:input path="email" id="email" type="email" class="form-control mb-2 bg-dark text-white" placeholder='Ingresar Mail' />
					<form:input path="password" type="password" id="password" class="form-control mb-2 bg-dark text-white" placeholder='Ingresar contraseña'/>     		  
					
					<button class="btn btn-lg btn-danger btn-block mt-2 w-100" Type="Submit"/>Ingresar</button>
				</form:form>
				<c:if test="${not empty error}">
			        <div class="alert alert-danger mt-2" role="alert" >${error}</div>
			        <br>
		        </c:if>	
				<a href='registro' class="d-block mt-4 text-danger">¿Todavía no te registraste? Hace click aquí</a>
				<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>