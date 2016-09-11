package com.thiscc.tools.utils;

import java.lang.reflect.Array;
import java.util.LinkedList;

import com.thiscc.tools.preferences.SystemGlobal;

public class StringUtils {
	private static final String PROPERTY_NAME = "string.utils.";
	private static final int fillchar = 61;
	private static final String cvt = getPropertyName("s");

	public static final String getPropertyName(String paramString) {
		return SystemGlobal.get("string.utils." + paramString);
	}


	public static String TransactSQLInjection(String paramString) {
		if ((paramString == null) || (paramString.equals("")))
			return "";
		return paramString.replaceAll("'", "''");
	}

	public static boolean isEmpty(Object paramObject) {
		return !((paramObject != null) && (!("".equals(paramObject))));
	}

	public static String getValue(String paramString1, String paramString2) {
		if (!(isEmpty(paramString1)))
			return paramString1;
		return paramString2;
	}

	public static String encodeBase64(String paramString) {
		return encodeBase64(paramString.getBytes());
	}

	public static String encodeBase64(byte[] paramArrayOfByte) {
		int j = paramArrayOfByte.length;
		StringBuffer localStringBuffer = new StringBuffer((j / 3 + 1) * 4);
		for (int k = 0; k < j; ++k) {
			int i = paramArrayOfByte[k] >> 2 & 0x3F;
			localStringBuffer.append(cvt.charAt(i));
			i = paramArrayOfByte[k] << 4 & 0x3F;
			if (++k < j)
				i |= paramArrayOfByte[k] >> 4 & 0xF;
			localStringBuffer.append(cvt.charAt(i));
			if (k < j) {
				i = paramArrayOfByte[k] << 2 & 0x3F;
				if (++k < j)
					i |= paramArrayOfByte[k] >> 6 & 0x3;
				localStringBuffer.append(cvt.charAt(i));
			} else {
				++k;
				localStringBuffer.append('=');
			}
			if (k < j) {
				i = paramArrayOfByte[k] & 0x3F;
				localStringBuffer.append(cvt.charAt(i));
			} else {
				localStringBuffer.append('=');
			}
		}
		return localStringBuffer.toString();
	}

	
	public static String decodeBase64(String paramString) {
		return decodeBase64(paramString.getBytes());
	}

	public static String decodeBase64(byte[] paramArrayOfByte) {
		int k = paramArrayOfByte.length;
		StringBuffer localStringBuffer = new StringBuffer(k * 3 / 4);
		for (int l = 0; l < k; ++l) {
			int i = cvt.indexOf(paramArrayOfByte[l]);
			int j = cvt.indexOf(paramArrayOfByte[(++l)]);
			i = i << 2 | j >> 4 & 0x3;
			localStringBuffer.append((char) i);
			if (++l < k) {
				i = paramArrayOfByte[l];
				if (61 == i)
					break;
				i = cvt.indexOf((char) i);
				j = j << 4 & 0xF0 | i >> 2 & 0xF;
				localStringBuffer.append((char) j);
			}
			if (++l >= k)
				continue;
			j = paramArrayOfByte[l];
			if (61 == j)
				break;
			j = cvt.indexOf((char) j);
			i = i << 6 & 0xC0 | j;
			localStringBuffer.append((char) i);
		}
		return localStringBuffer.toString();
	}
	public static String formatJSON(String paramString) {
		if (isEmpty(paramString))
			return "";
		paramString = paramString.replaceAll("\\\\", "\\\\\\\\");
		paramString = paramString.replaceAll("'", "\\\\'");
		paramString = paramString.replaceAll("\r", "\\\\r");
		paramString = paramString.replaceAll("\n", "\\\\n");
		return paramString;
	}

	public static final String formatHtml(String paramString) {
		if (!(isEmpty(paramString))) {
			paramString = paramString.replaceAll("<", "&lt;");
			paramString = paramString.replaceAll(">", "&gt;");
			paramString = paramString.replaceAll("\"", "&quot;");
			paramString = formatJSON(paramString);
		}
		return paramString;
	}

