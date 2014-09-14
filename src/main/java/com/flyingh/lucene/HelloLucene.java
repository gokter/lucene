package com.flyingh.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class HelloLucene {
	public void index() throws IOException {
		try (IndexWriter indexWriter = new IndexWriter(FSDirectory.open(new File("C:/lucene/indices")), new IndexWriterConfig(Version.LUCENE_35,
				new StandardAnalyzer(Version.LUCENE_35)));) {
			for (final File file : new File("C:/lucene/files").listFiles()) {
				final Document doc = new Document();
				doc.add(new Field("content", new FileReader(file)));
				doc.add(new Field("fileName", file.getName(), Store.YES, Index.NOT_ANALYZED));
				doc.add(new Field("path", file.getCanonicalPath(), Store.YES, Index.NOT_ANALYZED));
				indexWriter.addDocument(doc);
			}
		}
	}

	public void searcher() throws CorruptIndexException, IOException, ParseException {
		try (IndexSearcher indexSearcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File("C:/lucene/indices"))))) {
			final TopDocs topDocs = indexSearcher.search(
					new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35)).parse("java"), 10);
			for (final ScoreDoc scoreDoc : topDocs.scoreDocs) {
				final Document doc = indexSearcher.doc(scoreDoc.doc);
				System.out.println(doc.get("fileName") + "--->" + doc.get("path"));
			}
		}
	}
}
