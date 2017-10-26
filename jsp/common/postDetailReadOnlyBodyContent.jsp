<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr height="450" valign="top">
		<td>
			<div class="index">
				<h1><s:message code="template00.postDetailReadOnlyResult.contentInstTitle.LABEL"/></h1>
				<hr/>
				<c:if test="${not empty sessionScope.POST_COMMENT_PRESENT_BEAN.post}">
					<a href="#">#&nbsp;<c:out value="${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postId}"/></a>
					<img src="<c:url value="/resources/images/${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
					<c:set var="uname" value="${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postUser.userName}"/>
					<strong><c:out value="${fn:toUpperCase(uname)}"/></strong>
					&nbsp;-<b>&nbsp;[<c:out value="${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postTopic}"/>]</b>
					<table style="width: 100%; padding: 5;">
						<tr height="350" valign="top">
							<td>
								<p>${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postContent}</p>
							</td>
						</tr>
					</table>
					<c:if test="${not empty sessionScope.POST_COMMENT_PRESENT_BEAN.post.postAttachmentImage}">
						<p>
							<br/>====&nbsp;<s:message code="template00.postDetailReadOnlyResult.contentText.postAttachmentImage.LABEL"/>&nbsp;====<br/>
							<c:set var="str1" value="${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postAttachmentImage}"/>
							<c:forEach items="${fn:split(str1, ',')}" var="ai_str" varStatus="loop">
								<img src="<c:url value="/resources/images/img_icon.gif"/>"/>&nbsp;
								<a href="<c:url value="/attachment/common/downloadimage?image=${ai_str}"/>" target="_blank">
									<s:message code="template00.postDetailReadOnlyResult.contentText.clickLinkToSeeImage.LABEL"/>&nbsp;<c:out value="${loop.index + 1}"/>
								</a>
								<br/>
							</c:forEach>
						</p>
					</c:if>
					<b><s:message code="template00.postZoneResult.contentText.mark.LABEL"/>&nbsp;</b>
					(<c:out value="${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postMark}"/>)
					&nbsp;
					<b><s:message code="template00.postZoneResult.contentText.commnetTimes.LABEL"/>&nbsp;</b>
					(<c:out value="${sessionScope.POST_COMMENT_PRESENT_BEAN.post.postCommentNum}"/>)
					&nbsp;
					<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${POST_COMMENT_PRESENT_BEAN.post.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
				</c:if>
				<c:if test="${empty sessionScope.POST_COMMENT_PRESENT_BEAN.post}">
					<h1><font color="red"><s:message code="template00.postDetailReadOnlyResult.contentText.noPostInPool.LABEL"/></font></h1>
				</c:if>
				<hr/><br/>
				<c:if test="${not empty sessionScope.POST_COMMENT_PRESENT_BEAN.pageDataList}">
					<c:forEach items="${sessionScope.POST_COMMENT_PRESENT_BEAN.pageDataList}" var="pc" varStatus="loop">
						<a href="#">#&nbsp;<c:out value="${loop.index + 1}"/></a>
						<img src="<c:url value="/resources/images/${pc.postCommentUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
						&nbsp;
						<c:set var="uname1" value="${pc.postCommentUser.userName}"/>
						<strong><c:out value="${fn:toUpperCase(uname1)}"/></strong>
						<p><c:out value="${pc.postCommentContent}"/></p>
						<b><s:message code="template00.postDetailReadOnlyResult.contentText.postMark.LABEL"/>&nbsp;</b>(
						<c:choose>
							<c:when test="${pc.postCommentMark == 0}">
								<img src="<c:url value="/resources/images/pmark_0.gif"/>" width="25px" height="25px" border="0"/>
							</c:when>
							<c:otherwise>
								<c:forEach begin="1" end="${pc.postCommentMark}" step="1">
        							<img src="<c:url value="/resources/images/pmark_1.gif"/>" width="25px" height="25px" border="0"/>
      							</c:forEach>
							</c:otherwise>
					    </c:choose>
						)&nbsp;<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${pc.postCommentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
						<hr/>
					</c:forEach>
				</c:if>
				<c:if test="${empty sessionScope.POST_COMMENT_PRESENT_BEAN.pageDataList}">
					<h1><font color="red"><s:message code="template00.postDetailReadOnlyResult.contentText.noCommentForThisPost.LABEL"/></font></h1>
				</c:if>
			</div>
		</td>
	</tr>
	<tr align="right">
		<td>
			<div class="pagination">
				<c:choose>
					<c:when test="${sessionScope.POST_COMMENT_PRESENT_BEAN.firstPage == true}">
						FIRST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/first?PRESENT_BEAN_NAME=POST_COMMENT_PRESENT_BEAN&RESULT_PAGE=LOADED_POST_DETAIL_PAGE"/>">
							<strong>first&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
				<c:choose>
					<c:when test="${sessionScope.POST_COMMENT_PRESENT_BEAN.firstPage == true}">
						PREVIOUS&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/prev?PRESENT_BEAN_NAME=POST_COMMENT_PRESENT_BEAN&RESULT_PAGE=LOADED_POST_DETAIL_PAGE"/>">
							<strong>previous&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    [<c:out value="${sessionScope.POST_COMMENT_PRESENT_BEAN.pageNo}"/>/<c:out value="${sessionScope.POST_COMMENT_PRESENT_BEAN.maxPageNo}"/>]
			    <c:choose>
					<c:when test="${sessionScope.POST_COMMENT_PRESENT_BEAN.lastPage == true}">
						NEXT&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/next?PRESENT_BEAN_NAME=POST_COMMENT_PRESENT_BEAN&RESULT_PAGE=LOADED_POST_DETAIL_PAGE"/>">
							<strong>next&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			    <c:choose>
					<c:when test="${sessionScope.POST_COMMENT_PRESENT_BEAN.lastPage == true}">
						LAST&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/pagingList/common/last?PRESENT_BEAN_NAME=POST_COMMENT_PRESENT_BEAN&RESULT_PAGE=LOADED_POST_DETAIL_PAGE"/>">
							<strong>last&nbsp;</strong>
						</a>
					</c:otherwise>
			    </c:choose>
			</div>
		</td>
	</tr>
</table>