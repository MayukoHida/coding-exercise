package com.jpmorgan.trade;

import org.junit.Test;

import com.jpmorgan.trade.TradeReportRunner;


public class TradeReportRunnerIT {

	@Test
	public void testTradeReportRunner() {
		String[] args = {"src/test/resources/sample.csv"};		
		TradeReportRunner.main(args);		
	}
}
