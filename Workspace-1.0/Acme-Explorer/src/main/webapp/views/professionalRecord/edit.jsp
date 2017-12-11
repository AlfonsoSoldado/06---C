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

<form:form action="professionalRecord/ranger/edit.do"
	modelAttribute="professionalRecord">
	<security:authorize access="hasRole('RANGER')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:label path="companyName">
			<spring:message code="professionalRecord.companyName" />:
	</form:label>
		<form:input path="companyName" />
		<form:errors cssClass="error" path="companyName" />
		<br />
		
		<form:label path="start">
			<spring:message code="professionalRecord.start" />:
	</form:label>
		<form:input path="start" />
		<form:errors cssClass="error" path="start" />
		<br />
		
		<form:label path="end">
			<spring:message code="professionalRecord.end" />:
	</form:label>
		<form:input path="end" />
		<form:errors cssClass="error" path="end" />
		<br />
		
		<form:label path="rol">
			<spring:message code="professionalRecord.rol" />:
	</form:label>
		<form:input path="rol" />
		<form:errors cssClass="error" path="rol" />
		<br />
		
		<form:label path="link">
			<spring:message code="professionalRecord.link" />:
	</form:label>
		<form:input path="link" />
		<form:errors cssClass="error" path="link" />
		<br />
		
		<form:label path="comment">
			<spring:message code="professionalRecord.comment" />:
	</form:label>
		<form:input path="comment" />
		<form:errors cssClass="error" path="comment" />
		<br />
		
		<input type="submit" name="save"
			value="<spring:message code="professionalRecord.save" />" />&nbsp; 
		<jstl:if test="${professionalRecord.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="professionalRecord.delete" />"
				onclick="javascript: return confirm('<spring:message code="professionalRecord.confirm.delete" />')" />&nbsp;
	</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="professionalRecord.cancel" />"
			onclick="javascript: relativeRedir('/');" />
	</security:authorize>
</form:form>