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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="trip" requestURI="trip/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="trip.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" sortable="true" />

	<spring:message code="trip.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="trip.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
	
	<spring:message code="trip.publication" var="publicationHeader" />
	<display:column property="publication" title="${publicationHeader}"	sortable="true" />
	
	<spring:message code="trip.tripStart" var="tripStartHeader" />
	<display:column property="tripStart" title="${tripStartHeader}"	sortable="true" />
	
	<spring:message code="trip.tripEnd" var="tripEndHeader" />
	<display:column property="tripEnd" title="${tripEndHeader}"	sortable="true" />

	<spring:message code="trip.cancelled" var="cancelledHeader" />
	<display:column property="cancelled" title="${cancelledHeader}"	sortable="false" />
	
	<spring:message code="trip.category" var="categoryHeader" />
	<display:column property="category" title="${categoryHeader}"	sortable="true" />
	
	<spring:message code="trip.audit" var="auditHeader" />
	<display:column>
		<a href= "audit/list.do?tripId=${row.id}">
		<spring:message code="trip.audit" var="auditHeader" /></a>
	</display:column>
</display:table>

<!-- Action links -->

<security:authorize access="hasRole('MANAGER')">
	<div>
		<a href="trip/manager/edit.do"> <spring:message
				code="trip.create" />
		</a>
	</div>
</security:authorize>