<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index"><h1><s:message code="template00.sendFriendRequestLinkForm.contentInstTitle.LABEL"/></h1></div>
			<hr/>
		</td>
	</tr>
	<tr height="350" valign="top">
		<td>
			<div class="index">
				<table style="width: 360; padding: 2;">
					<tr>
						<td>
							<div class="index"><h2><s:message code="template00.sendFriendRequestLinkForm.contentText.pleaseTypeFriendRequestToBeSent.LABEL"/></h2></div>
							<hr/>
						</td>
					</tr>
					<tr valign="top">
						<td>
							<form:form method="post" modelAttribute="friendJointRequestForm">
								<table style="width: 380;">
									<tr>
										<td width="15%"><b><s:message code="template00.sendFriendRequestLinkForm.contentText.userName.LABEL"/></b></td>
										<td width="85%">
											<form:input path="friendUserName" type="text" size="30" maxlength="30"/>
											<s:bind path="friendUserName">
												<c:if test="${status.error}">
													<br/><form:errors path="friendUserName" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
												</c:if>
											</s:bind>
										</td>
									</tr>
									<tr>
										<td width="15%" valign="top"><b><s:message code="template00.sendFriendRequestLinkForm.contentText.message.LABEL"/></b></td>
										<td width="85%" valign="top">
											<form:textarea path="requestMessage" cols="35" rows="4"/>
											<s:bind path="requestMessage">
												<c:if test="${status.error}">
													<br/><form:errors path="requestMessage" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
												</c:if>
											</s:bind>
										</td>
									</tr>
									<tr height="35px">
										<td valign="top" align="center">
											<input type="submit" style="height: 2em; width: 6em" value="<s:message code="template00.sendFriendRequestLinkForm.submitButton.LABEL"/>"/>
										</td>
										<td valign="top" align="center">
											<input type="reset" style="height: 2em; width: 6em" value="<s:message code="template00.sendFriendRequestLinkForm.resetButton.LABEL"/>"/>
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
			</div>
		</td>
	</tr>
</table>