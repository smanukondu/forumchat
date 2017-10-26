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

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The form bean of User Login Submit
 */
public class LoginForm {

	@NotEmpty
	@Size(min = 6, max = 20)
	@Pattern(regexp = "^[a-z0-9_-]{6,20}$")
	private String userName = null;

	@NotEmpty
	@Size(min = 6, max = 20)
	@Pattern(regexp = "^[a-z0-9_-]{6,20}$")
	private String userPassword = null;

	/**
	 * Construct empty default object.
	 */
	public LoginForm() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String toString() {
		return "userName : " + userName + ", userPassword : " + userPassword;
	}
}
