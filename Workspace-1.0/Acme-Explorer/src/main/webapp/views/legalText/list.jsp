<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<security:authorize access="hasRole('ADMIN')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="legalText" requestURI="legalText/administrator/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="legalText.edit"/>
	<display:column>
		<a href= "legalText/administrator/edit.do?legalTextId=${row.id}">
		<spring:message code="legalText.edit"/></a>
	</display:column>
	
	<spring:message code="legalText.delete"/>
	<display:column>
		<a href= "legalText/administrator/delete.do?legalTextId=${row.id}">
		<spring:message code="legalText.delete"/></a>
	</display:column>
	
	<spring:message code="legalText.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="legalText.body" var="bodyHeader" />
	<display:column property="body" title="${bodyHeader}" sortable="true" />
	
	<spring:message code="legalText.numberLaw" var="numberLaw" />
	<display:column property="numberLaw" title="${numberLawHeader}" sortable="true" />
	
	<spring:message code="legalText.moment" var="moment" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />
	
	<spring:message code="legalText.draftMode" var="draftMode" />
	<display:column property="draftMode" title="${draftModeHeader}" sortable="true" />
	
</display:table>
</security:authorize>