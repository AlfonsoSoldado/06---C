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
	name="finder" requestURI="finder/display.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="finder.singleKey" var="singleKeyHeader" />
	<display:column property="singleKey" title="${singleKeyHeader}" sortable="true" />

	<security:authorize access="hasRole('EXPLORER')">
		<spring:message code="finder.minPrice" var="minPriceHeader" />
		<display:column property="minPrice" title="${minPriceHeader}" sortable="true" />

		<spring:message code="finder.maxPrice" var="maxPriceHeader" />
		<display:column property="maxPrice" title="${maxPriceHeader}" sortable="true" />

		<spring:message code="finder.start" var="startHeader" />
		<display:column property="start" title="${startHeader}" sortable="true" />

		<spring:message code="finder.end" var="endHeader" />
		<display:column property="end" title="${endHeader}" sortable="true" />
	</security:authorize>

</display:table>

<spring:message code="finder.res" var="res" />
<h1><jstl:out value="${personalRecordHeader}"></jstl:out></h1>
<display:table name="finder.trips" class="displaytag" id="row">

	<spring:url var="link" value="trip/display.do">
		<spring:param name="tripId" value="${row.getId() }" />
	</spring:url>

	<display:column>
	<spring:message code="trip.title"/><jstl:out value="  ${row.getTitle()}" />
	</display:column>

</display:table>