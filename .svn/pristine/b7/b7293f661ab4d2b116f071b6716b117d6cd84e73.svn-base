package com.thiscc.tools.preferences;

import java.net.URL;
import java.util.Properties;

import com.shensoft.tools.utils.FileUtils;

public class SystemGlobal implements VariableStore {
	private static SystemGlobal SYSTEMGLOBAL = new SystemGlobal();
	private static Properties CONFIGS_PROPERTIES = null;
	private VariableExpander EXPANDER = new VariableExpander(this, "${", "}");

	private void initKeys() {
		URL localURL = super.getClass().getResource("/global.properties");
		String str = localURL.getFile();
		try {
			CONFIGS_PROPERTIES = FileUtils.readProperties(str);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public static String get(String paramString) {
		if (CONFIGS_PROPERTIES == null)
			SYSTEMGLOBAL.initKeys();
		return SYSTEMGLOBAL.getVariableValue(paramString);
	}

	public static Integer getInteger(String paramString) {
		int i = -1;
		String str = get(paramString);
		if ((str != null) && (!("".equals(str))))
			try {
				i = Integer.valueOf(str).intValue();
			} catch (Exception localException) {
			}
		return Integer.valueOf(i);
	}

	public static boolean getBoolean(String paramString) {
		boolean bool = false;
		String str = get(paramString);
		if ((str != null) && (!("".equals(str))))
			try {
				bool = Boolean.valueOf(str).booleanValue();
			} catch (Exception localException) {
			}
		return bool;
	}

	public String getVariableValue(String paramString) {
		String str = CONFIGS_PROPERTIES.getProperty(paramString);
		if (str == null)
			return null;
		return this.EXPANDER.expandVariables(str);
	}
}