<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 4;">
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="20px">
			<div class="index"><strong><s:message code="template00.searchForPostResult.rightMenubarTitle.LABEL"/></strong></div>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: 1px solid #CCCCCC" height="40px">
			<div class="index">
				<!-- search/postSearchForm -->
				<a href="<c:url value="#"/>">
					<s:message code="template00.searchForPostResult.rightMenubarItem.back.LABEL"/>
				</a>
			</div>
		</td>
	</tr>
</table>