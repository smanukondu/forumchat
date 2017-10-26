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

package com.wiz.jspforum.web.basic.bean.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The form bean of Post Comment Send Submit
 */
public class PostCommentForm {
	
	private int postId = 0;
	
	private int postCommentUserId = 0;
	
	@NotEmpty
	@Size(min = 1, max = 300)
	private String postCommentContent = null;
	
	private int postCommentMark = 0;
	
	/**
     * Construct empty default object.
     */
	public PostCommentForm() {
		super();
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getPostCommentUserId() {
		return postCommentUserId;
	}

	public void setPostCommentUserId(int postCommentUserId) {
		this.postCommentUserId = postCommentUserId;
	}

	public String getPostCommentContent() {
		return postCommentContent;
	}

	public void setPostCommentContent(String postCommentContent) {
		this.postCommentContent = postCommentContent;
	}

	public int getPostCommentMark() {
		return postCommentMark;
	}

	public void setPostCommnetMark(int postMark) {
		this.postCommentMark = postMark;
	}
}
