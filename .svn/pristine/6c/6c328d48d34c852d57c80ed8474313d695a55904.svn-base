package com.thiscc.tools.common;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.thiscc.tools.preferences.SystemGlobal;
import com.thiscc.tools.utils.StringUtils;

/**
 * 
 * @author xgx
 *
 */
public class QueryUtils {
	
	private Logger logger = Logger.getLogger(QueryUtils.class);

	private static ThreadLocal<HttpServletRequest> USERDATA = new ThreadLocal();
	private static final Pattern CONDITION_IN_STATEMENT_PATTERN = Pattern
			.compile(getProperty("conditionInStatementPattern"), 2);
	private static final Pattern CONDITION_PATTERN = Pattern.compile(
			getProperty("conditionPattern"), 2);
	private static final Pattern MATCHER_VARIABLE_PATTERN = Pattern.compile(
			getProperty("matcherVariable"), 2);
	private static final Pattern REMOVE_AS_CLAUSE_PATTERN = Pattern.compile(
			getProperty("removeASClause"), 2);
	private static final Pattern DETACH_FROM_CLAUSE_PATTERN = Pattern.compile(
			getProperty("detachFromClause"), 2);
	private static final Pattern MATCHER_FIELD_CLAUSE_PATTERN = Pattern
			.compile(getProperty("matcherFieldClause"), 2);
	private static final String PROPERTY_NAME = "query.utils.logicalOperators.";
	private static final Map<String, String> CLAUSE = new HashMap();

	private static final String getProperty(String paramString) {
		return SystemGlobal.get("query.utils.pattern." + paramString);
	}

	private static final Integer getPropertyName(String paramString) {
		Integer localInteger = Integer.valueOf(-1);
		if (StringUtils.isEmpty(paramString))
			return localInteger;
		paramString = paramString.replaceAll(" ", "");
		String str = SystemGlobal.get("query.utils.logicalOperators."
				+ paramString);
		if (!(StringUtils.isEmpty(str)))
			try {
				localInteger = Integer.valueOf(str);
			} catch (Exception localException) {
			}
		return localInteger;
	}

