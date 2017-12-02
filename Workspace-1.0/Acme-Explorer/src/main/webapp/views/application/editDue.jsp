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
	<security:authorize access="hasRole('EXPLORER')">
		<form:hidden path="moment" />
		<form:hidden path="status" />
		<form:hidden path="reason" />
		<form:hidden path="comment" />
		<form:hidden path="manager" />
		<form:hidden path="explorer" />
		<form:hidden path="trip" />
 		
 		<form:label path="holderName">
			<spring:message code="application.holderName" />:
		</form:label>
		<form:input path="holderName" />
		<form:errors cssClass="error" path="holderName" />
		<br />
		
		<form:label path="brandName">
			<spring:message code="application.brandName" />:
		</form:label>
		<form:input path="brandName" />
		<form:errors cssClass="error" path="brandName" />
		<br />
		
		<form:label path="cardNumber">
			<spring:message code="application.cardNumber" />:
		</form:label>
		<form:input path="cardNumber" />
		<form:errors cssClass="error" path="cardNumber" />
		<br />
		
		<form:label path="expirationMonth">
			<spring:message code="application.expirationMonth" />:
		</form:label>
		<form:input path="expirationMonth" />
		<form:errors cssClass="error" path="expirationMonth" />
		<br />
		
		<form:label path="expirationYear">
			<spring:message code="application.expirationYear" />:
		</form:label>
		<form:input path="expirationYear" />
		<form:errors cssClass="error" path="expirationYear" />
		<br />
		
		<form:label path="CVV">
			<spring:message code="application.CVV" />:
		</form:label>
		<form:input path="CVV" />
		<form:errors cssClass="error" path="CVV" />
		<br />
		
		<input type="submit" name="save"
			value="<spring:message code="application.save" />" />&nbsp; 
		<input type="button" name="cancel"
			value="<spring:message code="application.cancel" />"
			onclick="javascript: relativeRedir('/application/explorer/list.do');" />
		<br />
	
	</security:authorize>
</form:form>