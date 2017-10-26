
package com.wiz.jspforum.util.search;

import java.util.ResourceBundle;

public class SearchArgs {
	
	private boolean matchAllKeywords = false;
	private String keywords          = null;
	private String orderDir          = "DESC";
	private String orderBy           = null;
	private String matchType         = null;
	private int initialRecord        = 0;

	public String toString() {
		return "keywords = " + keywords + ", orderDir = " + orderDir + ", orderBy = " + orderBy + ", matchAllKeywords = " + matchAllKeywords + ", initialRecord = " + initialRecord + ", matchType = " + matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getMatchType() {
		return this.matchType;
	}

	public int fetchCount() {
		return Integer.valueOf(ResourceBundle.getBundle("config").getString("RAM_DIRECTORY_DOCUMENT_NUM"));
	}

	public void startFetchingAtRecord(int initialRecord) {
		this.initialRecord = initialRecord;
	}

	public int startFrom() {
		return this.initialRecord;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void matchAllKeywords() {
		this.matchAllKeywords = true;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}

	public String rawKeywords() {
		if (keywords == null) {
			return "";
		}
		return keywords.trim();
	}

	public boolean shouldMatchAllKeywords() {
		return matchAllKeywords;
	}

	public String getOrderDir() {
		if (!"ASC".equals(orderDir) && !"DESC".equals(orderDir)) {
			return "DESC";
		}
		return orderDir;
	}

	public String getOrderBy() {
		return orderBy;
	}
}
