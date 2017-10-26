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

import com.wiz.jspforum.persistence.basic.data.dao.PostDao;
import com.wiz.jspforum.persistence.basic.data.dto.PostComment;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForHibernate;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The Hibernate Implement Class of PostDao
 */
@Repository("postDao")
public class PostDaoHbnImple extends DaoSupportForHibernate implements PostDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> getUserPostRecordsForAllOrderByPostTimeDesc() {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsForAllOrderByPostTimeDesc()");
		String hql = "from UserPost up order by up.postTime desc";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserPostRecordsForAllOrderByPostTimeDesc' = " + hql);
		return (List<UserPost>)getHibernateTemplate().find(hql);
	}
	
	@Override
	public UserPost getUserPostRecordByPostId(int postId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordByPostId(postId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "postId = " + postId);
		return (UserPost)getHibernateTemplate().get(UserPost.class, postId);
	}
	
	@Override
	public boolean insertUserPostRecord(UserPost post) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertUserPostRecord(post)");
		toLog(CommonLog.DEBUG, getClass().getName(), "post = " + post);
		getHibernateTemplate().save(post);
		return true;
	}
	
	@Override
	public boolean insertPostCommentRecord(PostComment comment) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertPostCommentRecord(comment)");
		toLog(CommonLog.DEBUG, getClass().getName(), "comment = " + comment);
		getHibernateTemplate().save(comment);
		return true;
	}
	
	@Override
	public boolean updateUserPostRecordForPostMark(UserPost post) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserPostRecordForPostMark(post)");
		toLog(CommonLog.DEBUG, getClass().getName(), "post = " + post);
		getHibernateTemplate().update(post);
		return true;
	}
	
	@Override
	public boolean updateUserPostRecordForPostCommentTimes(UserPost post) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserPostRecordForPostCommentTimes(post)");
		toLog(CommonLog.DEBUG, getClass().getName(), "post = " + post);
		getHibernateTemplate().update(post);
		return true;
	}
	
	@Override
	public List<UserPost> getUserPostRecordsWithConditions(HashMap<String, Object> conditions) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsWithConditions(conditions)");
//		toLog(CommonLog.DEBUG, getClass().getName(), "conditions = [" + conditions + "]");
//		String hql = "from UserPost up " + conditions;
//		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserPostRecordsWithConditions' = " + hql);
//		return (List<UserPost>)getHibernateTemplate().find(hql);
		toLog(CommonLog.DEBUG, getClass().getName(), "The old logic of search-creteria had been discareded, so this interface about Hibernate is disable now.");
		List<UserPost> resultList = new ArrayList<UserPost>();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> getUserPostRecordsFromFriends(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsFromFriends(userId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId);
		String hql = "from UserPost up where up.postUser.userId in (select fl.friendLinkCustUser.userId from FriendLink fl where fl.friendLinkHostUser.userId = :userId)";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserPostRecordsFromFriends' = " + hql);
		return (List<UserPost>)getHibernateTemplate().findByNamedParam(hql, "userId", userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserPost> getUserPostRecordsFromMine(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsFromMine(userId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + userId);
		String hql = "from UserPost up where up.postUser.userId = :userId";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getUserPostRecordsFromMine' = " + hql);
		return (List<UserPost>)getHibernateTemplate().findByNamedParam(hql, "userId", userId);
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
