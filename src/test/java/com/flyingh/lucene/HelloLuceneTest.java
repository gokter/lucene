package com.flyingh.lucene;

import java.io.IOException;

import org.junit.Test;

public class HelloLuceneTest {
	@Test
	public void testIndex() throws IOException {
		new HelloLucene().index();
	}
}
