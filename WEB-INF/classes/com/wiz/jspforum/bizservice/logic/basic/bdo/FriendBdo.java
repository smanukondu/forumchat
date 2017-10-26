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

import com.wiz.jspforum.persistence.basic.data.dto.FriendLink;
import com.wiz.jspforum.persistence.basic.data.dto.FriendJointRequest;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;

/**
 * The Friend Entity BDO is assigned as an interface to handle with all the operations to the business logic action
 */
public interface FriendBdo extends AbstractBdoInterface {
	
	/**
	 * Get the request list of the specified user's friend with the status of 'Pending'
	 * 
	 * @param user the user entity
	 * 
	 * @return the result of friend request list
	 */
	List<FriendJointRequest> getFriendJointRequestListforPendingStatusForUser(UserProfile user);
	/**
	 * Approve the friend request
	 * 
	 * @param fri the friend request entity
	 */
	void approveFriendJointRequest(FriendJointRequest fri);
	/**
	 * Reject the friend request
	 * 
	 * @param fri the friend request entity
	 */
	void rejectFriendJointRequest(FriendJointRequest fri);
	/**
	 * Cancel the Friend relationship With Friend
	 * 
	 * @param currentUser the current user entity
	 * @param friendUser the friend user entity
	 */
	void cancelFriendRelationshipWithFriend(UserProfile currentUser, UserProfile friendUser);
	/**
	 * Raise a new friend request to the indicated friend for the status of 'Pending'
	 * 
	 * @param user the current user entity
	 * @param friend the friend user entity
	 * @param requestMessage the request message
	 */
	void addNewFriendJointRequestForPending(UserProfile user, UserProfile friend, String requestMessage);
	/**
	 * Get all the friends in a list for the user 
	 * 
	 * @param user the current user entity
	 * 
	 * @return the list of friends
	 */
	List<FriendLink> getUserFriendList(UserProfile user);
	/**
	 * Get the friend request By RequestId
	 * 
	 * @param requestId the Request Id
	 * 
	 * @return the friend request entity
	 */
	FriendJointRequest getFriendJointRequestByRequestId(int requestId);
	/**
	 * Process the logic of approving friend request
	 * 
	 * @param friendRequestId the friend request Id
	 */
	void processLogicOfApprovingFriendJointRequest(int friendRequestId);
	/**
	 * Process the logic of rejecting friend request
	 * 
	 * @param friendRequestId the friend request Id
	 */
	void processLogicOfRejectingFriendJointRequest(int friendRequestId);
	/**
	 * Process the logic of canceling the friend relationship
	 * 
	 * @param user the user entity
	 * @param friendId the friend user Id
	 */
	void processLogicOfCancelingFriendRelationship(UserProfile user, int friendId);
	/**
	 * Check whether the user have sent a request to the friend previously
	 * 
	 * @param user the user entity
	 * @param friend the friend entity
	 */
	boolean isJointRequestSentToFriendForPending(UserProfile user, UserProfile friend);
	/**
	 * Check whether the relationship between the user and the friend already built or not
	 * 
	 * @param user the user entity
	 * @param friend the friend entity
	 */
	boolean isFriendRelationshipAlreadyExisted(UserProfile user, UserProfile friend);
}
