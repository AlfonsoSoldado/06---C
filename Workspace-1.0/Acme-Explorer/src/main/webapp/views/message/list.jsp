<%--
 * list.jsp
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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="messages" requestURI="${requestURI }" id="row">

	<!-- Attributes -->
	
	<spring:message code="message.edit"/>
	<display:column>
		<a href= "message/edit.do?messageId=${row.id}">
		<spring:message code="message.edit"/></a>
	</display:column>
	
	<spring:message code="message.move"/>
	<display:column>
		<a href= "message/move.do?messageId=${row.id}">
		<spring:message code="message.move"/></a>
	</display:column>
	
	<spring:message code="message.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />

	<spring:message code="message.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}" />
	
	<spring:message code="message.priority" var="priorityHeader"/>
	<display:column property="priority" title="${priorityHeader}" sortable="false"/>

	<spring:message code="message.body" var="bodyHeader"/>
	<display:column property="body" title="${bodyHeader}" sortable="false"/>
	
	<spring:message code="message.sender" var="senderHeader"/>
	<display:column property="sender.email" title="${senderHeader}" sortable="false"/>
	
	<spring:message code="message.sender" var="senderUserHeader"/>
	<display:column property="sender.userAccount.username" title="${senderUserHeader}" sortable="false"/>
	
	<jstl:if test="${Folder.name == \"Out Box\" && Folder.systemFolder == true}">
		<spring:message code="message.recipient" var="recipientHeader"/>
		<display:column property="recipient.email" title="${recipientHeader}" sortable="false"/>
	</jstl:if>

</display:table>

<a href="message/create.do">
	<button>
		<spring:message code="message.create" />
	</button>
</a>