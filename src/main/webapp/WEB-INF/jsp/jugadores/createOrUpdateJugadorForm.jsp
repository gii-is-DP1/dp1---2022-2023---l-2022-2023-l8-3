<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<style>
@import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap');</style>

<petclinic:layout pageName="jugadorForm">
	
				
	
	<div class="WelcomeNuevoUsu">
		<h1>Bienvenido<p class="letras">JUGADOR</p>que tal si nos registramos</h1>
	</div>
	<br>
	<c:if test="${contraseñaIncorrecta}">
		<div class="alert alert-info">
    	 Contrase&ntilde;a incorrecta, debe tener entre 10 y 50 caracteres y al menos un numero
		</div>
	</c:if>
	<c:if test="${emailIncorrecto1}">
		<div class="alert alert-info">
    	 Email incorrecto, ya esta registrado a nombre de otro jugador
		</div>
	</c:if>
	<c:if test="${emailIncorrecto2}">
		<div class="alert alert-info">
    	 El Email no cumple el formato correcto
		</div>
	</c:if>
    <c:if test="${sinJugadores}"><div class="alert alert-info">No hay jugadores registrados actualmente</div></c:if>
	<form:form modelAttribute="jugador" class="form-horizontal"
		id="add-owner-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="Nombre de usuario" name="user.username" />
			<petclinic:inputField label="Nombre" name="firstName" />
			<petclinic:inputField label="Apellido" name="lastName" />
			<petclinic:inputField label="Email" name="user.email" />
			<petclinic:inputField label="Contrase&ntilde;a" name="user.password" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${jugador['new']}">
						<button class="btn btn-default btn-block" type="submit">Únete a nosotros</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default btn-block" type="submit">Actualizar información</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
</petclinic:layout>
<style type="text/css">

.WelcomeNuevoUsu{

text-align: center;

}

.letras{
position: relative;
	text-align: center;
	font-size: xx-large;
	font-family: 'Press Start 2P', cursive;
	animation-name: anim;
	animation-duration: 5s;
	animation-iteration-count: infinite;
}
@keyframes anim{
    0% {color: blue;} /*Amarillo*/
   25% {color: purple;} /*Naranja*/
   50% {color: red;} /*Negro*/
   75% {color: black;} /*Otra vez naranja*/
  100% {color: blue;} /*Otra vez amarillo*/
}

</style>

