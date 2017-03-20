package com.jpmorgan.trade.reader.impl;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.trade.data.Entity;

public class CSVReaderTest {
	
	private CSVReader reader;
	
	@Before
	public void init() {
		reader = new CSVReader();
	}
	
	@Test
	public void testRead() throws Exception {
		Path path = Paths.get("src/test/resources/sample.csv");
		List<Entity> actual = reader.read(path);
		assertEquals(13, actual.size());
	}
	
	@Test(expected=Exception.class)
	public void testReadThrowsException() throws Exception {
		Path path = Paths.get("src/test/resources/nonexistant.csv");
		List<Entity> actual = reader.read(path);		
	}

}
