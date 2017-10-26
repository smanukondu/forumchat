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
 * The DTO model of storing the information of Friend Request
 * Which be mapped with Hibernate Entity [FriendJointRequest.hbm.xml] or myBatis Entity [FriendMapper.xml]
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FORUM_FRIEND_JOINT_REQUEST")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class FriendJointRequest implements Serializable {

	public final static String FRIEND_JOINT_REQUEST_STATUS_APPROVED = "Approved";
	public final static String FRIEND_JOINT_REQUEST_STATUS_REJECTED = "Rejected";
	public final static String FRIEND_JOINT_REQUEST_STATUS_PENDING  = "Pending";

	private int friendJointRequestId = 0;
	private String friendJointRequestRemark = null;
	private Date friendJointRequestDate = null;
	private String friendJointRequestStatus = null;
	private UserProfile friendJointRequestFromUser = null;
	private UserProfile friendJointRequestToUser = null;

	/**
     * Construct empty default object.
     */
	public FriendJointRequest() {
		
	}

	public String toString() {
		return "joinFriendRequestId = " + friendJointRequestId + ", joinFriendRequestRemark = " + friendJointRequestRemark + ", joinFriendRequestDate = " + friendJointRequestDate + ", joinFriendRequestStatus = " + friendJointRequestStatus + " | joinFriendRequestFromUser.userId = " + ((friendJointRequestFromUser != null) ? friendJointRequestFromUser.getUserId() : "null") + ", joinFriendRequestToUser.userId = " + ((friendJointRequestToUser != null) ? friendJointRequestToUser.getUserId() : "null");
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="FRIEND_JOINT_REQUEST_ID")
	public int getFriendJointRequestId() {
		return friendJointRequestId;
	}

	public void setFriendJointRequestId(int friendJointRequestId) {
		this.friendJointRequestId = friendJointRequestId;
	}

	@Column(name="FRIEND_JOINT_REQUEST_REMARK")
	public String getFriendJointRequestRemark() {
		return friendJointRequestRemark;
	}

	public void setFriendJointRequestRemark(String friendJointRequestRemark) {
		this.friendJointRequestRemark = friendJointRequestRemark;
	}

	@Column(name="FRIEND_JOINT_REQUEST_DATE")
	public Date getFriendJointRequestDate() {
		return friendJointRequestDate;
	}

	public void setFriendJointRequestDate(Date friendJointRequestDate) {
		this.friendJointRequestDate = friendJointRequestDate;
	}

	@Column(name="FRIEND_JOINT_REQUEST_STATUS")
	public String getFriendJointRequestStatus() {
		return friendJointRequestStatus;
	}

	public void setFriendJointRequestStatus(String friendJointRequestStatus) {
		this.friendJointRequestStatus = friendJointRequestStatus;
	}

	@ManyToOne
	@JoinColumn(name="FRIEND_JOINT_REQUEST_FROM_USER_ID", nullable=false)
	public UserProfile getFriendJointRequestFromUser() {
		return friendJointRequestFromUser;
	}

	public void setFriendJointRequestFromUser(UserProfile friendJointRequestFromUser) {
		this.friendJointRequestFromUser = friendJointRequestFromUser;
	}

	@ManyToOne
	@JoinColumn(name="FRIEND_JOINT_REQUEST_TO_USER_ID", nullable=false)
	public UserProfile getFriendJointRequestToUser() {
		return friendJointRequestToUser;
	}

	public void setFriendJointRequestToUser(UserProfile friendJointRequestToUser) {
		this.friendJointRequestToUser = friendJointRequestToUser;
	}
}
