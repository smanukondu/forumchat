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
import com.wiz.jspforum.bizservice.logic.basic.bdo.FriendBdo;
import com.wiz.jspforum.persistence.basic.data.dao.FriendDao;
import com.wiz.jspforum.persistence.basic.data.dao.UserDao;
import com.wiz.jspforum.persistence.basic.data.dto.FriendLink;
import com.wiz.jspforum.persistence.basic.data.dto.FriendJointRequest;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.util.log.CommonLog;
import com.wiz.jspforum.util.log.SimpleLog;

/**
 * The Implement Class of FriendBdo
 */
@Service("friendBdo")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class FriendBdoImple extends AbstractBdoSupport implements FriendBdo {

	private FriendDao friendDao = null;
	private UserDao userDao = null;

	public FriendBdoImple() {
        
    }

	@Autowired
	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<FriendJointRequest> getFriendJointRequestListforPendingStatusForUser(UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendJointRequestListforPendingStatusForUser(user)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + user.getUserId());
		return friendDao.getFriendJointRequestRecordsByToUserId(user.getUserId());
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void approveFriendJointRequest(FriendJointRequest fri) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.approveFriendRequest(fri)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendRequestId = " + fri.getFriendJointRequestId());
		fri.setFriendJointRequestStatus(FriendJointRequest.FRIEND_JOINT_REQUEST_STATUS_APPROVED);
		friendDao.updateFriendJointRequestRecordForRequestStatus(fri);
		FriendLink fl_hc = new FriendLink();
		fl_hc.setFriendLinkHostUser(fri.getFriendJointRequestToUser());
		fl_hc.setFriendLinkCustUser(fri.getFriendJointRequestFromUser());
		fl_hc.setFriendLinkMappedDate(new Date());
		friendDao.insertFriendLinkRecord(fl_hc);
		FriendLink fl_ch = new FriendLink();
		fl_ch.setFriendLinkHostUser(fri.getFriendJointRequestFromUser());
		fl_ch.setFriendLinkCustUser(fri.getFriendJointRequestToUser());
		fl_ch.setFriendLinkMappedDate(new Date());
		friendDao.insertFriendLinkRecord(fl_ch);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void rejectFriendJointRequest(FriendJointRequest fri) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.rejectFriendRequest(fri)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendRequestId = " + fri.getFriendJointRequestId());
		fri.setFriendJointRequestStatus(FriendJointRequest.FRIEND_JOINT_REQUEST_STATUS_REJECTED);
		friendDao.updateFriendJointRequestRecordForRequestStatus(fri);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void cancelFriendRelationshipWithFriend(UserProfile currentUser, UserProfile friendUser) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.cancelFriendRelationshipWithFriend(currentUser,friendUser)");
		toLog(CommonLog.DEBUG, getClass().getName(), "currentUserId = " + currentUser.getUserId());
		toLog(CommonLog.DEBUG, getClass().getName(), "friendUserId = " + friendUser.getUserId());
		FriendLink fl_hc = friendDao.getFriendLinkRecordBetweenUsers(currentUser.getUserId(), friendUser.getUserId());
		friendDao.deleteFriendLinkRecord(fl_hc);
		FriendLink fl_ch = friendDao.getFriendLinkRecordBetweenUsers(friendUser.getUserId(), currentUser.getUserId());
		friendDao.deleteFriendLinkRecord(fl_ch);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void addNewFriendJointRequestForPending(UserProfile user, UserProfile friend, String requestMessage) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.addNewFriendRequestForPending(user,friend,requestMessage)");
		toLog(CommonLog.DEBUG, getClass().getName(), "fromUserId = " + user.getUserId());
		toLog(CommonLog.DEBUG, getClass().getName(), "toUserId = " + friend.getUserId());
		toLog(CommonLog.DEBUG, getClass().getName(), "requestMessage = " + requestMessage);
		FriendJointRequest fri = new FriendJointRequest();
		fri.setFriendJointRequestFromUser(user);
		fri.setFriendJointRequestToUser(friend);
		fri.setFriendJointRequestDate(new Date());
		fri.setFriendJointRequestRemark(requestMessage);
		fri.setFriendJointRequestStatus(FriendJointRequest.FRIEND_JOINT_REQUEST_STATUS_PENDING);
		friendDao.insertFriendJointRequestRecord(fri);
	}

	@Override
	public List<FriendLink> getUserFriendList(UserProfile user) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getUserFriendList(user)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendUserId = " + user.getUserId());
		return friendDao.getFriendLinkRecordsByHostUserId(user.getUserId());
	}

	@Override
	public FriendJointRequest getFriendJointRequestByRequestId(int requestId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.getFriendRequestByRequestId(requestId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "requestId = " + requestId);
		return friendDao.getFriendJointRequestRecordByFriendRequestId(requestId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void processLogicOfApprovingFriendJointRequest(int friendRequestId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfApprovingFriendRequest(friendRequestId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendRequestId = " + friendRequestId);
		FriendJointRequest fri = getFriendJointRequestByRequestId(friendRequestId);
		approveFriendJointRequest(fri);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void processLogicOfRejectingFriendJointRequest(int friendRequestId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfRejectingFriendRequest(friendRequestId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "friendRequestId = " + friendRequestId);
		FriendJointRequest fri = getFriendJointRequestByRequestId(friendRequestId);
		rejectFriendJointRequest(fri);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	@Override
	public void processLogicOfCancelingFriendRelationship(UserProfile user, int friendId) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.processLogicOfCancelingFriendRelationship(user,friendId)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + user.getUserId() + ", friendId = " + friendId);
		UserProfile userFriend = userDao.getUserProfileRecordByUserId(friendId);
		cancelFriendRelationshipWithFriend(user, userFriend);
	}

	@Override
	public boolean isJointRequestSentToFriendForPending(UserProfile user, UserProfile friend) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.isSentTheRequestToFriendForPending(user,friend)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + user.getUserId() + ", friendId = " + friend.getUserId());
		FriendJointRequest fri = friendDao.getFriendJointRequestRecordBetweenUsersWithStatus(user.getUserId(), friend.getUserId(), FriendJointRequest.FRIEND_JOINT_REQUEST_STATUS_PENDING);
		return (fri == null) ? false : true;
	}

	@Override
	public boolean isFriendRelationshipAlreadyExisted(UserProfile user, UserProfile friend) {
		toLog(CommonLog.INFOR, getClass().getName(), "-->.isTheFriendRelationshipAlreadyExisted(user,friend)");
		toLog(CommonLog.DEBUG, getClass().getName(), "userId = " + user.getUserId() + ", friendId = " + friend.getUserId());
		FriendLink fl = friendDao.getFriendLinkRecordBetweenUsers(user.getUserId(), friend.getUserId());
		return (fl == null) ? false : true;
	}

	@Override
	public void setSessionLogger(SimpleLog logger) {
		super.logger = logger;
		setSessionLoggerToDaos(logger);
	}

	@Override
	public void setSessionLoggerToDaos(SimpleLog logger) {
		friendDao.setUserLogInstance(logger);
		userDao.setUserLogInstance(logger);
	}
}
