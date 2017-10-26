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
 * The DTO model of storing the information of Friend Link
 * Which be mapped with Hibernate Entity [FriendLink.hbm.xml] or myBatis Entity [FriendMapper.xml]
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FORUM_FRIEND_LINK")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class FriendLink implements Serializable {

	private int friendLinkId = 0;
	private UserProfile friendLinkHostUser = null;
	private UserProfile friendLinkCustUser = null;
	private Date friendLinkMappedDate = null;

	/**
     * Construct empty default object.
     */
	public FriendLink() {
		
	}

	public String toString() {
		return "friendLinkId = " + friendLinkId + ", friendLinkMappedDate = " + friendLinkMappedDate + " | friendLinkHostUser.userId = " + ((friendLinkHostUser != null) ? friendLinkHostUser.getUserId() : "null") + ", friendLinkCustUser.userId = " + ((friendLinkCustUser != null) ? friendLinkCustUser.getUserId() : "null");
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="FRIEND_LINK_ID")
	public int getFriendLinkId() {
		return friendLinkId;
	}

	public void setFriendLinkId(int friendLinkId) {
		this.friendLinkId = friendLinkId;
	}

	@ManyToOne
	@JoinColumn(name="FRIEND_LINK_HOST_USER_ID", nullable=false)
	public UserProfile getFriendLinkHostUser() {
		return friendLinkHostUser;
	}

	public void setFriendLinkHostUser(UserProfile friendLinkHostUser) {
		this.friendLinkHostUser = friendLinkHostUser;
	}

	@ManyToOne
	@JoinColumn(name="FRIEND_LINK_CUST_USER_ID", nullable=false)
	public UserProfile getFriendLinkCustUser() {
		return friendLinkCustUser;
	}

	public void setFriendLinkCustUser(UserProfile friendLinkCustUser) {
		this.friendLinkCustUser = friendLinkCustUser;
	}

	@Column(name="FRIEND_LINK_MAP_DATE")
	public Date getFriendLinkMappedDate() {
		return friendLinkMappedDate;
	}

	public void setFriendLinkMappedDate(Date friendLinkMappedDate) {
		this.friendLinkMappedDate = friendLinkMappedDate;
	}
}
