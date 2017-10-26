/*
 * Licensed to the WIZ under one or more contributor license agreements. 
 * The WIZ licenses this file to You under the WIZ License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *     http://www.wiz.com/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.  For additional information regarding 
 * copyright in this work, please see the NOTICE file in the top level 
 * directory of this distribution.
 */

package com.wiz.jspforum.web.basic.bean.form;

import java.util.Date;

import com.wiz.jspforum.util.date.DateSimpleFormat;

/**
 * The form bean of Search in Message IN Submit
 */
public class MessageSearchForm {
	
	private int messageId = 0;
	private Date fromMessageDate = null;
	private Date toMessageDate = null;
	private String friendUserName = null;
	private String messageContent = null;
	private String fromMessageDateStr = null;
	private String toMessageDateStr = null;
	
	/**
     * Construct empty default object.
     */
	public MessageSearchForm() {
		super();
	}
	
	public int getMessageId() {
		return messageId;
	}
	
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	public Date getFromMessageDate() {
		if (fromMessageDateStr != null && !fromMessageDateStr.equals("")) {
			fromMessageDate = DateSimpleFormat.simpleFormate(fromMessageDateStr);
		}
		return fromMessageDate;
	}
	
	public void setFromMsgDate(Date fromMessageDate) {
		this.fromMessageDate = fromMessageDate;
		this.fromMessageDateStr = DateSimpleFormat.simpleFormate(fromMessageDate);
	}
	
	public Date getToMessageDate() {
		if (toMessageDateStr != null && !toMessageDateStr.equals("")) {
			toMessageDate = DateSimpleFormat.simpleFormate(toMessageDateStr);
		}
		return toMessageDate;
	}
	
	public void setToMessageDate(Date toMessageDate) {
		this.toMessageDate = toMessageDate;
		this.toMessageDateStr = DateSimpleFormat.simpleFormate(toMessageDate);
	}
	
	public String getFriendUserName() {
		return friendUserName;
	}
	
	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}
	
	public String getFriendUserNameUpperCase() {
		return (friendUserName != null) ? friendUserName.toUpperCase() : "";
	}
	
	public String getMessageContent() {
		return messageContent;
	}
	
	public String getMessageContentUpperCase() {
		return (messageContent != null) ? messageContent.toUpperCase() : "";
	}
	
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getFromMessageDateStr() {
		return fromMessageDateStr;
	}

	public void setFromMessageDateStr(String fromMsgInDateStr) {
		this.fromMessageDateStr = fromMsgInDateStr;
	}

	public String getToMessageDateStr() {
		return toMessageDateStr;
	}

	public void setToMessageDateStr(String toMessageDateStr) {
		this.toMessageDateStr = toMessageDateStr;
	}
}
