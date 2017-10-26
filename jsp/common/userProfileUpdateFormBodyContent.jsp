<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index"><h1><s:message code="template00.userProfileUpdateForm.contentInstTitle.LABEL"/></h1></div>
			<hr/>
		</td>
	</tr>
	<tr valign="top">
		<td>
			<form:form method="post" modelAttribute="userProfileForm">
				<form:hidden path="id" value="${sessionScope.USER_CONFIG.userId}"/>
				<table style="width: 400;">
					<tr>
						<td width="15%">
							<form:label path="username"><b><s:message code="template00.userProfileUpdateForm.userName.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:hidden path="username" value="${sessionScope.USER_CONFIG.userName}"/>
							<c:out value="${sessionScope.USER_CONFIG.userName}"/>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<form:label path="password"><b><s:message code="template00.userProfileUpdateForm.password.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:input path="password" type="text" size="40" maxlength="30"/>
							<s:bind path="password">
								<c:if test="${status.error}">
									<br/><form:errors path="password" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<form:label path="alias"><b><s:message code="template00.userProfileUpdateForm.alias.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:input path="alias" type="text" size="40" maxlength="40"/>
							<s:bind path="alias">
								<c:if test="${status.error}">
									<br/><form:errors path="alias" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<form:label path="email"><b><s:message code="template00.userProfileUpdateForm.eMail.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:input path="email" type="text" size="40" maxlength="40"/>
							<s:bind path="email">
								<c:if test="${status.error}">
									<br/><form:errors path="email" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top">
							<form:label path="signature"><b><s:message code="template00.userProfileUpdateForm.signature.LABEL"/></b></form:label>
						</td>
						<td width="85%" valign="top">
							<form:textarea path="signature" cols="35" rows="5"/>
							<s:bind path="signature">
								<c:if test="${status.error}">
									<br/><form:errors path="signature" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr height="35px">
						<td width="15%" valign="top"><b><s:message code="template00.userProfileUpdateForm.avatarPICture.LABEL"/></b></td>
						<td width="85%" valign="top">
							<form:select path="avatarPICValue" size="1">
								<form:option value="0">No.1</form:option>
								<form:option value="1">No.2</form:option>
								<form:option value="2">No.3</form:option>
								<form:option value="3">No.4</form:option>
								<form:option value="4">No.5</form:option>
							</form:select>
							<br/>
							<img src="<c:url value="/resources/images/avatar_0.jpg"/>" width="35px" height="35px" border="1" alt="No.1"/>
							<img src="<c:url value="/resources/images/avatar_1.jpg"/>" width="35px" height="35px" border="1" alt="No.2"/>
							<img src="<c:url value="/resources/images/avatar_2.jpg"/>" width="35px" height="35px" border="1" alt="No.3"/>
							<img src="<c:url value="/resources/images/avatar_3.jpg"/>" width="35px" height="35px" border="1" alt="No.4"/>
							<img src="<c:url value="/resources/images/avatar_4.jpg"/>" width="35px" height="35px" border="1" alt="No.5"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table style="width: 100%; padding: 2;">
								<tr>
									<td align="left" width="50%">
										<input type="submit" style="height: 2em; width: 6em" value="<s:message code="template00.userProfileUpdateForm.updateButton.LABEL"/>"/>
									</td>
									<td align="left" width="50%">
										<input type="reset" style="height: 2em; width: 6em" value="<s:message code="template00.userProfileUpdateForm.resetButton.LABEL"/>"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form:form>
		</td>
	</tr>
</table>