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

<form:form action="endorserRecord/ranger/edit.do"
	modelAttribute="endorserRecord">
	<security:authorize access="hasRole('RANGER')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:label path="endorserName">
			<spring:message code="endorserRecord.endorserName" />:
	</form:label>
		<form:input path="endorserName" />
		<form:errors cssClass="error" path="endorserName" />
		<br />
		
		<form:label path="email">
			<spring:message code="endorserRecord.email" />:
	</form:label>
		<form:input path="email" />
		<form:errors cssClass="error" path="email" />
		<br />
		
		<form:label path="phoneNumber">
			<spring:message code="endorserRecord.phoneNumber" />:
	</form:label>
		<form:input path="phoneNumber" />
		<form:errors cssClass="error" path="phoneNumber" />
		<br />
		
		<form:label path="likedln">
			<spring:message code="endorserRecord.likedln" />:
	</form:label>
		<form:input path="likedln" />
		<form:errors cssClass="error" path="likedln" />
		<br />
		
		<form:label path="comment">
			<spring:message code="endorserRecord.comment" />:
	</form:label>
		<form:input path="comment" />
		<form:errors cssClass="error" path="comment" />
		<br />
		
		<input type="submit" name="save"
			value="<spring:message code="endorserRecord.save" />" />&nbsp; 
		<jstl:if test="${endorserRecord.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="endorserRecord.delete" />"
				onclick="javascript: return confirm('<spring:message code="endorserRecord.confirm.delete" />')" />&nbsp;
	</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="endorserRecord.cancel" />"
			onclick="javascript: relativeRedir('/');" />
	</security:authorize>
</form:form>