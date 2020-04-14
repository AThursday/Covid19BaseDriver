package com.covid19.base.deserialize;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 6763946848426309382L;

	private static final DateTimeFormatterFactory FORMATTER_FACTORY = new DateTimeFormatterFactory();

	public LocalDateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	public LocalDateTimeDeserializer() {
		this(null);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		DateTimeFormatter formatter = FORMATTER_FACTORY.factory(p.getText());
		return LocalDateTime.parse(p.getText(), formatter);
	}

	private static class DateTimeFormatterFactory {

		private final String[] patterns = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "M/d/yyyy H:mm",
				"M/d/yy H:mm" };

		private final DateTimeFormatter[] formatters = new DateTimeFormatter[patterns.length];

		public DateTimeFormatter factory(String value) {
			int patternIndex = findPatternIndex(value);
			if (formatters[patternIndex] == null) {
				formatters[patternIndex] = DateTimeFormatter.ofPattern(patterns[patternIndex]);
			}
			return formatters[patternIndex];
		}

		private int findPatternIndex(String value) {
			if (value.contains("T")) {
				return 0;
			} else if (value.length() >= 19) {
				return 1;
			} else if (value.contains("/")) {
				int yearLength = value.substring(value.lastIndexOf("/"), value.indexOf(" ")).length() - 1;
				if (yearLength == 4) {
					return 2;
				} else {
					return 3;
				}
			}

			throw new IllegalStateException("Unrecognizable date pattern: \"" + value + "\"");
		}

	}

}
