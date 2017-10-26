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

import java.util.List;

import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.web.basic.bean.form.FriendMessageForm;
import com.wiz.jspforum.web.basic.bean.form.MessageForm;

/**
 * The Message Entity BDO is assigned as interface to handle with all the operations to the business logic action
 */
public interface MessageBdo extends AbstractBdoInterface {
	/**
	 * Get the message list By DestinationUser
	 * 
	 * @param destUser the destination user entity
	 * 
	 * @return the result of message list
	 */
	List<UserMessage> getMessageListByDestinationUser(UserProfile destUser);
	/**
	 * Get the message list By SourceUser
	 * 
	 * @param srcUser the source user entity
	 * 
	 * @return the result of message list
	 */
	List<UserMessage> getMessageListBySourceUser(UserProfile srcUser);
	/**
	 * Get the message detail By messageId
	 * 
	 * @param messageId the message Id
	 * 
	 * @return the specified message detail
	 */
	UserMessage getMessageDetailByMessageId(int messageId);
	/**
	 * Set the message status as the indicated value
	 * 
	 * @param message the message entity
	 * @param newFlagStatus the new flag status
	 * 
	 * @return true
	 */
	boolean setReadFlagValueOfMessageAs(UserMessage message, int newFlagStatus);
	/**
	 * Send a new message To the Friend
	 * 
	 * @param message the message entity
	 * @param destUserId the destination User Id (friend)
	 * @param srcUserId the source User Id (user)
	 * 
	 * @return true
	 */
	boolean addNewMessageToFriend(UserMessage message, int destUserId, int srcUserId);
	/**
	 * Get the message list in the history with friend
	 * 
	 * @param user the user entity
	 * @param friend the friend entity
	 * 
	 * @return the message list of history
	 */
	List<UserMessage> getHistoryMessageListWithFriend(UserProfile user, UserProfile friend);
	/**
	 * Process the logic of sending the message to the friend
	 * 
	 * @param messageForm the message submit form
	 * 
	 * @return the message entity
	 */
	void processLogicOfSendingMessageToFriend(MessageForm messageForm);
	/**
	 * Process the logic of sending the message by the friend
	 * 
	 * @param friendMessageForm the message submit form
	 */
	void processLogicOfSendingMessageByFriend(FriendMessageForm friendMessageForm);
	/**
	 * Get all the unread message records for all the users
	 * 
	 * @return the result of user message list
	 */
	List<UserMessage> getAllUnreadMessagesForAllUsers();
}
