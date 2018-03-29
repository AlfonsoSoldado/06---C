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
	name="folders" requestURI="folder/list.do" id="row">
	
	<spring:message code="folder.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="folder.folders"
		var="foldersHeader" />
	<display:column title="${foldersHeader}">
		<a href="folder/list.do?folderId=${row.id}">
			<button>
				<spring:message code="folder.foldersLink" />
			</button>
		</a>
	</display:column>

	<spring:message code="folder.messages" var="messagesHeader" />
	<display:column title="${messagesHeader}">
		<a href="message/list.do?folderId=${row.id}">
			<button>
				<spring:message code="folder.messagesLink" />
			</button>
		</a>
	</display:column>
	<display:column>
		<jstl:if test="${row.systemFolder == false}">
			<a href="folder/edit.do?folderId=${row.id}">
				<button>
					<spring:message code="folder.edit" />
				</button>
			</a>
		</jstl:if>
	</display:column>

</display:table>

<a href="folder/create.do">
	<button>
		<spring:message code="folder.create" />
	</button>
</a>

<a href="message/create.do">
	<button>
		<spring:message code="folder.create.message" />
	</button>
</a>


<security:authorize access="hasRole('ADMIN')">
	<a href="message/administrator/create.do">
	<button>
		<spring:message code="folder.create.notification" />
	</button>
</a>
	</security:authorize>