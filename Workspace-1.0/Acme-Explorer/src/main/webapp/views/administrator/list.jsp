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

<spring:message code="administrator.informationApplicationLabel"/>
<jstl:out value="${informationApplicationLabel}"></jstl:out>
<table class="displaytag"  name="informationApplication">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.minimum" var="minimumHeader" />
			<jstl:out value="${minimumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.maximum" var="maximumHeader" />
			<jstl:out value="${maximumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${informationApplication}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.informationTripsManagerLabel" />
<jstl:out value="${informationTripsManagerLabel}"></jstl:out>
<table class="displaytag"  name="avgMinMaxSqtrManager">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.minimum" var="minimumHeader" />
			<jstl:out value="${minimumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.maximum" var="maximumHeader" />
			<jstl:out value="${maximumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${avgMinMaxSqtrManager}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.informationPriceTripsLabel" />
<jstl:out value="${informationPriceTripsLabel}"></jstl:out>
<table class="displaytag"  name="avgMinMaxSqtr2">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.minimum" var="minimumHeader" />
			<jstl:out value="${minimumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.maximum" var="maximumHeader" />
			<jstl:out value="${maximumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${avgMinMaxSqtr2}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.informationTripsRangerLabel" />
<jstl:out value="${informationTripsRangerLabel}"></jstl:out>
<table class="displaytag"  name="avgMinMaxSqtrRanger">
	<tr>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.minimum" var="minimumHeader" />
			<jstl:out value="${minimumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.maximum" var="maximumHeader" />
			<jstl:out value="${maximumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${avgMinMaxSqtrRanger}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.informationPendingLabel" />
<jstl:out value="${informationPendingLabel}"></jstl:out>
<table class="displaytag"  name="applicationPending">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${applicationPending}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.informationDueLabel" />
<jstl:out value="${informationDueLabel}"></jstl:out>
<table class="displaytag"  name="applicationDue">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${applicationDue}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.informationAcceptedLabel" />
<jstl:out value="${informationAcceptedLabel}"></jstl:out>
<table class="displaytag"  name="applicationAccepted">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${applicationAccepted}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.informationCancelledLabel" />
<jstl:out value="${informationCancelledLabel}"></jstl:out>
<table class="displaytag"  name="applicationCancelled">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${applicationCancelled}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.cancelledOrganisedLabel" />
<jstl:out value="${cancelledOrganisedLabel}"></jstl:out>
<table class="displaytag"  name="ratioTripsCancelled">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${ratioTripsCancelled}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.informationTripLabel" />
<jstl:out value="${informationTripLabel}"></jstl:out>
<table class="displaytag"  name="tripsThanAverage">
	<tr>
		<th>
			<spring:message code="administrator.trip" var="titleHeader" />
			<jstl:out value="${titleHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${tripsThanAverage}"></jstl:out>
		</td>
	</tr>
</table>
<%--
<display:table class="displaytag"  name="tripsThanAverage" id="row">
	<spring:message code="trip.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="false">
	<jstl:out value="${row.title}"></jstl:out>
	</display:column>
</display:table>
 --%>

<spring:message code="administrator.informationLegalTextLabel" />
<jstl:out value="${informationLegalTextLabel}"></jstl:out>
<table class="displaytag"  name="tripsLegalTextReferenced">
	<tr>
		<th>
			<spring:message code="administrator.legalText" var="legalTextHeader" />
			<jstl:out value="${legalTextHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.referenced" var="referencedHeader" />
			<jstl:out value="${referencedHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${tripsLegalTextReferenced[1]}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.informationNotesLabel" />
<jstl:out value="${informationNotesLabel}"></jstl:out>
<table class="displaytag"  name="avgMinMaxSqtr3">
	<tr>
		<th>
			<spring:message code="administrator.minimum" var="minimumHeader" />
			<jstl:out value="${minimumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.maximum" var="maximumHeader" />
			<jstl:out value="${maximumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${avgMinMaxSqtr3}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.informationAuditLabel" />
<jstl:out value="${informationAuditLabel}"></jstl:out>
<table class="displaytag"  name="avgMinMaxSqtr4">
	<tr>
		<th>
			<spring:message code="administrator.minimum" var="minimumHeader" />
			<jstl:out value="${minimumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.maximum" var="maximumHeader" />
			<jstl:out value="${maximumHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.average" var="averageHeader" />
			<jstl:out value="${averageHeader}"></jstl:out>
		</th>
		<th>
			<spring:message code="administrator.standardDeviation" var="standardDeviationHeader" />
			<jstl:out value="${standardDeviationHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<jstl:forEach var="datos" items="${avgMinMaxSqtr4}">
			<td>
				<jstl:out value="${datos}"></jstl:out>
			</td>
		</jstl:forEach>
	</tr>
</table>

<spring:message code="administrator.informationRatioAuditLabel" />
<jstl:out value="${informationRatioAuditLabel}"></jstl:out>
<table class="displaytag"  name="avgMinMaxSqtr5">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${avgMinMaxSqtr5}"></jstl:out>
		</td>
	</tr>
</table>







<spring:message code="administrator.informationRatioCurriculaLabel" />
<jstl:out value="${informationRatioCurriculaLabel}"></jstl:out>
<table class="displaytag"  name="ratioRangerCurriculum">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${ratioRangerCurriculum}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.informationRatioEndorsedLabel" />
<jstl:out value="${informationRatioEndorsedLabel}"></jstl:out>
<table class="displaytag"  name="ratioRangerCurriculum">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${ratioRangerEndorser}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.informationRatioSuspiciousManagerLabel" />
<jstl:out value="${informationRatioSuspiciousManagerLabel}"></jstl:out>
<table class="displaytag"  name="ratioManagerSuspicious">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${ratioManagerSuspicious}"></jstl:out>
		</td>
	</tr>
</table>

<spring:message code="administrator.informationRatioSuspiciousRangerLabel" />
<jstl:out value="${informationRatioSuspiciousRangerLabel}"></jstl:out>
<table class="displaytag"  name="ratioSuspiciousRanger">
	<tr>
		<th>
			<spring:message code="administrator.ratio" var="ratioHeader" />
			<jstl:out value="${ratioHeader}"></jstl:out>
		</th>
	</tr>
	<tr>
		<td>
			<jstl:out value="${ratioSuspiciousRanger}"></jstl:out>
		</td>
	</tr>
</table>


<%--





<form:label path="informationRatioAuditLabel">
		<spring:message code="administrator.informationRatioAuditLabel" />:
</form:label>
<display:table pagesize="1" class="displaytag" keepStatus="true"
	name="informationRatioAudit" requestURI="administrator/list.do" id="row">
	
	<!-- Attributes -->
	<spring:message code="administrator.ratio" var="ratioHeader" />
	<display:column property="ratio" title="${ratioHeader}" sortable="true" />

</display:table>
 --%>

</security:authorize>







