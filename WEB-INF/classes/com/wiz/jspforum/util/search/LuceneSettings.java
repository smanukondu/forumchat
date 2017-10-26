
package com.wiz.jspforum.util.search;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import net.jforum.util.preferences.ConfigKeys;
//import net.jforum.util.preferences.SystemGlobals;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class LuceneSettings {
	
	private static final Logger logger = Logger.getLogger(LuceneSettings.class);
	
	private Analyzer analyzer = null;
	private Directory directory = null;
	
	public LuceneSettings(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	public void useRAMDirectory() throws Exception {
		directory = new RAMDirectory();
		// simple test for the purpose of Index Writing (any exception thrown out or not)
		IndexWriter writer = new IndexWriter(directory, analyzer, true);
		writer.close();
	}
	
	public void useFSDirectory(String indexDirectory) throws Exception {
		logger.info("--> LuceneSettings.useFSDirectory(indexDirectory) ...");
		logger.info("DEBUG - set the local folder for indexer cache is done for the path of '" + indexDirectory + "'");
		if (!IndexReader.indexExists(indexDirectory)) {
			createIndexDirectory(indexDirectory);
		}
		directory = FSDirectory.getDirectory(indexDirectory);
	}
	
	public void createIndexDirectory(String directoryPath) throws IOException {
		logger.info("--> LuceneSettings.createIndexDirectory(directoryPath) ...");
		FSDirectory fsDir = FSDirectory.getDirectory(directoryPath);
		logger.info("get the instance of 'FSDirectory' from FSDirectory.getDirectory");
		// simple test for the purpose of Index Writing (any exception thrown out or not)
		IndexWriter writer = new IndexWriter(fsDir, analyzer, true);
		writer.close();
	}
	
	public Directory directory() {
		return directory;
	}
	
	public Analyzer analyzer() {
		return analyzer;
	}
	
	public String formatDateTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
}
