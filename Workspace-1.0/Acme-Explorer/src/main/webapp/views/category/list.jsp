<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<jstl:if test="!category.getCategories().isEmpty()">

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="categories" requestURI="category/list.do" id="row">
	
	<!-- Attributes -->
	
	<spring:message code="category.categories" var ="categoriesHeader"/>
	
	<display:column>
		<jstl:forEach var="category" items="${category.getCategories()}">
		<a href="trip/list.do?categoryId=*">
		<jstl:out value="${category.getName()}"/>
		</a>
		</jstl:forEach>
	</display:column>
	
</display:table>
</jstl:if>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="category/administrator/edit.do"> <spring:message
				code="category.create" />
		</a>
	</div>
</security:authorize>