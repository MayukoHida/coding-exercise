package com.jpmorgan.trade.writer.impl;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.trade.writer.Writer;

public class WriterTest {
	
	private Writer writer;
	
	@Before
	public void init() {
		writer = new ConsoleWriter();
	}
	
	@Test
	public void testWrite() {
		writer.write(Arrays.asList("output1", "output2"), null);
	}

}
