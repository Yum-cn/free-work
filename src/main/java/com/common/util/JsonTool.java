package com.common.util;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Collection;

public class JsonTool {
	
	
	public static JSONObject fromObject(Object obj) {
		
		if(obj==null){
			return null;
		}
		
		Field[] fields = obj.getClass().getDeclaredFields();
		JSONObject jsonObject = new JSONObject();
		
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				String key = field.getName();
				Object value = field.get(obj);
				if (StringUtils.isNotBlank(key)) {
					if(StringUtils.equals(field.getGenericType().toString(), "class java.lang.Long")){
						jsonObject.put(key, value.toString());
					}else{
						jsonObject.put(key, value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return jsonObject;
	}
	
	/*public static String objToJson(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		JSONObject jsonObject = new JSONObject();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				String key = field.getName();
				Object value = field.get(obj);
				if (StringUtils.isNotBlank(key)) {
					jsonObject.put(key, value.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return jsonObject.toString();
	}

	public static String listToJson(Collection objs) {
		JSONArray jsonArray = new JSONArray();
		for (Object obj : objs) {
			JSONObject jsonObject = new JSONObject();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					String key = field.getName();
					Object value = field.get(obj);
					if (StringUtils.isNotBlank(key)) {
						jsonObject.put(key, value.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}*/
}