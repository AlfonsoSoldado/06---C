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
		<spring:message code="curriculum.delete" />
		<display:column>
			<a href="curriculum/ranger/delete.do?curriculumId=${row.id}"> <spring:message
					code="curriculum.delete" /></a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('RANGER')">
<a href="curriculum/ranger/create.do">
	<button>
		<spring:message code="curriculum.create" />
	</button>
</a>
</security:authorize>

<spring:message code="curriculum.personalRecord" var="personalRecordHeader" />
<h1><jstl:out value="${personalRecordHeader}"></jstl:out></h1>
<display:table name="personalRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.name" var="titleHeader" />
	<display:column property="name" title="${titleHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.personalRecord.photo" var="photoHeader" />
	<display:column property="photo" title="${photoHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.personalRecord.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}"
		sortable="true" />

	<spring:message code="curriculum.personalRecord.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.personalRecord.linkedIn" var="linkedlnHeader" />
	<display:column property="likedln" title="${linkedlnHeader}"
		sortable="true" />

</display:table>

<security:authorize access="hasRole('RANGER')">
<a href="personalRecord/ranger/create.do">
	<button>
		<spring:message code="curriculum.create.personalRecord" />
	</button>
</a>
</security:authorize>

<spring:message code="curriculum.educationRecord" var="educationRecordHeader" />
<h1><jstl:out value="${educationRecordHeader}"></jstl:out></h1>
<display:table name="educationRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.educationRecord.start" var="startHeader" />
	<display:column property="start" title="${startHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.educationRecord.end" var="endHeader" />
	<display:column property="end" title="${endHeader}"
		sortable="true" />

	<spring:message code="curriculum.educationRecord.institution" var="institutionHeader" />
	<display:column property="institution" title="${institutionHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.educationRecord.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.educationRecord.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}"
		sortable="true" />

</display:table>

<security:authorize access="hasRole('RANGER')">
<a href="educationRecord/ranger/create.do">
	<button>
		<spring:message code="curriculum.create.educationRecord" />
	</button>
</a>
</security:authorize>

<spring:message code="curriculum.professionalRecord" var="professionalRecordHeader" />
<h1><jstl:out value="${professionalRecordHeader}"></jstl:out></h1>
<display:table name="professionalRecord" class="displaytag" id="row">

	<spring:message code="curriculum.professionalRecord.campanyName" var="companyNameHeader" />
	<display:column property="companyName" title="${companyNameHeader}"
		sortable="true" />

	<spring:message code="curriculum.professionalRecord.start" var="startHeader" />
	<display:column property="start" title="${startHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.professionalRecord.end" var="endHeader" />
	<display:column property="end" title="${endHeader}"
		sortable="true" />

	<spring:message code="curriculum.professionalRecord.rol" var="rolHeader" />
	<display:column property="rol" title="${rolHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.professionalRecord.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.professionalRecord.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}"
		sortable="true" />
	
</display:table>

<security:authorize access="hasRole('RANGER')">
<a href="professionalRecord/ranger/create.do">
	<button>
		<spring:message code="curriculum.create.professionalRecord" />
	</button>
</a>
</security:authorize>

<spring:message code="curriculum.miscellaneousRecord" var="miscellaneousRecordHeader" />
<h1><jstl:out value="${miscellaneousRecordHeader}"></jstl:out></h1>
<display:table name="miscellaneousRecord" class="displaytag" id="row">

	<spring:message code="curriculum.records.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.miscellaneousRecord.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.miscellaneousRecord.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}"
		sortable="true" />

</display:table>

<security:authorize access="hasRole('RANGER')">
<a href="miscellaneousRecord/ranger/create.do">
	<button>
		<spring:message code="curriculum.create.miscellaneousRecord" />
	</button>
</a>
</security:authorize>

<spring:message code="curriculum.endorserRecord" var="endorserRecordHeader" />
<h1><jstl:out value="${endorserRecordHeader}"></jstl:out></h1>
<display:table name="endorserRecord" class="displaytag" id="row">

	<spring:message code="curriculum.endorserRecord.endorserName" var="endorserNameHeader" />
	<display:column property="endorserName" title="${endorserNameHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.endorserRecord.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}"
		sortable="true" />

	<spring:message code="curriculum.endorserRecord.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.endorserRecord.linkedIn" var="linkedlnHeader" />
	<display:column property="likedln" title="${linkedlnHeader}"
		sortable="true" />
		
	<spring:message code="curriculum.endorserRecord.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}"
		sortable="true" />
</display:table>

<security:authorize access="hasRole('RANGER')">
<a href="endorserRecord/ranger/create.do">
	<button>
		<spring:message code="curriculum.create.endorserRecord" />
	</button>
</a>
</security:authorize>