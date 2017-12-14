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

<!-- Listing grid -->

<security:authorize access="hasRole('MANAGER')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="survival" requestURI="survival/manager/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="survival.edit"/>
	<display:column>
		<a href= "survival/manager/edit.do?survivalId=${row.id}">
		<spring:message code="survival.edit"/></a>
	</display:column>
	
	<spring:message code="survival.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />

	<spring:message code="survival.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />

	<spring:message code="survival.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />

	<spring:message code="survival.location" var="locationHeader" />
	<display:column property="location.name" title="${locationHeader}"	sortable="true" />
	
	<spring:message code="survival.trip" var="tripHeader" />
	<display:column property="trip.title" title="${tripHeader}"	sortable="true" />
	
	<spring:message code="survival.explorer" var="explorerHeader" />
	<display:column property="explorer" title="${explorerHeader}"	sortable="true" />
</display:table>

<!-- Action links -->

	<div>
		<a href="survival/manager/create.do"> <spring:message
				code="survival.create" />
		</a>
	</div>
</security:authorize>