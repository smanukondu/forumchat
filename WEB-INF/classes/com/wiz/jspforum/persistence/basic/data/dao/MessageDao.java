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

import java.util.HashMap;
import java.util.List;

import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;

/**
 * The Message Entity DAO is assigned as interface to handle with all the operations to the data record in database
 */
public interface MessageDao extends AbstractDaoInterface {
	/**
	 * Get the user message record By Primary Key of FORUM_MESSAGE
	 * 
	 * @param messageId the primary key
	 * 
	 * @return the single record of user message
	 */
	UserMessage getUserMessageRecordByMessageId(int messageId);
	/**
	 * Update the fields of FORUM_MESSAGE record base on the input DTO
	 * 
	 * @param message the DTO of user message entity
	 * 
	 * @return true
	 */
	boolean updateUserMessageRecordForReadFlag(UserMessage message);
	/**
	 * Insert a new record to FORUM_MESSAGE base on the input DTO
	 * 
	 * @param message the DTO of user message entity
	 * 
	 * @return true
	 */
	boolean insertUserMessageRecord(UserMessage message);
	/**
	 * Get the history of user message records Between two friends and record set order By MessageTimeDesc
	 * 
	 * @param userId the user identity
	 * @param friendId the friend user identity
	 * 
	 * @return the result of user message list
	 */
	List<UserMessage> getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc(int userId, int friendId);
	/**
	 * Get user message records By DestUserId and record set order By MessageTimeDesc
	 * 
	 * @param destUserId the destination user identity
	 * 
	 * @return the result of user message list
	 */
	List<UserMessage> getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc(int destUserId);
	/**
	 * Get user message records By SourceUserId and record set order By MessageTimeDesc
	 * 
	 * @param sourceUserId the source user identity
	 * 
	 * @return the result of user message list
	 */
	List<UserMessage> getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc(int sourceUserId);
	/**
	 * Get user message records By UserId and ReadFlag value and record set order By MessageTimeDesc
	 * 
	 * @param userId the user identity
	 * @param readFlag the readFlag value
	 * 
	 * @return the result of user message list
	 */
	List<UserMessage> getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc(int userId, int readFlag);
	/**
	 * Get user message records By some condition criteria
	 * 
	 * @param conditions the conditions criteria
	 * 
	 * @return the result of user message list
	 */
	List<UserMessage> getUserMessageRecordsWithConditions(HashMap<String, Object> conditions);
	/**
	 * Get all the unread message records for all the users
	 * 
	 * @return the result of user message list
	 */
	List<UserMessage> getAllUnreadMessagesForAllUsers();
}
