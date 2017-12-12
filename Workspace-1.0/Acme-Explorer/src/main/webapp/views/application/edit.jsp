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

<form:form action="application/explorer/edit.do" modelAttribute="application">

	<security:authorize access="hasRole('EXPLORER')">

	<form:hidden path="moment" />
	<form:hidden path="status" />
	<form:hidden path="reason" />
	<form:hidden path="creditCard" />
	<form:hidden path="manager" />
	<form:hidden path="explorer" />
	
	<form:label path="comment">
		<spring:message code="application.comment" />:
	</form:label>
	<form:input path="comment" />
	<form:errors cssClass="error" path="comment" />
	<br />
	
	<form:label path="trip">
		<spring:message code="application.trip" />:
	</form:label>
	<form:select path="trip">
        <form:options items="${trip}" itemLabel="title"/>
	</form:select>
	<form:input path="trip" />
	<form:errors cssClass="error" path="trip" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="application.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="application.cancel" />"
		onclick="javascript: relativeRedir('/application/explorer/list.do');" />
	<br />
	</security:authorize>
</form:form>