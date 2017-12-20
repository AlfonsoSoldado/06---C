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
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	
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
	
	<form:label path="draftMode">
		<spring:message code="legalText.draftMode" />:
	</form:label>
	<form:select path="draftMode">
        <form:option value="true" label="Yes"/>
        <form:option value="false" label="No"/>
	</form:select>
	<form:errors cssClass="error" path="draftMode" />
	<br />
	
 	<form:label path="trip">
		<spring:message code="legalText.trip" />:
	</form:label>
	<form:select id="trip" path="trip" multiple="true">
		<form:option item="null" value="0" label="----"/>
        <form:options items="${trip}" itemValue="id" itemLabel="title"/>
	</form:select>
	<form:errors cssClass="error" path="trip" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="legalText.save" />" />&nbsp; 
	<jstl:if test="${legalText.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="legalText.delete" />"
			onclick="javascript: return confirm('<spring:message code="legalText.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="legalText.cancel" />"
		onclick="javascript: relativeRedir('/legalText/administrator/list.do');" />
	<br />
	
	</security:authorize>
</form:form>



