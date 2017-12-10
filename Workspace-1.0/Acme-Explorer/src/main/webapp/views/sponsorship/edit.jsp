<%--
 * list.jsp
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

<form:form action="sponsorship/sponsor/edit.do" modelAttribute="sponsorship">
	
	<security:authorize access="hasRole('SPONSOR')">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="sponsor" />
	
	<form:label path="banner">
		<spring:message code="sponsorship.banner" />:
	</form:label>
	<form:input path="banner" />
	<form:errors cssClass="error" path="banner" />
	<br />
	
	<form:label path="infoPage">
		<spring:message code="sponsorship.infoPage" />:
	</form:label>
	<form:input path="infoPage" />
	<form:errors cssClass="error" path="infoPage" />
	<br />
	
	<form:label path="creditCard">
		<spring:message code="sponsorship.creditCard" />:
	</form:label>
	<form:input path="creditCard" />
	<form:errors cssClass="error" path="creditCard" />
	<br />
	
	<form:label path="trip">
		<spring:message code="sponsorship.trip" />:
	</form:label>
	<form:select path="trip">
        <form:options items="${trip}" itemLabel="title"/>
	</form:select>
	<form:input path="trip" />
	<form:errors cssClass="error" path="trip" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="sponsorship.save" />" />&nbsp; 
	<jstl:if test="${sponsorship.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="sponsorship.delete" />"
			onclick="return confirm('<spring:message code="sponsorship.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="sponsorship.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />

	</security:authorize>
</form:form>