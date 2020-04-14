package com.covid19.base;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

import com.covid19.base.deserialize.DailyDataCsv;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

public class DailyDataCsvDriver implements Closeable {

	private final MappingIterator<DailyDataCsv> iterator;

	public DailyDataCsvDriver(BufferedReader reader) throws IOException {
		iterator = new CsvMapper().readerFor(DailyDataCsv.class).with(buildSchema(reader)).readValues(reader);
	}

	@Override
	public void close() throws IOException {
		iterator.close();
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public DailyData next() {
		return iterator.next();
	}

	private CsvSchema buildSchema(BufferedReader reader) throws IOException {
		Builder builder = CsvSchema.builder();
		for (String column : reader.readLine().split(",")) {
			builder.addColumn(normalize(column));
		}
		return builder.build();
	}

	private String normalize(String column) {
		return column.trim().toUpperCase().replaceAll("[^A-Z0-9]+", "_");
	}
}
