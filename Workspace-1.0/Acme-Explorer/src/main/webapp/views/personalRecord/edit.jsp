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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="personalRecord/ranger/edit.do"
	modelAttribute="personalRecord">
	<security:authorize access="hasRole('RANGER')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:label path="name">
			<spring:message code="personalRecord.name" />:
	</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />
		<br />

		<form:label path="photo">
			<spring:message code="personalRecord.photo" />:
	</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />
		<br />

		<form:label path="email">
			<spring:message code="personalRecord.email" />:
	</form:label>
		<form:input path="email" />
		<form:errors cssClass="error" path="email" />
		<br />

		<form:label path="phoneNumber">
			<spring:message code="personalRecord.phoneNumber" />:
	</form:label>
		<form:input path="phoneNumber" />
		<form:errors cssClass="error" path="phoneNumber" />
		<br />

		<form:label path="likedln">
			<spring:message code="personalRecord.likedln" />:
	</form:label>
		<form:input path="likedln" />
		<form:errors cssClass="error" path="likedln" />
		<br />



		<input type="submit" name="save"
			value="<spring:message code="personalRecord.save" />" />&nbsp; 
		<jstl:if test="${personalRecord.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="personalRecord.delete" />"
				onclick="javascript: return confirm('<spring:message code="personalRecord.confirm.delete" />')" />&nbsp;
	</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="personalRecord.cancel" />"
			onclick="javascript: relativeRedir('/');" />
	</security:authorize>
</form:form>