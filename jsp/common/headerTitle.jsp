<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table>
	<tr>
		<td align="left" width="30%">
			<c:if test="${not empty sessionScope.USER_CONFIG}">
				<a href="<c:url value="/post/common/loadallposts"/>">
					<img src="<c:url value="/resources/images/logo.gif"/>" width="150px" height="80px" border="0"/>
				</a>
			</c:if>
			<c:if test="${empty sessionScope.USER_CONFIG}">
				<a href="<c:url value="/user/common/loginform"/>">
					<img src="<c:url value="/resources/images/logo.gif"/>" width="150px" height="80px" border="0"/>
				</a>
			</c:if>
		</td>
		<td align="right" valign="bottom" width="70%">
			Welcome!&nbsp;<b><c:out value="${sessionScope.USER_CONFIG.userName}"/></b>&nbsp;[<i><c:out value="${sessionScope.USER_CONFIG.userSignature}"/></i>]
		</td>
	</tr>
	<tr>
		<td align="right" valign="bottom" colspan="2" width="970">
			<form action="<c:url value="/search/common/searchfromlucene"/>" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<b>Search post:</b>
				<input name="keyword" type="text" size="40" maxlength="200"/>
				<input type="submit" value="Go->" style="height: 2em; width: 4em"/>
			</form>
		</td>
	</tr>
</table>