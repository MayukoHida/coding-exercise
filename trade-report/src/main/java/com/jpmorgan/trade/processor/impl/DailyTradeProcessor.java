package com.jpmorgan.trade.processor.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.data.enums.Direction;
import com.jpmorgan.trade.processor.Processor;
import com.jpmorgan.trade.util.Utils;

/**
 * Process the input data and sum the daily incoming and outgoing amount.
 * 
 * @author Mayuko O'Hare
 *
 */
public class DailyTradeProcessor implements Processor {
	
	/* (non-Javadoc)
	 * @see com.jpmorgan.trade.processor.Processor#process(java.util.List)
	 */
	@Override
	public List<String> process(List<Entity> inputData) {
		
		List<String> output = new LinkedList<>();
		
		Map<String, Double> incoming = new HashMap<>();
		Map<String, Double> outgoing = new HashMap<>();
		
		for (Entity entity : inputData) {			
			if (entity.getDirection() == Direction.S) {
				this.putDataToMap(incoming, entity);
			} else {
				this.putDataToMap(outgoing, entity);
			}
		}
		
		output.add("Amount in USD settle incoming daily.");
		output.add("Date\t\t| Amount in USD");		
		this.addDataToList(incoming, output);
		output.add("");
		
		output.add("Amount in USD settle outgoing daily.");
		output.add("Date\t\t| Amount in USD");		
		this.addDataToList(outgoing, output);
		output.add("");

		return output;
	}
	
	/**
	 * Put entities into a map by settlement date.
	 * 
	 * @param map key: settlement date, value: sum of amount
	 * @param entity
	 */
	private void putDataToMap(Map<String, Double> map, Entity entity) {
		String key = (Utils.dateAsString(entity.getResetSettlementDate()));
		if (map.get(key) == null) {
			map.put(key, entity.getAmount());					
		} else {
			map.put(key, map.get(key) + entity.getAmount());										
		}		
	}

	/**
	 * Add the processed data to a list for report.
	 * 
	 * @param map processed data
	 * @param output list of formatted data
	 */
	private void addDataToList(Map<String, Double> map, List<String> output) {
		for (Map.Entry<String, Double> entry : map.entrySet()) {
			output.add(entry.getKey() + "\t| " + entry.getValue());
		}		
	}
	
	
}
