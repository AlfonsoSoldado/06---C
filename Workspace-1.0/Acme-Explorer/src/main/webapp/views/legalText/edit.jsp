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

<form:form action="legalText/administrator/edit.do" modelAttribute="legalText">
	<security:authorize access="hasRole('ADMIN')">
	
	<form:hidden path="trip" />
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="title">
		<spring:message code="legalText.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="body">
		<spring:message code="legalText.body" />:
	</form:label>
	<form:input path="body" />
	<form:errors cssClass="error" path="body" />
	<br />
	
	<form:label path="numberLaw">
		<spring:message code="legalText.numberLaw" />:
	</form:label>
	<form:input path="numberLaw" />
	<form:errors cssClass="error" path="numberLaw" />
	<br />
	
	<form:label path="moment">
		<spring:message code="legalText.moment" />:
	</form:label>
	<form:input path="moment" />
	<form:errors cssClass="error" path="moment" />
	<br />
	
	<form:label path="draftMode">
		<spring:message code="legalText.draftMode" />:
	</form:label>
	<form:input path="draftMode" />
	<form:errors cssClass="error" path="draftMode" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="legalText.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="legalText.cancel" />"
		onclick="javascript: relativeRedir('/legalText/administrator/list.do');" />
	<br />
	
	</security:authorize>
</form:form>



