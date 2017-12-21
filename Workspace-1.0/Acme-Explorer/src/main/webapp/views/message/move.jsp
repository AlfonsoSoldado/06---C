<%--
 * move.jsp
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

<form:form action="message/move.do" modelAttribute="msg">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender" />

	<form:hidden path="moment" />
	<form:hidden path="spam" />
	<form:hidden path="priority" />
	<form:hidden path="subject" />
	<form:hidden path="body" />

	<form:label path="folder">
		<spring:message code="message.folder" />:
	</form:label>
	<form:select path="folder">
        <form:options items="${folder}" itemLabel="name"/>
	</form:select>
	<form:errors cssClass="error" path="folder" />
	<br />
	
	<form:label path="recipient">
		<spring:message code="message.recipient" />:
	</form:label>
	<form:select path="recipient">
        <form:options items="${actor}" itemLabel="userAccount.username"/>
	</form:select>
	<form:errors cssClass="error" path="recipient" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 
	<jstl:if test="${msg.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="message.delete" />"
			onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
</form:form>