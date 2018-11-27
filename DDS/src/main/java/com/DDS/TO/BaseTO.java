package com.DDS.TO;

import java.util.HashMap;
import java.util.Map;

public class BaseTO {

	private Map<String,String> baseMap = new HashMap<String,String>();
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, String> getBaseMap() {
		return baseMap;
	}
	
	public void addBaseMap(String key, String val) {
		baseMap.put(key , val);
	}
	
	public void setBaseMap(Map<String, String> baseMap) {
		this.baseMap = baseMap;
	}

	
}
