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

package com.wiz.jspforum.persistence.basic.data.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The DTO model of storing the information of User Message
 * Which be mapped with Hibernate Entity - [UserMessage.hbm.xml] or myBatis Entity [MessageMapper.xml]
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FORUM_MESSAGE")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserMessage implements Serializable {

	public final static int CONSTANT_MESSAGE_READ_FLAG_READ    = 1;
	public final static int CONSTANT_MESSAGE_READ_FLAG_NOT_YET = 0;

	private int messageId = 0;
	private String messageContent = null;
	private Date messageTime = null;
	private int messageReadFlag = 0;
	private UserProfile messageDestUser = null;
	private UserProfile messageSourceUser = null;

	/**
	 * Construct empty default object.
	 */
	public UserMessage() {

	}

	public String toString() {
		return "messageId = " + messageId + ", messageContent = " + messageContent + ", messageTime = " + messageTime + ", messageReadFlag = " + messageReadFlag + " | messageDestUser.userId = " + ((messageDestUser != null) ? messageDestUser.getUserId() : "null") + ", messageSourceUser.userId = " + ((messageSourceUser != null) ? messageSourceUser.getUserId() : "null");
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="MESSAGE_ID")
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	@Column(name="MESSAGE_CONTENT")
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Column(name="MESSAGE_TIME")
	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}

	@Column(name="MESSAGE_READ_FLAG")
	public int getMessageReadFlag() {
		return messageReadFlag;
	}

	public void setMessageReadFlag(int messageReadFlag) {
		this.messageReadFlag = messageReadFlag;
	}

	@ManyToOne
	@JoinColumn(name="MESSAGE_DESTINATION_USER_ID", nullable=false)
	public UserProfile getMessageDestUser() {
		return messageDestUser;
	}

	public void setMessageDestUser(UserProfile messageDestUser) {
		this.messageDestUser = messageDestUser;
	}

	@ManyToOne
	@JoinColumn(name="MESSAGE_SOURCE_USER_ID", nullable=false)
	public UserProfile getMessageSourceUser() {
		return messageSourceUser;
	}

	public void setMessageSourceUser(UserProfile messageSourceUser) {
		this.messageSourceUser = messageSourceUser;
	}
}