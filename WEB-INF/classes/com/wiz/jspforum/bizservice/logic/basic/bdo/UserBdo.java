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

package com.wiz.jspforum.bizservice.logic.basic.bdo;

import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.web.basic.bean.form.UserProfileForm;
import com.wiz.jspforum.web.basic.bean.form.UserRegisterForm;

/**
 * The User Entity BDO is assigned as interface to handle with all the operations to the business logic action
 */
public interface UserBdo extends AbstractBdoInterface {
	/**
	 * Get the user detail by user name
	 * 
	 * @param username the user name
	 * 
	 * @return the user profile detail
	 */
	UserProfile getUserDetailsByUsername(String username);
	/**
	 * Get the user detail by userId
	 * 
	 * @param userId the user Id
	 * 
	 * @return the user profile detail
	 */
	UserProfile getUserDetailsByUserId(int userId);
	/**
	 * Update the User's Login Status
	 * 
	 * @param user the user entity
	 * @param newStatus the new Status value
	 * 
	 * @return true
	 */
	boolean updateUserLoginStatus(UserProfile user, int newStatus);
	/**
	 * Add the New User
	 * 
	 * @param userRegisterForm the user Register Form
	 * 
	 * @return true
	 */
	boolean addNewUser(UserRegisterForm userRegisterForm);
	/**
	 * Change the User Profile Detail
	 * 
	 * @param user the user entity
	 * @param userProfileForm the user Profile Form
	 * 
	 * @return true
	 */
	boolean changeUserProfileDetail(UserProfile user, UserProfileForm userProfileForm);
	/**
	 * Fetch the encrypted Password as text
	 * 
	 * @param password the raw password text
	 * 
	 * @return the encrypted Password
	 */
	String fetchEncryptedPassword(String password);
	/**
	 * Load the user detail by user name for friend
	 * 
	 * @param friendUserId the friend UserId
	 * 
	 * @return the friend profile detail
	 */
	UserProfile loadFriendUserProfileDetailByUserId(int friendUserId);
}
