package com.covid19.base.deserialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StringDeserializer extends StdDeserializer<String> {

	private static final long serialVersionUID = 8813450070692176307L;

	public StringDeserializer(Class<?> vc) {
		super(vc);
	}

	public StringDeserializer() {
		this(null);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String text = p.getText();
		if (text == null) {
			return null;
		}

		String normal = text.replaceAll("\\s+", " ").trim();
		if (normal.isEmpty()) {
			return null;
		}

		return normal;
	}

}
