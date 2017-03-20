package com.jpmorgan.trade.processor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.data.enums.Currency;
import com.jpmorgan.trade.data.enums.Direction;
import com.jpmorgan.trade.processor.impl.RankingProcessor;

public class RankingProcessorTest {

	private RankingProcessor processor;
	
	private static final String COLUMN_NAME =
			"Entity \t| "
					+ "Buy/Sell \t| "				
					+ "AgreedFx \t| "
					+ "Currency \t| "				
					+ "InstructionDate \t\t| "
					+ "SettlementDate \t\t| "
					+ "Units \t| "
					+ "Price per Unit \t| "
					+ "Reset SettlementDate \t\t| "
					+ "Amount in USD";

	@Before
	public void init() {
		processor = new RankingProcessor();
	}
	
	@Test
	public void testProcess() {
		
		Date date = new Date();
		
		List<Entity> inputData = new ArrayList<>();
		
		Entity entity1 = new Entity();
		entity1.setName("entity1");
		entity1.setAgreedFx(100);
		entity1.setAmount(100);
		entity1.setCurrency(Currency.AED);
		entity1.setDirection(Direction.S);
		entity1.setInstructionDate(date);
		entity1.setPricePerUnit(100);
		entity1.setResetSettlementDate(date);
		entity1.setSettlementDate(date);
		entity1.setUnits(100);
		inputData.add(entity1);
		
		Entity entity2 = new Entity();
		entity2.setName("entity2");
		entity2.setAgreedFx(100);
		entity2.setAmount(200);
		entity2.setCurrency(Currency.AED);
		entity2.setDirection(Direction.S);
		entity2.setInstructionDate(date);
		entity2.setPricePerUnit(100);
		entity2.setResetSettlementDate(date);
		entity2.setSettlementDate(date);
		entity2.setUnits(200);
		inputData.add(entity2);

		Entity entity3 = new Entity();
		entity3.setName("entity3");
		entity3.setAgreedFx(100);
		entity3.setAmount(300);
		entity3.setCurrency(Currency.AED);
		entity3.setDirection(Direction.B);
		entity3.setInstructionDate(date);
		entity3.setPricePerUnit(100);
		entity3.setResetSettlementDate(date);
		entity3.setSettlementDate(date);
		entity3.setUnits(300);
		inputData.add(entity3);

		Entity entity4 = new Entity();
		entity4.setName("entity4");
		entity4.setAgreedFx(100);
		entity4.setAmount(400);
		entity4.setCurrency(Currency.AED);
		entity4.setDirection(Direction.B);
		entity4.setInstructionDate(date);
		entity4.setPricePerUnit(100);
		entity4.setResetSettlementDate(date);
		entity4.setSettlementDate(date);
		entity4.setUnits(400);
		inputData.add(entity4);
		
		Entity entity5 = new Entity();
		entity5.setName("entity5");
		entity5.setAgreedFx(100);
		entity5.setAmount(500);
		entity5.setCurrency(Currency.AED);
		entity5.setDirection(Direction.B);
		entity5.setInstructionDate(date);
		entity5.setPricePerUnit(100);
		entity5.setResetSettlementDate(date);
		entity5.setSettlementDate(date);
		entity5.setUnits(500);
		inputData.add(entity5);

		List<String> actual = processor.process(inputData);

		assertEquals(11, actual.size());
		assertEquals("Ranking of entities based on incoming amount.", actual.get(0));
		assertEquals(COLUMN_NAME, actual.get(1));
		assertEquals(entity2.toString(), actual.get(2));
		assertEquals(entity1.toString(), actual.get(3));		
		assertEquals("", actual.get(4));
		assertEquals("Ranking of entities based on outgoing amount.", actual.get(5));
		assertEquals(COLUMN_NAME, actual.get(6));
		assertEquals(entity5.toString(), actual.get(7));
		assertEquals(entity4.toString(), actual.get(8));		
		assertEquals(entity3.toString(), actual.get(9));				
		assertEquals("", actual.get(10));	

		
	}
	
}
