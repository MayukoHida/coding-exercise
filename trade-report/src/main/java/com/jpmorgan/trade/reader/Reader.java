package com.jpmorgan.trade.reader;

import java.nio.file.Path;
import java.util.List;

import com.jpmorgan.trade.data.Entity;

/**
 * Reads the given file and represents as entities.
 * Implement this interface when adding a different implementation of Reader.
 * 
 * @author Mayuko O'Hare
 *
 */
public interface Reader {

	/**
	 * Reads the given file and map to a list of entities.
	 * 
	 * @param filePath
	 * @return list of entities
	 * @throws Exception
	 */
	List<Entity> read(Path filePath) throws Exception;
	
}
