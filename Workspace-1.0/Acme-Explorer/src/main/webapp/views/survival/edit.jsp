<%--
 * edit.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="survival/manager/edit.do" modelAttribute="survival">
	
	<security:authorize access="hasRole('MANAGER')">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="moment" />
	<form:hidden path="manager" />
	<form:hidden path="explorer" />
	
	<form:label path="title">
		<spring:message code="survival.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="description">
		<spring:message code="survival.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="location">
		<spring:message code="survival.location" />:
	</form:label>
	<form:input path="location" />
	<form:errors cssClass="error" path="location" />
	<br />
	
	<form:label path="trip">
		<spring:message code="survival.trip" />:
	</form:label>
	<form:select path="trip">
        <form:options items="${trip}" itemLabel="title"/>
	</form:select>
	<form:errors cssClass="error" path="trip" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="survival.save" />" />&nbsp; 
	<jstl:if test="${survival.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="survival.delete" />"
			onclick="return confirm('<spring:message code="survival.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="survival.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />

	</security:authorize>
</form:form>