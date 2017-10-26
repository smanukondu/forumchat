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

package com.wiz.jspforum.persistence.basic.data.dao;

import java.util.List;

import com.wiz.jspforum.persistence.basic.data.dto.FriendLink;
import com.wiz.jspforum.persistence.basic.data.dto.FriendJointRequest;

/**
 * The Friend Entity DAO is assigned as interface to handle with all the operations to the data record in database
 */
public interface FriendDao extends AbstractDaoInterface {
	/**
	 * Get friend request records base on the given 'ToUserId'
	 * 
	 * @param userId the 'TO' user's identity
	 * 
	 * @return the result of friend request
	 */
	List<FriendJointRequest> getFriendJointRequestRecordsByToUserId(int userId);
	/**
	 * Update the fields of FORUM_FRIEND_REQUEST record base on the input DTO
	 * 
	 * @param fri the DTO of friend request entity
	 * 
	 * @return true
	 */
	boolean updateFriendJointRequestRecordForRequestStatus(FriendJointRequest fri);
	/**
	 * Insert a new record into FORUM_FRIEND_REQUEST
	 * 
	 * @param fri the DTO of friend request entity
	 * 
	 * @return true
	 */
	boolean insertFriendJointRequestRecord(FriendJointRequest fri);
	/**
	 * Get friend request records base on the given 'HostUserId'
	 * 
	 * @param hostUserId the 'HOST' user's identity
	 * 
	 * @return the result of friend link
	 */
	List<FriendLink> getFriendLinkRecordsByHostUserId(int hostUserId);
	/**
	 * Insert a new record into FORUM_FRIEND_MAP
	 * 
	 * @param fl the DTO of friend link entity
	 * 
	 * @return true
	 */
	boolean insertFriendLinkRecord(FriendLink fl);
	/**
	 * Get friend request record base on the primary key
	 * 
	 * @param friendRequestInId the primary key of FORUM_FRIEND_REQUEST
	 * 
	 * @return the returned single record of friend request
	 */
	FriendJointRequest getFriendJointRequestRecordByFriendRequestId(int friendRequestInId);
	/**
	 * Delete the record from FORUM_FRIEND_MAP
	 * 
	 * @param fl the DTO of friend link entity
	 * 
	 * @return true
	 */
	boolean deleteFriendLinkRecord(FriendLink fl);
	/**
	 * Get friend link record base on the 'HOST' User Id and 'CUSTOMER' User Id
	 * 
	 * @param hostUserId the 'HOST' User Id
	 * @param custUserId the 'CUSTOMER' User Id
	 * 
	 * @return the returned single record of friend link
	 */
	FriendLink getFriendLinkRecordBetweenUsers(int hostUserId, int custUserId);
	/**
	 * Get friend request record base on the 'FROM' User Id, 'TO' User Id and status
	 * 
	 * @param fromUserId the 'FROM' User Id
	 * @param toUserId the 'TO' User Id
	 * @param status the status of record - FORUM_FRIEND_REQUEST
	 * 
	 * @return the returned single record of friend link
	 */
	FriendJointRequest getFriendJointRequestRecordBetweenUsersWithStatus(int fromUserId, int toUserId, String status);
}
