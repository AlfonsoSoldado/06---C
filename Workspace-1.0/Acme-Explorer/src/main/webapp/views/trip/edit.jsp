<%--
 * trip.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="trip/manager/edit.do" modelAttribute="trip">

	<security:authorize access="hasRole('MANAGER')">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="ticker"/>
	<form:hidden path="publication" />
	<form:hidden path="reason" />
	<form:hidden path="cancelled" />
	
	<form:hidden path="ranger" />
	<form:hidden path="manager" />
	<form:hidden path="legalText" />
	<form:hidden path="application" />
	<form:hidden path="value" />
	
	
	<form:label path="title">
		<spring:message code="trip.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="description">
		<spring:message code="trip.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="price">
		<spring:message code="trip.price" />:
	</form:label>
	<form:input path="price" />
	<form:errors cssClass="error" path="price" />
	<br />
	
	<form:label path="requirement">
		<spring:message code="trip.requirement" />:
	</form:label>
	<form:input path="requirement" />
	<form:errors cssClass="error" path="requirement" />
	<br />
	
	<form:label path="tripStart">
		<spring:message code="trip.tripStart" />:
	</form:label>
	<form:input path="tripStart" />
	<form:errors cssClass="error" path="tripStart" />
	<br />
	
	<form:label path="tripEnd">
		<spring:message code="trip.tripEnd" />:
	</form:label>
	<form:input path="tripEnd" />
	<form:errors cssClass="error" path="tripEnd" />
	<br />
	
	<form:label path="stage">
		<spring:message code="trip.stage" />:
	</form:label>
	<form:select path="stage">
		<jstl:forEach var="stage" items=${row.stage }>
              <form:option value="${stage}"/>
		</jstl:forEach>
	</form:select>
	<form:input path="stage" />
	<form:errors cssClass="error" path="stage" />
	<br />
	
	<form:label path="category">
		<spring:message code="trip.category" />:
	</form:label>
	<form:select path="category">
		<jstl:forEach var="category" items=${row.category }>
              <form:option value="${category}"/>
		</jstl:forEach>
	</form:select>
	<form:input path="category" />
	<form:errors cssClass="error" path="category" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="trip.save" />" />&nbsp; 
	<jstl:if test="${trip.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="trip.delete" />"
			onclick="return confirm('<spring:message code="trip.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="trip.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	</security:authorize>
</form:form>