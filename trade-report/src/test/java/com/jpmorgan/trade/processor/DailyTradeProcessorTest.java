package com.jpmorgan.trade.processor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.data.enums.Direction;
import com.jpmorgan.trade.processor.impl.DailyTradeProcessor;
import com.jpmorgan.trade.util.Utils;

public class DailyTradeProcessorTest {
	
	private DailyTradeProcessor processor;
	
	@Before
	public void init() {
		processor = new DailyTradeProcessor();
	}
	
	@Test
	public void testProcess() {
		
		Date date = new Date();
		String dateAsString = Utils.dateAsString(date);
		
		List<Entity> inputData = new ArrayList<>();
		Entity entity1 = new Entity();
		entity1.setDirection(Direction.B);
		entity1.setResetSettlementDate(date);
		entity1.setAmount(100);
		
		Entity entity2 = new Entity();
		entity2.setDirection(Direction.S);
		entity2.setResetSettlementDate(date);
		entity2.setAmount(200);
		
		inputData.add(entity1);
		inputData.add(entity2);
		
		List<String> actual = processor.process(inputData);
		
		assertEquals(8, actual.size());
		assertEquals("Amount in USD settle incoming daily.", actual.get(0));
		assertEquals("Date\t\t| Amount in USD", actual.get(1));
		assertEquals(dateAsString + "\t| " + entity2.getAmount(), actual.get(2));
		assertEquals("", actual.get(3));
		assertEquals("Amount in USD settle outgoing daily.", actual.get(4));
		assertEquals("Date\t\t| Amount in USD", actual.get(5));
		assertEquals(dateAsString + "\t| " + entity1.getAmount(), actual.get(6));
		assertEquals("", actual.get(7));	
	}

	@Test
	public void testProcessWithMultipleRecords() {
		
		Date date1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date date2 = cal.getTime();
		String date1AsString = Utils.dateAsString(date1);
		String date2AsString = Utils.dateAsString(date2);
		
		List<Entity> inputData = new ArrayList<>();
		Entity entity1 = new Entity();
		entity1.setDirection(Direction.S);
		entity1.setResetSettlementDate(date1);
		entity1.setAmount(100);

		Entity entity2 = new Entity();
		entity2.setDirection(Direction.S);
		entity2.setResetSettlementDate(date1);
		entity2.setAmount(100);

		Entity entity3 = new Entity();
		entity3.setDirection(Direction.S);
		entity3.setResetSettlementDate(date2);
		entity3.setAmount(150);
		
		Entity entity4 = new Entity();
		entity4.setDirection(Direction.B);
		entity4.setResetSettlementDate(date1);
		entity4.setAmount(200);
		
		Entity entity5 = new Entity();
		entity5.setDirection(Direction.B);
		entity5.setResetSettlementDate(date1);
		entity5.setAmount(200);

		Entity entity6 = new Entity();
		entity6.setDirection(Direction.B);
		entity6.setResetSettlementDate(date2);
		entity6.setAmount(250);
		
		inputData.add(entity1);
		inputData.add(entity2);		
		inputData.add(entity3);
		inputData.add(entity4);
		inputData.add(entity5);
		inputData.add(entity6);
		
		List<String> actual = processor.process(inputData);
		
		assertEquals(10, actual.size());
		assertEquals("Amount in USD settle incoming daily.", actual.get(0));
		assertEquals("Date\t\t| Amount in USD", actual.get(1));
		assertEquals(date1AsString + "\t| " + (entity1.getAmount() + entity2.getAmount()), actual.get(2));
		assertEquals(date2AsString + "\t| " + entity3.getAmount(), actual.get(3));		
		assertEquals("", actual.get(4));
		assertEquals("Amount in USD settle outgoing daily.", actual.get(5));
		assertEquals("Date\t\t| Amount in USD", actual.get(6));
		assertEquals(date1AsString + "\t| " + (entity4.getAmount() + entity5.getAmount()), actual.get(7));
		assertEquals(date2AsString + "\t| " + entity6.getAmount(), actual.get(8));		
		assertEquals("", actual.get(9));	
	}

}
