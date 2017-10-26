<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index"><h1><s:message code="template01.userRegisterForm.contentInstTitle.LABEL"/></h1></div>
			<hr/>
		</td>
	</tr>
	<tr valign="top">
		<td>
			<form:form method="post" modelAttribute="userRegisterForm">
				<table style="width: 400;">
					<tr>
						<td width="15%">
							<form:label path="userName"><b><s:message code="template01.userRegisterForm.userName.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:input path="userName" type="text" size="40" maxlength="30"/>
							<s:bind path="userName">
								<c:if test="${status.error}">
									<br/><form:errors path="userName" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<form:label path="userPassword"><b><s:message code="template01.userRegisterForm.userPassword.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:password path="userPassword" size="40" maxlength="30"/>
							<s:bind path="userPassword">
								<c:if test="${status.error}">
									<br/><form:errors path="userPassword" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<form:label path="userConfirmedPassword"><b><s:message code="template01.userRegisterForm.userConfirmedPassword.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:password path="userConfirmedPassword" size="40" maxlength="30"/>
							<s:bind path="userConfirmedPassword">
								<c:if test="${status.error}">
									<br/><form:errors path="userConfirmedPassword" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<form:label path="userAlias"><b><s:message code="template01.userRegisterForm.userAlias.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:input path="userAlias" type="text" size="40" maxlength="40"/>
							<s:bind path="userAlias">
								<c:if test="${status.error}">
									<br/><form:errors path="userAlias" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%">
							<form:label path="userEmail"><b><s:message code="template01.userRegisterForm.userEmail.LABEL"/></b></form:label>
						</td>
						<td width="85%">
							<form:input path="userEmail" type="text" size="40" maxlength="40"/>
							<s:bind path="userEmail">
								<c:if test="${status.error}">
									<br/><form:errors path="userEmail" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top">
							<form:label path="userSignature"><b><s:message code="template01.userRegisterForm.userSignature.LABEL"/></b></form:label>
						</td>
						<td width="85%" valign="top">
							<form:textarea path="userSignature" cols="38" rows="5"/>
							<s:bind path="userSignature">
								<c:if test="${status.error}">
									<br/><form:errors path="userSignature" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
								</c:if>
							</s:bind>
						</td>
					</tr>
					<tr height="35px">
						<td width="15%" valign="top"><b><s:message code="template01.userRegisterForm.userAvatarPic.LABEL"/></b></td>
						<td width="85%" valign="top">
							<form:select path="userAvatarPicValue" size="1">
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
										<input type="submit" style="height: 2em; width: 6em" value="<s:message code="template01.userRegisterForm.commitButton.LABEL"/>"/>
									</td>
									<td align="left" width="50%">
										<input type="reset" style="height: 2em; width: 6em" value="<s:message code="template01.userRegisterForm.resetButton.LABEL"/>"/>
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