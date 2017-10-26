<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<table style="width: 100%; padding: 2;">
	<tr>
		<td>
			<span style="font-family:arial;font-weight:bold;color:red;">HTTP:500</span>
		</td>
	</tr>
	<tr>
		<td>
			<span style="font-family:arial;font-size:10px;"><c:out value="${ERROR_DETAIL}"/></span>
		</td>
	</tr>
</table>