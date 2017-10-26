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

package com.wiz.jspforum.persistence.basic.data.daoImpl.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wiz.jspforum.persistence.basic.data.dao.MessageDao;
import com.wiz.jspforum.persistence.basic.data.dto.PostWithComment;
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForHibernate;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The Hibernate Implement Class of MessageDao
 */
@Repository("messageDao")
public class MessageDaoHbnImple extends DaoSupportForHibernate implements MessageDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMessage> getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc(int destUserId) {
		List<PostWithComment> list = (List<PostWithComment>)getHibernateTemplate().find("from PostWithComment pwc");
		toLog(CommonLog.DEBUG, getClass().getName(), "Here is : " + list);
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc(destUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "destUserId = " + destUserId);
		String hql = "from UserMessage um where um.messageDestUser.userId = :destUserId order by um.messageTime desc";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserMessageRecordsByDestUserIdOrderByMessageTimeDesc' = " + hql);
		return (List<UserMessage>)getHibernateTemplate().findByNamedParam(hql, "destUserId", destUserId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMessage> getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc(int sourceUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc(sourceUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "sourceUserId = " + sourceUserId);
		String hql = "from UserMessage um where um.messageSourceUser.userId = :sourceUserId order by um.messageTime desc";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserMessageRecordsBySourceUserIdOrderByMessageTimeDesc' = " + hql);
		return (List<UserMessage>)getHibernateTemplate().findByNamedParam(hql, "sourceUserId", sourceUserId);
	}
	
	@Override
	public UserMessage getUserMessageRecordByMessageId(int messageId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordByMessageId(messageId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "messageId = " + messageId);
		return (UserMessage)getHibernateTemplate().get(UserMessage.class, messageId);
	}
	
	@Override
	public boolean updateUserMessageRecordForReadFlag(UserMessage message) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserMessageRecord(message)");
		toLog(CommonLog.DEBUG, getClass().getName(), "messageDetail = " + message);
		getHibernateTemplate().update(message);
		return true;
	}

	@Override
	public boolean insertUserMessageRecord(UserMessage message) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertUserMessageRecord(message)");
		toLog(CommonLog.DEBUG, getClass().getName(), "messageDetail = " + message);
		getHibernateTemplate().save(message);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMessage> getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc(int userId, int friendId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc(userId,friendId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId + ", friendId = " + friendId);
		String hql = "from UserMessage um " + 
			"where (um.messageDestUser.userId = " + userId + " and um.messageSourceUser.userId = " + friendId + ") or " + 
			"(um.messageDestUser.userId = " + friendId + " and um.messageSourceUser.userId = " + userId + ") order by um.messageTime desc";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getHistoryUserMessageRecordsBetweenFriendsOrderByMessageTimeDesc' = " + hql);
		return (List<UserMessage>)getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMessage> getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc(int userId, int readFlag) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc(userId,readFlag)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId);
		toLog(CommonLog.DEBUG, getClass().getName(), "readFlag = " + readFlag);
		String hql = "from UserMessage um where um.messageDestUser.userId = :userId and um.messageReadFlag = :readFlag order by um.messageTime desc";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserMessageRecordsByUserIdWithReadFlagOrderByMessageTimeDesc' = " + hql);
		String[] paramNames = {"userId", "readFlag"};
		Object[] values = {userId, readFlag};
		return (List<UserMessage>)getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}
	
	@Override
	public List<UserMessage> getUserMessageRecordsWithConditions(HashMap<String, Object> conditions) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserMessageRecordsWithConditions(conditions)");
//		toLog(CommonLog.DEBUG, getClass().getName(), "conditions = [" + conditions + "]");
//		String hql = "from UserMessage um " + conditions;
//		return (List<UserMessage>)getHibernateTemplate().find(hql);
		toLog(CommonLog.DEBUG, getClass().getName(), "The old logic of search-creteria had been discareded, so this interface about Hibernate is disable now.");
		List<UserMessage> resultList = new ArrayList<UserMessage>();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserMessage> getAllUnreadMessagesForAllUsers() {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getAllUnreadMessagesForAllUsers()");
		String hql = "from UserMessage um where um.messageReadFlag = 0 order by um.messageTime desc";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getAllUnreadMessagesForAllUsers' = " + hql);
		return (List<UserMessage>)getHibernateTemplate().find(hql);
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
