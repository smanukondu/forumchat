<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 4;">
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="20px">
			<div class="index">
				<h1><s:message code="template01.loginForm.leftMenubarTitle.LABEL"/></h1>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/user/default/loginform"/>">
					<s:message code="template01.loginForm.leftMenubarItem.signIn.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/user/default/userregisterform"/>">
					<s:message code="template01.loginForm.leftMenubarItem.userRegister.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
</table>