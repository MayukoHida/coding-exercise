package com.jpmorgan.trade.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import com.jpmorgan.trade.data.Entity;

public class UtilsTest {
	
	@Test
	public void testCalculateAmount() {
		Entity entity = new Entity();
		entity.setAgreedFx(10);
		entity.setUnits(10);
		entity.setPricePerUnit(10);
		
		assertEquals(1000, Utils.calculateAmount(entity), 0);
	}
	
	@Test
	public void testDateAsString() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy EEE", Locale.UK);
		String expected = sdf.format(date);
		assertEquals(expected, Utils.dateAsString(date));		
	}
	
	@Test
	public void testParseStringToDate() throws ParseException {
		String dateAsString = "01 Mar 2017";
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
		Date expected = sdf.parse(dateAsString);
		assertEquals(expected, Utils.parseStringToDate(dateAsString));
	}
	
	@Test(expected=ParseException.class)
	public void testParseStringToDateThrowsException() throws ParseException {
		String dateAsString = "01-03-2017";
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
		sdf.parse(dateAsString);
		Utils.parseStringToDate(dateAsString);
	}

	@Test
	public void testValidateStringValue() throws Exception {
		assertEquals("test", Utils.validateStringValue("test"));
	}
	
	@Test(expected=Exception.class)
	public void testValidateStringValueWhenValueIsNull() throws Exception {
		Utils.validateStringValue(null);
	}

	@Test(expected=Exception.class)
	public void testValidateStringValueWhenValueIsEmpty() throws Exception {
		Utils.validateStringValue("");
	}
}
