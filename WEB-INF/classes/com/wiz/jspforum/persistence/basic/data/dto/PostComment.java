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

package com.wiz.jspforum.persistence.basic.data.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The DTO model of storing the information of Post Comment
 * Which be mapped with Hibernate Entity [PostComment.hbm.xml] or myBatis Entity [PostMapper.xml]
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FORUM_POST_COMMENT")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class PostComment implements Serializable {

	private int postCommentId = 0;
	private String postCommentContent = null;
	private Date postCommentDate = null;
	private int postCommentMark = 0;
	private int postCommentPostId = 0;
	private UserPost postCommentPost = null;
	private UserProfile postCommentUser = null;

	/**
     * Construct empty default object.
     */
	public PostComment() {
		
	}

	public String toString() {
		return "postCommentId = " + postCommentId + ", postCommentContent = " + postCommentContent + ", postCommentDate = " + postCommentDate + ", postCommentMark = " + postCommentMark + " | postCommentPostId = " + postCommentPostId + ", postCommentUser.userId = " + ((postCommentUser != null) ? postCommentUser.getUserId() : "null");
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="POST_COMMENT_ID")
	public int getPostCommentId() {
		return postCommentId;
	}

	public void setPostCommentId(int postCommentId) {
		this.postCommentId = postCommentId;
	}

	@Column(name="POST_COMMENT_CONTENT")
	public String getPostCommentContent() {
		return postCommentContent;
	}

	public void setPostCommentContent(String postCommentContent) {
		this.postCommentContent = postCommentContent;
	}

	@Column(name="POST_COMMENT_DATE")
	public Date getPostCommentDate() {
		return postCommentDate;
	}

	public void setPostCommentDate(Date postCommentDate) {
		this.postCommentDate = postCommentDate;
	}

	@Column(name="POST_COMMENT_MARK")
	public int getPostCommentMark() {
		return postCommentMark;
	}

	public void setPostCommentMark(int postCommentMark) {
		this.postCommentMark = postCommentMark;
	}

	@ManyToOne
	@JoinColumn(name="POST_COMMENT_POST_ID", nullable=false)
	public UserPost getPostCommentPost() {
		return postCommentPost;
	}

	public void setPostCommentPost(UserPost postCommentPost) {
		this.postCommentPost = postCommentPost;
	}

	@ManyToOne
	@JoinColumn(name="POST_COMMENT_USER_ID", nullable=false)
	public UserProfile getPostCommentUser() {
		return postCommentUser;
	}

	public void setPostCommentUser(UserProfile postCommentUser) {
		this.postCommentUser = postCommentUser;
	}
}
