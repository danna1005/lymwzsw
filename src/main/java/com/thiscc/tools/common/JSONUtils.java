package com.thiscc.tools.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.shensoft.tools.utils.JsonValueProcessorImpl;

public class JSONUtils {
	
	private static Logger logger = Logger.getLogger(JSONUtils.class);
	
	// JsonConfig
	private static final JsonConfig JSON_CONGIF = JsonConfig.getInstance();

	// json 转换日期格式
	private static final String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss.0"};
    
    static {
        JSON_CONGIF.registerJsonValueProcessor(java.util.Date.class,
                new JsonValueProcessorImpl());
        JSON_CONGIF.registerJsonValueProcessor(java.sql.Date.class,
                new JsonValueProcessorImpl());
        JSON_CONGIF.registerJsonValueProcessor(Timestamp.class,
                new JsonValueProcessorImpl());
        
        MorpherRegistry morpherRegistry = net.sf.json.util.JSONUtils.getMorpherRegistry(); 
        morpherRegistry.registerMorpher(new DateMorpher(dateFormats));              
        
    }
	



	public static final String toJson(Object paramObject) {
		if (paramObject == null)
			return null;
		JSONObject localJSONObject = JSONObject.fromObject(paramObject);
		return localJSONObject.toString();
	}

	public static final String toJson(Object[] paramArrayOfObject) {
		if (paramArrayOfObject == null)
			return null;
		JSONArray localJSONArray = JSONArray.fromObject(paramArrayOfObject);
		return localJSONArray.toString();
	}

	public static final Object toBean(Class clazz, String strJson) {
		if (StringUtils.isEmpty(strJson))
			return null;
		try {
			JSONObject localJSONObject = JSONObject.fromObject(strJson);
			return JSONObject.toBean(localJSONObject, clazz);
		} catch (Exception e) {
		    logger.error("to bean error: " + e.getMessage(), e);
		}
		return null;
	}

	public static final Object toBean(Class clazz, JSONObject objJson) {
		if (objJson == null)
			return null;
		try {
			return JSONObject.toBean(objJson, clazz);
		} catch (Exception e) {
		    logger.error("to bean error: " + e.getMessage(), e);
		}
		return null;
	}

	public static final List<Object> jsonArrayToBean(Class paramClass,
			String paramString) {
		if (StringUtils.isEmpty(paramString))
			return null;
		try {
			JSONArray localJSONArray = JSONArray.fromObject(paramString);
			return jsonArrayToBean(paramClass, localJSONArray);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	public static final List<Object> jsonArrayToBean(Class paramClass,
			JSONArray paramJSONArray) {
		try {
			ArrayList localArrayList = new ArrayList();
			for (int i = 0; i < paramJSONArray.size(); ++i) {
				JSONObject localJSONObject = (JSONObject) paramJSONArray.get(i);
				localArrayList.add(JSONObject.toBean(localJSONObject,
						paramClass));
			}
			return localArrayList;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	public static final String getRows(List<Object> paramList) {
		if (paramList == null)
			return null;
		JSONArray localJSONArray = JSONArray.fromObject(paramList);
		return localJSONArray.toString();
	}

	private static final JSONArray getRows(List<Object[]> paramList,
			String[] paramArrayOfString1, String[] paramArrayOfString2,
			String paramString) {
		JSONArray localJSONArray = new JSONArray();
		if ((paramList == null)
				|| ((paramArrayOfString1 == null) && (paramArrayOfString2 == null)))
			return localJSONArray;
		int i = paramList.size();
		int j = (paramArrayOfString1 != null) ? paramArrayOfString1.length : 0;
		if (paramArrayOfString2 == null)
			paramArrayOfString2 = paramArrayOfString1;
		int k = paramArrayOfString2.length;
		long l = -1L;
		if (!(StringUtils.isEmpty(paramString)))
			l = new Integer(paramString).intValue();
		for (int i1 = 0; i1 < i; ++i1) {
			Object[] arrayOfObject = (Object[]) paramList.get(i1);
			JSONObject localJSONObject1 = new JSONObject();
			if (l > -1L) {
				l += 1L;
				localJSONObject1.put("rowID", Long.valueOf(l));
			}
			JSONObject localJSONObject2 = null;
			for (int i2 = 0; i2 < arrayOfObject.length; ++i2) {
				if ((i2 >= j) && (i2 >= k))
					break;
				Object localObject = arrayOfObject[i2];
				if (localObject == null)
					continue;
				if ("String".equals(localObject.getClass().getSimpleName())) {
					String str = localObject.toString();
					if ((str.indexOf("{") == 0)
							&& (str.lastIndexOf("}") == str.length() - 1))
						localObject = " " + str;
					else
						localObject = com.thiscc.tools.utils.StringUtils.formatHtml(str);
				}
				if (i2 < k) {
					localJSONObject1.put(paramArrayOfString2[i2], localObject);
				} else {
					if (localJSONObject2 == null)
						localJSONObject2 = new JSONObject();
					localJSONObject2.put(paramArrayOfString1[i2], localObject);
				}
			}
			if (localJSONObject2 != null)
				localJSONObject1.put("defField", localJSONObject2);
			localJSONArray.add(localJSONObject1);
		}
		return ((JSONArray) localJSONArray);
	}
}