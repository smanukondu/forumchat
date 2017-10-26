<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr height="450" valign="top">
		<td>
			<div class="index">
				<h1><s:message code="template00.newPostCommentForm.contentInstTitle.LABEL"/></h1>
				<hr/>
				<c:if test="${not empty POST_COMMENT_PRESENT_BEAN.post}">
					<a href="#">#&nbsp;<c:out value="${POST_COMMENT_PRESENT_BEAN.post.postId}"/></a>
					<img src="<c:url value="/resources/images/${POST_COMMENT_PRESENT_BEAN.post.postUser.userAvatarPic}"/>" width="25px" height="25px" border="0"/>
					<c:set var="uname" value="${POST_COMMENT_PRESENT_BEAN.post.postUser.userName}"/>
					<strong><c:out value="${fn:toUpperCase(uname)}"/></strong>
					&nbsp;-<b>&nbsp;[<c:out value="${POST_COMMENT_PRESENT_BEAN.post.postTopic}"/>]</b>
					<table style="width: 100%; padding: 5;">
						<tr height="350" valign="top">
							<td>
								<p>${POST_COMMENT_PRESENT_BEAN.post.postContent}</p>
							</td>
						</tr>
					</table>
					<c:if test="${not empty POST_COMMENT_PRESENT_BEAN.post.postAttachmentImage}">
						<p>
							<br/>====&nbsp;<s:message code="template00.postDetailReadOnlyResult.contentText.postAttachmentImage.LABEL"/>&nbsp;====<br/>
							<c:set var="str1" value="${POST_COMMENT_PRESENT_BEAN.post.postAttachmentImage}"/>
							<c:set var="str2" value="${fn:split(str1, ',')}" />
							<c:forEach items="str2" var="ai_str" varStatus="loop">
								<img src="<c:url value="/resources/images/img_icon.gif"/>"/>&nbsp;
								<a href="<c:url value="/attachmentDownload?resImage=${ai_str}"/>" target="_blank">
									<s:message code="template00.postDetailReadOnlyResult.contentText.clickLinkToSeeImage.LABEL"/>&nbsp;<c:out value="${loop.index + 1}"/>
								</a>
								<br/>
							</c:forEach>
							<br/>
							<b><s:message code="template00.postDetailReadOnlyResult.contentText.postMark.LABEL"/>&nbsp;</b>(<c:out value="${POST_COMMENT_PRESENT_BEAN.post.postMark}"/>)
							&nbsp;
							<b><s:message code="template00.postDetailReadOnlyResult.contentText.commentTimes.LABEL"/>&nbsp;</b>
							(<c:out value="${POST_COMMENT_PRESENT_BEAN.post.postCommentNum}"/>)&nbsp;
							<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${POST_COMMENT_PRESENT_BEAN.post.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
						</p>
					</c:if>
					<b><s:message code="template00.postZoneResult.contentText.mark.LABEL"/>&nbsp;</b>
					(<c:out value="${POST_COMMENT_PRESENT_BEAN.post.postMark}"/>)
					&nbsp;
					<b><s:message code="template00.postZoneResult.contentText.commnetTimes.LABEL"/>&nbsp;</b>
					(<c:out value="${POST_COMMENT_PRESENT_BEAN.post.postCommentNum}"/>)
					&nbsp;
					<font color="darkorchid" size="1"><b>&lt;<fmt:formatDate value="${POST_COMMENT_PRESENT_BEAN.post.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&gt;</b></font>
				</c:if>
				<c:if test="${empty POST_COMMENT_PRESENT_BEAN.post}">
					<h1><font color="red"><s:message code="template00.postDetailReadOnlyResult.contentText.noPostInPool.LABEL"/></font></h1>
				</c:if>
				<hr/><br/>
				<table style="width: 360; padding: 2;">
					<tr>
						<td>
							<div class="index"><h2><s:message code="template00.newPostCommentForm.contentText.pleaseTypePostComment.LABEL"/></h2></div>
							<hr/>
						</td>
					</tr>
					<tr valign="top">
						<td>
							<form:form modelAttribute="postCommentForm" method="post">
								<form:hidden path="postId" value="${POST_COMMENT_PRESENT_BEAN.post.postId}"/>
								<form:hidden path="postCommentUserId" value="${USER_CONFIG.userId}"/>
								<table style="width: 350;">
									<tr>
										<td width="15%" valign="top"><b><s:message code="template00.newPostCommentForm.contentText.comment.LABEL"/></b></td>
										<td width="85%" valign="top">
											<form:textarea path="postCommentContent" cols="50" rows="15"/>
											<s:bind path="postCommentContent">
												<c:if test="${status.error}">
													<br/><form:errors path="postCommentContent" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
												</c:if>
											</s:bind>
										</td>
									</tr>
									<tr height="35px">
										<td valign="top" align="left" colspan="2" >
											<b><s:message code="template00.newPostCommentForm.contentText.givenMark.LABEL"/>&nbsp;</b>
											<select name="postCommnetMark">
												<option value="0">Mark 0</option>
												<option value="1" selected>Mark 1</option>
												<option value="2">Mark 2</option>
												<option value="3">Mark 3</option>
												<option value="4">Mark 4</option>
												<option value="5">Mark 5</option>
											</select>
											<input type="submit" value="<s:message code="template00.newPostCommentForm.submitButton.LABEL"/>" style="height: 2em; width: 6em"/>
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
