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

import java.util.Date;

import com.wiz.jspforum.util.date.DateSimpleFormat;

/**
 * The form bean of Search in Post Submit
 */
public class PostSearchForm {
	
	private int postId = 0;
	private Date fromPostDate = null;
	private Date toPostDate = null;
	private String postUserName = null;
	private String postTitle = null;
	private String fromPostDateStr = null;
	private String toPostDateStr = null;
	
	/**
     * Construct empty default object.
     */
	public PostSearchForm() {
		super();
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Date getFromPostDate() {
		if (fromPostDateStr != null && !fromPostDateStr.equals("")) {
			fromPostDate = DateSimpleFormat.simpleFormate(fromPostDateStr);
		}
		return fromPostDate;
	}

	public void setFromPostDate(Date fromPostDate) {
		this.fromPostDate = fromPostDate;
		this.fromPostDateStr = DateSimpleFormat.simpleFormate(fromPostDate);
	}

	public Date getToPostDate() {
		if (toPostDateStr != null && !toPostDateStr.equals("")) {
			toPostDate = DateSimpleFormat.simpleFormate(toPostDateStr);
		}
		return toPostDate;
	}

	public void setToPostDate(Date toPostDate) {
		this.toPostDate = toPostDate;
		this.toPostDateStr = DateSimpleFormat.simpleFormate(toPostDate);
	}

	public String getPostUserName() {
		return postUserName;
	}
	
	public String getPostUserNameUpperCase() {
		return (postUserName != null) ? postUserName.toUpperCase() : null;
	}

	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
	}

	public String getPostTitle() {
		return postTitle;
	}
	
	public String getPostTitleUpperCase() {
		return (postTitle != null) ? postTitle.toUpperCase() : null;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getFromPostDateStr() {
		return fromPostDateStr;
	}

	public void setFromPostDateStr(String fromPostDateStr) {
		this.fromPostDateStr = fromPostDateStr;
	}

	public String getToPostDateStr() {
		return toPostDateStr;
	}

	public void setToPostDateStr(String toPostDateStr) {
		this.toPostDateStr = toPostDateStr;
	}
}
