
package com.wiz.jspforum.util.search;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;

import com.wiz.jspforum.persistence.basic.data.dto.UserPost;

public class LuceneContentCollector implements LuceneResultCollector {
	
	private static final Logger logger = Logger.getLogger(LuceneContentCollector.class);
	
	private LuceneSettings settings = null;
	
	public LuceneContentCollector(LuceneSettings settings) {
		this.settings = settings;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List collect(SearchArgs args, Hits hits, Query query) {
		logger.info("--> LuceneContentCollector.collect(args, hits, query) ...");
		try {
			int[] postIds = new int[Math.min(args.fetchCount(), hits.length())];
			List resultList = new ArrayList();
			logger.info("DEBUG - args.fetchCount() == " + args.fetchCount() + ", hits.length() == " + hits.length());
			for (int docIndex = args.startFrom(), i = 0; docIndex < args.startFrom() + args.fetchCount() && docIndex < hits.length(); docIndex++, i++) {
				Document doc = hits.doc(docIndex);
				postIds[i] = Integer.parseInt(doc.get(SearchFields.Keyword.POST_ID));
				logger.info("DEBUG - postIds[" + i + "] == " + postIds[i]);
				resultList.add(postIds[i]);
			}
			return resultList;
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes" })
	public List retrieveRealPosts(List posts, Query query) throws IOException {
		logger.info("--> LuceneContentCollector.retrieveRealPosts(postIds, query) ...");
		for (Iterator iter = posts.iterator(); iter.hasNext(); ) {
			UserPost post = (UserPost)iter.next();
			logger.info("instante the object of 'QueryScorer' with 'query' ...");
			Scorer scorer = new QueryScorer(query);
			logger.info("instante the object of 'Highlighter' with 'scorer' ...");
			Highlighter highlighter = new Highlighter(scorer);
			logger.info("start to text search based on the post.text >>>>");
			TokenStream tokenStream = settings.analyzer().tokenStream(SearchFields.Indexed.POST_CONTENTS, new StringReader(post.getPostContent()));
			logger.info("try to select the best fragement ...");
			String fragment = highlighter.getBestFragment(tokenStream, post.getPostContent());
			post.setPostContent((fragment != null) ? fragment : post.getPostContent());
		}
		return posts;
	}
}
