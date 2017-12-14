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
	name="sponsorship" requestURI="sponsorship/sponsor/list.do" id="row">
	
	<!-- Attributes -->
	
	<security:authorize access="hasRole('SPONSOR')">
	
	<spring:message code="sponsorship.edit"/>
	<display:column>
		<a href= "sponsorship/sponsor/edit.do?sponsorshipId=${row.id}">
		<spring:message code="sponsorship.edit"/></a>
	</display:column>
	
	<spring:message code="sponsorship.banner" var="bannerHeader" />
	<display:column property="banner" title="${bannerHeader}" sortable="true" />
	
	<spring:message code="sponsorship.infoPage" var="infoPageHeader" />
	<display:column property="infoPage" title="${infoPageHeader}" sortable="true" />
	
	<spring:message code="sponsorship.creditCard" var="creditCardHeader" />
	<display:column property="creditCard.number" title="${creditCardHeader}" sortable="false" />
	
	<spring:message code="sponsorship.trip" var="tripHeader" />
	<display:column property="trip.title" title="${tripHeader}" sortable="true" />
	
	<spring:message code="sponsorship.sponsor" var="sponsorHeader" />
	<display:column property="sponsor.id" title="${sponsorHeader}" sortable="true" />
	
	</security:authorize>
</display:table>
	
	<div>
		<a href="sponsorship/sponsor/create.do"> <spring:message
				code="sponsorship.create" />
		</a>
	</div>