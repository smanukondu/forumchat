<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index">
				<h1><s:message code="template00.messageInBoxDetailReadOnly.contentInstTitle.LABEL"/></h1>
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
				<hr/>
				<s:message code="template00.messageInBoxDetailReadOnly.contentText.dateTime.LABEL"/>&nbsp;<font color="darkorchid" size="1"><b><fmt:formatDate value="${MESSAGE_DETAIL.messageTime}" pattern="yyyy-MM-dd HH:mm:ss"/></b></font>
			</div>
		</td>
	</tr>
</table>