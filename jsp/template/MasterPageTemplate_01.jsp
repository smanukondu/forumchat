<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/2000/REC-xhtml1-200000126/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
		<link rel="shortcut icon" href="<c:url value="/resources/favicon.ico"/>"/>
		<title><tiles:getAsString name="PageTitle" ignore="true"/></title>
	</head>
	<body bgcolor="#CCCCCC">
		<table align="center" style="border-top: 2px outset #999999; border-left: 2px outset #999999; border-right: 2px outset #999999; border-bottom: 2px outset #999999;">
			<tr>
				<td>
					<table width="970" align="center" bgcolor="#FFFFFF">
						<tr>
							<td style="border-bottom: 2px solid #CCCCCC;">
								<!-- page header for JSP Forum -->
								<table width="970">
									<tr>
										<td align="left">
											<img id="headerImage" src="<c:url value="/resources/images/logo.gif"/>" border="0" width="150px" height="80px"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellspacing="0" cellpadding="12">
									<tr valign="top" style="height: 1020px;">
										<td width="135" valign="top">
											<!-- left menu bar for Forum Menu -->
											<tiles:insertAttribute name="LeftMenuBar"/>
										</td>
										<td style="border-left: 1px solid #CCCCCC; border-right: 1px solid #CCCCCC" width="700">
											<!-- page body content for Forum Menu -->
											<tiles:insertAttribute name="BodyContent"/>
										</td>
										<td width="135" valign="top" >
											<!-- right menu bar for Forum Menu -->
											<table width="100%" cellspacing="0" cellpadding="4">
												<tr>
													<td style="border-bottom: 1px solid #CCCCCC" height="20px">
														<div class="index"><h1><spring:message code="template01.rightMenubar.LINKS"/></h1></div>
													</td>
												</tr>
												<tr>
													<td style="border-bottom: 1px solid #CCCCCC" height="40px">
														<div class="index"><a href="https://www.google.com" rel="nofollow">Google.com</a></div>
													</td>
												</tr>
												<tr>
													<td style="border-bottom: 1px solid #CCCCCC" height="40px">
														<div class="index"><a href="http://www.yahoo.com" rel="nofollow">Yahoo.com</a></div>
													</td>
												</tr>
												<tr>
													<td style="border-bottom: 1px solid #CCCCCC" height="40px">
														<div class="index"><a href="http://www.msn.com" rel="nofollow">MSN.com</a></div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center" style="border-top: 1px solid #CCCCCC;" height="30px">
								<div class="index">
									<span id="footer"><a href="#" style="font-size:12px">HELP</a>&nbsp;|&nbsp;<a href="#" style="font-size:12px">ABOUT US</a>&nbsp;|&nbsp;COPYRIGHT&copy;WIZ.COM 2012</span>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>