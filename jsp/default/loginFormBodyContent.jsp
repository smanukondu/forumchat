<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index"><h1><s:message code="template01.loginForm.contentInstTitle.LABEL"/></h1></div>
			<hr/>
		</td>
	</tr>
	<tr valign="top">
		<td>
			<form:form method="post" modelAttribute="loginForm">
				<table style="width: 380;">
					<tr>
						<td width="25%" align="left" valign="top">
							<form:label path="userName"><b><s:message code="template01.loginForm.userName.LABEL"/></b></form:label>
						</td>
						<td width="75%" align="left" valign="top">
							<form:input path="userName" type="text" size="30" maxlength="30"/>
							<s:bind path="userName">
								<c:if test="${status.error}">
									<br/><form:errors path="userName" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="25%" align="left" valign="top">
							<form:label path="userPassword"><b><s:message code="template01.loginForm.userPassword.LABEL"/></b></form:label>
						</td>
						<td width="75%" align="left" valign="top">
							<form:password path="userPassword" size="30" maxlength="30"/>
							<s:bind path="userPassword">
								<c:if test="${status.error}">
									<br/><form:errors path="userPassword" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table style="width: 100%;">
								<tr>
									<td align="left" width="50%">
										<input type="submit" style="height: 2em; width: 6em" value="<s:message code="template01.loginForm.submitButton.LABEL"/>"/>
									</td>
									<td align="left" width="50%">
										<input type="reset" style="height: 2em; width: 6em" value="<s:message code="template01.loginForm.resetButton.LABEL"/>"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form:form>
			<c:if test="${not empty message}">
				<span style="font-family:arial;font-weight:bold;color:blue;font-size:12px;">
					<c:out value="${message}"/>
				</span>
			</c:if>
		</td>
	</tr>
</table>
