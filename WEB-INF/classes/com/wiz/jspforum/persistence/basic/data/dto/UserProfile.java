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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The DTO model of storing the information of User Profile 
 * Which be mapped with Hibernate Entity [UserProfile.hbm.xml] or myBatis Entity [UserMapper.xml]
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FORUM_USER_PROFILE")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserProfile implements Serializable {

	public final static int FLAG_OF_USER_LOGIN_STATUS_ACTIVE   = 1;
	public final static int FLAG_OF_USER_LOGIN_STATUS_INACTIVE = 0;

	public final static String SYS_USER_ROLE_TYPE_COMMON = "common";
	public final static String SYS_USER_ROLE_TYPE_ADMIN  = "admin";

	private int userId = 0;
	private String userName = null;
	private String userPassword = null;
	private String userAlias = null;
	private String userEmail = null;
	private String userType = null;
	private int userLevel = 0;
	private String userSignature = null;
	private String userAvatarPic = null;
	private Date userRegisterDate = null;
	private int userLoginStatus = 0;

	/**
     * Construct empty default object.
     */
	public UserProfile() {
		
	}

	public String toString() {
		return "userId = " + userId + ", userName = " + userName + ", userPassword = " + userPassword + ", userAlias = " + userAlias + ", userEmail = " + userEmail + ", userType = " + userType + ", userLevel = " + userLevel + ", userSignature = " + userSignature + ", userAvatarPic = " + userAvatarPic + ", userRegisterDate = " + userRegisterDate + ", userLoginStatus = " + userLoginStatus;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="USER_PASSWORD")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name="USER_ALIAS")
	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	@Column(name="USER_EMAIL")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name="USER_TYPE")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name="USER_LEVEL")
	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	@Column(name="USER_SIGNATURE")
	public String getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	@Column(name="USER_AVATAR_PIC")
	public String getUserAvatarPic() {
		return userAvatarPic;
	}

	public void setUserAvatarPic(String userAvatarPic) {
		this.userAvatarPic = userAvatarPic;
	}

	@Column(name="USER_REGISTER_DATE")
	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	@Column(name="USER_LOGIN_STATUS")
	public int getUserLoginStatus() {
		return userLoginStatus;
	}

	public void setUserLoginStatus(int userLoginStatus) {
		this.userLoginStatus = userLoginStatus;
	}
}
