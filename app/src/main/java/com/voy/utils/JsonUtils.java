package com.voy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Gson类库的封装工具类，专门负责解析json数据</br> 内部实现了Gson对象的单例
 */
public class JsonUtils {

	private static Gson gson = null;

	static {
		if (gson == null) {
//			gson = new Gson();
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
//			gson = new GsonBuilder()  
//			  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
//			  .create();
		}
	}

	public JsonUtils() {

	}

	/**
	 * 将对象转换成json格式
	 * 
	 * @param ts
	 * @return
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
			
		}
		return jsonStr;
	}


	/**
	 * 将json格式转换成list对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static List<?> jsonToList(String jsonStr) {
		List<?> objList = null;
		if (gson != null) {
			Type type = new com.google.gson.reflect.TypeToken<List<?>>() {
			}.getType();
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * 将json格式转换成list对象
	 * 
	 * @param
	 * @return
	 */
	public static <T> List<T> jsonToList(String data, Class<T> klass) {
		if(data == null||"".equals(data)) return null;
		return gson.fromJson(data, new TypeOfT<T>(klass));
	}

	/**
	 * 将json格式转换成list对象，并准确指定类型
	 * 
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static List<?> jsonToList(String jsonStr, Type type) {
		List<?> objList = null;
		if (gson != null) {
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> jsonToMap(String jsonStr) {
		Map<?, ?> objMap = null;
		if (gson != null) {
			Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
			}.getType();
			objMap = gson.fromJson(jsonStr, type);
		}
		return objMap;
	}

//	/**
//	 * 将json转换成bean对象
//	 *
//	 * @param jsonStr
//	 * @return
//	 */
	public static Object jsonToBean(String jsonStr, Class<?> cl) {
		Object obj = null;
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return obj;
	}
//	public static <T>  T jsonToBean(String data, Class<T> klass) {
//		if (data == null || "".equals(data)) return null;
//		return gson.fromJson(data, new TypeOfT<T>(klass));
//	}
}