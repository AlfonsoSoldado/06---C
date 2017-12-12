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
<security:authorize access="hasRole('AUDITOR')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="auditsAuditor" requestURI="audit/auditor/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="audit.edit"/>
	<display:column>
		<a href= "audit/auditor/edit.do?auditId=${row.id}">
		<spring:message code="audit.edit"/></a>
	</display:column>
	
	<spring:message code="audit.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />
	
	<spring:message code="audit.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="audit.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="audit.attachment" var="attachmentHeader" />
	<display:column property="attachment" title="${attachmentHeader}" sortable="false" />
	
	<spring:message code="audit.draftMode" var="draftModeHeader" />
	<display:column property="draftMode" title="${draftModeHeader}" sortable="true" />
	
	<spring:message code="audit.trip" var="tripHeader" />
	<display:column property="trip.title" title="${tripHeader}" sortable="true" />
	
	<spring:message code="audit.auditor" var="auditorHeader" />
	<display:column property="auditor.name" title="${auditorHeader}" sortable="true" />
	
</display:table>

	<div>
		<a href="audit/auditor/create.do">
			<button>
				<spring:message code="audit.create" />
			</button>
		</a>
	</div>
</security:authorize>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="audits" requestURI="audit/list.do" id="row">
	
	<spring:message code="audit.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />
	
	<spring:message code="audit.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="audit.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="audit.attachment" var="attachmentHeader" />
	<display:column property="attachment" title="${attachmentHeader}" sortable="false" />
	
	<spring:message code="audit.draftMode" var="draftModeHeader" />
	<display:column property="draftMode" title="${draftModeHeader}" sortable="true" />
	
	<spring:message code="audit.trip" var="tripHeader" />
	<display:column property="trip" title="${tripHeader}" sortable="true" />
	
	<spring:message code="audit.auditor" var="auditorHeader" />
	<display:column property="auditor" title="${auditorHeader}" sortable="true" />
	
</display:table>