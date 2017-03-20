package com.jpmorgan.trade.writer;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface for Writer class.
 * Implement this interface when adding a different implementation of writer.
 * 
 * @author Mayuko O'Hare
 *
 */
public interface Writer {
	
	/**
	 * Writes the given data to the given path.
	 * 
	 * @param outputData data to write
	 * @param outputPath path to write to
	 */
	void write(List<String> outputData, Path outputPath);

}
