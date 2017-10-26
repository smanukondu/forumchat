<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr height="450" valign="top">
		<td>
			<div class="index">
				<h1><s:message code="template00.friendRequestInOfUserResult.contentInstTitle.LABEL"/></h1>
				<hr/>
				<c:if test="${not empty sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.pageDataList}">
					<c:forEach items="${sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.pageDataList}" var="fjr">
						<a href="#">#&nbsp;<c:out value="${fjr.friendJointRequestId}"/></a>
						<img src="<c:url value="/resources/images/${fjr.friendJointRequestFromUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
						<c:set var="uname" value="${fjr.friendJointRequestFromUser.userName}"/>
						<strong><c:out value="${fn:toUpperCase(uname)}"/></strong>
						&nbsp;<s:message code="template00.friendRequestInOfUserResult.contentText.sendARequestToYou.LABEL"/>&nbsp;
						<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${fjr.friendJointRequestDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
						<br/>
						<p><c:out value="${fjr.friendJointRequestRemark}"/></p>
						<c:url value="/friend/common/rejectfriendjointrequest" var="rejectfjrUrl">
							<c:param name="FRIEND_REQUEST_ID" value="${fjr.friendJointRequestId}"/>
						</c:url>
						<a href="<c:out value="${rejectfjrUrl}"/>">
							<s:message code="template00.friendRequestInOfUserResult.contentText.rejectRequest.LABEL"/>
						</a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<c:url value="/friend/common/acceptfriendjointrequest" var="acceptfjrUrl">
							<c:param name="FRIEND_REQUEST_ID" value="${fjr.friendJointRequestId}"/>
						</c:url>
						<a href="<c:out value="${acceptfjrUrl}"/>">
							<s:message code="template00.friendRequestInOfUserResult.contentText.acceptRequest.LABEL"/>
						</a>
						<hr/>
					</c:forEach>
				</c:if>
				<c:if test="${empty sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.pageDataList}">
					<h1><font color="red"><s:message code="template00.friendRequestInOfUserResult.contentText.noJoinFriendRequest.LABEL"/></font></h1>
				</c:if>
			</div>
		</td>
	</tr>
	<tr align="right">
		<td>
			<div class="pagination">
				<c:choose>
					<c:when test="${sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.firstPage == true}">
						FIRST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/first?PRESENT_BEAN_NAME=FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_FRIEND_JOINT_REQUEST_LIST_PAGE"/>">
							<strong>first&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
				<c:choose>
					<c:when test="${sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.firstPage == true}">
						PREVIOUS&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/prev?PRESENT_BEAN_NAME=FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_FRIEND_JOINT_REQUEST_LIST_PAGE"/>">
							<strong>previous&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    [<c:out value="${sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.pageNo}"/>/<c:out value="${sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.maxPageNo}"/>]
			    <c:choose>
					<c:when test="${sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.lastPage == true}">
						NEXT&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/next?PRESENT_BEAN_NAME=FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_FRIEND_JOINT_REQUEST_LIST_PAGE"/>">
							<strong>next&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    <c:choose>
					<c:when test="${sessionScope.FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN.lastPage == true}">
						LAST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/last?PRESENT_BEAN_NAME=FRIEND_JOINT_REQUEST_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_FRIEND_JOINT_REQUEST_LIST_PAGE"/>">
							<strong>last&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			</div>
		</td>
	</tr>
</table>