<%--
 * search.jsp
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

<form:form action="finder/search.do" modelAttribute="finder">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="minPrice" />
	<form:hidden path="maxPrice" />
	<form:hidden path="start" />
	<form:hidden path="end" />	
	<form:hidden path="cache" />
	
	<form:hidden path="trip" />
	
	<form:label path="singleKey">
		<spring:message code="finder.singleKey" />:
	</form:label>
	<form:input path="singleKey" />
	<form:errors cssClass="error" path="singleKey" />
	<br />
	
	<security:authorize access="hasRole('EXPLORER')">
		<form:label path="minPrice">
			<spring:message code="finder.minPrice" />:
		</form:label>
		<form:input path="minPrice" />
		<form:errors cssClass="error" path="minPrice" />
		<br />
		
		<form:label path="maxPrice">
			<spring:message code="finder.maxPrice" />:
		</form:label>
		<form:input path="maxPrice" />
		<form:errors cssClass="error" path="maxPrice" />
		<br />
		
		<form:label path="start">
			<spring:message code="finder.start" />:
		</form:label>
		<form:input path="start" />
		<form:errors cssClass="error" path="start" />
		<br />
		
		<form:label path="end">
			<spring:message code="finder.end" />:
		</form:label>
		<form:input path="end" />
		<form:errors cssClass="error" path="end" />
		<br />
	</security:authorize>
	
	<input type="submit" name="search"
		value="<spring:message code="finder.search" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="finder.cancel" />"
		onclick="javascript: relativeRedir('finder/search.do');" />
	<br />
</form:form>