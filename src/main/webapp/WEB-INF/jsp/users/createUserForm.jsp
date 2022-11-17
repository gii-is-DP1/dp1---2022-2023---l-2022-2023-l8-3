<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="userForm">
	<h2>Reg&iacute;strate</h2>
	<br>
	<form:form modelAttribute="jugador" class="form-horizontal"
		id="add-owner-form">
		<div class="form-group has-feedback">
			<c:choose>
				<c:when test="${jugador['new']}">
					<petclinic:inputField label="Nombre de usuario"
						name="user.username" />
				</c:when>
			</c:choose>
			<petclinic:inputField label="Nombre" name="firstName" />
			<petclinic:inputField label="Apellido" name="lastName" />
			<petclinic:inputField label="Email" name="user.email" />
			<petclinic:inputField label="Contrase&ntilde;a" name="user.password" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Registrarse</button>
			</div>
		</div>
	</form:form>
</petclinic:layout>
