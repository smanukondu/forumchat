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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wiz.jspforum.bizservice.logic.basic.bdo.AbstractBdoSupport;
import com.wiz.jspforum.bizservice.logic.basic.bdo.MessageBdo;
import com.wiz.jspforum.persistence.basic.data.dao.MessageDao;
import com.wiz.jspforum.persistence.basic.data.dao.UserDao;
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;
import com.wiz.jspforum.web.basic.bean.form.FriendMessageForm;
import com.wiz.jspforum.web.basic.bean.form.MessageForm;

/**
 * The Implement Class of MessageBdo
 */
@Service("messageBdo")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class MessageBdoImple extends AbstractBdoSupport implements MessageBdo {

	private UserDao userDao = null;
	private MessageDao messageDao = null;
	
	public MessageBdoImple() {
        
    }

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public List<UserMessage> getMessageListByDestinationUser(UserProfile destUser) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getMessageListByDestinationUser(destUser)");
		toLog(CommonLog.DEBUG, getClass().getName(), "destUser = " + destUser.getUserName());
		return messageDao.getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc(destUser.getUserId());
	}

	@Override
	public List<UserMessage> getMessageListBySourceUser(UserProfile srcUser) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getMessageListBySrcUser(srcUser)");
		toLog(CommonLog.DEBUG, getClass().getName(), "srcUser = " + srcUser.getUserName());
		return messageDao.getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc(srcUser.getUserId());
	}

	@Override
	public UserMessage getMessageDetailByMessageId(int messageId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getMessageDetailsByID(messageId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "messageId = " + messageId);
		return messageDao.getUserMessageRecordByMessageId(messageId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public boolean setReadFlagValueOfMessageAs(UserMessage message, int newFlagStatus) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.setMessageReadFlagValue(message,newFlagStatus)");
		toLog(CommonLog.DEBUG, getClass().getName(), "message = " + message.getMessageId() + ", newFlagStatus = " + newFlagStatus);
		message.setMessageReadFlag(newFlagStatus);
		messageDao.updateUserMessageRecordForReadFlag(message);
		return true;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public boolean addNewMessageToFriend(UserMessage message, int destUserId, int srcUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.addNewMessage(message,destUserId,srcUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "destUserId = " + destUserId + ", srcUserId = " + srcUserId + ", message = " + message.getMessageContent());
		message.setMessageDestUser(userDao.getUserProfileRecordByUserId(destUserId));
		message.setMessageSourceUser(userDao.getUserProfileRecordByUserId(srcUserId));
		message.setMessageTime(new Date());
		message.setMessageReadFlag(UserMessage.CONSTANT_MESSAGE_READ_FLAG_NOT_YET);
		messageDao.insertUserMessageRecord(message);
		return true;
	}

	@Override
	public List<UserMessage> getHistoryMessageListWithFriend(UserProfile user, UserProfile friend) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getMessageListHistoryWithFriend(user,friend)");
		toLog(CommonLog.DEBUG, getClass().getName(), "user = " + user.getUserId() + ", friend = " + friend.getUserId());
		return messageDao.getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc(user.getUserId(), friend.getUserId());
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void processLogicOfSendingMessageToFriend(MessageForm messageForm) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfSendingMessageToFriend(messageForm)");
		toLog(CommonLog.DEBUG, getClass().getName(), "messageId = " + messageForm.getMessageId() + ", destUserId = " + messageForm.getMessageDestUserId() + ", sourceUserId = " + messageForm.getMessageSourceUserId() + ", content = " + messageForm.getMessageContent());
		// insert the new message into DB
		UserMessage um = new UserMessage();
		um.setMessageContent(messageForm.getMessageContent());
		addNewMessageToFriend(um, messageForm.getMessageDestUserId(), messageForm.getMessageSourceUserId());
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void processLogicOfSendingMessageByFriend(FriendMessageForm friendMessageForm) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfSendingMessageByFriend(messageForm)");
		toLog(CommonLog.DEBUG, getClass().getName(), "messageId = " + friendMessageForm.getMessageId() + ", destUserId = " + friendMessageForm.getMessageDestUserId() + ", sourceUserId = " + friendMessageForm.getMessageSourceUserId() + ", content = " + friendMessageForm.getMessageContent());
		// insert the new message into DB
		UserMessage um = new UserMessage();
		um.setMessageContent(friendMessageForm.getMessageContent());
		setSessionLogger(logger);
		addNewMessageToFriend(um, friendMessageForm.getMessageDestUserId(), friendMessageForm.getMessageSourceUserId());
	}

	@Override
	public List<UserMessage> getAllUnreadMessagesForAllUsers() {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getAllUnreadMessagesForAllUsers()");
		return messageDao.getAllUnreadMessagesForAllUsers();
	}

	@Override
	public void setSessionLogger(SimpleLog logger) {
		super.logger = logger;
		setSessionLoggerToDaos(logger);
	}

	@Override
	public void setSessionLoggerToDaos(SimpleLog logger) {
		userDao.setUserLogInstance(logger);
		messageDao.setUserLogInstance(logger);
	}
}
