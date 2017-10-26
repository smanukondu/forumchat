
package com.wiz.jspforum.util.search;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;

import com.wiz.jspforum.persistence.basic.data.dto.UserPost;
import com.wiz.jspforum.persistence.basic.data.dto.UserProfile;

public class LuceneSearch implements NewDocumentAdded {
	
	private static final Logger logger = Logger.getLogger(LuceneSearch.class);
	
	private LuceneSettings settings                = null;
	private LuceneResultCollector contentCollector = null;
	private IndexSearcher search                   = null;
	
	public LuceneSearch(LuceneSettings settings, LuceneResultCollector contentCollector) {
		this.settings = settings;
		this.contentCollector = contentCollector;
		openSearch();
	}
	
	public void newDocumentAdded() {
		logger.info("--> LuceneSearch.newDocumentAdded() ...");
		try {
			search.close();
			openSearch();
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
	}
	
	public SearchResult search(SearchArgs args) {
		logger.info("--> LuceneSearch.search(args) ...");
		return performSearch(args, contentCollector, null);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private SearchResult performSearch(SearchArgs args, LuceneResultCollector resultCollector, Filter filter) {
		logger.info("--> LuceneSearch.performSearch(args, resultCollector, filter) ...");
		logger.info("DEBUG - args == [" + args.toString() + "]");
		SearchResult result = new SearchResult(new ArrayList(), 0);
		try {
			StringBuffer criteria = new StringBuffer(256);
			filterByKeywords(args, criteria);
			logger.info("DEBUG - the final criteria for StandardAnalyzer = " + criteria.toString());
			logger.info("start to instante the object of 'QueryParser' with the 'StandardAnalyzer' and 'criteria' ...");
			//String afterEscapeCriteria = QueryParser.escape(criteria.toString());
			String afterEscapeCriteria = criteria.toString();
			logger.info("afterEscapeCriteria == " + afterEscapeCriteria);
			if (afterEscapeCriteria != null && !"".equals(afterEscapeCriteria)) {
				Query query = new QueryParser("", new StandardAnalyzer()).parse(afterEscapeCriteria);
				if (logger.isDebugEnabled()) {
					logger.info("DEBUG - Generated query: " + query);
				}
				Hits hits = (filter == null) ? search.search(query, getSorter(args)) : search.search(query, filter, getSorter(args));
				if (hits != null && hits.length() > 0) {
					result = new SearchResult(resultCollector.retrieveRealPosts(populatePostsFromSearchDocuments(resultCollector.collect(args, hits, query)), query), hits.length());
				}
			}
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List populatePostsFromSearchDocuments(List postIds) throws Exception {
		logger.info("--> LuceneSearch.populatePostsFromSearchDocuments(postIds) ...");
		List posts = new ArrayList();
		for (int i = 0; i < postIds.size(); i++) {
			Document d = findDocumentByPostId((int)postIds.get(i));
			logger.info("DEBUG - postId == " + (int)postIds.get(i));
			String postUserName = d.getField(SearchFields.Keyword.POST_USER_NAME).stringValue();
			logger.info("DEBUG - postUserName == " + postUserName);
			String postUserAvatarPic = d.getField(SearchFields.Keyword.POST_USER_AVATAR_PIC).stringValue();
			logger.info("DEBUG - postUserAvatarPic == " + postUserAvatarPic);
			String postTopic = d.getField(SearchFields.Keyword.POST_TOPIC).stringValue();
			logger.info("DEBUG - postTopic == " + postTopic);
			String postContent = d.getField(SearchFields.Keyword.POST_CONTENT).stringValue();
			logger.info("DEBUG - postContent == " + postContent);
			int postMark = Integer.valueOf(d.getField(SearchFields.Keyword.POST_MARK).stringValue());
			logger.info("DEBUG - postMark == " + postMark);
			int postCommentNum = Integer.valueOf(d.getField(SearchFields.Keyword.POST_COMMENT_NUM).stringValue());
			logger.info("DEBUG - postCommentNum == " + postCommentNum);
			Date postDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(d.getField(SearchFields.Keyword.POST_DATE).stringValue());
			logger.info("DEBUG - postDate == " + postDate);
			UserPost p = new UserPost();
			p.setPostId((int)postIds.get(i));
			UserProfile u = new UserProfile();
			u.setUserName(postUserName);
			u.setUserAvatarPic(postUserAvatarPic);
			p.setPostUser(u);
			p.setPostTopic(postTopic);
			p.setPostContent(postContent);
			p.setPostMark(postMark);
			p.setPostCommentNum(postCommentNum);
			p.setPostTime(postDate);
			posts.add(p);
		}
		return posts;
	}
	
	public Document findDocumentByPostId(int postId) {
		Document doc = null;
		try {
			Hits hit = search.search(new TermQuery(new Term(SearchFields.Keyword.POST_ID, String.valueOf(postId))));
			if (hit.length() > 0) {
				doc = hit.doc(0);
			}
		} catch (IOException e) {
			logger.warn(e.toString(), e);
		}
		return doc;
	}

	private Sort getSorter(SearchArgs args) {
		logger.info("--> LuceneSearch.getSorter(args) ...");
		Sort sort = Sort.RELEVANCE;
		if ("time".equals(args.getOrderBy())) {
			sort = new Sort(SearchFields.Keyword.POST_ID, "DESC".equals(args.getOrderDir()));
		}
		return sort;
	}
	
	private void filterByKeywords(SearchArgs args, StringBuffer criteria) {
		logger.info("--> LuceneSearch.filterByKeywords(args, criteria) ...");
		String[] keywords = analyzeKeywords(args.rawKeywords());
		for (int i = 0; i < keywords.length; i++) {
			logger.info("DEBUG - keyword item parsed from 'Analyze' = " + keywords[i]);
			if (args.shouldMatchAllKeywords()) {
				criteria.append(" +");
			}
			criteria.append('(').append(SearchFields.Indexed.POST_CONTENTS).append(':').append(QueryParser.escape(keywords[i])).append(") ");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String[] analyzeKeywords(String contents) {
		logger.info("--> LuceneSearch.analyzeKeywords(contents) ...");
		try {
			TokenStream stream = settings.analyzer().tokenStream("contents", new StringReader(contents));
			List tokens = new ArrayList();
			while (true) {
				Token token = stream.next();
				if (token == null) {
					break;
				}
				tokens.add(token.termText());
			}
			return (String[])tokens.toArray(new String[0]);
		} catch (IOException e) {
			logger.warn(e.toString(), e);
		}
		return null;
	}
	
	private void openSearch() {
		logger.info("--> LuceneSearch.openSearch() ...");
		try {
			logger.info("create the object of 'IndexSearcher' with 'directory' and instante it ...");
			search = new IndexSearcher(settings.directory());
		} catch (IOException e) {
			logger.warn(e.toString(), e);
		}
	}
}
