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

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The form bean of User Register Submit
 */
public class UserRegisterForm {

	private int userId = 0;

	@NotEmpty
	@Size(min = 6, max = 20)
	@Pattern(regexp = "^[a-z0-9_-]{6,20}$")
	private String userName = null;

	@NotEmpty
	@Size(min = 6, max = 20)
	@Pattern(regexp = "^[a-z0-9_-]{6,20}$")
	private String userPassword = null;

	private String userConfirmedPassword = null;

	@NotEmpty
	@Size(min = 6, max = 20)
	@Pattern(regexp = "^[a-z0-9_-]{6,20}$")
	private String userAlias = null;

	@Email
	private String userEmail = null;
	
	private String userType = null;
	
	private int userLevel = 0;
	
	@NotEmpty
	@Size(min=1, max=175)
	private String userSignature = null;
	
	private String userAvatarPic = null;
	
	private String userAvatarPicValue = null;
	
	private Date userRegisterDate = null;
	
	/**
     * Construct empty default object.
     */
	public UserRegisterForm() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserAlias() {
		return userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	public String getUserAvatarPic() {
		userAvatarPic = "avatar_" + getUserAvatarPicValue() + ".jpg";
		return userAvatarPic;
	}

	public void setUserAvatarPic(String userAvatarPic) {
		this.userAvatarPic = userAvatarPic;
	}

	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	public String getUserConfirmedPassword() {
		return userConfirmedPassword;
	}

	public void setUserConfirmedPassword(String userConfirmedPassword) {
		this.userConfirmedPassword = userConfirmedPassword;
	}

	public String getUserAvatarPicValue() {
		return userAvatarPicValue;
	}

	public void setUserAvatarPicValue(String userAvatarPicValue) {
		this.userAvatarPicValue = userAvatarPicValue;
	}	
}
