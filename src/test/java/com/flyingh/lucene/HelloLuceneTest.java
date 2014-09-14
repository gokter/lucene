package com.flyingh.lucene;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.junit.Before;
import org.junit.Test;

public class HelloLuceneTest {

	private HelloLucene helloLucene;

	@Before
	public void before() {
		helloLucene = new HelloLucene();
	}

	@Test
	public void testIndex() throws IOException {
		helloLucene.index();
	}

	@Test
	public void testSearcher() throws CorruptIndexException, IOException, ParseException {
		helloLucene.searcher();
	}

}
