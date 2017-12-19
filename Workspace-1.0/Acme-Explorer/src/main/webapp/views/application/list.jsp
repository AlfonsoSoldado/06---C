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

<security:authorize access="hasRole('EXPLORER')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="applicationExplorer" requestURI="application/explorer/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="application.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" sortable="true" class="${row.status }"/>
	
	<spring:message code="application.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="false" />

	<spring:message code="application.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}" sortable="false" />

	<spring:message code="application.reason" var="reasonHeader" />
	<display:column property="reason" title="${reasonHeader}" sortable="false" />
	
	<spring:message code="application.creditCard" var="creditCardHeader" />
	<display:column>
	<jstl:forEach var="s" items="${row.status}">
		<jstl:choose>
			<jstl:when test="${s == 'DUE' }">
				<a href= "application/explorer/editDue.do?applicationId=${row.id}">
					<spring:message code="application.creditCard" /></a>
			</jstl:when>
			<jstl:otherwise>
				${row.creditCard.number}
			</jstl:otherwise>
		</jstl:choose>
	</jstl:forEach>
	</display:column>
	
	<spring:message code="application.trip" var="tripHeader" />
	<display:column property="trip.title" title="${tripHeader}"	sortable="false" />
	
	<spring:message code="application.cancel" var="cancelHeader" />
	<display:column>
		<a href= "application/explorer/editAccepted.do?applicationId=${row.id}">
					<spring:message code="application.cancel" /></a>
	</display:column>
	
</display:table>

	<div>
		<a href="application/explorer/create.do">
			<button>
				<spring:message code="audit.create" />
			</button>
		</a>
	</div>
</security:authorize>

<security:authorize access="hasRole('MANAGER')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="applicationManager" requestURI="application/manager/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="application.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />

	<spring:message code="application.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" sortable="true" class="${row.status }"/>

	<spring:message code="application.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}" sortable="true" />

	<spring:message code="application.reason" var="reasonHeader" />
	<display:column property="reason" title="${reasonHeader}" sortable="false" />

	<spring:message code="application.creditCard" var="creditCardHeader" />
	<display:column>
	<jstl:forEach var="s" items="${row.status}">
		<jstl:choose>
			<jstl:when test="${s == 'ACCEPTED' }">
				${row.creditCard.number}
			</jstl:when>
			<jstl:otherwise>
				<spring:message code="application.notApply" />
			</jstl:otherwise>
		</jstl:choose>
	</jstl:forEach>
	</display:column>
	
	<spring:message code="application.trip" var="tripHeader" />
	<display:column property="trip.title" title="${tripHeader}"	sortable="true" />
	
	<spring:message code="application.change" var="changeHeader" />
	<display:column>
	<jstl:forEach var="s" items="${row.status}">
		<jstl:choose>
				<jstl:when test="${s == 'PENDING'}">
					<a href= "application/manager/editPending.do?applicationId=${row.id}">
					<spring:message code="application.change" /></a>
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="application.notApply" />
				</jstl:otherwise>
		</jstl:choose>
	</jstl:forEach>
	</display:column>
	
</display:table>
</security:authorize>