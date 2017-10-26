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
import com.wiz.jspforum.persistence.basic.data.dto.UserMessage;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;

/**
 * The present bean of the Message History List between two users
 */
public class MessageListHistoryModel extends PagingListModel<UserMessage> implements Serializable {

	private static final long serialVersionUID = -4074534141276796028L;

	private UserProfile userFriend = null;

	/**
     * Construct empty default object.
     */
	public MessageListHistoryModel() {
		super();
	}

	public MessageListHistoryModel(UserProfile userFriend, List<UserMessage> dataList) {
		super();
		this.userFriend = userFriend;
		super.setDataList(dataList);
	}

	public UserProfile getUserFriend() {
		return userFriend;
	}

	public void setUserFriend(UserProfile userFriend) {
		this.userFriend = userFriend;
	}
}
