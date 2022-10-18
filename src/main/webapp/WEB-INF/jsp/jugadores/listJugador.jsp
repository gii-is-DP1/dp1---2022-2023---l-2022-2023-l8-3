<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">

    <h2>Informaci�n sobre jugadores</h2>


    <table class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Apellido</th>
            <th style="width: 120px">Estado</th>
        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${selections}" var="jugador">
	            <tr>
	                
	                <td>
	                    <c:out value="${jugador.firstName}"/>
	                </td>
	                <td>
	                    <c:out value="${jugador.lastName}"/>
	                </td>
	                <td>
	                	<c:choose>
	                		<c:when test="${jugador.estadoOnline}">online</c:when>
	                		<c:otherwise>offline</c:otherwise>
	                    </c:choose>
	                </td>
	        	</tr>
	  		</c:forEach>
        </tbody>
    </table>

   

</petclinic:layout>