	public static String convert(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer(1000);
		localStringBuffer.setLength(0);
		for (int i = 0; i < paramString.length(); ++i) {
			char c = paramString.charAt(i);
			if (c > 255) {
				localStringBuffer.append("\\u");
				int j = c >>> '\b';
				String str = Integer.toHexString(j);
				if (str.length() == 1)
					localStringBuffer.append("0");
				localStringBuffer.append(str);
				j = c & 0xFF;
				str = Integer.toHexString(j);
				if (str.length() == 1)
					localStringBuffer.append("0");
				localStringBuffer.append(str);
			} else {
				localStringBuffer.append(c);
			}
		}
		return new String(localStringBuffer);
	}

	public static String removeRepeate(String paramString) {
		String[] arrayOfString = paramString.split(",");
		if (arrayOfString.length == 1)
			return paramString;
		LinkedList localLinkedList = new LinkedList();
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < arrayOfString.length; ++i) {
			if ((localLinkedList.contains(arrayOfString[i]))
					|| ("".equals(arrayOfString[i])))
				continue;
			localLinkedList.add(arrayOfString[i]);
		}
		for (int i = 0; i < localLinkedList.size(); ++i) {
			if (i != 0)
				localStringBuffer.append(",");
			localStringBuffer.append((String) localLinkedList.get(i));
		}
		return localStringBuffer.toString();
	}

	public static String removeOneID(String paramString1, String paramString2) {
		String[] arrayOfString = paramString1.split(",");
		LinkedList localLinkedList = new LinkedList();
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < arrayOfString.length; ++i) {
			if ((localLinkedList.contains(arrayOfString[i]))
					|| ("".equals(arrayOfString[i]))
					|| (arrayOfString[i].equals(paramString2)))
				continue;
			localLinkedList.add(arrayOfString[i]);
		}
		for (int i = 0; i < localLinkedList.size(); ++i) {
			if (i != 0)
				localStringBuffer.append(",");
			localStringBuffer.append((String) localLinkedList.get(i));
		}
		String str = localStringBuffer.toString();
		if ("".equals(str))
			return null;
		return str;
	}

	public static String[] addData(String[] paramArrayOfString1,
			String[] paramArrayOfString2) {
		int i = Array.getLength(paramArrayOfString1);
		int j = i + paramArrayOfString2.length;
		Object localObject = Array.newInstance(String.class, j);
		System.arraycopy(paramArrayOfString1, 0, localObject, 0, i);
		for (int k = 0; k < paramArrayOfString2.length; ++k)
			Array.set(localObject, i + k, paramArrayOfString2[k]);
		return ((String[]) (String[]) localObject);
	}

	public static boolean isExist(String paramString1, String paramString2) {
		if ("".equals(paramString1))
			return false;
		String[] arrayOfString = paramString1.split(",");
		for (int i = 0; i < arrayOfString.length; ++i)
			if (paramString2.equals(arrayOfString[i]))
				return true;
		return false;
	}

	public static final String getFillDigits(String paramString,
			Integer paramInteger) {
		if (paramString == null)
			paramString = "";
		paramInteger = Integer.valueOf(paramInteger.intValue()
				- paramString.length());
		StringBuffer localStringBuffer = new StringBuffer("");
		for (int i = 0; i < paramInteger.intValue(); ++i)
			localStringBuffer.append("0");
		localStringBuffer.append(paramString);
		return localStringBuffer.toString();
	}

	public static String replaceStrAtPos(String paramString1,
			String paramString2, int paramInt) {
		StringBuffer localStringBuffer = new StringBuffer(paramString1);
		localStringBuffer.replace(paramInt, paramInt + 1, paramString2);
		return localStringBuffer.toString();
	}
	
	/**
	 * 判断字符串是否仅为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] paramArrayOfString) {
	}
}