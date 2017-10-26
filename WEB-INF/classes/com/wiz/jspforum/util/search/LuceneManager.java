
package com.wiz.jspforum.util.search;

import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;

import com.wiz.jspforum.persistence.basic.data.dto.UserPost;

public class LuceneManager implements SearchManager {
	
	private static Logger logger = Logger.getLogger(LuceneManager.class);
	
	private LuceneSettings settings = null;
	private LuceneIndexer indexer   = null;
	private LuceneSearch search     = null;
	
	public void init() {
		logger.info("--> LuceneManager.init() ...");
		try {
			// ---- org.apache.lucene.analysis.standard.StandardAnalyzer ----
			Analyzer analyzer = (Analyzer)Class.forName(ResourceBundle.getBundle("config").getString("CLASS_NAME_OF_LUCENE_ANALYZER")).newInstance();
			logger.info("start to instante the object of 'LuceneSettings' with 'analyzer'");
			settings = new LuceneSettings(analyzer);
			// ---- C:/Users/rc3570/Downloads/log4js/jspforumLuceneIndex ----
			settings.useFSDirectory(ResourceBundle.getBundle("config").getString("LUCENE_INDEXER_FS_DIRECTORY_PATH"));
			removeLockFile();
			logger.info("start to instante the object of 'LuceneIndexer' with 'settings'");
			indexer = new LuceneIndexer(settings);
			logger.info("start to instante the object of 'LuceneContentCollector' with 'settings'");
			logger.info("start to instante the object of 'LuceneSearch' with 'contentCollector'");
			search = new LuceneSearch(settings, new LuceneContentCollector(settings));
			indexer.watchNewDocuDocumentAdded(search);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
	}
	
	public LuceneSearch luceneSearch() {
		return search;
	}
	
	public LuceneIndexer luceneIndexer() {
		return indexer;
	}
	
	public void removeLockFile() {
		logger.info("--> LuceneManager.removeLockFile() ...");
		try {
			if (IndexReader.isLocked(settings.directory())) {
				IndexReader.unlock(settings.directory());
			}
		} catch (IOException e) {
			logger.warn(e.toString(), e);
		}
	}
	
	public void create(UserPost post) {
		logger.info("--> LuceneManager.create(post) ...");
		indexer.create(post);
	}
	
	public void delete(UserPost post) {
		logger.info("--> LuceneManager.delete(post) ...");
		indexer.delete(post);
	}
	
	public void update(UserPost post) {
		logger.info("--> LuceneManager.update(post) ...");
		indexer.update(post);
	}
	
	public SearchResult search(SearchArgs args) {
		logger.info("--> LuceneManager.search(args) ...");
		return search.search(args);
	}
}
