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

package com.wiz.jspforum.bizservice.logic.basic.bdoImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wiz.jspforum.bizservice.logic.basic.bdo.AbstractBdoSupport;
import com.wiz.jspforum.bizservice.logic.basic.bdo.SearchBdo;
import com.wiz.jspforum.persistence.basic.data.dao.MessageDao;
import com.wiz.jspforum.persistence.basic.data.dao.PostDao;
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.form.MessageSearchForm;
import com.wiz.jspforum.web.basic.bean.form.PostSearchForm;

/**
 * The Implement Class of SearchBdo
 */
@Service("searchBdo")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class SearchBdoImple extends AbstractBdoSupport implements SearchBdo {

	private MessageDao messageDao = null;
	private PostDao postDao = null;

	public SearchBdoImple() {
		
	}

	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Autowired
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public List<UserMessage> getMessageListByUserWithUnread(UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getMessageListByUserWithUnread(user)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + user.getUserId());
		return messageDao.getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc(user.getUserId(), UserMessage.CONSTANT_MESSAGE_READ_FLAG_NOT_YET);
	}

	@Override
	public UserPost searchForPostByPostId(int postId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.searchForPostByPostId(postId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "postId = " + postId);
		return postDao.getUserPostRecordByPostId(postId);
	}

	@Override
	public List<UserPost> processLogicOfSearchingForPostsWithConditions(PostSearchForm postSearchForm) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfSearchingForPostsWithConditions(postSearchForm)");
		HashMap<String, Object> paramHash = new HashMap<String, Object>();
		paramHash.put("postSearchForm", postSearchForm);
		List<UserPost> postList = postDao.getUserPostRecordsWithConditions(paramHash);
		return postList;
	}

	@Override
	public List<UserMessage> processLogicOfSearchingForInBoxMessageWithConditions(MessageSearchForm messageSearchForm, UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfSearchingForInBoxMessageWithConditions(messageSearchForm,user)");
		HashMap<String, Object> paramHash = new HashMap<String, Object>();
		paramHash.put("isInBoxMessage", true);
		paramHash.put("messageSearchForm", messageSearchForm);
		paramHash.put("user", user);
		List<UserMessage> messageList = messageDao.getUserMessageRecordsWithConditions(paramHash);
		return messageList;
	}

	@Override
	public List<UserMessage> processLogicOfSearchingForOutBoxMessageWithConditions(MessageSearchForm messageSearchForm, UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfSearchingForOutBoxMessageWithConditions(messageSearchForm,user)");
		HashMap<String, Object> paramHash = new HashMap<String, Object>();
		paramHash.put("isInBoxMessage", false);
		paramHash.put("messageSearchForm", messageSearchForm);
		paramHash.put("user", user);
		List<UserMessage> messageList = messageDao.getUserMessageRecordsWithConditions(paramHash);
		return messageList;
	}

	@Override
	public List<UserPost> searchForPostsFromFriends(UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.searchForPostsFromFriends(user)");
		toLog(CommonLog.DEBUG, getClass().getName(), "user = " + user);
		return postDao.getUserPostRecordsFromFriends(user.getUserId());
	}

	@Override
	public List<UserPost> searchForPostsFromMine(UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.searchForPostsFromMine(user)");
		toLog(CommonLog.DEBUG, getClass().getName(), "user = " + user);
		return postDao.getUserPostRecordsFromMine(user.getUserId());
	}

	@Override
	public void setSessionLogger(SimpleLog logger) {
		super.logger = logger;
	}

	@Override
	public void setSessionLoggerToDaos(SimpleLog logger) {
		messageDao.setUserLogInstance(logger);
		postDao.setUserLogInstance(logger);
	}
}
