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
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.web.basic.bean.form.MessageSearchForm;
import com.wiz.jspforum.web.basic.bean.form.PostSearchForm;

/**
 * The Search Entity BDO is assigned as interface to handle with all the operations to the business logic action
 */
public interface SearchBdo extends AbstractBdoInterface {
	/**
	 * Get the unread message list for the user
	 * 
	 * @param user the user entity
	 * 
	 * @return the result of message list
	 */
	List<UserMessage> getMessageListByUserWithUnread(UserProfile user);
	/**
	 * Search for the Post By PostId
	 * 
	 * @param postId the post Id
	 * 
	 * @return the post entity
	 */
	UserPost searchForPostByPostId(int postId);
	/**
	 * Process the logic of searching For Posts With specified Conditions
	 * 
	 * @param postSearchForm the post search submit form
	 * 
	 * @return the post list
	 */
	List<UserPost> processLogicOfSearchingForPostsWithConditions(PostSearchForm postSearchForm);
	/**
	 * Process the logic of searching For Messages IN With specified Conditions
	 * 
	 * @param messageSearchForm the message IN search submit Form
	 * @param user the user entity
	 * 
	 * @return the message list
	 */
	List<UserMessage> processLogicOfSearchingForInBoxMessageWithConditions(MessageSearchForm messageSearchForm, UserProfile user);
	/**
	 * Process the logic of searching For Messages OUT With specified Conditions
	 * 
	 * @param messageSearchForm the message OUT search submit Form
	 * @param user the user entity
	 * 
	 * @return the message list
	 */
	List<UserMessage> processLogicOfSearchingForOutBoxMessageWithConditions(MessageSearchForm messageSearchForm, UserProfile user);
	/**
	 * Search for the Post List from the Friends
	 * 
	 * @param user the user profile entity
	 * 
	 * @return the post list
	 */
	List<UserPost> searchForPostsFromFriends(UserProfile user);
	/**
	 * Search for the Post List from mine
	 * 
	 * @param user the user profile entity
	 * 
	 * @return the post list
	 */
	List<UserPost> searchForPostsFromMine(UserProfile user);
}
