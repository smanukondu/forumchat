<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<div class="index"><h1>Please Login at Here:</h1></div>
			<hr/>
		</td>
	</tr>
	<tr valign="top">
		<td>
			<c:if test="${not empty error}">
				<span style="font-family:arial;font-weight:bold;color:red;font-size:12px;">
					<c:out value="${error}"/>
				</span>
			</c:if>
			<form action="<c:url value="/j_spring_security_check" />" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<table style="width: 380;">
					<tr>
						<td width="25%" align="left" valign="top">
							<b>username:</b>
						</td>
						<td width="75%" align="left" valign="top">
							<input name="username" type="text" size="30" maxlength="30"/>
						</td>
					</tr>
					<tr>
						<td width="25%" align="left" valign="top">
							<b>password:</b>
						</td>
						<td width="75%" align="left" valign="top">
							<input name="password" type="password" size="30" maxlength="30"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<table style="width: 380;">
								<tr>
									<td align="left" width="50%">
										<input type="submit" style="height: 2em; width: 6em" value="Submit"/>
									</td>
									<td align="left" width="50%">
										<input type="reset" style="height: 2em; width: 6em" value="Reset"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
			<c:if test="${not empty msg}">
				<span style="font-family:arial;font-weight:bold;color:blue;font-size:12px;">
					<c:out value="${msg}"/>
				</span>
			</c:if>
		</td>
	</tr>
</table>
