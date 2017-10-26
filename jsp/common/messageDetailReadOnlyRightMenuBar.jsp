<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 4;">
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="20px">
			<div class="index">
				<strong><s:message code="template00.messageInBoxDetailReadOnly.rightMenubarTitle.LABEL"/></strong>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/message/common/loadinboxmessages"/>">
					<s:message code="template00.messageInBoxDetailReadOnly.rightMenubarItem.back.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<c:url value="/message/common/messageform" var="messageFormUrl">
					<c:param name="MESSAGE_ID" value="${MESSAGE_DETAIL.messageId}"/>
				</c:url>
				<a href="<c:out value="${messageFormUrl}"/>" rel="nofollow">
					<s:message code="template00.messageInBoxDetailReadOnly.rightMenubarItem.reply.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
</table>