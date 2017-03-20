package com.jpmorgan.trade.processor.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.data.enums.Direction;
import com.jpmorgan.trade.processor.Processor;

/**
 * Process the input data and rank the entities based on incoming and outcoming amount.
 * 
 * @author Mayuko O'Hare
 *
 */
public class RankingProcessor implements Processor {
	
	/* (non-Javadoc)
	 * @see com.jpmorgan.trade.processor.Processor#process(java.util.List)
	 */
	@Override
	public List<String> process(List<Entity> inputData) {
		
		List<String> output = new ArrayList<>();
		
		List<Entity> incoming = new ArrayList<>();
		List<Entity> outgoing = new ArrayList<>();
		
		for (Entity entity : inputData) {
			if (entity.getDirection() == Direction.S) {
				incoming.add(entity);
			} else {
				outgoing.add(entity);
			}
		}
		
		Collections.sort((List<Entity>) incoming);
		Collections.sort((List<Entity>) outgoing);
		
		output.add("Ranking of entities based on incoming amount.");
		output.add(this.getColumnNames());
		this.addDataToList(incoming, output);
		output.add("");

		output.add("Ranking of entities based on outgoing amount.");
		output.add(this.getColumnNames());		
		this.addDataToList(outgoing, output);
		output.add("");
		
		return output;
	}
	
	/**
	 * Returns column names.
	 * @return column names
	 */
	public String getColumnNames() {
		return "Entity \t| "
				+ "Buy/Sell \t| "				
				+ "AgreedFx \t| "
				+ "Currency \t| "				
				+ "InstructionDate \t\t| "
				+ "SettlementDate \t\t| "
				+ "Units \t| "
				+ "Price per Unit \t| "
				+ "Reset SettlementDate \t\t| "
				+ "Amount in USD";
	}
	
	/**
	 * Add entities to a list for report.
	 * 
	 * @param input list of entities
	 * @param output list of formatted entities for report.
	 */
	private void addDataToList(List<Entity> input, List<String> output) {
		for (Entity entity : input) {
			output.add(entity.toString());			
		}		
	}



}
