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

import java.util.HashMap;
import java.util.List;

import com.wiz.jspforum.persistence.basic.data.dto.PostComment;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;

/**
 * The Post Entity DAO is assigned as interface to handle with all the operations to the data record in database
 */
public interface PostDao extends AbstractDaoInterface {
	/**
	 * Get all the post records in the whole forum and sorted by PostTime
	 * 
	 * @return the post list
	 */
	List<UserPost> getUserPostRecordsForAllOrderByPostTimeDesc();
	/**
	 * Get the user post record By Primary Key of FORUM_POST_POOL
	 * 
	 * @param postId the primary key
	 * 
	 * @return the single record of user post
	 */
	UserPost getUserPostRecordByPostId(int postId);
	/**
	 * Insert a new record to FORUM_POST_POOL base on the input DTO
	 * 
	 * @param post the DTO of user post entity
	 * 
	 * @return true
	 */
	boolean insertUserPostRecord(UserPost post);
	/**
	 * Update the field of FORUM_POST_POOL record for post mark base on the input DTO
	 * 
	 * @param post the DTO of user post entity
	 * 
	 * @return true
	 */
	boolean updateUserPostRecordForPostMark(UserPost post);
	/**
	 * Update the field of FORUM_POST_POOL record for post comment times base on the input DTO
	 * 
	 * @param post the DTO of user post entity
	 * 
	 * @return true
	 */
	boolean updateUserPostRecordForPostCommentTimes(UserPost post);
	/**
	 * Insert a new record to FORUM_POST_COMMENT base on the input DTO
	 * 
	 * @param comment the DTO of user post comment entity
	 * 
	 * @return true
	 */
	boolean insertPostCommentRecord(PostComment comment);
	/**
	 * Get user post records By some condition criteria
	 * 
	 * @param conditions the conditions criteria
	 * 
	 * @return the result of user post list
	 */
	List<UserPost> getUserPostRecordsWithConditions(HashMap<String, Object> conditions);
	/**
	 * Get user post records from friends
	 * 
	 * @param userId the user Id
	 * 
	 * @return the result of user post list
	 */
	List<UserPost> getUserPostRecordsFromFriends(int userId);
	/**
	 * Get user post records from mine
	 * 
	 * @param userId the user Id
	 * 
	 * @return the result of user post list
	 */
	List<UserPost> getUserPostRecordsFromMine(int userId);
}
