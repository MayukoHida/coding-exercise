package com.jpmorgan.trade;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.processor.impl.DailyTradeProcessor;
import com.jpmorgan.trade.processor.impl.RankingProcessor;
import com.jpmorgan.trade.reader.Reader;
import com.jpmorgan.trade.reader.impl.CSVReader;
import com.jpmorgan.trade.report.Report;
import com.jpmorgan.trade.writer.impl.ConsoleWriter;

/**
 * Reads a file and outputs a report in a defined way.
 * 
 * @author Mayuko O'Hare
 *
 */
public class TradeReportRunner {
	
	public static void main(String args[]) {
		
		if (args == null || args.length == 0) {
			throw new RuntimeException("Provide the file path as an argument.");
		}
		
		Reader reader = new CSVReader();
		List<Entity> inputData;
		try {
			inputData = reader.read(Paths.get(args[0]));
		} catch (Exception e) {
			throw new RuntimeException("Cannot read the file.", e);
		}
		
		List<Report> reportList = new ArrayList<>();
		reportList.add(new Report(inputData, new DailyTradeProcessor(), new ConsoleWriter()));
		reportList.add(new Report(inputData, new RankingProcessor(), new ConsoleWriter()));
		
		for (Report report : reportList) {
			report.output();
		}	
	}
}
