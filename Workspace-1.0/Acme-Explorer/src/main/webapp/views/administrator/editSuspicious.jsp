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

<form:form action="administrator/editSuspicious.do" modelAttribute="actor">
	
	<security:authorize access="hasRole('ADMIN')">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="folders" />
	<form:hidden path="received" />
	<form:hidden path="sent" />
	<form:hidden path="socialId" />
	<form:hidden path="userAccount" />
	
	
	<form:label path="suspicious">
		<spring:message code="administrator.suspicious" />:
	</form:label>
	<form:select path="suspicious">
        <form:option value="true" label="Yes"/>
        <form:option value="false" label="No"/>
	</form:select>
	<form:errors cssClass="error" path="suspicious" />
	<br />
	
	
	<input type="submit" name="save"
		value="<spring:message code="administrator.save" />" />&nbsp; 
	
	
	<input type="button" name="cancel"
		value="<spring:message code="administrator.cancel" />"
		onclick="javascript: relativeRedir('/administrator/suspicious.do');" />
	<br />


	</security:authorize>
</form:form>