package com.jpmorgan.trade.writer.impl;

import java.nio.file.Path;
import java.util.List;

import com.jpmorgan.trade.writer.Writer;

/**
 * Outputs the given data to the console.
 * 
 * @author Mayuko O'Hare
 *
 */
public class ConsoleWriter implements Writer {

	/* (non-Javadoc)
	 * @see com.ohare.writer.Writer#write(java.util.List, java.nio.file.Path)
	 */
	@Override
	public void write(List<String> outputData, Path outputPath) {
		for (String output : outputData) {
			System.out.println(output);
		}
	}


}
