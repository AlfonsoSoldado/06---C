<%--
 * register_Auditor.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="administrator/register_Auditor.do"
	modelAttribute="auditor">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="userAccount"/>
	<form:hidden path="userAccount.authorities"/>
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="socialId"/>
	<form:hidden path="suspicious" />

	<form:hidden path="audit" />
	<form:hidden path="note" />

	<form:label path="name">
		<spring:message code="auditor.name" />:
		</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="auditor.surname" />:
		</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="email">
		<spring:message code="auditor.email" />:
		</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phoneNumber">
		<spring:message code="auditor.phoneNumber" />:
		</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />

	<form:label path="address">
		<spring:message code="auditor.address" />:
		</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="userAccount.username">
		<spring:message code="auditor.username" />
	</form:label>
	<form:input path="userAccount.username" />
	<form:errors class="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="auditor.password" />
	</form:label>
	<form:password path="userAccount.password" />
	<form:errors class="error" path="userAccount.password" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="administrator.save" />" />

	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
</form:form>