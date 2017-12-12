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
	name="emergency" requestURI="emergency/explorer/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="emergency.edit"/>
	<display:column>
		<a href= "emergency/explorer/edit.do?emergencyId=${row.id}">
		<spring:message code="emergency.edit"/></a>
	</display:column>
	
	<spring:message code="emergency.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="emergency.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" sortable="true" />
	
	<spring:message code="emergency.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}" sortable="false" />
	
</display:table>

	<div>
		<a href="emergency/explorer/create.do">
			<button>
				<spring:message code="emergency.create" />
			</button>
		</a>
	</div>
</security:authorize>
