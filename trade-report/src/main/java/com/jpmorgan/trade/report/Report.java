package com.jpmorgan.trade.report;

import java.util.List;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.processor.Processor;
import com.jpmorgan.trade.writer.Writer;

/**
 * Run a processor and a writer the given input data.
 * 
 * @author Mayuko O'Hare
 *
 */
public class Report {	

	private List<Entity> inputData;
	
	private Processor processor;
	
	private Writer writer;
	
	/**
	 * Disable a default constructor.
	 */
	private Report() {		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param inputData
	 * @param processor
	 * @param writer
	 */
	public Report(List<Entity> inputData, Processor processor, Writer writer) {
		this.inputData = inputData;
		this.processor = processor;
		this.writer = writer;
	}
	
	/**
	 * Run the given processor and writer.
	 */
	public final void output() {

		List<String> output = this.processor.process(inputData);
		
		this.writer.write(output, null);
	}
	
}
