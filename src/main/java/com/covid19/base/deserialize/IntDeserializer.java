package com.covid19.base.deserialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class IntDeserializer extends StdDeserializer<Integer> {

	private static final long serialVersionUID = -4417877753128183423L;

	public IntDeserializer(Class<?> vc) {
		super(vc);
	}

	public IntDeserializer() {
		this(null);
	}

	@Override
	public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String text = p.getText();
		if (text == null || text.trim().isEmpty()) {
			return 0;
		}
		return Integer.valueOf(text);
	}

}
