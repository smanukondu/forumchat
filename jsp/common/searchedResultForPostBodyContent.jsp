<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr height="450" valign="top">
		<td>
			<div class="index">
				<h1><s:message code="template00.searchForPostResult.contentInstTitle.LABEL"/></h1>
				<hr/>
				<c:if test="${not empty sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.pageDataList}">
					<c:forEach items="${sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.pageDataList}" var="post">
						<a href="<c:url value="/post/common/loadpostdetail?POST_ID=${post.postId}"/>">#&nbsp;<c:out value="${post.postId}"/></a>
						<img src="<c:url value="/resources/images/${post.postUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
						<c:set var="uname" value="${post.postUser.userName}"/>
						<strong><c:out value="${fn:toUpperCase(uname)}"/></strong>
						&nbsp;-<b>&nbsp;[<c:out value="${post.postTopic}"/>]</b>
						<p>
							<c:out value="${post.postContent}"/>
							&nbsp;
							<a href="<c:url value="/post/common/loadpostdetail?POST_ID=${post.postId}"/>">... <s:message code="template00.searchForPostResult.contentText.more.LABEL"/></a>
						</p>
						<b><s:message code="template00.searchForPostResult.contentText.postMark.LABEL"/>&nbsp;</b>
						(<c:out value="${post.postMark}"/>)
						&nbsp;
						<b><s:message code="template00.searchForPostResult.contentText.commentTimes.LABEL"/>&nbsp;</b>
						(<c:out value="${post.postCommentNum}"/>)
						&nbsp;
						<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${post.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
						<hr/>
					</c:forEach>
				</c:if>
				<c:if test="${empty sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.pageDataList}">
					<h1><font color="red"><s:message code="template00.searchForPostResult.contentText.noPost.LABEL"/></font></h1>
				</c:if>
			</div>
		</td>
	</tr>
	<tr align="right">
		<td>
			<div class="pagination">
				<c:choose>
					<c:when test="${sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.firstPage == true}">
						FIRST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/first?PRESENT_BEAN_NAME=SEARCH_FOR_POST_PRESENT_BEAN&RESULT_PAGE=SEARCHED_POST_RECORD_RESULT_PAGE"/>">
							<strong>first&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
				<c:choose>
					<c:when test="${sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.firstPage == true}">
						PREVIOUS&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/prev?PRESENT_BEAN_NAME=SEARCH_FOR_POST_PRESENT_BEAN&RESULT_PAGE=SEARCHED_POST_RECORD_RESULT_PAGE"/>">
							<strong>previous&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    [<c:out value="${sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.pageNo}"/>/<c:out value="${sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.maxPageNo}"/>]
			    <c:choose>
					<c:when test="${sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.lastPage == true}">
						NEXT&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/next?PRESENT_BEAN_NAME=SEARCH_FOR_POST_PRESENT_BEAN&RESULT_PAGE=SEARCHED_POST_RECORD_RESULT_PAGE"/>">
							<strong>next&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    <c:choose>
					<c:when test="${sessionScope.SEARCH_FOR_POST_PRESENT_BEAN.lastPage == true}">
						LAST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/last?PRESENT_BEAN_NAME=SEARCH_FOR_POST_PRESENT_BEAN&RESULT_PAGE=SEARCHED_POST_RECORD_RESULT_PAGE"/>">
							<strong>last&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			</div>
		</td>
	</tr>
</table>