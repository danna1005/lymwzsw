package com.thiscc.tools.preferences;

import java.util.HashMap;
import java.util.Map;

public class VariableExpander {
	private VariableStore variables;
	private String pre;
	private String post;
	private Map cache;

	public VariableExpander(VariableStore paramVariableStore,
			String paramString1, String paramString2) {
		this.variables = paramVariableStore;
		this.pre = paramString1;
		this.post = paramString2;
		this.cache = new HashMap();
	}

	public void clearCache() {
		this.cache.clear();
	}

	public String expandVariables(String paramString) {
		String str1 = (String) this.cache.get(paramString);
		if ((paramString == null) || (str1 != null))
			return str1;
		int i = paramString.indexOf(this.pre);
		if (i == -1)
			return paramString;
		StringBuffer localStringBuffer = new StringBuffer(paramString);
		while (i > -1) {
			String str2;
			int j = localStringBuffer.indexOf(this.post);
			int k = i + this.pre.length();
			if (i == 0) {
				str2 = localStringBuffer
						.substring(k, k + j - this.pre.length());
				localStringBuffer.replace(i, i + j + 1,
						this.variables.getVariableValue(str2));
			} else {
				str2 = localStringBuffer.substring(k, j);
				localStringBuffer.replace(i, j + 1,
						this.variables.getVariableValue(str2));
			}
			i = localStringBuffer.indexOf(this.pre);
		}
		str1 = localStringBuffer.toString();
		this.cache.put(paramString, str1);
		return str1;
	}
}