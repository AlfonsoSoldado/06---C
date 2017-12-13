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
<security:authorize access="hasRole('ADMIN')">


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationApplication" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	
	<form:label path="applicationsTrip">
		<spring:message code="information.informationApplicationLabel" />:
	</form:label>

	<spring:message code="information.average" var="averageHeader" />
	<display:column property="average" title="${averageHeader}" sortable="true" />

	<spring:message code="information.minimum" var="minimumHeader" />
	<display:column property="minimum" title="${minimumHeader}" sortable="true" />

	<spring:message code="information.maximum" var="maximumHeader" />
	<display:column property="maximum" title="${maximumHeader}" sortable="true" />
	
	<spring:message code="information.standardDeviation" var="standardDeviationHeader" />
	<display:column property="standardDeviation" title="${standardDeviationHeader}" sortable="true" />
	
</display:table>

<%--
<form:label path="tripsManager">
		<spring:message code="information.informationTripsManagerLabel" />:
</form:label>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationTripsManager" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="information.average" var="averageHeader" />
	<display:column property="average" title="${averageHeader}" sortable="true" />

	<spring:message code="information.minimum" var="minimumHeader" />
	<display:column property="minimum" title="${minimumHeader}" sortable="true" />

	<spring:message code="information.maximum" var="maximumHeader" />
	<display:column property="maximum" title="${maximumHeader}" sortable="true" />
	
	<spring:message code="information.standardDeviation" var="standardDeviationHeader" />
	<display:column property="standardDeviation" title="${standardDeviationHeader}" sortable="true" />
	
</display:table>


<form:label path="priceTrips">
		<spring:message code="information.informationPriceTripsLabel" />:
</form:label>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationPriceTrips" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="information.average" var="averageHeader" />
	<display:column property="average" title="${averageHeader}" sortable="true" />

	<spring:message code="information.minimum" var="minimumHeader" />
	<display:column property="minimum" title="${minimumHeader}" sortable="true" />

	<spring:message code="information.maximum" var="maximumHeader" />
	<display:column property="maximum" title="${maximumHeader}" sortable="true" />
	
	<spring:message code="information.standardDeviation" var="standardDeviationHeader" />
	<display:column property="standardDeviation" title="${standardDeviationHeader}" sortable="true" />
	
</display:table>


<form:label path="guidedRanger">
		<spring:message code="information.informationTripsRangerLabel" />:
</form:label>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationTripsRanger" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="information.average" var="averageHeader" />
	<display:column property="average" title="${averageHeader}" sortable="true" />

	<spring:message code="information.minimum" var="minimumHeader" />
	<display:column property="minimum" title="${minimumHeader}" sortable="true" />

	<spring:message code="information.maximum" var="maximumHeader" />
	<display:column property="maximum" title="${maximumHeader}" sortable="true" />
	
	<spring:message code="information.standardDeviation" var="standardDeviationHeader" />
	<display:column property="standardDeviation" title="${standardDeviationHeader}" sortable="true" />
	
</display:table>


<form:label path="statusPending">
		<spring:message code="information.informationPendingLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationPending" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="statusDue">
		<spring:message code="information.informationDueLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationDue" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="statusAccepted">
		<spring:message code="information.informationAcceptedLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationAccepted" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="statusCancelled">
		<spring:message code="information.informationCancelledLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationCancelled" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="statusCancelled">
		<spring:message code="information.cancelledOrganisedLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationCancelledOrganised" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.cancelledOrganised" var="cancelledOrganisedHeader" />
	<display:column property="cancelledOrganised" title="${cancelledOrganisedHeader}" sortable="true" />

</display:table>


<form:label path="informationTripLabel">
		<spring:message code="information.informationTripLabel" />:
</form:label>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationTrip" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.trip" var="tripHeader" />
	<display:column property="trip" title="${tripHeader}" sortable="true" />

</display:table>


<form:label path="informationLegalTextLabel">
		<spring:message code="information.informationLegalTextLabel" />:
</form:label>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationLegalText" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.legalText" var="legalTextHeader" />
	<display:column property="legalText" title="${legalTextHeader}" sortable="true" />
	
	<spring:message code="information.referenced" var="referencedHeader" />
	<display:column property="referenced" title="${referencedHeader}" sortable="true" />
	
</display:table>


<form:label path="informationNotesLabel">
		<spring:message code="information.informationNotesLabel" />:
</form:label>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationNotes" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="information.average" var="averageHeader" />
	<display:column property="average" title="${averageHeader}" sortable="true" />

	<spring:message code="information.minimum" var="minimumHeader" />
	<display:column property="minimum" title="${minimumHeader}" sortable="true" />

	<spring:message code="information.maximum" var="maximumHeader" />
	<display:column property="maximum" title="${maximumHeader}" sortable="true" />
	
	<spring:message code="information.standardDeviation" var="standardDeviationHeader" />
	<display:column property="standardDeviation" title="${standardDeviationHeader}" sortable="true" />
	
</display:table>


<form:label path="informationAuditLabel">
		<spring:message code="information.informationAuditLabel" />:
</form:label>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="informationAudit" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="information.average" var="averageHeader" />
	<display:column property="average" title="${averageHeader}" sortable="true" />

	<spring:message code="information.minimum" var="minimumHeader" />
	<display:column property="minimum" title="${minimumHeader}" sortable="true" />

	<spring:message code="information.maximum" var="maximumHeader" />
	<display:column property="maximum" title="${maximumHeader}" sortable="true" />
	
	<spring:message code="information.standardDeviation" var="standardDeviationHeader" />
	<display:column property="standardDeviation" title="${standardDeviationHeader}" sortable="true" />
	
</display:table>


<form:label path="informationRatioAuditLabel">
		<spring:message code="information.informationRatioAuditLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationRatioAudit" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="informationRatioCurriculaLabel">
		<spring:message code="information.informationRatioCurriculaLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationRatioCurricula" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="informationRatioEndorsedLabel">
		<spring:message code="information.informationRatioEndorsedLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationRatioEndorsed" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="informationRatioSuspiciousManagerLabel">
		<spring:message code="information.informationRatioSuspiciousManagerLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationRatioSuspiciousManager" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>


<form:label path="informationRatioSuspiciousRangerLabel">
		<spring:message code="information.informationRatioSuspiciousRangerLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationRatioSuspiciousRanger" requestURI="information/administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="information.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>
 --%>

</security:authorize>







