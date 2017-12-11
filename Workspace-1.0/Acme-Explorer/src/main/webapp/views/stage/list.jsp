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
	name="stage" requestURI="stage/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="stage.delete"/>
	<display:column>
		<a href= "stage/delete.do?stageId=${row.id}">
		<spring:message code="stage.delete"/></a>
	</display:column>
	
	<spring:message code="stage.edit"/>
	<display:column>
		<a href= "stage/edit.do?stageId=${row.id}">
		<spring:message code="stage.edit"/></a>
	</display:column>
	
	<spring:message code="stage.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="stage.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="true" />
	
	<spring:message code="stage.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" sortable="true" />
	
	<spring:message code="stage.trip" var="tripHeader" />
	<display:column property="trip" title="${tripHeader}" sortable="true" />
	
</display:table>

	<div>
		<button>
			<a href="stage/create.do"> <spring:message
					code="stage.create" />
			</a>
		</button>
	</div>
