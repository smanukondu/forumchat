<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index">
				<h1><s:message code="template00.messageInBoxDetailForm.contentInstTitle.LABEL"/></h1>
			</div>
			<hr/>
		</td>
	</tr>
	<tr height="350" valign="top">
		<td>
			<div class="index">
				<img src="<c:url value="/resources/images/${MESSAGE_DETAIL.messageSourceUser.userAvatarPic}"/>" width="40px" height="32px" border="0"/>
				<a href="#">#&nbsp;<c:out value="${MESSAGE_DETAIL.messageId}"/></a>
				&nbsp;
				<c:set var="uname" value="${MESSAGE_DETAIL.messageSourceUser.userName}"/>
				<strong><c:out value="${fn:toUpperCase(uname)}"/></strong>
				&nbsp;<s:message code="template00.messageInBoxDetailReadOnly.contentText.saidToYou.LABEL"/>&nbsp;
				<p><c:out value="${MESSAGE_DETAIL.messageContent}"/></p>
				<s:message code="template00.messageInBoxDetailReadOnly.contentText.dateTime.LABEL"/>&nbsp;<font color="darkorchid" size="1"><b><fmt:formatDate value="${MESSAGE_DETAIL.messageTime}" pattern="yyyy-MM-dd HH:mm:ss"/></b></font>
				<hr/>
				<table style="width: 360; padding: 2;">
					<tr>
						<td>
							<div class="index">
								<h2><s:message code="template00.messageInBoxDetailForm.contentText.pleaseTypeRepliedMessage.LABEL"/></h2>
							</div>
							<hr/>
						</td>
					</tr>
					<tr valign="top">
						<td>
							<form:form method="post" modelAttribute="messageForm">
								<form:hidden path="messageDestUserId" value="${MESSAGE_DETAIL.messageSourceUser.userId}"/>
								<form:hidden path="messageSourceUserId" value="${MESSAGE_DETAIL.messageDestUser.userId}"/>
								<form:hidden path="messageId" value="${MESSAGE_DETAIL.messageId}"/>
								<table style="width: 380;">
									<tr>
										<td width="15%" valign="top">
											<form:label path="messageContent">
												<b><s:message code="template00.messageInBoxDetailForm.contentText.message.LABEL"/></b>
											</form:label>
										</td>
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
											<input type="submit" style="height: 2em; width: 6em" value="<s:message code="template00.messageInBoxDetailForm.button.submit.LABEL"/>"/>
										</td>
										<td valign="top" align="center">
											<input type="reset" style="height: 2em; width: 6em" value="<s:message code="template00.messageInBoxDetailForm.button.reset.LABEL"/>"/>
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