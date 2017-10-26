<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index"><h1><s:message code="template00.friendProfileDetail.contentInstTitle.LABEL"/></h1></div>
			<hr/>
		</td>
	</tr>
	<tr valign="top">
		<td>
			<table style="width: 400;">
				<tr>
					<td width="15%"><b><s:message code="template00.friendProfileDetail.userName.LABEL"/></b></td>
					<td width="85%">
						<c:out value="${USER_FRIEND_DETAIL.userName}"/>
					</td>
				</tr>
				<tr>
					<td width="15%"><b><s:message code="template00.friendProfileDetail.alias.LABEL"/></b></td>
					<td width="85%">
						<c:out value="${USER_FRIEND_DETAIL.userAlias}"/>
					</td>
				</tr>
				<tr>
					<td width="15%"><b><s:message code="template00.friendProfileDetail.eMail.LABEL"/></b></td>
					<td width="85%">
						<c:out value="${USER_FRIEND_DETAIL.userEmail}"/>
					</td>
				</tr>
				<tr>
					<td width="15%" valign="top"><b><s:message code="template00.friendProfileDetail.signature.LABEL"/></b></td>
					<td width="85%" valign="top">
						<c:out value="${USER_FRIEND_DETAIL.userSignature}"/>
					</td>
				</tr>
				<tr>
					<td width="15%" valign="top"><b><s:message code="template00.friendProfileDetail.userType.LABEL"/></b></td>
					<td width="85%" valign="top">
						<c:out value="${USER_FRIEND_DETAIL.userType}"/>
					</td>
				</tr>
				<tr>
					<td width="15%" valign="top"><b><s:message code="template00.friendProfileDetail.userRegisterDate.LABEL"/></b></td>
					<td width="85%" valign="top">
						<c:out value="${USER_FRIEND_DETAIL.userRegisterDate}"/>
					</td>
				</tr>
				<tr height="35px">
					<td width="15%" valign="top"><b><s:message code="template00.friendProfileDetail.avatarPICture.LABEL"/></b></td>
					<td width="85%" valign="top">
						<img src="<c:url value="/resources/images/${USER_FRIEND_DETAIL.userAvatarPic}"/>" width="35px" height="35px" border="1"/>
					</td>
				</tr>
			</table>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<!-- For login user -->
				<form action="<c:url value="/j_spring_security_logout"/>" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<p>User : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()">Logout</a> from Spring Security ...</p>
				</c:if>
			</sec:authorize>
		</td>
	</tr>
</table>