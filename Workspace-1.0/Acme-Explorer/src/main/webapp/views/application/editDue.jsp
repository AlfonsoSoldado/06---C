<%--
 * editDue.jsp
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

<form:form action="application/explorer/editDue.do" modelAttribute="application">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
	
	<security:authorize access="hasRole('EXPLORER')">
		<form:hidden path="moment" />
		<form:hidden path="status" />
		<form:hidden path="reason" />
		<form:hidden path="comment" />
		<form:hidden path="manager" />
		<form:hidden path="explorer" />
		<form:hidden path="trip" />
 		
 		<form:label path="creditCard.holderName">
		<spring:message code="sponsorship.creditCard.holderName" />:
	</form:label>
	<form:input path="creditCard.holderName" />
	<form:errors cssClass="error" path="creditCard.holderName" />
	<br />
	
	<form:label path="creditCard.brandName">
		<spring:message code="sponsorship.creditCard.brandName" />:
	</form:label>
	<form:input path="creditCard.brandName" />
	<form:errors cssClass="error" path="creditCard.brandName" />
	<br />
	
	<form:label path="creditCard.number">
		<spring:message code="sponsorship.creditCard.number" />:
	</form:label>
	<form:input path="creditCard.number" />
	<form:errors cssClass="error" path="creditCard.number" />
	<br />
	
	<form:label path="creditCard.expirationMonth">
	<spring:message code="sponsorship.creditCard.expirationMonth" />:
		</form:label>
	<form:input path="creditCard.expirationMonth" />
	<form:errors cssClass="error" path="creditCard.expirationMonth" />
	<br />
	
	<form:label path="creditCard.expirationYear">
	<spring:message code="sponsorship.creditCard.expirationYear" />:
		</form:label>
	<form:input path="creditCard.expirationYear" />
	<form:errors cssClass="error" path="creditCard.expirationYear" />
	<br />
	
	<form:label path="creditCard.CVV">
	<spring:message code="sponsorship.creditCard.CVV" />:
		</form:label>
	<form:input path="creditCard.CVV" />
	<form:errors cssClass="error" path="creditCard.CVV" />
	<br />
		
		<input type="submit" name="save"
			value="<spring:message code="application.save" />" />&nbsp; 
		<input type="button" name="cancel"
			value="<spring:message code="application.cancel" />"
			onclick="javascript: relativeRedir('/application/explorer/list.do');" />
		<br />
	
	</security:authorize>
</form:form>