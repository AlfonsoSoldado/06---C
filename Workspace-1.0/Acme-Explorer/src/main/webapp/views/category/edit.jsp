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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="category/administrator/edit.do" modelAttribute="category">

	<security:authorize access="hasRole('ADMIN')">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="categories" />

		<form:label path="name">
			<spring:message code="category.name" />:
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />
		
		<form:label path="categoryParent">
			<spring:message code="category.categoryParent" />:
		</form:label>
		<form:select path="categoryParent">
			<form:options items="${categoryParent}" itemLabel="name" />
		</form:select>
		<form:errors cssClass="error" path="categoryParent" />
		<br />

		<form:label path="trip">
			<spring:message code="category.trip" />:
		</form:label>
		<form:select path="trip">
			<form:options items="${trip}" itemLabel="title" />
		</form:select>
		<form:errors cssClass="error" path="trip" />
		<br />

		<input type="submit" name="save"
			value="<spring:message code="category.save" />" />&nbsp; 
		<jstl:if test="${category.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="category.delete" />"
				onclick="return confirm('<spring:message code="category.confirm.delete" />')" />&nbsp;
		</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="category.cancel" />"
			onclick="javascript: relativeRedir('category/list.do');" />
		<br />
	</security:authorize>
</form:form>