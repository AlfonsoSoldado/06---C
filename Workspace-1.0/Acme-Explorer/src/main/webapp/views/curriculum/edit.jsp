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

<form:form action="curriculum/ranger/edit.do"
	modelAttribute="curriculum">
	<security:authorize access="hasRole('RANGER')">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:hidden path="ticker" />
		<form:hidden path="personalRecord" />
		<form:hidden path="endorserRecord" />
		<form:hidden path="miscellaneousRecord" />
		<form:hidden path="professionalRecord" />
		<form:hidden path="educationRecord" />
		
		<jstl:if test="${curriculum.id == 0}">
		<form:label path="personalRecord.name">
			<spring:message code="curriculum.personalRecord.name" />:
		</form:label>
		<form:input path="personalRecord.name" />
		<form:errors cssClass="error" path="personalRecord.name" />
		<br />
		
		<form:label path="personalRecord.photo">
			<spring:message code="curriculum.personalRecord.photo" />:
		</form:label>
		<form:input path="personalRecord.photo" />
		<form:errors cssClass="error" path="personalRecord.photo" />
		<br />
		
		<form:label path="personalRecord.email">
			<spring:message code="curriculum.personalRecord.email" />:
		</form:label>
		<form:input path="personalRecord.email" />
		<form:errors cssClass="error" path="personalRecord.email" />
		<br />
		
		<form:label path="personalRecord.phoneNumber">
			<spring:message code="curriculum.personalRecord.phoneNumber" />:
		</form:label>
		<form:input path="personalRecord.phoneNumber" />
		<form:errors cssClass="error" path="personalRecord.phoneNumber" />
		<br />
		
		<form:label path="personalRecord.likedln">
			<spring:message code="curriculum.personalRecord.linkedIn" />:
		</form:label>
		<form:input path="personalRecord.likedln" />
		<form:errors cssClass="error" path="personalRecord.likedln" />
		<br />

		<input type="submit" name="save"
			value="<spring:message code="curriculum.save" />" />&nbsp; 
		</jstl:if>
		<jstl:if test="${curriculum.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="curriculum.delete" />"
				onclick="javascript: return confirm('<spring:message code="curriculum.confirm.delete" />')" />&nbsp;
		</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="curriculum.cancel" />"
			onclick="javascript: relativeRedir('/');" />
	</security:authorize>
</form:form>