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

package com.wiz.jspforum.persistence.basic.data.dao;

import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;

/**
 * The DAO interface for the user entity operation associated with Database
 */
public interface UserDao extends AbstractDaoInterface {
	/**
	 * Depend on the given UserName to fetch the user profile object from the User Entity
	 * 
	 * @param username the given UserName
	 * 
	 * @return the single record of user profile
	 */
	UserProfile getUserProfileRecordByUserName(String username);
	/**
	 * Add a new user entity object into FORUM_USER_PROFILE 
	 * 
	 * @param userDetail the user profile detail entity
	 * 
	 * @return true
	 */
	boolean insertUserProfileRecord(UserProfile userDetail);
	/**
	 * Depend on the given UserId to fetch the user profile object from the User Entity
	 * 
	 * @param userId the given UserId
	 * 
	 * @return the single record of user profile
	 */
	UserProfile getUserProfileRecordByUserId(int userId);
	/**
	 * Update a existed user entity object in the FORUM_USER_PROFILE 
	 * 
	 * @param userDetail the user profile detail entity
	 * 
	 * @return true
	 */
	boolean updateUserProfileRecord(UserProfile userDetail);
	/**
	 * Fetch encrypted Password From Database
	 * 
	 * @param password the raw password
	 * 
	 * @return the encrypted Password text
	 */
	String selectEncryptedPasswordFromDatabase(String password);
	/**
	 * Update the Login Status of the User Entity 
	 * 
	 * @param userDetail including the new login status of user
	 * 
	 * @return true
	 */
	boolean updateUserProfileRecordForLoginStatus(UserProfile userDetail);
}
