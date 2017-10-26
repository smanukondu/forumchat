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

import com.wiz.jspforum.persistence.basic.data.dao.FriendDao;
import com.wiz.jspforum.persistence.basic.data.dto.FriendLink;
import com.wiz.jspforum.persistence.basic.data.dto.FriendJointRequest;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForBatis;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The MyBatis Implement Class of FriendDao 
 */
public class FriendDaoBatisImple extends DaoSupportForBatis implements FriendDao {

	public FriendDaoBatisImple() {
		
	}

	@Override
	public List<FriendJointRequest> getFriendJointRequestRecordsByToUserId(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendJointRequestRecordsByToUserId(userId)");
		List<FriendJointRequest> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.getFriendJointRequestRecordsByToUserId", userId);
		return resultList;
	}

	@Override
	public boolean updateFriendJointRequestRecordForRequestStatus(FriendJointRequest fjr) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateFriendJointRequestRecordForRequestStatus(fri)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fjr = " + fjr);
		int result = getSqlSession().update("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.updateFriendJointRequestRecordForRequestStatus", fjr);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of updateFriendJointRequestRecordForRequestStatus = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public boolean insertFriendJointRequestRecord(FriendJointRequest fjr) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertFriendJointRequestRecord(fri)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fjr = " + fjr);
		int result = getSqlSession().insert("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.insertFriendJointRequestRecord", fjr);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of insertFriendJointRequestRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public List<FriendLink> getFriendLinkRecordsByHostUserId(int hostUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendLinkRecordsByHostUserId(hostUserId)");
		List<FriendLink> resultList = getSqlSession().selectList("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.getFriendLinkRecordsByHostUserId", hostUserId);
		return resultList;
	}

	@Override
	public boolean insertFriendLinkRecord(FriendLink fl) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertFriendLinkRecord(fl)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fl = " + fl);
		int result = getSqlSession().insert("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.insertFriendLinkRecord", fl);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of insertFriendLinkRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public FriendJointRequest getFriendJointRequestRecordByFriendRequestId(int friendRequestInId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendJointRequestRecordByFriendRequestId(friendRequestInId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendRequestInId = " + friendRequestInId);
		return (FriendJointRequest)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.getFriendJointRequestRecordByFriendRequestId", friendRequestInId);
	}

	@Override
	public boolean deleteFriendLinkRecord(FriendLink fl) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.deleteFriendLinkRecord(fl)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fl = " + fl);
		int result = getSqlSession().delete("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.deleteFriendLinkRecord", fl);
		toLog(CommonLog.DEBUG, getClass().getName(), "the result of deleteFriendLinkRecord = [" + result + "]");
		return (result > -1) ? true : false;
	}

	@Override
	public FriendLink getFriendLinkRecordBetweenUsers(int hostUserId, int custUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendLinkRecordBetweenUsers(hostUserId,custUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "hostUserId = " + hostUserId + ", custUserId = " + custUserId);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hostUserId", hostUserId);
		paramMap.put("custUserId", custUserId);
		return (FriendLink)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.getFriendLinkRecordBetweenUsers", paramMap);
	}

	@Override
	public FriendJointRequest getFriendJointRequestRecordBetweenUsersWithStatus(int fromUserId, int toUserId, String status) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendJointRequestRecordBetweenUsersWithStatus(fromUserId,toUserId,status)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fromUserId = " + fromUserId + ", toUserId = " + toUserId + ", status = " + status);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fromUserId", fromUserId);
		paramMap.put("toUserId", toUserId);
		paramMap.put("status", status);
		return (FriendJointRequest)getSqlSession().selectOne("com.wiz.jspforum.persistence.basic.data.myBatis.mapper.FriendMapper.getFriendJointRequestRecordBetweenUsersWithStatus", paramMap);
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
