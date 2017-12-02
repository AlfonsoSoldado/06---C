<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Explorer Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/action-1.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message	code="master.page.manager" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message code="master.page.manager.create.trip" /></a></li>
					<li><a href=""><spring:message code="master.page.manager.notes" /></a></li>
					<li><a href=""><spring:message code="master.page.manager.applications" /></a></li>
					<li><a href=""><spring:message code="master.page.manager.survivals" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('EXPLORER')">
			<li><a class="fNiv"><spring:message	code="master.page.explorer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message code="master.page.explorer.create.application" /></a></li>				
					<li><a href=""><spring:message code="master.page.explorer.applications" /></a></li>
					<li><a href=""><spring:message code="master.page.explorer.survivals" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message	code="master.page.auditor" /></a>
				<ul>
					<li class="arrow"></li>
						<li><a href=""><spring:message code="master.page.auditor.audits" /></a></li>
						<li><a href=""><spring:message code="master.page.auditor.notes" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
						<li><a href=""><spring:message code="master.page.sponsor.sponsorships" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.register" /></a>
				<ul>
					<li><a href=""><spring:message code="master.page.register.ranger" /></a>
					<li><a href=""><spring:message code="master.page.register.explorer" /></a>
				</ul>
			</li>
			<li><a class="fNiv" href=""><spring:message code="master.page.trips" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.categories" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.finder" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
			</li>
			<li><a class="fNiv" href=""><spring:message code="master.page.logout" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.trips" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.categories" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.finder" /></a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

