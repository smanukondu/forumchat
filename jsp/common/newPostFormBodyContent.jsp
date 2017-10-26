<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<script src="<c:url value="/resources/js/SimpleBBCode.js"/>"></script>
<script type="text/javascript">
	var fileCount = 0;
	function appendNewFileAttachment() {
		if (fileCount < 2) {
			var div = document.getElementById("multiple_files_area");
			div.innerHTML += "<div id='attachedFile_" + fileCount + "'><br/><input type='file' name='formFiles[" + (fileCount + 1) + "]' size='35' value=''/></div>";
			fileCount++;
		} else {
			alert("Can't exceed the maximum of attachable files (3)!");
		}
	}
	function removeFileAttachmentItem() {
		if (fileCount >= 1) {
			var divParent = document.getElementById("multiple_files_area");
			var divChild = document.getElementById("attachedFile_" + (fileCount - 1));
			divParent.removeChild(divChild);
			fileCount--;
		} else {
			alert("At least 1 file attachment item remained in a Post!");
		}
	}
</script>
<table>
	<tr height="350" valign="top">
		<td>
			<div class="index">
				<table>
					<tr>
						<td>
							<div class="index"><h1><s:message code="template00.newPostForm.contentInstTitle.LABEL"/></h1></div>
							<hr/>
						</td>
					</tr>
					<tr valign="top">
						<td>
							<c:url var="actionUrl" value="userpostform?${_csrf.parameterName}=${_csrf.token}"/>
							<form:form action="${actionUrl}" name="userPostForm" modelAttribute="userPostForm" method="post" enctype="multipart/form-data">
								<table>
									<tr>
										<td width="90" valign="top">
											<form:label path="postTopic"><b><s:message code="template00.newPostForm.contentText.topic.LABEL"/></b></form:label>
										</td>
										<td width="260" valign="top">
											<s:message code="template00.newPostForm.contentText.giveYourPostTopic.LABEL"/><br/>
											<form:input path="postTopic" size="50" maxlength="50"/>
											<s:bind path="postTopic">
												<c:if test="${status.error}">
													<br/><form:errors path="postTopic" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/>
												</c:if>
											</s:bind>
										</td>
									</tr>
									<tr>
										<td width="90" valign="top">
											<form:label path="postContent"><b><s:message code="template00.newPostForm.contentText.content.LABEL"/></b></form:label>
										</td>
										<td width="260" valign="top">
											<s:message code="template00.newPostForm.contentText.articleContentArea.LABEL"/><br/>
											<b>Supported BBCodes:</b><br/>
											<input type="button" id="bbcode_0" value="[b]" onclick="insertBBCode(0)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_2" value="[i]" onclick="insertBBCode(2)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_4" value="[u]" onclick="insertBBCode(4)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_6" value="[s]" onclick="insertBBCode(6)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_8" value="[br]" onclick="insertBBCode(8)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_16" value="[:)]" onclick="insertBBCode(16)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_18" value="[:(]" onclick="insertBBCode(18)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_10" value="[url]" onclick="insertBBCode(10)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_12" value="[img]" onclick="insertBBCode(12)" style="font-family: consolas; font-size:10px; width:55px"/> | 
											<input type="button" id="bbcode_14" value="[color]" onclick="insertBBCode(14)" style="font-family: consolas; font-size:10px; width:55px"/>
											<select id="ddl_color_selector">
												<option style="color: black; background-color: #fafafa" value="black" selected="selected">black</option>
												<option style="color: darkred; background-color: #fafafa" value="darkred">darkred</option>
												<option style="color: red; background-color: #fafafa" value="red">red</option>
												<option style="color: orange; background-color: #fafafa" value="orange">orange</option>
												<option style="color: brown; background-color: #fafafa" value="brown">brown</option>
												<option style="color: yellow; background-color: #fafafa" value="yellow">yellow</option>
												<option style="color: green; background-color: #fafafa" value="green">green</option>
												<option style="color: olive; background-color: #fafafa" value="olive">olive</option>
												<option style="color: cyan; background-color: #fafafa" value="cyan">cyan</option>
												<option style="color: blue; background-color: #fafafa" value="blue">blue</option>
												<option style="color: darkblue; background-color: #fafafa" value="darkblue">dark blue</option>
												<option style="color: violet; background-color: #fafafa" value="violet">violet</option>
											</select>
											<form:textarea path="postContent" cols="60" rows="30"/>
											<s:bind path="postContent">
												<c:if test="${status.error}">
													<br/><form:errors path="postContent" cssStyle="font-family:arial;font-weight:bold;color:red;font-size:8px;"/><br/>
												</c:if>
											</s:bind>
											<font color="brown" size="1" style="text-decoration: underline;"><b><s:message code="template00.newPostForm.contentText.youCanAddHTMLElement.LABEL"/></b></font>
										</td>
									</tr>
									<tr>
										<td width="90" valign="top"><b><s:message code="template00.newPostForm.contentText.attachment.LABEL"/></b></td>
										<td width="260" valign="top">
											<s:message code="template00.newPostForm.contentText.addAnImageAttachment.LABEL"/><br/>
											<div id="multiple_files_area">
												<input id="attachedFile" type="file" name="formFiles[0]" size="35" value=""/>
											</div>
										</td>
									</tr>
									<tr height="35px">
										<td colspan="2" align="center">
											<table>
												<tr>
													<td align="left" width="25%">
														<input type="submit" value="<s:message code="template00.newPostForm.submitButton.LABEL"/>" style="height: 2em; width: 6em"/>
													</td>
													<td align="left" width="25%">
														<input type="reset" value="<s:message code="template00.newPostForm.resetButton.LABEL"/>" style="height: 2em; width: 6em"/>
													</td>
													<td align="left" width="25%">
														<input type="button" value="<s:message code="template00.newPostForm.moreFilesButton.LABEL"/>" style="height: 2em; width: 7em" onclick="appendNewFileAttachment();"/>
													</td>
													<td align="left" width="25%">
														<input type="button" value="<s:message code="template00.newPostForm.removeFilesButton.LABEL"/>" style="height: 2em; width: 9em" onclick="removeFileAttachmentItem();"/>
													</td>
												</tr>
											</table>
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