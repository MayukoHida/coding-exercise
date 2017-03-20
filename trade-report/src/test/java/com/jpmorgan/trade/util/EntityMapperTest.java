package com.jpmorgan.trade.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.data.enums.Currency;
import com.jpmorgan.trade.data.enums.Direction;

public class EntityMapperTest {
	
	@Test
	public void testMapCase1() throws ParseException {
		String line = "test,B,0.50,SGP,04 Jan 2016,05 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.B, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.SGP, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("05 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("05 Jan 2016"), actual.getResetSettlementDate());		
	}

	@Test
	public void testMapCase2() throws ParseException {
		String line = "test,S,0.50,JPN,04 Jan 2016,05 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.S, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.JPN, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("05 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("05 Jan 2016"), actual.getResetSettlementDate());
		
	}
	
	@Test
	public void testMapWhenCurrencyIsAEDANDSettlementDateIsFriday() throws ParseException {
		String line = "test,S,0.50,AED,04 Jan 2016,08 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.S, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.AED, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("08 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("10 Jan 2016"), actual.getResetSettlementDate());		
	}
	
	@Test
	public void testMapWhenCurrencyIsAEDANDSettlementDateIsSaturday() throws ParseException {
		String line = "test,S,0.50,AED,04 Jan 2016,09 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.S, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.AED, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("09 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("10 Jan 2016"), actual.getResetSettlementDate());		
	}

	@Test
	public void testMapWhenCurrencyIsSARANDSettlementDateIsFriday() throws ParseException {
		String line = "test,S,0.50,SAR,04 Jan 2016,08 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.S, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.SAR, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("08 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("10 Jan 2016"), actual.getResetSettlementDate());		
	}
	
	@Test
	public void testMapWhenCurrencyIsSARANDSettlementDateIsSaturday() throws ParseException {
		String line = "test,S,0.50,SAR,04 Jan 2016,09 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.S, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.SAR, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("09 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("10 Jan 2016"), actual.getResetSettlementDate());
		
	}

	@Test
	public void testMapWhenCurrencyIsUSDANDSettlementDateIsSaturday() throws ParseException {
		String line = "test,S,0.50,USD,04 Jan 2016,09 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.S, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.USD, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("09 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("11 Jan 2016"), actual.getResetSettlementDate());		
	}
	
	@Test
	public void testMapWhenCurrencyIsUSDANDSettlementDateIsSunday() throws ParseException {
		String line = "test,S,0.50,USD,04 Jan 2016,10 Jan 2016,200,100.0";
		Entity actual = EntityMapper.map(line);
		assertEquals("test", actual.getName());
		assertEquals(Direction.S, actual.getDirection());
		assertEquals(0.50, actual.getAgreedFx(), 0);
		assertEquals(Currency.USD, actual.getCurrency());
		assertEquals(Utils.parseStringToDate("04 Jan 2016"), actual.getInstructionDate());
		assertEquals(Utils.parseStringToDate("10 Jan 2016"), actual.getSettlementDate());
		assertEquals(200, actual.getUnits());
		assertEquals(100.0, actual.getPricePerUnit(), 0);
		assertEquals(0.50 * 200 * 100.0, actual.getAmount(), 0);
		assertEquals(Utils.parseStringToDate("11 Jan 2016"), actual.getResetSettlementDate());
		
	}
	
	@Test
	public void testMapWhenEntityIsNotComplete() throws ParseException {
		String line = ",S,0.50,USD,04 Jan 2016,10 Jan 2016,200,100.0";
		EntityMapper.map(line);		
	}


}
