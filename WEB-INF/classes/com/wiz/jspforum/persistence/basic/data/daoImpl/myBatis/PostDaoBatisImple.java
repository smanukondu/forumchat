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

import com.wiz.jspforum.persistence.basic.data.dao.PostDao;
import com.wiz.jspforum.persistence.basic.data.dto.PostComment;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForBatis;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The MyBatis Implement Class of PostDao
 */
public class PostDaoBatisImple extends DaoSupportForBatis implements PostDao {
	
	public PostDaoBatisImple() {
		
	}
	
	@Override
	public List<UserPost> getUserPostRecordsForAllOrderByPostTimeDesc() {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsForAllOrderByPostTimeDesc()");
		List<UserPost> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.getUserPostRecordsForAllOrderByPostTimeDesc");
		return resultList;
	}

	@Override
	public UserPost getUserPostRecordByPostId(int postId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordByPostId(postId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "postId = " + postId);
		return (UserPost)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.getUserPostRecordByPostId", postId);
	}

	@Override
	public boolean insertUserPostRecord(UserPost post) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertUserPostRecord(post)");
		toLog(CommonLog.DEBUG, getClass().getName(), "post = " + post);
		int result = getSqlSession().insert("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.insertUserPostRecord", post);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of insertUserPostRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public boolean updateUserPostRecordForPostMark(UserPost post) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserPostRecordForPostMark(post)");
		toLog(CommonLog.DEBUG, getClass().getName(), "post = " + post);
		int result = getSqlSession().update("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.updateUserPostRecordForPostMark", post);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of updateUserPostRecordForPostMark = [" + result + "]");
		return (result > -1) ? true : false;
	}
	
	@Override
	public boolean updateUserPostRecordForPostCommentTimes(UserPost post) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateUserPostRecordForPostCommentTimes(post)");
		toLog(CommonLog.DEBUG, getClass().getName(), "post = " + post);
		int result = getSqlSession().update("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.updateUserPostRecordForPostCommentTimes", post);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of updateUserPostRecordForPostCommentTimes = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public boolean insertPostCommentRecord(PostComment comment) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertPostCommentRecord(comment)");
		toLog(CommonLog.DEBUG, getClass().getName(), "post = " + comment);
		int result = getSqlSession().insert("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.insertPostCommentRecord", comment);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of insertPostCommentRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public List<UserPost> getUserPostRecordsWithConditions(HashMap<String, Object> conditions) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsWithConditions(conditions)");
		List<UserPost> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.getUserPostRecordsWithConditions", conditions);
		return resultList;
	}

	@Override
	public List<UserPost> getUserPostRecordsFromFriends(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsFromFriends(userId)");
		List<UserPost> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.getUserPostRecordsFromFriends", userId);
		return resultList;
	}

	@Override
	public List<UserPost> getUserPostRecordsFromMine(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserPostRecordsFromMine(userId)");
		List<UserPost> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.PostMapper.getUserPostRecordsFromMine", userId);
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
