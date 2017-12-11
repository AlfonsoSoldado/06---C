<%--
 * edit.jsp
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

<form:form action="story/edit.do" modelAttribute="story">
	
<security:authorize access="hasRole('EXPLORER')">
	
	<form:hidden path="explorer" />
	
	<form:label path="title">
		<spring:message code="story.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="pieceText">
		<spring:message code="story.pieceText" />:
	</form:label>
	<form:input path="pieceText" />
	<form:errors cssClass="error" path="pieceText" />
	<br />
	
	<form:label path="link">
		<spring:message code="story.link" />:
	</form:label>
	<form:input path="link" />
	<form:errors cssClass="error" path="link" />
	<br />
	
	<form:label path="trip">
		<spring:message code="story.trip" />:
	</form:label>
	<form:select path="trip">
        <form:options items="${trip}" itemLabel="title"/>
	</form:select>
	<form:input path="trip" />
	<form:errors cssClass="error" path="trip" />
	<br />
	


	
	<input type="submit" name="create"
		value="<spring:message code="story.create" />" />&nbsp; 
	<jstl:if test="${note.id != 0}">
		<input type="submit" name="create"
			value="<spring:message code="story.create" />"
			onclick="return confirm('<spring:message code="story.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="story.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	

</security:authorize>


</form:form>
