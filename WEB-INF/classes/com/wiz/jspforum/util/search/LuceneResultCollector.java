
package com.wiz.jspforum.util.search;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.search.Hits;
import org.apache.lucene.search.Query;

public interface LuceneResultCollector {
	
	@SuppressWarnings("rawtypes")
	public List collect(SearchArgs args, Hits hits, Query query);
	
	@SuppressWarnings("rawtypes")
	public List retrieveRealPosts(List posts, Query query) throws IOException;
}
