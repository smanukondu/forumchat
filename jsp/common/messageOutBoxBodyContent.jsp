<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr height="350" valign="top">
		<td>
			<div class="index">
				<h1><s:message code="template00.messageOutBoxResult.contentInstTitle.LABEL"/></h1>
				<hr/>
				<c:if test="${not empty sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.pageDataList}">
					<c:forEach items="${sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.pageDataList}" var="msg">
						<a href="#">#&nbsp;<c:out value="${msg.messageId}"/></a>
						&nbsp;<s:message code="template00.messageOutBoxResult.contentText.youSaidToYourFriend.LABEL"/>
						<img src="<c:url value="/resources/images/${msg.messageDestUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
						<a href="<c:url value="/message/common/loadmessagehistorywithfriend?FRIEND_USER_ID=${msg.messageDestUser.userId}"/>">
							<c:set var="uname" value="${msg.messageDestUser.userName}"/>
							<c:out value="${fn:toUpperCase(uname)}"/>
						</a>
						<font color="darkorchid" size="1">
							<b>&lt;<fmt:formatDate value="${msg.messageTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b>
						</font>
						<p><c:out value="${msg.messageContent}"/></p>
						<hr/>
					</c:forEach>
				</c:if>
				<c:if test="${empty sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.pageDataList}">
					<h1><font color="red"><s:message code="template00.messageOutBoxResult.contentText.noMessageOut.LABEL"/></font></h1>
				</c:if>
			</div>
		</td>
	</tr>
	<tr align="right">
		<td>
			<div class="pagination">
				<c:choose>
					<c:when test="${sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.firstPage == true}">
						FIRST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/first?PRESENT_BEAN_NAME=OUTBOX_MESSAGE_PRESENT_BEAN&RESULT_PAGE=LOADED_OUTBOX_MESSAGES_PAGE"/>">
							<strong>first&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
				<c:choose>
					<c:when test="${sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.firstPage == true}">
						PREVIOUS&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/prev?PRESENT_BEAN_NAME=OUTBOX_MESSAGE_PRESENT_BEAN&RESULT_PAGE=LOADED_OUTBOX_MESSAGES_PAGE"/>">
							<strong>previous&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    [<c:out value="${sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.pageNo}"/>/<c:out value="${sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.maxPageNo}"/>]
			    <c:choose>
					<c:when test="${sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.lastPage == true}">
						NEXT&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/next?PRESENT_BEAN_NAME=OUTBOX_MESSAGE_PRESENT_BEAN&RESULT_PAGE=LOADED_OUTBOX_MESSAGES_PAGE"/>">
							<strong>next&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    <c:choose>
					<c:when test="${sessionScope.OUTBOX_MESSAGE_PRESENT_BEAN.lastPage == true}">
						LAST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/last?PRESENT_BEAN_NAME=OUTBOX_MESSAGE_PRESENT_BEAN&RESULT_PAGE=LOADED_OUTBOX_MESSAGES_PAGE"/>">
							<strong>last&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			</div>
		</td>
	</tr>
</table>