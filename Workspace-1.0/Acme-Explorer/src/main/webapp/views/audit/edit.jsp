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

<form:form action="audit/auditor/edit.do" modelAttribute="audit">
	
	<security:authorize access="hasRole('AUDITOR')">
	
	<form:hidden path="auditor" />
	<form:hidden path="draftMode" />
	<form:hidden path="moment" />
	
	<form:label path="title">
		<spring:message code="audit.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="description">
		<spring:message code="audit.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="attachment">
		<spring:message code="audit.attachment" />:
	</form:label>
	<form:input path="attachment" />
	<form:errors cssClass="error" path="attachment" />
	<br />
	
	<form:label path="draftMode">
		<spring:message code="audit.draftMode" />:
	</form:label>
	<form:select path="draftMode">
        <form:option value="YES"/>
        <form:option value="NO"/>
	</form:select>
	<form:input path="draftMode" />
	<form:errors cssClass="error" path="draftMode" />
	<br />
	
	<form:label path="trip">
		<spring:message code="audit.trip" />:
	</form:label>
	<form:select path="trip">
		<jstl:forEach var="trip" items=${row.trip }>
              <form:option value="${trip}">${trip.title}</form:option>
		</jstl:forEach>
	</form:select>
	<form:input path="trip" />
	<form:errors cssClass="error" path="trip" />
	<br />


	<input type="submit" name="save"
		value="<spring:message code="audit.save" />" />&nbsp; 
	<jstl:if test="${audit.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="audit.delete" />"
			onclick="return confirm('<spring:message code="audit.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="audit.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	

	</security:authorize>
</form:form>


