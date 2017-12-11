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
	name="storyExplorer" requestURI="story/explorer/list.do" id="row">
	
	<!-- Attributes -->
	
	
	<spring:message code="story.edit"/>
	<display:column>
		<a href= "story/explorer/edit.do?storyId=${row.id}">
		<spring:message code="story.edit"/></a>
	</display:column>
	
	<spring:message code="story.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="story.pieceText" var="pieceTextHeader" />
	<display:column property="pieceText" title="${pieceTextHeader}" sortable="true" />
	
	<spring:message code="story.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" sortable="false" />
	
	<spring:message code="story.trip" var="tripHeader" />
	<display:column property="trip.title" title="${tripHeader}" sortable="true" />
	
	
	<div>
		<a href="story/explorer/edit.do"> <spring:message
				code="audit.create" />
		</a>
	</div>
	
</display:table>
</security:authorize>
