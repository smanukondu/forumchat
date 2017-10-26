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

package com.wiz.jspforum.web.basic.bean.presentation;

import java.io.Serializable;
import java.util.List;

import com.wiz.jspforum.common.model.PagingListModel;
import com.wiz.jspforum.persistence.basic.data.dto.UserPost;

/**
 * The present bean of the Post and Comment List
 * @param <E>
 */
public class PostCommentListModel<E> extends PagingListModel<E> implements Serializable {

	private static final long serialVersionUID = 8739881329554460917L;

	private UserPost post = null;

	/**
     * Construct empty default object.
     */
	public PostCommentListModel() {
		super();
	}

	@SuppressWarnings("unchecked")
	public PostCommentListModel(UserPost post) {
		super();
		this.post = post;
		super.setDataList((List<E>)post.getPostCommentList());
	}

	public UserPost getPost() {
		return post;
	}

	public void setPost(UserPost post) {
		this.post = post;
	}
}
