package com.simple.bsp.common.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	private static JsonFactory jsonFactory = new JsonFactory();

	public static <T> Object fromJson(String jsonAsString, Class<T> clazz)
			throws JsonMappingException, JsonParseException, IOException {
		return mapper.readValue(jsonAsString, clazz);
	}

	public static <T> Object fromJson(FileReader fr, Class<T> clazz)
			throws JsonParseException, IOException {
		return mapper.readValue(fr, clazz);
	}

	public static String toJson(Object obj){
		StringWriter sw = new StringWriter();
		JsonGenerator jg;
		try {
			jg = jsonFactory.createJsonGenerator(sw);
			jg.useDefaultPrettyPrinter();
			mapper.writeValue(jg, obj);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}

	public static void toJson(Object obj, FileWriter fw){
		JsonGenerator jg;
		try {
			jg = jsonFactory.createJsonGenerator(fw);
			jg.useDefaultPrettyPrinter();
			mapper.writeValue(jg, obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
