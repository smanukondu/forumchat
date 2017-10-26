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

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The form bean of Message Send Submit
 */
public class FriendMessageForm {
	
	private int messageId = 0;
	
	private int messageSourceUserId = 0;
	
	private int messageDestUserId = 0;
	
	@NotEmpty
	@Size(min = 1, max = 130)
	private String messageContent = null;
	
	/**
     * Construct empty default object.
     */
	public FriendMessageForm() {
		super();
	}
	
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getMessageSourceUserId() {
		return messageSourceUserId;
	}

	public void setMessageSourceUserId(int messageSourceUserId) {
		this.messageSourceUserId = messageSourceUserId;
	}

	public int getMessageDestUserId() {
		return messageDestUserId;
	}

	public void setMessageDestUserId(int messageDestUserId) {
		this.messageDestUserId = messageDestUserId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	public void reset() {
		messageId = 0;
		messageSourceUserId = 0;
		messageDestUserId = 0;
		messageContent = null;
	}
}
