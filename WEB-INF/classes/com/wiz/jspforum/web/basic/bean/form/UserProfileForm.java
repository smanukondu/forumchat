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

import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;

/**
 * The form bean of User Profile Submit
 */
public class UserProfileForm {
	
	private int id = 0;

	private String username = null;

	@NotEmpty
	@Size(min=6, max=20)
	@Pattern(regexp="^[a-z0-9_-]{6,20}$")
	private String password = null;

	@NotEmpty
	@Size(min=6, max=20)
	@Pattern(regexp="^[a-z0-9_-]{6,20}$")
	private String alias = null;

	@Email
	private String email = null;

	private String type = null;

	private int level = 0;

	@NotEmpty
	@Size(min=1, max=175)
	private String signature = null;

	private String avatarPIC = null;

	private String avatarPICValue = null;

	private Date registerDate = null;

	/**
     * Construct empty default object.
     */
	public UserProfileForm() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAvatarPIC() {
		this.avatarPIC = "avatar_" + getAvatarPICValue() + ".jpg";
		return this.avatarPIC;
	}

	public void setAvatarPIC(String avatarPIC) {
		this.avatarPIC = avatarPIC;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getAvatarPICValue() {
		return avatarPICValue;
	}

	public void setAvatarPICValue(String avatarPICValue) {
		this.avatarPICValue = avatarPICValue;
	}
	
	public void copyFrom(UserProfile user) {
		setId(user.getUserId());
		setUsername(user.getUserName());
		setPassword(user.getUserPassword());
		setAlias(user.getUserAlias());
		setEmail(user.getUserEmail());
		setSignature(user.getUserSignature());
		setAvatarPICValue(user.getUserAvatarPic().substring(7, 8));
	}
}
