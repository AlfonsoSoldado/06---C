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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- displaying grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="curriculum" requestURI="curriculum/display.do" id="row">

	<!-- Attributes -->

	<spring:message code="curriculum.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}"
		sortable="true" />

	<security:authorize access="hasRole('RANGER')">
		<spring:message code="curriculum.create" />
		<display:column>
			<a href="curriculum/ranger/create.do?curriculumId=${row.id}"> <spring:message
					code="curriculum.create" /></a>
		</display:column>
	</security:authorize>

</display:table>

<spring:message code="curriculum.personalRecord" var="personalRecordHeader" />
<h1><jstl:out value="${personalRecordHeader}"></jstl:out></h1>
<display:table name="personalRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />

</display:table>

<spring:message code="curriculum.educationRecord" var="educationRecordHeader" />
<h1><jstl:out value="${educationRecordHeader}"></jstl:out></h1>
<display:table name="educationRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />

</display:table>

<spring:message code="curriculum.professionalRecord" var="professionalRecordHeader" />
<h1><jstl:out value="${professionalRecordHeader}"></jstl:out></h1>
<display:table name="professionalRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />

</display:table>

<spring:message code="curriculum.miscellaneousRecord" var="miscellaneousRecordHeader" />
<h1><jstl:out value="${miscellaneousRecordHeader}"></jstl:out></h1>
<display:table name="miscellaneousRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />

</display:table>

<spring:message code="curriculum.endorserRecord" var="endorserRecordHeader" />
<h1><jstl:out value="${endorserRecordHeader}"></jstl:out></h1>
<display:table name="endorserRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />

</display:table>