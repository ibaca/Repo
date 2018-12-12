package com.royalty.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royalty.model.Episode;
import com.royalty.model.Studio;

public class UtilsJSON {	
	
	public final static ObjectMapper mapper = new ObjectMapper();
	public final static JSONParser parser = new JSONParser();
	
	public static List<Studio> getStudiosfromJSON() {
		JSONArray results = new JSONArray();
		try {

			InputStream resource = UtilsJSON.class.getResourceAsStream("studios.json");
			JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(resource));
		 results = (JSONArray) jsonObject.get("studios");
		 
		} catch (ParseException | IOException e) {			
			return Collections.emptyList();
		}

		return (List<Studio>) results.stream()
				.map(elem -> createStudioFromRawJson(elem))
				.collect(Collectors.toList());
	}

	private static Studio createStudioFromRawJson(Object elem) {
		try {
			return mapper.readValue(elem.toString(), Studio.class);
		} catch (IOException e) {			
			return new Studio();
		}
	}
	
	public static List<Episode> getEpisodesfromJSON() {
		JSONArray results = new JSONArray();
		try {

			InputStream resource = UtilsJSON.class.getResourceAsStream("episodes.json");
			JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(resource));
		 results = (JSONArray) jsonObject.get("episodes");
		 
		} catch (ParseException | IOException e) {			
			return Collections.emptyList();
		}

		return (List<Episode>) results.stream()
				.map(elem -> createEpisodeFromRawJson(elem))
				.collect(Collectors.toList());

	}
	
	private static Episode createEpisodeFromRawJson(Object elem) {
		try {
			return mapper.readValue(elem.toString(), Episode.class);
		} catch (IOException e) {			
			return new Episode();
		}
	}
}
