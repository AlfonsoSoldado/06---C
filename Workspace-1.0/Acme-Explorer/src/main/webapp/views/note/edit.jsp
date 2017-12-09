<%--
 * list.jsp
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

<form:form action="note/edit.do" modelAttribute="note">
	
<security:authorize access="hasRole('AUDITOR')">
	
	<form:hidden path="auditor" />
	<form:hidden path="moment" />
	<form:hidden path="reply" />
	<form:hidden path="momentReply" />
	<form:hidden path="trip" />
	
	<form:label path="remark">
		<spring:message code="note.remark" />:
	</form:label>
	<form:input path="remark" />
	<form:errors cssClass="error" path="remark" />
	<br />

</security:authorize>

<security:authorize access="hasRole('MANAGER')">
	
	<form:hidden path="auditor" />
	<form:hidden path="moment" />
	<form:hidden path="remark" />
	<form:hidden path="momentReply" />
	<form:hidden path="trip" />
	
	<form:label path="reply">
		<spring:message code="note.reply" />:
	</form:label>
	<form:input path="reply" />
	<form:errors cssClass="error" path="reply" />
	<br />

</security:authorize>
	
	<input type="submit" name="save"
		value="<spring:message code="note.save" />" />&nbsp; 
	<jstl:if test="${note.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="note.delete" />"
			onclick="return confirm('<spring:message code="note.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="note.cancel" />"
		onclick="javascript: relativeRedir('/');" />
	<br />
	




</form:form>


