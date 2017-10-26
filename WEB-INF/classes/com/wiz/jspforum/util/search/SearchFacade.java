
package com.wiz.jspforum.util.search;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.wiz.jspforum.persistence.basic.data.dto.UserPost;

public class SearchFacade {
	
	private static Logger logger = Logger.getLogger(SearchFacade.class);
	
	private static SearchManager searchManager = null;
	
	public static void init() {
		logger.info("--> SearchFacade.init() ...");
		String clazz = ResourceBundle.getBundle("config").getString("CLASS_NAME_OF_LUCENE_SEARCH_MANAGER");
		logger.info("DEBUG - the className of SEARCH_INDEXER_IMPLEMENTATION == " + clazz);
		if (clazz == null || "".equals(clazz)) {
			logger.info(clazz + " is not defined. Skipping.");
		} else {
			try {
				// ---- net.jforum.search.LuceneManager ----
				searchManager = (SearchManager)Class.forName(clazz).newInstance();
				logger.info("instantiating SearchManager is done ...");
			} catch (Exception e) {
				logger.warn(e.toString(), e);
			}
			searchManager.init();
			logger.info("initializing SearchManager is done ...");
		}
	}
	
	public static void create(UserPost post) {
		logger.info("--> SearchFacade.create(post) ...");
		searchManager.create(post);
	}
	
	public static void update(UserPost post) {
		logger.info("--> SearchFacade.update(post) ...");
		searchManager.update(post);
	}
	
	public static void delete(UserPost post) {
		logger.info("--> SearchFacade.delete(p) ...");
		searchManager.delete(post);
	}
	
	public static SearchResult search(SearchArgs args) {
		logger.info("--> SearchFacade.search(args) ...");
		return searchManager.search(args);
	}

	public static SearchManager manager() {
		return searchManager;
	}
}
