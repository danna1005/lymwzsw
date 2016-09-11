package com.thiscc.tools.common;

import java.awt.Color;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.thiscc.tools.utils.StringUtils;

@SuppressWarnings("unchecked")
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	
	//~ Static fields/initializer ==================================================================
	
	private static Map<String, Integer> JAVA_TYPE_MAP = new HashMap<String, Integer>();
	
	private static Logger logger = Logger.getLogger(BeanUtils.class);
	
	//~ Methods ====================================================================================
	
	/**
	 * 
	 * @param paramObject
	 * @param paramMap
	 */
	public static void setProperties(Object paramObject, Map<String, Object> paramMap) {
		try {
			Iterator<String> it = paramMap.keySet().iterator();
			while (it.hasNext()) {
				String strKey = (String) it.next();
				PropertyDescriptor propDescriptor = null;
				try {
					propDescriptor = new PropertyDescriptor(strKey, paramObject.getClass());
				} catch (IntrospectionException e) {
					logger.warn("can't get property [" + strKey + "]. ");
				}
				if ( propDescriptor == null ) continue;
				Class localClass = propDescriptor.getPropertyType();
				
				String arrayOfString = null;
				if(paramMap.get(strKey) instanceof Date){
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
					arrayOfString = df.format(((java.util.Date)paramMap.get(strKey)));
				}else if(paramMap.get(strKey) instanceof Integer){
					arrayOfString = ((Integer)paramMap.get(strKey)).toString();
				}else{
					arrayOfString = (String)paramMap.get(strKey);
				}
				Object localObject = null;
				String str2 = localClass.getPackage().toString();
				if (str2.indexOf("com.thiscc") != -1)
					localObject = getSonBeanValue(
							localClass.getCanonicalName(), arrayOfString); 
				else
					localObject = decode(localClass, arrayOfString);
				propDescriptor.getWriteMethod().invoke(paramObject,
						new Object[] { localObject });
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public static Object getSonBeanValue(String paramString1,
			String paramString2) throws IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, SecurityException,
			NoSuchMethodException, ClassNotFoundException {
		Object localObject = null;
		if (StringUtils.isEmpty(paramString2))
			return localObject;
		Class localClass = Class.forName(paramString1);
		Field[] arrayOfField = localClass.getDeclaredFields();
		String str = arrayOfField[0].getName();
		Constructor[] arrayOfConstructor = localClass.getConstructors();
		for (int i = 0; i < arrayOfConstructor.length; ++i) {
			Class[] arrayOfClass = arrayOfConstructor[i].getParameterTypes();
			if (arrayOfClass.length != 1)
				continue;
			localObject = arrayOfConstructor[i]
					.newInstance(new Object[] { paramString2 });
		}
		return localObject;
	}

	public static boolean existsField(String paramString1, String paramString2) {
		try {
			Class localClass = Class.forName(paramString1);
			Field[] arrayOfField = localClass.getDeclaredFields();
			for (int i = 0; i < arrayOfField.length; ++i)
				if (arrayOfField[i].getName().equals(paramString2))
					return true;
		} catch (ClassNotFoundException localClassNotFoundException) {
			localClassNotFoundException.printStackTrace();
		}
		return false;
	}

	public static Map getProperties(Object paramObject) {
		HashMap localHashMap = new HashMap();
		if (paramObject == null)
			return localHashMap;
		try {
			BeanInfo localBeanInfo = Introspector.getBeanInfo(paramObject
					.getClass());
			PropertyDescriptor[] arrayOfPropertyDescriptor = localBeanInfo
					.getPropertyDescriptors();
			String[] arrayOfString1 = new String[arrayOfPropertyDescriptor.length];
			for (int i = 0; i < arrayOfString1.length; ++i) {
				String str1 = arrayOfPropertyDescriptor[i].getName();
				String str2 = arrayOfPropertyDescriptor[i].getPropertyType()
						.getSimpleName();
				if (str1.equals("class"))
					continue;
				if (str2.equals("Set"))
					continue;
				Object localObject1 = arrayOfPropertyDescriptor[i]
						.getReadMethod().invoke(paramObject, new Object[0]);
				if (localObject1 == null)
					continue;
				if (str2.equals("Date")) {
					Object localObject2 = localObject1.toString();
					if (((String) localObject2).length() > 19) {
						localObject2 = ((String) localObject2).substring(0, 19);
						localObject2 = ((String) localObject2).replace(
								" 00:00:00", "");
						localObject1 = localObject2;
					}
				}
				Object localObject2 = localObject1.getClass();
				String str3 = ((Class) localObject2).getCanonicalName();
				Class localClass = arrayOfPropertyDescriptor[i]
						.getPropertyType();
				String str4 = localClass.getPackage().toString();
				if (str4.indexOf("com.thiscc") != -1) {
					String[] arrayOfString2 = str3.split("\\$\\$");
					Object localObject3 = Class.forName(arrayOfString2[0])
							.newInstance();
					localObject3 = localObject1;
					Map localMap = getProperties(localObject3);
					localHashMap.put(str1, localMap);
				} else {
					localHashMap.put(str1, encode(localObject1));
				}
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return ((Map) localHashMap);
	}

	private static String encode(Object paramObject) {
		Object localObject;
		if (paramObject instanceof String)
			return StringUtils.formatJSON((String) paramObject);
		if (paramObject instanceof String[]) {
			localObject = new StringBuffer();
			String[] arrayOfString = (String[]) (String[]) paramObject;
			if (arrayOfString.length > 0) {
				((StringBuffer) localObject).append(StringUtils
						.encodeBase64(arrayOfString[0]));
				for (int i = 1; i < arrayOfString.length; ++i)
					((StringBuffer) localObject).append(",").append(
							StringUtils.encodeBase64(arrayOfString[i]));
			}
			return StringUtils.formatJSON(((StringBuffer) localObject)
					.toString());
		}
		if ((paramObject instanceof Boolean)
				|| (paramObject instanceof Integer)
				|| (paramObject instanceof Long)
				|| (paramObject instanceof Float)
				|| (paramObject instanceof Double))
			return paramObject.toString();
		if (paramObject instanceof Color) {
			localObject = (Color) paramObject;
			return ((Color) localObject).getRed() + ","
					+ ((Color) localObject).getGreen() + ","
					+ ((Color) localObject).getBlue();
		}
		if (paramObject instanceof Class)
			return ((Class) paramObject).getName();
		return ((String) null);
	}

	private static Object decode(Class paramClass, String[] paramArrayOfString)
			throws Exception {
		if (paramArrayOfString == null)
			return null;
		StringBuffer localStringBuffer = new StringBuffer("");
		for (int i = 0; i < paramArrayOfString.length; ++i) {
			if (i > 0)
				localStringBuffer.append(",");
			localStringBuffer.append(paramArrayOfString[i]);
		}
		return decode(paramClass, localStringBuffer.toString());
	}

	private static Object decode(Class paramClass, String paramString)
			throws Exception {
		if ((paramString == null) || (paramString.equals("")))
			return null;
		String str = paramClass.getName();
		if (str.equals("java.lang.String"))
			return paramString;
		if (str.equals("[Ljava.lang.String;")) {
			Object localObject = new StringTokenizer(paramString, ",");
			String[] arrayOfString = new String[((StringTokenizer) localObject)
					.countTokens()];
			for (int j = 0; j < arrayOfString.length; ++j)
				arrayOfString[j] = StringUtils
						.decodeBase64(((StringTokenizer) localObject)
								.nextToken());
			return arrayOfString;
		}
		Object localObject = paramClass.getSimpleName();
		int i = ((Integer) JAVA_TYPE_MAP.get(localObject)).intValue();
		return decode(Integer.valueOf(i), paramString);
	}

	public static Object[] decode(String paramString,
			String[] paramArrayOfString) throws Exception {
		if (paramArrayOfString == null)
			return null;
		Object[] arrayOfObject = new Object[paramArrayOfString.length];
		for (int i = 0; i < paramArrayOfString.length; ++i)
			arrayOfObject[i] = decode(paramString, paramArrayOfString[i]);
		return arrayOfObject;
	}

	public static Object decode(String paramString1, String paramString2)
			throws Exception {
		if ("String".equals(paramString1))
			return paramString2;
		int i = ((Integer) JAVA_TYPE_MAP.get(paramString1)).intValue();
		return decode(Integer.valueOf(i), paramString2);
	}

	private static Object decode(Integer paramInteger, String paramString)
			throws Exception {
		if ((paramString == null) || (paramString.equals("")))
			return null;
		switch (paramInteger.intValue()) {
		case 0:
			return Integer.valueOf(paramString);
		case 1:
			return Long.valueOf(paramString);
		case 2:
			if (paramString.length() > 10)
				return DateUtils.CHN_DATE_TIME_EXTENDED_FORMAT
						.parse(paramString);
			return DateUtils.CHN_DATE_FORMAT.parse(paramString);
		case 3:
			return Float.valueOf(paramString);
		case 4:
			return Double.valueOf(paramString);
		case 5:
			return Boolean.valueOf(paramString);
		case 6:
			return Class.forName(paramString);
		}
		return null;
	}

	public static void fatherToChild(Object paramObject1, Object paramObject2)
			throws Exception {
		if (paramObject2.getClass().getSuperclass() != paramObject1.getClass())
			throw new Exception("child不是father的子类");
		Class localClass1 = paramObject1.getClass();
		Field[] arrayOfField = localClass1.getDeclaredFields();
		for (int i = 0; i < arrayOfField.length; ++i) {
			Field localField = arrayOfField[i];
			localField.setAccessible(true);
			Class localClass2 = localField.getType();
			String str1 = localField.getName();
			String str2 = localField.getName().substring(0, 1);
			String str3 = str2.toUpperCase() + str1.substring(1, str1.length());
			Method localMethod = localClass1.getMethod("get" + str3,
					new Class[0]);
			Object localObject = localMethod
					.invoke(paramObject1, new Object[0]);
			if ((localClass2.getName().equals("java.util.Date"))
					&& (localObject != null))
				localField
						.set(paramObject2, DateUtils.CHN_DATE_FORMAT
								.parse(localObject.toString()));
			else
				localField.set(paramObject2, localObject);
		}
	}

	public static String setSuperName(Object paramObject, String paramString1,
			int paramInt, String paramString2, String paramString3) {
		if (StringUtils.isEmpty(paramString2))
			return null;
		Object localObject = null;
		String[] arrayOfString = paramString2.split("/");
		HashMap localHashMap = new HashMap();
		int i = 0;
		int j = arrayOfString.length;
		while (i < j) {
			int k = i + 1;
			if (k > paramInt)
				break;
			String str = paramString1 + k;
			if (paramString3.equals(arrayOfString[i]))
				localObject = str;
			localHashMap.put(str, arrayOfString[i]);
			++i;
		}
		for (i = localHashMap.size(); i < paramInt; ++i)
			localHashMap.put(paramString1 + i + 1, null);
		try {
			copyProperties(paramObject, localHashMap);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return (String) localObject;
	}
	
	public static String getPropertiesJson(Object paramObject) {
		if (paramObject == null)
			return "";
		return JSONUtils.toJson(paramObject);
	}

	static {
		JAVA_TYPE_MAP.put("Integer", Integer.valueOf(0));
		JAVA_TYPE_MAP.put("Long", Integer.valueOf(1));
		JAVA_TYPE_MAP.put("Date", Integer.valueOf(2));
		JAVA_TYPE_MAP.put("Float", Integer.valueOf(3));
		JAVA_TYPE_MAP.put("Double", Integer.valueOf(4));
		JAVA_TYPE_MAP.put("Boolean", Integer.valueOf(5));
		JAVA_TYPE_MAP.put("Class", Integer.valueOf(6));
	}
}