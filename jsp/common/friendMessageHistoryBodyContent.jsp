<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr height="450" valign="top">
		<td>
			<div class="index">
				<h1>
					<c:set var="uname" value="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.userFriend.userName}"/>
					<s:message code="template00.friendMessageHistoryResult.contentInstTitle.LABEL"/>&nbsp;<c:out value="${fn:toUpperCase(uname)}"/>
				</h1>
				<hr/>
				<c:if test="${not empty sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.pageDataList}">
					<c:forEach items="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.pageDataList}" var="msg">
						<c:choose>
							<c:when test="${sessionScope.USER_CONFIG.userId == msg.messageSourceUser.userId}">
								<a href="<c:url value="/friend/common/friendmessageform?FRIEND_USER_ID=${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.userFriend.userId}"/>">
									#&nbsp;<c:out value="${msg.messageId}"/>
								</a>
								&nbsp;<s:message code="template00.friendMessageHistoryResult.contentText.youSaidToYourFriend.LABEL"/>
								<img src="<c:url value="/resources/images/${msg.messageDestUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
								<c:set var="uname1" value="${msg.messageDestUser.userName}"/>
								<strong><c:out value="${fn:toUpperCase(uname1)}"/></strong>
							</c:when>
							<c:otherwise>
								<a href="<c:url value="/message/common/loadinmessagedetail?MESSAGE_ID=${msg.messageId}"/>">
									#&nbsp;<c:out value="${msg.messageId}"/>
								</a>
								<img src="<c:url value="/resources/images/${msg.messageSourceUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
								<c:set var="uname2" value="${msg.messageSourceUser.userName}"/>
								<strong><c:out value="${fn:toUpperCase(uname2)}"/></strong>
								&nbsp;<s:message code="template00.friendMessageHistoryResult.contentText.saidToYou.LABEL"/>
							</c:otherwise>
					    </c:choose>
						<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${msg.messageTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
						<p><c:out value="${msg.messageContent}"/></p>
						<hr/>
					</c:forEach>
				</c:if>
				<c:if test="${empty sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.pageDataList}">
					<h1><font color="red"><s:message code="template00.friendMessageHistoryResult.contentText.noMessageIN.LABEL"/></font></h1>
				</c:if>
			</div>
		</td>
	</tr>
	<tr align="right">
		<td>
			<div class="pagination">
				<c:choose>
					<c:when test="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.firstPage == true}">
						FIRST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/first?PRESENT_BEAN_NAME=MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN&RESULT_PAGE=LOADED_MESSAGES_HISTORY_WITH_FRIEND_PAGE"/>">
							<strong>First&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
				<c:choose>
					<c:when test="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.firstPage == true}">
						PREVIOUS&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/prev?PRESENT_BEAN_NAME=MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN&RESULT_PAGE=LOADED_MESSAGES_HISTORY_WITH_FRIEND_PAGE"/>">
							<strong>Previous&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    [<c:out value="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.pageNo}"/>/<c:out value="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.maxPageNo}"/>]
			    <c:choose>
					<c:when test="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.lastPage == true}">
						NEXT&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/next?PRESENT_BEAN_NAME=MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN&RESULT_PAGE=LOADED_MESSAGES_HISTORY_WITH_FRIEND_PAGE"/>">
							<strong>Next&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    <c:choose>
					<c:when test="${sessionScope.MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN.lastPage == true}">
						LAST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/last?PRESENT_BEAN_NAME=MESSAGE_HISTORY_WITH_FRIEND_PRESENT_BEAN&RESULT_PAGE=LOADED_MESSAGES_HISTORY_WITH_FRIEND_PAGE"/>">
							<strong>Next&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			</div>
		</td>
	</tr>
</table>