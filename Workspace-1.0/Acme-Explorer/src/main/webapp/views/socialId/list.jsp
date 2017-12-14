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

<display:table pagesize="5" class="displaysocialId" keepStatus="true"
	name="socialId" requestURI="socialId/list.do" id="row">
	
	<spring:message code="socialId.edit"/>
	<display:column>
		<a href= "socialId/edit.do?socialIdId=${row.id}">
		<spring:message code="socialId.edit"/></a>
	</display:column>
	
	<spring:message code="socialId.nameSocialNetwork" var="nameSocialNetworkHeader" />
	<display:column property="nameSocialNetwork" title="${nameSocialNetworkHeader}" sortable="true" />

	<spring:message code="socialId.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true" />

	<spring:message code="socialId.socialNetwork" var="socialNetworkHeader" />
	<display:column property="socialNetwork" title="${socialNetworkHeader}" sortable="false" />

	<spring:message code="socialId.photo" var="photoHeader" />
	<display:column property="photo" title="${photoHeader}"	sortable="false" />
	
</display:table>