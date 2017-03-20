package com.jpmorgan.trade.util;

import java.util.Calendar;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.data.enums.Currency;
import com.jpmorgan.trade.data.enums.Direction;

/**
 * Map data as text to data as entity.
 * 
 * @author Mayuko O'Hare
 *
 */
public class EntityMapper {
		
	/**
	 * Disable a default constructor.
	 */
	private EntityMapper() {		
	}
	
	/**
	 * Map a String text to an entity object.
	 * If the text is imcomplete or damages, skip the text.
	 * 
	 * @param line
	 * @return entity
	 */
	public static Entity map(String line) {
		String[] s = line.split(",");
		Entity entity = new Entity();
		
		try {
			entity.setName(Utils.validateStringValue(s[0]));		
			
			for (Direction direction : Direction.values()) {
				if (direction.name().equals(Utils.validateStringValue(s[1]))) {
					entity.setDirection(direction);							
				}
			}
		
			entity.setAgreedFx(Double.valueOf(Utils.validateStringValue(s[2])));
			
			for (Currency currency : Currency.values()) {
				if (currency.name().equals(Utils.validateStringValue(s[3]))) {
					entity.setCurrency(currency);							
				}
			}
			
			entity.setInstructionDate(Utils.parseStringToDate(Utils.validateStringValue(s[4])));
			entity.setSettlementDate(Utils.parseStringToDate(Utils.validateStringValue(s[5])));
			
			Calendar c = Calendar.getInstance();
			c.setTime(entity.getSettlementDate());
			int day = c.get(Calendar.DAY_OF_WEEK);
			if (entity.getCurrency() == Currency.AED || entity.getCurrency() == Currency.SAR) {
				if (day == Calendar.FRIDAY) {
					c.add(Calendar.DAY_OF_MONTH, 2);
				} else if (day == Calendar.SATURDAY) {
					c.add(Calendar.DAY_OF_MONTH, 1);							
				}
			} else {
				if (day == Calendar.SATURDAY) {
					c.add(Calendar.DAY_OF_MONTH, 2);
				} else if (day == Calendar.SUNDAY) {
					c.add(Calendar.DAY_OF_MONTH, 1);				
				}			
			}
			entity.setResetSettlementDate(c.getTime());			
			
			entity.setUnits(Integer.valueOf(Utils.validateStringValue(s[6])));
			entity.setPricePerUnit(Double.valueOf(Utils.validateStringValue(s[7])));
			entity.setAmount(Utils.calculateAmount(entity));
		} catch (Exception e) {
			System.err.println("Error occured in loading an entity. Skipping Entity: " + line + "Error: " + e.getMessage());
		}

		return entity;
	}	

}
