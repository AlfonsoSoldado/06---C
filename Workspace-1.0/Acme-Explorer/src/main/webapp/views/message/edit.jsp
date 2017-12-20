<%--
 * edit.jsp
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

<form:form action="${requestURI }" modelAttribute="msg">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="moment"/>
	<form:hidden path="folder"/>
	<form:hidden path="spam"/>
	
	<form:label path="sender">
		<spring:message code="message.sender"/>
	</form:label>
	<form:input path="sender.email"/>
	<form:errors cssClass="error" path="sender"/>
	<br/>
	
	<form:label path="priority">
		<spring:message code="message.priority"/>
	</form:label>
	<form:select path="priority">
		<form:option value="LOW"/>
        <form:option value="NEUTRAL"/>
        <form:option value="HIGH"/>
	</form:select>
	<form:errors cssClass="error" path="priority"/>
	<br/>
	
	<form:label path="subject">
		<spring:message code="message.subject"/>
	</form:label>
	<form:input path="subject"/>
	<form:errors cssClass="error" path="subject"/>
	<br/>
	
	<form:label path="body">
		<spring:message code="message.body"/>
	</form:label>
	<form:textarea path="body"/>
	<form:errors cssClass="error" path="body"/>
	<br/>
	
	<form:label path="recipient">
		<spring:message code="message.recipient" />:
	</form:label>
	
	<form:select path="recipient">
		<jstl:forEach var="datos" items="${actor}">
			<form:option value="${datos.id}">
				<jstl:out value="${datos.name}"/>
			</form:option>
		</jstl:forEach>
	</form:select>
	<form:errors cssClass="error" path="recipient" />
	<br />
	<%--
	<form:select path="actor">
        <form:options items="${actor}" itemLabel="email"/>
	</form:select>
	<form:input path="recipient" />
	<form:errors cssClass="error" path="recipient" />
	<br />
	 --%>
	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 
	<jstl:if test="${message.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="message.delete" />"
			onclick="return confirm('<spring:message code="message.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
</form:form>