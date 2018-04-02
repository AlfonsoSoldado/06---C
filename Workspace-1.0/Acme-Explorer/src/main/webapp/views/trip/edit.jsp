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
	<form:hidden path="price" />
	
	<form:hidden path="manager" />
	<form:hidden path="application" />
	
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
	
	<form:label path="requirement">
		<spring:message code="trip.requirement" />:
	</form:label>
	<form:input path="requirement" />
	<form:errors cssClass="error" path="requirement" />
	<br />
	
	<form:label path="tripStart">
		<spring:message code="trip.tripStart" />:
	</form:label>
	<form:input path="tripStart"  placeholder="dd/MM/yyyy" />
	<form:errors cssClass="error" path="tripStart" />
	<br />
	
	<form:label path="tripEnd">
		<spring:message code="trip.tripEnd" />:
	</form:label>
	<form:input path="tripEnd" placeholder="dd/MM/yyyy" />
	<form:errors cssClass="error" path="tripEnd" />
	<br />
	
	<form:label path="category">
		<spring:message code="trip.category" />:
	</form:label>
	<form:select path="category">
		<form:options items="${category}" itemLabel="name"/>
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
	
	<form:label path="ranger">
		<spring:message code="trip.ranger" />:
	</form:label>
	<form:select path="ranger">
		<form:option item="null" value="0" label="----"/>
        <form:options items="${ranger}" itemLabel="name"/>
	</form:select>
	<form:errors cssClass="error" path="ranger" />
	<br />

	<form:label path="stage">
		<spring:message code="trip.stage"/>:
	</form:label>
	<form:select path="stage">
		<form:options items="${stage}" itemLabel="title"/>
	</form:select>
	<form:errors cssClass="error" path="stage" />
	<br />
	
	<form:label path="legalText">
		<spring:message code="trip.legalText"/>:
	</form:label>
	<form:select path="legalText">
		<form:options items="${legalText}" itemLabel="title"/>
	</form:select>
	<form:errors cssClass="error" path="legalText" />
	<br />
	
	<form:label path="value">
		<spring:message code="trip.value"/>:
	</form:label>
	<select name="factory">
    <jstl:forEach var="value" items="${values}" >
        <option value="${value}" label="${value.tag.name}"/>
    </jstl:forEach>
	</select>
	<form:errors cssClass="error" path="value" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="trip.save" />" />&nbsp; 
	<jstl:if test="${trip.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="trip.delete" />"
			onclick="javascript: return confirm('<spring:message code="trip.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="trip.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	</security:authorize>
</form:form>