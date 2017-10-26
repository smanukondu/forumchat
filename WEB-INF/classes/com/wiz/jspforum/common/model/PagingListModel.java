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

package com.wiz.jspforum.common.model;

import java.util.List;

import com.wiz.jspforum.common.constant.GlobalSetting;

/**
 * The function model for paging the list in the web page 
 * @param <E>
 */
public abstract class PagingListModel<E> {

	private List<E> dataList = null;
	private int pageIndex = 0;

	/**
     * Construct empty default object.
     */
	public PagingListModel() {
		
	}

	/**
     * Get the max number of available paging
     * 
     * @return the max number
     */
	public int getMaxPageNo() {
		return (dataList.size()/GlobalSetting.MAX_PAGE_LIST_ITEM) + ((dataList.size()%GlobalSetting.MAX_PAGE_LIST_ITEM > 0) ? 1 : 0);
	}

	/**
     * Get the element list in the current page
     * 
     * @return the object list
     */
	public List<E> getPageDataList() {
		List<E> resList = null;
		int listSize = dataList.size();
		if (listSize != 0) {
			int idx = pageIndex*GlobalSetting.MAX_PAGE_LIST_ITEM;
			int len = (pageIndex == (getMaxPageNo() - 1)) ? ((listSize%GlobalSetting.MAX_PAGE_LIST_ITEM > 0) ? listSize%GlobalSetting.MAX_PAGE_LIST_ITEM : GlobalSetting.MAX_PAGE_LIST_ITEM) : GlobalSetting.MAX_PAGE_LIST_ITEM;
			resList = dataList.subList(idx, idx + len);
		}
		return resList;
	}

	public boolean isFirstPage() {
		return (pageIndex == 0) ? true : false; 
	}

	public boolean isLastPage() {
		return (pageIndex == getMaxPageNo() - 1 || getMaxPageNo() == 0) ? true : false;
	}

	public boolean isAble2MoveToPrev() {
		return (pageIndex == 0) ? false : true; 
	}

	public boolean isAble2MoveToNext() {
		return (pageIndex == getMaxPageNo() - 1 || getMaxPageNo() == 0) ? false : true;
	}

	public void setDataList(List<E> dataList) {
		this.dataList = dataList;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageNo() {
		return pageIndex + 1;
	}
}
