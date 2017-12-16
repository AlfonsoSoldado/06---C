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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="socialId/edit.do" modelAttribute="socialId">

		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:hidden path="actor" />
		
		<form:label path="photo">
			<spring:message code="socialId.photo" />:
		</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />
		<br />
		
		<form:label path="nick">
			<spring:message code="socialId.nick" />:
		</form:label>
		<form:input path="nick" />
		<form:errors cssClass="error" path="nick" />
		<br />
		
		<form:label path="nameSocialNetwork">
			<spring:message code="socialId.nameSocialNetwork" />:
		</form:label>
		<form:input path="nameSocialNetwork" />
		<form:errors cssClass="error" path="nameSocialNetwork" />
		<br />
		
		<form:label path="socialNetwork">
			<spring:message code="socialId.socialNetwork" />:
		</form:label>
		<form:input path="socialNetwork" />
		<form:errors cssClass="error" path="socialNetwork" />
		<br />

		<input type="submit" name="save"
			value="<spring:message code="socialId.save" />" />&nbsp; 
		<jstl:if test="${socialId.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="socialId.delete" />"
				onclick="javascript: return confirm('<spring:message code="socialId.confirm.delete" />')" />&nbsp;
	</jstl:if>
		<input type="button" name="cancel"
			value="<spring:message code="socialId.cancel" />"
			onclick="javascript: relativeRedir('/');" />
</form:form>