
package com.wiz.jspforum.util.search;

import java.util.List;

public class SearchResult {

	@SuppressWarnings("rawtypes")
	private List records = null;
	private int numberOfHits = 0;

	@SuppressWarnings("rawtypes")
	public SearchResult(List records, int numberOfHits) {
		this.records = records;
		this.numberOfHits = numberOfHits;
	}

	@SuppressWarnings("rawtypes")
	public List records() {
		return records;
	}

	public int numberOfHits() {
		return numberOfHits;
	}
}