	public static final String getQueryField(String strQuerySql) {
		if (StringUtils.isEmpty(strQuerySql))
			return ((String) CLAUSE.get("field"));
		String str1 = detachFromClause(strQuerySql, "field");
		Matcher localMatcher = MATCHER_FIELD_CLAUSE_PATTERN.matcher(str1);
		StringBuffer sb = new StringBuffer();
		while (localMatcher.find()) {
			String str2 = localMatcher.group(1);
			if ((str2 != null) && (!("".equals(str2)))) {
				if (sb.length() > 0)
					sb.append(",");
				sb.append(str2);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param strHSql
	 * @return
	 */
	public static final String getQueryCount(String strHSql) {
		if (StringUtils.isEmpty(strHSql))
			return ((String) CLAUSE.get("count"));
		return detachFromClause(strHSql, "count");
	}
	
	private static final String detachFromClause(String strHSql, String strKey) {
		String str2;
		Matcher localMatcher = REMOVE_AS_CLAUSE_PATTERN.matcher(strHSql);
		StringBuffer localStringBuffer = new StringBuffer("");
		String str1 = "";
		for (str1 = strHSql; localMatcher.find(); str1 = str1.replace(
				str2, "")) {
			str2 = localMatcher.group(0);
			String str3 = str2.toLowerCase();
			if (str3.indexOf("select ") != 0)
				continue;
			str2 = str2.substring(7);
		}
		localMatcher = DETACH_FROM_CLAUSE_PATTERN.matcher(str1);
		if (localMatcher.find()) {
			CLAUSE.put("field", localMatcher.group(1));
			CLAUSE.put("count", "SELECT count(*) " + localMatcher.group(2));
		}
		return ((String) CLAUSE.get(strKey));
	}

	private static final String getCustomConditions() {
		Object localObject2;
		Object localObject3;
		StringBuffer localStringBuffer = new StringBuffer("");
		String str1 = getRequest("c");
		if (!("".equals(str1))) {
			Object localObject1 = new StringBuffer("");
			localObject2 = CONDITION_IN_STATEMENT_PATTERN.matcher(str1);
			if (((Matcher) localObject2).groupCount() > 0) {
				while (((Matcher) localObject2).find()) {
					localObject3 = StringUtils
							.TransactSQLInjection(((Matcher) localObject2)
									.group(1));
					localObject3 = " in ('"
							+ ((String) localObject3).replaceAll(",", "','")
							+ "')";
					((Matcher) localObject2).appendReplacement(
							(StringBuffer) localObject1, (String) localObject3);
				}
				((Matcher) localObject2)
						.appendTail((StringBuffer) localObject1);
				str1 = ((StringBuffer) localObject1).toString();
			}
			localObject2 = CONDITION_PATTERN.matcher(str1);
			while (((Matcher) localObject2).find()) {
				localObject3 = "";
				try {
					localObject3 = assemblyField(
							((Matcher) localObject2).group(1),
							((Matcher) localObject2).group(2),
							((Matcher) localObject2).group(3));
				} catch (Exception localException1) {
				}
				((Matcher) localObject2).appendReplacement(localStringBuffer,
						(String) localObject3);
			}
			((Matcher) localObject2).appendTail(localStringBuffer);
		}
		Object localObject1 = getRequest("sf");
		if (!("".equals(localObject1))) {
			localObject2 = new StringBuffer("");
			localObject3 = getRequest("op").split(",");
			String[] arrayOfString1 = getRequest("s").split("╣╠");
			String[] arrayOfString2 = ((String) localObject1).split(",");
			for (int i = 0; i < arrayOfString2.length; ++i) {
				String str2 = "";
				try {
					str2 = assemblyField(arrayOfString2[i],
							((String[]) localObject3)[i], arrayOfString1[i]);
				} catch (Exception localException2) {
				}
				if ("".equals(str2))
					continue;
				if (i > 0)
					((StringBuffer) localObject2).append(" and ");
				((StringBuffer) localObject2).append(str2);
			}
			if ((localStringBuffer.length() > 0)
					&& (((StringBuffer) localObject2).length() > 0))
				localStringBuffer.append(" and ");
			System.out.println(localObject2);
			localStringBuffer.append((StringBuffer) localObject2);
		}
		return ((String) (String) (String) localStringBuffer.toString());
	}

	private static final String matcherVariable(String paramString) {
		Matcher localMatcher = MATCHER_VARIABLE_PATTERN.matcher(paramString);
		StringBuffer localStringBuffer = new StringBuffer("");
		while (localMatcher.find()) {
			String str = StringUtils.TransactSQLInjection(getVariableValue(
					localMatcher.group(1), localMatcher.group(2)));
			localMatcher.appendReplacement(localStringBuffer, str);
		}
		localMatcher.appendTail(localStringBuffer);
		return localStringBuffer.toString();
	}

	private static final String getVariableValue(String paramString1,
			String paramString2) {
		if (StringUtils.isEmpty(paramString1))
			return "";
		Integer localInteger1 = getPropertyName(paramString1);
		if ("".equals(paramString2))
			paramString2 = "0";
		Integer localInteger2 = new Integer(paramString2);
		DateUtils localDateUtils = new DateUtils();
		String str = "";
		switch (localInteger1.intValue()) {
		case 14:
			localInteger2 = Integer.valueOf(Integer.valueOf(
					localDateUtils.getYear()).intValue()
					+ localInteger2.intValue());
			str = localInteger2.toString();
			break;
		case 15:
			localInteger2 = Integer.valueOf(Integer.valueOf(
					localDateUtils.getSeason()).intValue()
					+ localInteger2.intValue());
			str = localInteger2.toString();
			break;
		case 16:
			localInteger2 = Integer.valueOf(Integer.valueOf(
					localDateUtils.getMonth()).intValue()
					+ localInteger2.intValue());
			str = localInteger2.toString();
			break;
		case 17:
			localInteger2 = Integer.valueOf(Integer.valueOf(
					localDateUtils.getWeek()).intValue()
					+ localInteger2.intValue());
			str = localInteger2.toString();
			break;
		case 18:
			localInteger2 = Integer.valueOf(Integer.valueOf(
					localDateUtils.getDay()).intValue()
					+ localInteger2.intValue());
			str = localInteger2.toString();
			break;
		case 19:
			localDateUtils.addDay(localInteger2.intValue());
			str = localDateUtils.format(DateUtils.ORA_DATE_FORMAT);
			break;
		case 20:
			localDateUtils.addDay(localInteger2.intValue());
			str = localDateUtils.format(DateUtils.CHN_DATE_FORMAT);
			break;
		default:
			str = getSession(paramString1);
			if (!("".equals(str)))
				return str;
			str = getRequest(paramString1);
		}
		return str;
	}

	private static final String assemblyField(String paramString1,
			String paramString2, String paramString3) {
		if (StringUtils.isEmpty(paramString1))
			return "";
		Integer localInteger = getPropertyName(paramString2);
		if ((StringUtils.isEmpty(paramString3))
				&& (localInteger.intValue() < 12))
			return "";
		paramString3 = StringUtils.TransactSQLInjection(paramString3);
		StringBuffer localStringBuffer = new StringBuffer("");
		switch (localInteger.intValue()) {
		case 1:
			localStringBuffer.append(paramString1 + " not like '%"
					+ paramString3 + "%'");
			break;
		case 2:
		case 3:
			paramString3 = paramString3.replaceAll(",", "','");
			localStringBuffer.append(paramString1);
			if (localInteger.intValue() == 3)
				localStringBuffer.append(" not ");
			localStringBuffer.append(" in ('" + paramString3 + "')");
			break;
		case 4:
			localStringBuffer.append(paramString1 + "='" + paramString3 + "'");
			break;
		case 5:
			localStringBuffer.append(paramString1 + "<>'" + paramString3 + "'");
			break;
		case 6:
			localStringBuffer.append(paramString1 + ">'" + paramString3 + "'");
			break;
		case 7:
			localStringBuffer.append(paramString1 + "<'" + paramString3 + "'");
			break;
		case 8:
			localStringBuffer.append(paramString1 + ">='" + paramString3 + "'");
			break;
		case 9:
			localStringBuffer.append(paramString1 + "<='" + paramString3 + "'");
			break;
		case 10:
			localStringBuffer.append(paramString1 + " like '" + paramString3
					+ "%'");
			break;
		case 11:
			localStringBuffer.append(paramString1 + " like '%" + paramString3
					+ "'");
			break;
		case 12:
			localStringBuffer.append("isnull(" + paramString1 + ")=1");
			break;
		case 13:
			localStringBuffer.append("isnull(" + paramString1 + ")=0");
			break;
		case 14:
		case 15:
		case 16:
		case 17:
			localStringBuffer.append("Year(" + paramString1
					+ ")=Year(getDate())");
			if (localInteger.intValue() == 15)
				localStringBuffer.append(" and datename(qq," + paramString1
						+ ")=datename(qq,getDate())");
			if (localInteger.intValue() == 16)
				localStringBuffer.append(" and Month(" + paramString1
						+ ")=Month(getDate())");
			if (localInteger.intValue() != 17)
				return localStringBuffer.toString();
			localStringBuffer.append(" and datename(ww," + paramString1
					+ ")=datename(ww,getDate())");
			break;
		case 18:
			localStringBuffer.append(paramString1 + "=getDate()");
			break;
		default:
			localStringBuffer.append(paramString1 + " like '%" + paramString3
					+ "%'");
		}
		return localStringBuffer.toString();
	}

	private static final HttpServletRequest getRequestThread() {
		return ((HttpServletRequest) USERDATA.get());
	}

	private static final String getRequest(String paramString) {
		HttpServletRequest localHttpServletRequest = getRequestThread();
		String str = "";
		if (localHttpServletRequest != null)
			try {
				str = localHttpServletRequest.getParameter(paramString);
				if (StringUtils.isEmpty(str))
					str = "";
				str = URLDecoder.decode(str, "UTF-8");
			} catch (Exception localException) {
				localException.printStackTrace();
			}
		return str;
	}

	private static final String getSession(String paramString) {
		String str = "";
		HttpServletRequest localHttpServletRequest = getRequestThread();
		if (localHttpServletRequest != null)
			try {
				Object localObject = localHttpServletRequest.getSession()
						.getAttribute(paramString);
				if (localObject != null)
					str = localObject.toString();
			} catch (Exception localException) {
				localException.printStackTrace();
			}
		return str;
	}

	private static final String checkWhereClause(String paramString,
			int paramInt) {
		if (paramString.indexOf("where", paramInt) != -1)
			return " and ";
		return " where ";
	}
}