<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
<!DOCTYPE html>
	<head>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<!-- Bootstrap core CSS 
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme 
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	</head>
	<body class="w3l-login">
		<div class = "container">
			<div id="loginbox" class="pt-5 col-6 mx-auto text-center form-section">
				<form:form action="validar-registro" method="POST" modelAttribute="datosRegistro">
		<h3 class="form-signin-heading display-5"><img src="${contextPath}/images/logo.png"></h3>

		<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
		<form:input path="email" id="email" type="email"
			class="form-control mb-2" placeholder="Ingresar Mail"/>
		<form:input path="password" type="password" id="password"
			class="form-control mb-2" placeholder="Ingresar contraseña"/>
		<form:input path="repitePassword" type="password" id="password"
			class="form-control mb-2" placeholder="Repetir contraseña"/>
		<button class="btn btn-lg btn-primary btn-block mt-2 w-100" Type="Submit" />Registrar</button>
	</form:form>
				<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
				<c:if test="${not empty error}">
			        <div class="alert alert-danger mt-2" role="alert" >${error}</div>
		        </c:if>
			</div>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
