<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<c:set value='<%=session.getAttribute("idUsuario")%>' var="idUsuario" />
<c:if test="${empty idUsuario}">
	<c:set value="0" var="idUsuario" />
</c:if>

<c:set  value='<%= session.getAttribute("rolUsuario") %>' var="rol"  />
<c:if test="${empty rol}">
  <c:set  value="null" var="rol"  />
  </c:if>  
   
 

<c:set  value="<%=request.getContextPath()%>" var="contextPath"  />
		 
				<a href="${contextPath}/home" class="text-white text-decoration-none fw-bold">Home</a>
			
				<c:if test="${ rol == 'admin' }">
				<a href="${contextPath}/agregar-profesor" class="text-white text-decoration-none fw-bold">Agregar Profesor</a>
							
				<a href="${contextPath}/listado-pendiente/${idUsuario}"	class="text-white text-decoration-none fw-bold">Aprobar Compras</a>
				<a href="${contextPath}/clases-eliminadas" class="text-white text-decoration-none fw-bold">Clases Eliminadas</a>	
						
				</c:if>
				<a href="${contextPath}/filtar-profesor" class="text-white text-decoration-none fw-bold">Clases por profesor</a>
				<a href="${contextPath}/clases-disponibles" class="text-white text-decoration-none fw-bold">Clases Disponibles</a>	
				
				<c:if test="${ rol == 'usuario' }">	
				<a href="${contextPath}/clases-inscriptas/${idUsuario}" class="text-white text-decoration-none fw-bold">Clases incriptas</a>
				<a href="${contextPath}/comprarTarjeta/${idUsuario}" class="text-white text-decoration-none fw-bold">Comprar Tarjetas</a>
				<a href="${contextPath}/verMisCompras/${idUsuario}" class="text-white text-decoration-none fw-bold">Ver mis Compras</a>
				<a href="${contextPath}/notificaciones" class="text-white text-decoration-none fw-bold">notificaciones</a>
				</c:if>
				<a href="${contextPath}/login" class="text-white text-decoration-none fw-bold">Salir</a>
				