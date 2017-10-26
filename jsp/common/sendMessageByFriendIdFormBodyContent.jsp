<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index"><h1><s:message code="template00.sendMessageByFriendIdForm.contentInstTitle.LABEL"/></h1></div>
			<hr/>
		</td>
	</tr>
	<tr height="350" valign="top">
		<td>
			<div class="index">
				<s:message code="template00.sendMessageByFriendIdForm.contentText.sayToYourFriend.LABEL"/>&nbsp;
				<a href="#">#&nbsp;<c:out value="${sessionScope.USER_FRIEND_DETAIL.userId}"/></a>
				<img src="<c:url value="/resources/images/${sessionScope.USER_FRIEND_DETAIL.userAvatarPic}"/>" width="40px" height="32px" border="0"/>
				<c:set var="uname" value="${sessionScope.USER_FRIEND_DETAIL.userName}"/>
				<strong><c:out value="${fn:toUpperCase(uname)}"/></strong>
				<hr/>
				<table style="width: 360; padding: 2;">
					<tr>
						<td>
							<div class="index"><h2><s:message code="template00.sendMessageByFriendIdForm.contentText.pleaseTypeMessageToYourFriend.LABEL"/></h2></div>
							<hr/>
						</td>
					</tr>
					<tr valign="top">
						<td>
							<form:form method="post" modelAttribute="friendMessageForm">
								<form:hidden path="messageDestUserId" value="${USER_FRIEND_DETAIL.userId}"/>
								<form:hidden path="messageSourceUserId" value="${USER_CONFIG.userId}"/>
								<table style="width: 380;">
									<tr>
										<td width="15%" valign="top"><b><s:message code="template00.sendMessageByFriendIdForm.contentText.message.LABEL"/></b></td>
										<td width="85%" valign="top">
											<form:textarea path="messageContent" cols="30" rows="4"/>
											<s:bind path="messageContent">
												<c:if test="${status.error}">
													<br/><form:errors path="messageContent" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
												</c:if>
											</s:bind>
										</td>
									</tr>
									<tr height="35px">
										<td valign="top" align="center">
											<input type="submit" style="height: 2em; width: 6em" value="<s:message code="template00.sendMessageByFriendIdForm.submitButton.LABEL"/>"/>
										</td>
										<td valign="top" align="center">
											<input type="reset" style="height: 2em; width: 6em" value="<s:message code="template00.sendMessageByFriendIdForm.resetButton.LABEL"/>"/>
										</td>
									</tr>
								</table>
							</form:form>
						</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
</table>