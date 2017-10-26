<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr height="450" valign="top">
		<td>
			<div class="index">
				<h1><s:message code="template00.friendListAllOfUserResult.contentInstTitle.LABEL"/></h1>
				<hr/>
				<c:if test="${not empty sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.pageDataList}">
					<c:forEach items="${sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.pageDataList}" var="fl">
						<c:url value="/friend/common/loadfriendprofiledetail" var="lfpdUrl">
							<c:param name="FRIEND_USER_ID" value="${fl.friendLinkCustUser.userId}"/>
						</c:url>
						<a href="<c:out value="${lfpdUrl}"/>">#&nbsp;${fl.friendLinkCustUser.userId}</a>
						<img src="<c:url value="/resources/images/${fl.friendLinkCustUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
						<c:url value="/message/common/loadmessagehistorywithfriend" var="lmhwfUrl">
							<c:param name="FRIEND_USER_ID" value="${fl.friendLinkCustUser.userId}"/>
						</c:url>
						<c:set var="uname" value="${fl.friendLinkCustUser.userName}"/>
						<a href="<c:out value="${lmhwfUrl}"/>"><c:out value="${uname}"/></a>
						&nbsp;<s:message code="template00.friendListAllOfUserResult.contentText.linkedDate.LABEL"/>&nbsp;
						<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${fl.friendLinkMappedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
						&nbsp;
						<c:choose>
							<c:when test="${fl.friendLinkCustUser.userLoginStatus == 1}">
								<font color="green"><b><s:message code="template00.friendListAllOfUserResult.contentText.Online.LABEL"/></b></font>
							</c:when>
							<c:otherwise>
								<font color="grey"><b><s:message code="template00.friendListAllOfUserResult.contentText.Offline.LABEL"/></b></font>
							</c:otherwise>
					    </c:choose>
						<br/>
						<c:url value="/friend/common/cancelfriendlink" var="cflUrl">
							<c:param name="FRIEND_ID" value="${fl.friendLinkCustUser.userId}"/>
						</c:url>
						<a href="<c:out value="${cflUrl}"/>"><s:message code="template00.friendListAllOfUserResult.contentText.cancelLink.LABEL"/></a>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						<c:url value="/friend/common/friendmessageform" var="msgfrmUrl">
							<c:param name="FRIEND_USER_ID" value="${fl.friendLinkCustUser.userId}"/>
						</c:url>
						<a href="<c:out value="${msgfrmUrl}"/>"><s:message code="template00.friendListAllOfUserResult.contentText.sendMessage.LABEL"/></a>
						<hr/>
					</c:forEach>
				</c:if>
				<c:if test="${empty sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.pageDataList}">
					<h1><font color="red"><s:message code="template00.friendListAllOfUserResult.contentText.noFriend.LABEL"/></font></h1>
				</c:if>
			</div>
		</td>
	</tr>
	<tr align="right">
		<td>
			<div class="pagination">
				<c:choose>
					<c:when test="${sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.firstPage == true}">
						FIRST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/first?PRESENT_BEAN_NAME=MY_FRIEND_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_ALL_USER_FRIEND_LIST_PAGE"/>">
							<strong>first&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
				<c:choose>
					<c:when test="${sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.firstPage == true}">
						PREVIOUS&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/prev?PRESENT_BEAN_NAME=MY_FRIEND_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_ALL_USER_FRIEND_LIST_PAGE"/>">
							<strong>previous&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    [<c:out value="${sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.pageNo}"/>/<c:out value="${sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.maxPageNo}"/>]
			    <c:choose>
					<c:when test="${sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.lastPage == true}">
						NEXT&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/next?PRESENT_BEAN_NAME=MY_FRIEND_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_ALL_USER_FRIEND_LIST_PAGE"/>">
							<strong>next&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    <c:choose>
					<c:when test="${sessionScope.MY_FRIEND_LIST_PRESENT_BEAN.lastPage == true}">
						LAST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/last?PRESENT_BEAN_NAME=MY_FRIEND_LIST_PRESENT_BEAN&RESULT_PAGE=LOADED_ALL_USER_FRIEND_LIST_PAGE"/>">
							<strong>last&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			</div>
		</td>
	</tr>
</table>