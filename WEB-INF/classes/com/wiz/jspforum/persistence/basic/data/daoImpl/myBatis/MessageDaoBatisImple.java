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

package com.wiz.jspforum.persistence.basic.data.daoImpl.myBatis;

import java.util.HashMap;
import java.util.List;

import com.wiz.jspforum.persistence.basic.data.dao.MessageDao;
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForBatis;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The MyBatis Implement Class of MessageDao
 */
public class MessageDaoBatisImple extends DaoSupportForBatis implements MessageDao {
	
	public MessageDaoBatisImple() {
		
	}
	
	@Override
	public UserMessage getUserMessageRecordByMessageId(int messageId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordByMessageId(messageId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "messageId = " + messageId);
		return (UserMessage)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.getUserMessageRecordByMessageId", messageId);
	}

	@Override
	public boolean updateUserMessageRecordForReadFlag(UserMessage message) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserMessageRecord(message)");
		toLog(CommonLog.DEBUG, getClass().getName(), "message = " + message);
		int result = getSqlSession().update("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.updateUserMessageRecordForReadFlag", message);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of updateUserMessageRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public boolean insertUserMessageRecord(UserMessage message) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertUserMessageRecord(message)");
		toLog(CommonLog.DEBUG, getClass().getName(), "message = " + message);
		int result = getSqlSession().insert("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.insertUserMessageRecord", message);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of insertUserMessageRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public List<UserMessage> getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc(int userId, int friendId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc(userId,friendId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId + ", friendId = " + friendId);
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("userId", userId);
		paramMap.put("friendId", friendId);
		List<UserMessage> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc", paramMap);
		return resultList;
	}

	@Override
	public List<UserMessage> getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc(int destUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc(destUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "destUserId = " + destUserId);
		List<UserMessage> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc", destUserId);
		return resultList;
	}

	@Override
	public List<UserMessage> getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc(int sourceUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc(sourceUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "sourceUserId = " + sourceUserId);
		List<UserMessage> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc", sourceUserId);
		return resultList;
	}

	@Override
	public List<UserMessage> getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc(int userId, int readFlag) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc(userId,readFlag)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId + ", readFlag = " + readFlag);
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("userId", userId);
		paramMap.put("readFlag", readFlag);
		List<UserMessage> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc", paramMap);
		return resultList;
	}

	@Override
	public List<UserMessage> getUserMessageRecordsWithConditions(HashMap<String, Object> conditions) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsWithConditions(conditions)");
		List<UserMessage> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.getUserMessageRecordsWithConditions", conditions);
		return resultList;
	}
	
	@Override
	public List<UserMessage> getAllUnreadMessagesForAllUsers() {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getAllUnreadMessagesForAllUsers()");
		List<UserMessage> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.MessageMapper.getAllUnreadMessagesForAllUsers");
		return resultList;
	}
	
	@Override
	public void setUserLogInstance(SimpleLog logger) {
		super.logger = logger;
	}

	@Override
	public SimpleLog getUserLogInstance() {
		return super.logger;
	}
}
