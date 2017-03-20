package com.jpmorgan.trade.processor;

import java.util.List;

import com.jpmorgan.trade.data.Entity;

/**
 * Process the input data and output into a defined way of report.
 * Implement this interface when adding a different implementation of Processor.
 * 
 * @author Mayuko O'Hare
 *
 */
public interface Processor {

	/**
	 * Process the given inputData and output into a defined way of report.
	 * 
	 * @param inputData
	 * @return outputData
	 */
	List<String> process(List<Entity> inputData);
	
}
