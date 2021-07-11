<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.google.gson.Gson" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	
	
<meta charset="ISO-8859-1">
<title>Notificacion</title>
</head>
<body>

<c:set  value='<%= session.getAttribute("idUsuario") %>' var="idUsuario"  /> 
  <c:if test="${empty idUsuario}">
  <c:set  value="0" var="idUsuario"  />
  </c:if>	
  
  
	<div class="container-fluid">
					<main class="pt-5 col-6 mx-auto text-center">
					
					
													

            
            
						<h1 class="display-5 text-center col-12 mb-5">Notificación</h1>
						<div class="card mx-auto" >
							 
						<div class="card-body">
			
					 <c:if test="${not empty msj}">
			                <div class="alert alert-warning" role="alert">
			                    <p>${msj}</p>
			                </div>
			            </c:if>	
            
            	 <c:if test="${not empty json}">
			  123
				${json}
 
			         
			            </c:if>	
			            
			            
            			<a onClick="history.go(-1);" class="btn btn-danger btn-block mt-2 w-50"/>Volver</a>
						</div>
						
	</main>
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