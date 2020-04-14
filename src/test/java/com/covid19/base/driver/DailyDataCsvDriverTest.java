package com.covid19.base.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.covid19.base.DailyDataCsvDriver;

public class DailyDataCsvDriverTest {

	@Test
	public void test() throws FileNotFoundException, IOException {
		int lineNumber = 0;
		try (DailyDataCsvDriver driver = new DailyDataCsvDriver(
				new BufferedReader(new FileReader(new File("src/test/resources/sample.csv"))))) {
			while (driver.hasNext()) {
				driver.next();
				lineNumber++;
			}
		}
		Assert.assertEquals(3, lineNumber);
	}
}
