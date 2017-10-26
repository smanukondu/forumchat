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

import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;
import com.wiz.jspforum.web.basic.bean.form.PostCommentForm;
import com.wiz.jspforum.web.basic.bean.form.UserPostForm;

/**
 * The Post Entity BDO is assigned as interface to handle with all the operations to the business logic action
 */
public interface PostBdo extends AbstractBdoInterface {
	/**
	 * Get all the posts in the whole forum
	 * 
	 * @return the result of post list
	 */
	List<UserPost> getAllThePosts();
	/**
	 * Get the post by postId
	 * 
	 * @param postId the post Id
	 * 
	 * @return the post entity
	 */
	UserPost getPostByPostId(int postId);
	/**
	 * Raise and add the new post by the user
	 * 
	 * @param userPostForm the user post submit form
	 * @param user the user entity
	 */
	void addNewUserPost(UserPostForm userPostForm, UserProfile user);
	/**
	 * Raise and add the new post comment by the user
	 * 
	 * @param postCommentForm the post comment submit form
	 * @param user the user entity
	 */
	void addNewPostComment(PostCommentForm postCommentForm, UserProfile user);
	/**
	 * Update the mark for post by postId
	 * 
	 * @param postMark the post mark given by user
	 * @param postId the post Id
	 */
	void updatePostMarkByPostComment(int postMark, int postId);
	/**
	 * Increase the post comment time by 1
	 * 
	 * @param postId the post Id
	 */
	void increasePostCommentTimes(int postId);
	/**
	 * Process the logic of appending the comment to the Post
	 * 
	 * @param postCommentForm the post comment submit form
	 * @param user the user entity
	 */
	void processLogicOfAppendingCommentToPost(PostCommentForm postCommentForm, UserProfile user);
}
