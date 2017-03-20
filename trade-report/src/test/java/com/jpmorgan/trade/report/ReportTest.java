package com.jpmorgan.trade.report;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.trade.data.Entity;
import com.jpmorgan.trade.processor.Processor;
import com.jpmorgan.trade.writer.Writer;

public class ReportTest {
	
	private Report report;	
	private Processor processor;
	private Writer writer;
	
	private class ProfessorTest implements Processor {
		@Override
		public List<String> process(List<Entity> inputData) {
			System.out.println("ProfessorTest#process");
			return Arrays.asList("output");
		}		
	};
	
	private class WriterTest implements Writer {
		@Override
		public void write(List<String> outputData, Path outputPath) {			
			System.out.println("WriterTest#write");
		}
	};
	
	@Before
	public void init() {
		processor = new ReportTest.ProfessorTest();	
		writer = new ReportTest.WriterTest();
		
		List<Entity> inputData = new ArrayList<>();
		inputData.add(new Entity());
		report = new Report(inputData, processor, writer);				
	}
	
	@Test
	public void testOutput() {
		report.output();
	}

}
