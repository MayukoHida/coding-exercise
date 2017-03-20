package com.jpmorgan.trade.reader.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.reader.Reader;
import com.jpmorgan.trade.util.EntityMapper;

/**
 * Reads the given CSV file and map to a list of entities.
 * 
 * @author Mayuko O'Hare
 *
 */
public class CSVReader implements Reader {

	/* (non-Javadoc)
	 * @see com.jpmorgan.trade.reader.Reader#read(java.nio.file.Path)
	 */
	@Override
	public List<Entity> read(Path filePath) throws Exception {
		
		List<String> lines;
		try {
			lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new Exception("Failed to read a file " + filePath.getFileName(), e);
		}
		
		List<Entity> entityList = new ArrayList<>();
		for (String line : lines) {
			entityList.add(EntityMapper.map(line));
		}
		
		return entityList;
	}

}
