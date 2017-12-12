<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestUri}" modelAttribute="actor">

	<security:authorize access="hasRole('RANGER')">
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="received" />
	<form:hidden path="suspicious" />
	<form:hidden path="socialId"/>
	
	<form:hidden path="curriculum" />
	<form:hidden path="trip" />
	</security:authorize>
	
	<security:authorize access="hasRole('EXPLORER')">
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="received" />
	<form:hidden path="socialId"/>
	
	<form:hidden path="finder" />
	<form:hidden path="emergency" />
	<form:hidden path="stories" />
	<form:hidden path="application" />
	<form:hidden path="survival" />
	</security:authorize>
	
	<security:authorize access="hasRole('SPONSOR')">
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="received" />
	<form:hidden path="socialId"/>
	
	<form:hidden path="sponsorship" />
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="received" />
	<form:hidden path="socialId"/>
	
	<form:hidden path="audit" />
	<form:hidden path="note" />
	</security:authorize>
	
	<security:authorize access="hasRole('MANAGER')">
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="received" />
	<form:hidden path="suspicious" />
	<form:hidden path="socialId"/>
	
	<form:hidden path="application" />
	<form:hidden path="survival" />
	<form:hidden path="trip" />
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<form:hidden path="folders"/>
	<form:hidden path="sent" />
	<form:hidden path="received" />
	<form:hidden path="socialId"/>
	</security:authorize>
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="userAccount"/>
	<form:hidden path="userAccount.password"/>
	<form:hidden path="userAccount.authorities"/>

	<form:label path="name">
		<spring:message code="actor.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="actor.surname" />:
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="email">
		<spring:message code="actor.email" />:
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="phoneNumber">
		<spring:message code="actor.phoneNumber" />:
	</form:label>
	<form:input path="phoneNumber" />
	<form:errors cssClass="error" path="phoneNumber" />
	<br />

	<form:label path="address">
		<spring:message code="actor.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	
	<form:label path="userAccount.username">
		<spring:message code="actor.username" />
	</form:label>
	<form:input path="userAccount.username" />	
	<form:errors class="error" path="userAccount.username" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />&nbsp; 

	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	
</form:form>