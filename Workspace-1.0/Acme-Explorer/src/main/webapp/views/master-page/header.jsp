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
	<a href="/Acme-Explorer"><img src="images/banner.png" alt="Acme-Explorer Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/administrator/edit.do"><spring:message code="master.page.actorEdit" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.actorFolder" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="tag/administrator/list.do"><spring:message code="master.page.tag" /></a></li>
			<li><a class="fNiv" href="legalText/administrator/list.do"><spring:message code="master.page.legalText" /></a></li>
			<li><a class="fNiv" href="information/administrator/list.do"><spring:message code="master.page.information" /></a></li>
			<li><a class="fNiv" href="administrator/suspicious.do"><spring:message code="master.page.suspicious" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message	code="master.page.manager" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/manager/edit.do"><spring:message code="master.page.actorEdit" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.actorFolder" /></a></li>			
				</ul>
			</li>
			<li><a href="trip/manager/create.do"><spring:message code="master.page.manager.create.trip" /></a></li>
			<li><a href="note/manager/list.do"><spring:message code="master.page.manager.notes" /></a></li>
			<li><a href=""><spring:message code="master.page.manager.applications" /></a></li>
			<li><a href="survival/manager/list.do"><spring:message code="master.page.manager.survivals" /></a></li>	
		</security:authorize>
		
		<security:authorize access="hasRole('EXPLORER')">
			<li><a class="fNiv"><spring:message	code="master.page.explorer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/explorer/edit.do"><spring:message code="master.page.actorEdit" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.actorFolder" /></a></li>
				</ul>
			</li>
			<li><a href="application/explorer/list.do"><spring:message code="master.page.explorer.applications" /></a></li>
			<li><a href=""><spring:message code="master.page.explorer.survivals" /></a></li>
			<li><a href="emergency/explorer/list.do"><spring:message code="master.page.emergency" /></a></li>
			<li><a href="story/explorer/list.do"><spring:message code="master.page.story" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUDITOR')">
			<li><a class="fNiv"><spring:message	code="master.page.auditor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/auditor/edit.do"><spring:message code="master.page.actorEdit" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.actorFolder" /></a></li>
				</ul>
			</li>
			<li><a href="audit/auditor/list.do"><spring:message code="master.page.auditor.audits" /></a></li>
			<li><a href="note/auditor/list.do"><spring:message code="master.page.auditor.notes" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/sponsor/edit.do"><spring:message code="master.page.actorEdit" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.actorFolder" /></a></li>
				</ul>
			</li>
			<li><a href="sponsorship/sponsor/list.do"><spring:message code="master.page.sponsor.sponsorships" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('RANGER')">
			<li><a class="fNiv"><spring:message	code="master.page.ranger" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/ranger/edit.do"><spring:message code="master.page.actorEdit" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.actorFolder" /></a></li>
				</ul>
			</li>
			<li><a href="curriculum/ranger/display.do"><spring:message code="master.page.curriculum" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href=""><spring:message code="master.page.register" /></a>
				<ul>
					<li><a href="ranger/register_Ranger.do"><spring:message code="master.page.register.ranger" /></a>
					<li><a href="explorer/register_Explorer.do"><spring:message code="master.page.register.explorer" /></a>
				</ul>
			</li>
			<li><a class="fNiv" href="trip/list.do"><spring:message code="master.page.trips" /></a></li>
			<li><a class="fNiv" href="category/list.do"><spring:message code="master.page.categories" /></a></li>
			<li><a class="fNiv" href="finder/search.do"><spring:message code="master.page.finder" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="trip/list.do"><spring:message code="master.page.trips" /></a></li>
			<li><a class="fNiv" href="category/list.do"><spring:message code="master.page.categories" /></a></li>
			<li><a class="fNiv" href="finder/search.do"><spring:message code="master.page.finder" /></a></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

