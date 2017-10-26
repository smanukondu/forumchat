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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wiz.jspforum.persistence.basic.data.dao.FriendDao;
import com.wiz.jspforum.persistence.basic.data.dto.FriendLink;
import com.wiz.jspforum.persistence.basic.data.dto.FriendJointRequest;
import com.wiz.jspforum.persistence.basic.data.helper.DaoSupportForHibernate;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The Hibernate Implement Class of FriendDao
 */
@Repository("friendDao")
public class FriendDaoHbnImple extends DaoSupportForHibernate implements FriendDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<FriendJointRequest> getFriendJointRequestRecordsByToUserId(int userId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendJointRequestRecordsByToUserId(userId)");
		String hql = "from FriendJointRequest fjr where fjr.friendJointRequestToUser.userId = :userId and fjr.friendJointRequestStatus = :status order by fjr.friendJointRequestDate desc";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getFriendJointRequestRecordsByToUserId' = " + hql);
		String[] paramNames = {"userId", "status"};
		Object[] values = {userId, FriendJointRequest.FRIEND_JOINT_REQUEST_STATUS_PENDING};
		return (List<FriendJointRequest>)getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}

	@Override
	public boolean updateFriendJointRequestRecordForRequestStatus(FriendJointRequest fjr) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.updateFriendJointRequestRecordForRequestStatus(fjr)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fjr = " + fjr);
		getHibernateTemplate().update(fjr);
		return true;
	}

	@Override
	public boolean insertFriendJointRequestRecord(FriendJointRequest fjr) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertFriendJointRequestRecord(fjr)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fjr = " + fjr);
		getHibernateTemplate().save(fjr);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FriendLink> getFriendLinkRecordsByHostUserId(int hostUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendLinkRecordsByHostUserId(hostUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "hostUserId == " + hostUserId);
		String hql = "from FriendLink fl where fl.friendLinkHostUser.userId = :hostUserId order by fl.friendLinkCustUser.userName";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getFriendLinkRecordsByHostUserId' = " + hql);
		return (List<FriendLink>)getHibernateTemplate().findByNamedParam(hql, "hostUserId", hostUserId);
	}

	@Override
	public boolean insertFriendLinkRecord(FriendLink fl) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.insertFriendLinkRecord(fl)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendLink = " + fl);
		getHibernateTemplate().save(fl);
		return true;
	}

	@Override
	public FriendJointRequest getFriendJointRequestRecordByFriendRequestId(int friendRequestInId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendJointRequestRecordByFriendRequestId(friendRequestInId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendRequestInId == " + friendRequestInId);
		return (FriendJointRequest)getHibernateTemplate().get(FriendJointRequest.class, friendRequestInId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FriendLink getFriendLinkRecordBetweenUsers(int hostUserId, int custUserId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendLinkRecordBetweenUsers(hostUserId,custUserId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "hostUserId == " + hostUserId + ", custUserId == " + custUserId);
		String hql = "from FriendLink fl where fl.friendLinkHostUser.userId = :hostUserId and fl.friendLinkCustUser.userId = :custUserId";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getFriendLinkRecordBetweenUsers' = " + hql);
		String[] paramNames = {"hostUserId", "custUserId"};
		Object[] values = {hostUserId, custUserId};
		List<FriendLink> list = (List<FriendLink>)getHibernateTemplate().findByNamedParam(hql, paramNames, values);
		return (list == null || list.size() == 0) ? null : list.get(0);
	}

	@Override
	public boolean deleteFriendLinkRecord(FriendLink fl) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.deleteFriendLinkRecord(fl)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fl = " + fl);
		getHibernateTemplate().delete(fl);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FriendJointRequest getFriendJointRequestRecordBetweenUsersWithStatus(int fromUserId, int toUserId, String status) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendJointRequestRecordBetweenUsersWithStatus(fromUserId,toUserId,status)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fromUserId == " + fromUserId + ", toUserId == " + toUserId + ", status == " + status);
		String hql = "from FriendJointRequest fjr where fjr.friendJointRequestFromUser.userId = :fromUserId and fjr.friendJointRequestToUser.userId = :toUserId and fjr.friendJointRequestStatus = :status";
		toLog(CommonLog.DEBUG, getClass().getName(), "HQL Query for 'getFriendJointRequestRecordBetweenUsersWithStatus' = " + hql);
		String[] paramNames = {"fromUserId", "toUserId", "status"};
		Object[] values = {fromUserId, toUserId, status};
		List<FriendJointRequest> list = (List<FriendJointRequest>)getHibernateTemplate().findByNamedParam(hql, paramNames, values);
		return (list == null || list.size() == 0) ? null : list.get(0);
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
