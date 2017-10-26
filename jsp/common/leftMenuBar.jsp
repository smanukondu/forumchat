<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style_jquery_flip.css"/>"/>
<script src="<c:url value="/resources/js/jquery.flip.min.js"/>"></script>
<script type="text/javascript">
	$(function() {
    	$(".flip-horizontal").flip({
  			trigger: 'hover'
		});
    	$(".flip-vertical").flip({
			axis: 'x',
  			trigger: 'hover'
		});
	});
</script>
<table style="width: 100%; padding: 4;">
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="20px">
			<div class="index"><strong><s:message code="template00.leftMenubarTitle.LABEL"/></strong></div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div id="msgInNewCount" class="index">
				<a href="<c:url value="/message/common/loadinboxmessages"/>">
					<s:message code="template00.leftMenubarItem.messageIn.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/message/common/loadoutboxmessages"/>">
					<s:message code="template00.leftMenubarItem.messageOut.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/post/common/loadallposts"/>">
					<s:message code="template00.leftMenubarItem.postZone.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/user/common/userprofileform"/>">
					<s:message code="template00.leftMenubarItem.myProfile.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div id="requestInNewFriend" class="index">
				<a href="<c:url value="/friend/common/loadfriendjointrequestlist"/>">
					<s:message code="template00.leftMenubarItem.friends.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<a href="<c:url value="/user/common/logout"/>">
					<s:message code="template00.leftMenubarItem.signOut.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" align="left">
			<div class="index">
				<div id="flip-this" class="flip-horizontal">
					<div class="front">
						<img src="<c:url value="/resources/images/${sessionScope.USER_CONFIG.userAvatarPic}"/>" width="70px" height="70px" border="0"/>
					</div>
					<div class="back">
						<img src="<c:url value="/resources/images/avatar_back.jpg"/>" width="70px" height="70px" border="0"/>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>