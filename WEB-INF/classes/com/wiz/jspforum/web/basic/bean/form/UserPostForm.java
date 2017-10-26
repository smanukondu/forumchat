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

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * The form bean of Post Raise Submit
 */
public class UserPostForm {

	private int userId = 0;

	@NotEmpty
	@Size(min = 1, max = 50)
	private String postTopic = "";

	@NotEmpty
	@Size(min = 1, max = 800)
	private String postContent = "";

	private String attachmentImage = "";

	private List<MultipartFile> formFiles = new ArrayList<MultipartFile>();

	/**
	 * Construct empty default object.
	 */
	public UserPostForm() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPostTopic() {
		return postTopic;
	}

	public void setPostTopic(String postTopic) {
		this.postTopic = postTopic;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getAttachmentImage() {
		return attachmentImage;
	}

	public void setAttachmentImage(String attachmentImage) {
		this.attachmentImage = attachmentImage;
	}

	public List<MultipartFile> getFormFiles() {
		return formFiles;
	}

	public void setFormFiles(List<MultipartFile> formFiles) {
		this.formFiles = formFiles;
	}
}
