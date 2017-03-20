package com.jpmorgan.trade.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.jpmorgan.trade.data.Entity;

/**
 * Util class.
 * 
 * @author Mayuko O'Hare
 *
 */
public class Utils {
	
	private static final SimpleDateFormat stringToDate = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
	private static final SimpleDateFormat dateToString = new SimpleDateFormat("dd MMM yyyy EEE", Locale.UK);
	
	/**
	 * Disable default constructor.
	 */
	private Utils() {		
	}
	
	/** 
	 * Calculate USD amount of a trade
	 * 
	 * @param entity
	 * @return USD amount of a trade
	 */
	public static double calculateAmount(Entity entity) {		
		return entity.getAgreedFx() * entity.getUnits() * entity.getPricePerUnit();
	}
	
	/**
	 * Convert date as Date to date as String.
	 * 
	 * @param date as Date
	 * @return date as String
	 */
	public static String dateAsString(Date date) {
		return dateToString.format(date);
	}
	
	/**
	 * Convert date as String to date as Date
	 * 
	 * @param dateAsString
	 * @return date as Date
	 * @throws ParseException
	 */
	public static Date parseStringToDate(String dateAsString) throws ParseException {
		Date date = null;
		try {
			date = stringToDate.parse(dateAsString);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}
	
	/**
	 * Checks if the given String value is null or empty.
	 * 
	 * @param s String value
	 * @return String value
	 * @throws Exception
	 */
	public static String validateStringValue(String s) throws Exception {
		if (s == null || s.isEmpty()) {
			throw new Exception("String value is null or empty");
		}
		return s;
	}
}
