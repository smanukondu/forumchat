
package com.wiz.jspforum.util.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.wiz.jspforum.persistence.basic.data.dto.UserPost;

public class LuceneIndexer {
	
	private static final Logger logger = Logger.getLogger(LuceneIndexer.class);
	
	private static final Object MUTEX = new Object();
	private LuceneSettings settings   = null;
	private IndexWriter ramWriter     = null;
	private Directory ramDirectory    = null;
	private int ramNumDocs            = 0;
	@SuppressWarnings("rawtypes")
	private List newDocumentAddedList = new ArrayList();
	
	public LuceneIndexer(LuceneSettings settings) {
		this.settings = settings;
		createRAMWriter();
	}
	
	@SuppressWarnings("unchecked")
	public void watchNewDocuDocumentAdded(NewDocumentAdded newDoc) {
		logger.info("--> LuceneIndexer.watchNewDocuDocumentAdded(newDoc) ...");
		newDocumentAddedList.add(newDoc);
	}
	
	public void batchCreate(UserPost post) {
		synchronized (MUTEX) {
			try {
				Document document = this.createDocument(post);
				ramWriter.addDocument(document);
				flushRAMDirectoryIfNecessary();
			} catch (IOException e) {
				logger.warn(e.toString(), e);
			}
		}
	}
	
	private void createRAMWriter() {
		logger.info("--> LuceneIndexer.createRAMWriter() ...");
		try {
			if (ramWriter != null) {
				ramWriter.close();
			}
			ramDirectory = new RAMDirectory();
			logger.info("DEBUG - start to create the object of 'ramWriter' for 'IndexWriter' with 'ramDirectory' and 'analyzer' ...");
			ramWriter = new IndexWriter(ramDirectory, settings.analyzer(), true);
			// ---- 10000 ----
			ramNumDocs = Integer.valueOf(ResourceBundle.getBundle("config").getString("RAM_DIRECTORY_DOCUMENT_NUM"));
		} catch (IOException e) {
			logger.warn(e.toString(), e);
		}
	}
	
	private void flushRAMDirectoryIfNecessary() {
		if (ramWriter.docCount() >= ramNumDocs) {
			flushRAMDirectory();
		}
	}
	
	public void flushRAMDirectory() {
		synchronized (MUTEX) {
			IndexWriter writer = null;
			try {
				writer = new IndexWriter(this.settings.directory(), settings.analyzer());
				writer.addIndexes(new Directory[] { ramDirectory });
				writer.optimize();
				createRAMWriter();
			} catch (IOException e) {
				logger.warn(e.toString(), e);
			} finally {
				if (writer != null) {
					try {
						writer.flush(); 
						writer.close();
						notifyNewDocumentAdded();
					} catch (Exception e) {
						
					}
				}
			}
		}
	}
	
	public void create(UserPost post) {
		logger.info("--> LuceneIndexer.create(post) ...");
		synchronized (MUTEX) {
			IndexWriter writer = null;
			try {
				logger.info("start to instante the object of 'IndexWriter' with 'directory' and 'analyzer' ...");
				writer = new IndexWriter(settings.directory(), settings.analyzer());
				Document document = createDocument(post);
				logger.info("try to write the object of 'Document' into IndexWriter ...");
				writer.addDocument(document);
				optimize(writer);
				if (logger.isDebugEnabled()) {
					logger.info("DEBUG - Indexed " + document);
				}
			} catch (Exception e) {
				logger.error(e.toString(), e);
			} finally {
				if (writer != null) {
					try {
						writer.flush();
						writer.close();
						notifyNewDocumentAdded();
					} catch (Exception e) {
						
					}
				}
			}
		}
	}
	
	public void update(UserPost post) {
		logger.info("--> LuceneIndexer.update(post) ...");
		if (performDelete(post)) {
			create(post);
		}
	}
	
	private void optimize(IndexWriter writer) throws Exception {
		logger.info("--> LuceneIndexer.optimize(writer) ...");
		if (writer.docCount()%100 == 0) {
			if (logger.isInfoEnabled()) {
				logger.info("Optimizing indexes. Current number of documents is " + writer.docCount());
			}
			writer.optimize();
			if (logger.isDebugEnabled()) {
				logger.info("DEBUG - Indexes optimized");
			}
		}
	}
	
	private Document createDocument(UserPost post) {
		logger.info("--> LuceneIndexer.createDocument(p) ...");
		Document d = new Document();
		d.add(new Field(SearchFields.Keyword.POST_ID, String.valueOf(post.getPostId()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Keyword.POST_USER_NAME, String.valueOf(post.getPostUser().getUserName()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Keyword.POST_USER_AVATAR_PIC, String.valueOf(post.getPostUser().getUserAvatarPic()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Keyword.POST_TOPIC, String.valueOf(post.getPostTopic()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Keyword.POST_CONTENT, String.valueOf(post.getPostContent()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Keyword.POST_MARK, String.valueOf(post.getPostMark()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Keyword.POST_COMMENT_NUM, String.valueOf(post.getPostCommentNum()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Keyword.POST_DATE, settings.formatDateTime(post.getPostTime()), Store.YES, Index.UN_TOKENIZED));
		d.add(new Field(SearchFields.Indexed.POST_CONTENTS, post.getPostTopic() + " " + post.getPostContent() + " " + post.getPostUser().getUserName() + " " + settings.formatDateTime(post.getPostTime()), Store.NO, Index.TOKENIZED));
		return d;
	}
	
	@SuppressWarnings("rawtypes")
	private void notifyNewDocumentAdded() {
		logger.info("--> LuceneIndexer.notifyNewDocumentAdded() ...");
		for (Iterator iter = newDocumentAddedList.iterator(); iter.hasNext(); ) {
			((NewDocumentAdded)iter.next()).newDocumentAdded();
		}
	}

	public void delete(UserPost post) {
		logger.info("--> LuceneIndexer.delete(p) ...");
		performDelete(post);
	}
	
	private boolean performDelete(UserPost post) {
		logger.info("--> LuceneIndexer.performDelete(p) ...");
		synchronized (MUTEX) {
			IndexReader reader = null;
			boolean status = false;
			try {
				logger.info("try to open the 'directory' ...");
				reader = IndexReader.open(settings.directory());
				reader.deleteDocuments(new Term(SearchFields.Keyword.POST_ID, String.valueOf(post.getPostId())));
				status = true;
			} catch (IOException e) {
				logger.error(e.toString(), e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (Exception e) {
						
					}
				}
			}
			return status;
		}
	}
}
