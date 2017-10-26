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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The DTO model of storing the information of User Post 
 * Which be mapped with Hibernate Entity - [UserPost.hbm.xml] or myBatis Entity [PostMapper.xml]
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FORUM_POST")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserPost implements Serializable {

	private int postId = 0;
	private String postTopic = null;
	private String postContent = null;
	private int postMark = 0;
	private int postLevel = 0;
	private Date postTime = null;
	private String postAttachmentImage = null;
	private int postCommentNum = 0;
	private List<PostComment> postCommentList = new ArrayList<PostComment>();
	private UserProfile postUser = null;

	/**
     * Construct empty default object.
     */
	public UserPost() {
		
	}

	public String toString() {
		return "postId = " + postId + ", postTopic = " + postTopic + ", postContent = " + postContent + ", postMark = " + postMark + ", postLevel = " + postLevel + ", postTime = " + postTime + ", postAttachmentImage = " + postAttachmentImage + ", postCommentNum = " + postCommentNum + " | postUser.userId = " + ((postUser != null) ? postUser.getUserId() : "null") + ", postCommentList.size = " + ((postCommentList != null && postCommentList.size() != 0) ? postCommentList.size() : 0);
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="POST_ID")
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Column(name="POST_TOPIC")
	public String getPostTopic() {
		return postTopic;
	}

	public void setPostTopic(String postTopic) {
		this.postTopic = postTopic;
	}

	@Column(name="POST_CONTENT")
	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@Column(name="POST_ATTACHMENT_IMG")
	public String getPostAttachmentImage() {
		return postAttachmentImage;
	}

	public void setPostAttachmentImage(String postAttachmentImage) {
		this.postAttachmentImage = postAttachmentImage;
	}

	@Column(name="POST_MARK")
	public int getPostMark() {
		return postMark;
	}

	public void setPostMark(int postMark) {
		this.postMark = postMark;
	}

	@Column(name="POST_COMMENT_NUM")
	public int getPostCommentNum() {
		return postCommentNum;
	}

	public void setPostCommentNum(int postCommentNum) {
		this.postCommentNum = postCommentNum;
	}

	@Column(name="POST_LEVEL")
	public int getPostLevel() {
		return postLevel;
	}

	public void setPostLevel(int postLevel) {
		this.postLevel = postLevel;
	}

	@Column(name="POST_TIME")
	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	@ManyToOne
	@JoinColumn(name="POST_USER_ID", nullable=false)
	public UserProfile getPostUser() {
		return postUser;
	}

	public void setPostUser(UserProfile postUser) {
		this.postUser = postUser;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="postCommentPost", fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	public List<PostComment> getPostCommentList() {
		return postCommentList;
	}

	public void setPostCommentList(List<PostComment> postCommentList) {
		this.postCommentList = postCommentList;
	}
}
