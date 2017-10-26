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

/**
 * The present bean of the Message IN/OUT List
 */
public class MessageListModel extends PagingListModel<UserMessage> implements Serializable {

	private static final long serialVersionUID = 5992979641457185888L;

	/**
     * Construct empty default object.
     */
	public MessageListModel() {
		super();
	}

	public MessageListModel(List<UserMessage> dataList) {
		super();
		super.setDataList(dataList);
	}
}
