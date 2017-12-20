<%--
 * display.jsp
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
	name="displayTrip" requestURI="${requestUri}" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="trip.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" sortable="true" />

	<spring:message code="trip.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="trip.requirement" var="requirementHeader" />
	<display:column property="requirement" title="${requirementHeader}" sortable="true" />
	
		<spring:message code="trip.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="true" />
	
	<spring:message code="trip.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
	
	<spring:message code="trip.publication" var="publicationHeader" />
	<display:column property="publication" title="${publicationHeader}"	sortable="true" />
	
	<spring:message code="trip.tripStart" var="tripStartHeader" />
	<display:column property="tripStart" title="${tripStartHeader}"	sortable="true" />
	
	<spring:message code="trip.tripEnd" var="tripEndHeader" />
	<display:column property="tripEnd" title="${tripEndHeader}"	sortable="true" />

	<spring:message code="trip.cancelled" var="cancelledHeader"/>
	<display:column title="${cancelledHeader}">
		<a href= "trip/manager/reason.do?tripId=${row.id}">
			<spring:message code="trip.cancelled"/>
		</a>
	</display:column>
	
	<spring:message code="trip.category" var="categoryHeader" />
	<display:column property="category.name" title="${categoryHeader}"	sortable="true" />
	
	<spring:message code="trip.ranger" var="ranger"/>
	<display:column title="${ranger}">
		<a href= "curriculum/display.do?rangerId=${row.ranger.id}">
			<spring:message code="trip.ranger"/>
		</a>
	</display:column>
	
	<spring:message code="trip.audit" var="audit"/>
	<display:column title="${audit}">
		<a href= "audit/list.do?tripId=${row.id}">
			<spring:message code="trip.audit"/>
		</a>
	</display:column>
	
	<spring:message code="trip.note" var="note"/>
	<display:column title="${note}">
		<a href= "note/list.do?tripId=${row.id}">
			<spring:message code="trip.note"/>
		</a>
	</display:column>
	
	<spring:message code="trip.stage" var="stage"/>
	<display:column title="${stage}">
		<a href= "stage/list.do?tripId=${row.id}">
			<spring:message code="trip.stage"/>
		</a>
	</display:column>
	
</display:table>


<spring:message code="trip.sponsorship" var="bannerHeader" />
<h1><jstl:out value="${bannerHeader}"></jstl:out></h1>
<display:table name="sponsorship" class="displaytag" id="row">
		<spring:message code="trip.sponsorship" var="bannerHeader" />
		<display:column property="banner" title="${bannerHeader}" sortable="false" >
			<jstl:out value="${bannerHeader}"></jstl:out>
		</display:column>
</display:table>