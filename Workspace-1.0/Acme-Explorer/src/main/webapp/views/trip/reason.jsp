<%--
 * trip.jsp
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

<form:form action="trip/manager/reason.do" modelAttribute="reason">

	<security:authorize access="hasRole('MANAGER')">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker"/>
	<form:hidden path="publication" />
	<form:hidden path="reason" />
	<form:hidden path="cancelled" />
	<form:hidden path="price" />
	<form:hidden path="manager" />
	<form:hidden path="application" />
	<form:hidden path="value" />
	<form:hidden path="title" />
	<form:hidden path="description" />
	<form:hidden path="requirement" />
	<form:hidden path="tripStart" />
	<form:hidden path="tripEnd" />
	<form:hidden path="category" />
	<form:hidden path="ranger" />
	<form:hidden path="stage" />

	<form:label path="reason">
		<spring:message code="trip.reason" />:
	</form:label>
	<form:input path="reason" />
	<form:errors cssClass="error" path="reason" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="trip.save" />" />&nbsp; 
	<jstl:if test="${trip.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="trip.delete" />"
			onclick="javascript: return confirm('<spring:message code="trip.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="trip.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	</security:authorize>
</form:form>