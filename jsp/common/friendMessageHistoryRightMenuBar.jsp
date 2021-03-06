<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 4;">
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="20px">
			<div class="index">
				<strong><s:message code="template00.friendMessageHistoryResult.rightMenubarTitle.LABEL"/></strong>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/friend/common/loadfriendlist"/>">
					<s:message code="template00.friendMessageHistoryResult.rightMenubarItem.friendList.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/message/common/loadmessagehistorywithfriend?FRIEND_USER_ID=${MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.userFriend.userId}"/>">
					<s:message code="template00.friendMessageHistoryResult.rightMenubarItem.refresh.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/friend/common/friendmessageform?FRIEND_USER_ID=${MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.userFriend.userId}"/>">
					<s:message code="template00.friendMessageHistoryResult.rightMenubarItem.toText.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
</table>
