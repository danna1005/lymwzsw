package com.thiscc.tools.common;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.shensoft.tools.utils.JsonValueProcessorImpl;
import com.thiscc.zm.StringUtil;
public class JSONQueryUtils extends BaseDAO {
	
	private static Logger logger = Logger.getLogger(JSONUtils.class);
	
	// JsonConfig
	private static final JsonConfig JSON_CONGIF = JsonConfig.getInstance();

	// json 转换日期格式
	private static final String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss.0"};
	
	//查询过滤字段 格式   "zd1,zd2,zd3"
	public String filterField;
    
    public String getFilterField() {
		return filterField;
	}

	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}

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
	
	public String dataGrid(String paramString) {
		return dataGrid(paramString, null, null);
	}

	public String dataGrid(String strQuerySql, String strStart,
			String strLimit) {
		List localList = execute(strQuerySql, strStart, strLimit);
		String[] arrayOfString = getFields(strQuerySql);
		JSONObject localJSONObject = new JSONObject();
		localJSONObject.put("total", Long.valueOf(getCount(strQuerySql)));
		JSONArray localJSONArray = getRows(localList, arrayOfString, null,
				(StringUtils.isEmpty(strStart)) ? "0" : strStart);
		localJSONObject.put("data", localJSONArray);
		return localJSONObject.toString();
	}

	/**
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public String tree(String paramString1, String paramString2, String paramString3) {
		List localList = execute(paramString1, null, null);
		String[] arrayOfString1 = getFields(paramString1);
		String[] arrayOfString2 = { "aid", "text", "leaf", "href", "desc", "icon" };
		JSONArray localJSONArray = new JSONArray();
		int i = localList.size();
		int j = (arrayOfString1 != null) ? arrayOfString1.length : 0;
		if (arrayOfString2 == null)
			arrayOfString2 = arrayOfString1;
		int k = arrayOfString2.length;
		for (int l = 0; l < i; ++l) {
			Object[] arrayOfObject = (Object[]) localList.get(l);
			JSONObject localJSONObject1 = new JSONObject();
			JSONObject localJSONObject2 = null;
			for (int i1 = 0; i1 < arrayOfObject.length; ++i1) {
				if ((i1 >= j) && (i1 >= k))
					break;
				Object localObject = arrayOfObject[i1];
				if (i1 == 0) {
					localJSONObject1.put("id", paramString2 + "-" + localObject);
				} else {
					if (i1 == 2) {
						boolean bool = true;
						if (localObject != null)
							try {
								Long localLong = (Long) localObject;
								if (localLong.longValue() > 0L)
									bool = false;
							} catch (Exception ex) {
								try {
									Integer localInteger = (Integer) localObject;
									if (localInteger.intValue() > 0) {
										bool = false;
									}
								} catch (Exception ex2) {
									ex.printStackTrace();
								}
							}
						localJSONObject1.put("leaf", Boolean.valueOf(bool));
						continue;
					}
					if ((i1 == 3) && ("0".equals(paramString3))) {
						localJSONObject1.put("href", null);
						continue;
					}
				}
				if (localObject == null)
					continue;
				if (i1 < k) {
					localJSONObject1.put(arrayOfString2[i1], localObject);
				} else {
					if (localJSONObject2 == null)
						localJSONObject2 = new JSONObject();
					localJSONObject2.put(arrayOfString1[i1], localObject);
				}
			}
			if (localJSONObject2 != null)
				localJSONObject1.put("defField", localJSONObject2);
			localJSONArray.add(localJSONObject1);
		}
		return localJSONArray.toString();
	}

	public String combo(String paramString, boolean paramBoolean) {
		List localList = execute(paramString, null, null);
		String[] arrayOfString1 = getFields(paramString);
		String[] arrayOfString2 = { "retrunValue", "displayText", "isDefault" };
		JSONObject localJSONObject1 = new JSONObject();
		JSONArray localJSONArray = new JSONArray();
		int i = localList.size();
		int j = (arrayOfString1 != null) ? arrayOfString1.length : 0;
		if (arrayOfString2 == null)
			arrayOfString2 = arrayOfString1;
		int k = arrayOfString2.length;
		int l = -1;
		for (int i1 = 0; i1 < i; ++i1) {
			Object[] arrayOfObject = (Object[]) localList.get(i1);
			JSONObject localJSONObject3 = new JSONObject();
			JSONObject localJSONObject4 = null;
			for (int i2 = 0; i2 < arrayOfObject.length; ++i2) {
				if ((i2 >= j) && (i2 >= k))
					break;
				Object localObject = arrayOfObject[i2];
				if (localObject == null)
					continue;
				String str = (i2 < k) ? arrayOfString2[i2] : arrayOfString1[i2];
				if ((l == -1) && ("isDefault".equals(str))
						&& ("1".equals(localObject.toString())))
					l = i1;
				if (i2 < k) {
					localJSONObject3.put(arrayOfString2[i2], localObject);
				} else {
					if (localJSONObject4 == null)
						localJSONObject4 = new JSONObject();
					localJSONObject4.put(arrayOfString1[i2], localObject);
				}
			}
			if (localJSONObject4 != null)
				localJSONObject3.put("defField", localJSONObject4);
			localJSONArray.add(localJSONObject3);
		}
		if (!(paramBoolean)) {
			JSONObject localJSONObject2 = new JSONObject();
			localJSONObject2.element("retrunValue", "");
			localJSONObject2.element("displayText", "<空>");
			if (l > -1)
				localJSONObject2.element("selectIndex", l + 1);
			localJSONArray.add(0, localJSONObject2);
		}
		localJSONObject1.put("results", Integer.valueOf(localJSONArray.size()));
		localJSONObject1.put("rows", localJSONArray);
		return localJSONObject1.toString();
	}

	public String checkBoxGroup(String paramString) {
		List localList = execute(paramString, null, null);
		String[] arrayOfString1 = getFields(paramString);
		String[] arrayOfString2 = { "inputValue", "boxLabel", "isDefault" };
		JSONArray localJSONArray1 = new JSONArray();
		JSONArray localJSONArray2 = new JSONArray();
		int i = localList.size();
		int j = (arrayOfString1 != null) ? arrayOfString1.length : 0;
		if (arrayOfString2 == null)
			arrayOfString2 = arrayOfString1;
		int k = arrayOfString2.length;
		for (int l = 0; l < i; ++l) {
			Object[] arrayOfObject = (Object[]) localList.get(l);
			JSONObject localJSONObject2 = new JSONObject();
			JSONObject localJSONObject3 = null;
			Object localObject1 = arrayOfObject[0];
			Object localObject2 = (arrayOfObject.length > 1) ? arrayOfObject[1]
					: null;
			if ((localObject1 == null) && (localObject2 == null))
				continue;
			localObject1 = (localObject1 != null) ? localObject1 : localObject2;
			localObject2 = (localObject2 != null) ? localObject2 : localObject1;
			localJSONObject2.put("inputValue", localObject1);
			localJSONObject2.put("boxLabel", localObject2);
			for (int i1 = 2; i1 < arrayOfObject.length; ++i1) {
				if ((i1 >= j) && (i1 >= k))
					break;
				Object localObject3 = arrayOfObject[i1];
				if (localObject3 == null)
					continue;
				if ((i1 == 2) && ("1".equals(localObject3)))
					localJSONArray2.add(localObject1);
				if (i1 < k) {
					localJSONObject2.put(arrayOfString2[i1], localObject3);
				} else {
					if (localJSONObject3 == null)
						localJSONObject3 = new JSONObject();
					localJSONObject3.put(arrayOfString1[i1], localObject3);
				}
			}
			if (localJSONObject3 != null)
				localJSONObject2.put("defField", localJSONObject3);
			localJSONArray1.add(localJSONObject2);
		}
		JSONObject localJSONObject1 = new JSONObject();
		localJSONObject1.put("items", localJSONArray1);
		if (localJSONArray2.size() > 0)
			localJSONObject1.put("value", localJSONArray2);
		System.out.println(localJSONObject1);
		return localJSONObject1.toString();
	}

	public String chooser(String paramString, int paramInt) {
		List localList = execute(paramString, null, null);
		String[] arrayOfString1 = getFields(paramString);
		JSONObject localJSONObject1 = new JSONObject();
		JSONArray localJSONArray = new JSONArray();
		int i = localList.size();
		int j = (arrayOfString1 != null) ? arrayOfString1.length : 0;
		String[] arrayOfString2 = arrayOfString1;
		int k = arrayOfString2.length;
		for (int l = 0; l < i; ++l) {
			Object[] arrayOfObject = (Object[]) localList.get(l);
			JSONObject localJSONObject2 = new JSONObject();
			JSONObject localJSONObject3 = null;
			int i1 = 0;
			for (int i2 = 0; i2 < arrayOfObject.length; ++i2) {
				if ((i2 >= j) && (i2 >= k))
					break;
				Object localObject = arrayOfObject[i2];
				if (localObject == null)
					continue;
				if (i2 < k) {
					String str = arrayOfString2[i2];
					if ("Amount".equals(str))
						i1 = 1;
					localJSONObject2.put(arrayOfString2[i2], localObject);
				} else {
					if (localJSONObject3 == null)
						localJSONObject3 = new JSONObject();
					localJSONObject3.put(arrayOfString1[i2], localObject);
				}
			}
			localJSONObject2.put("chooserLayer", Integer.valueOf(paramInt));
			if (i1 == 0)
				localJSONObject2.put("Amount", Integer.valueOf(0));
			if (localJSONObject3 != null)
				localJSONObject2.put("defField", localJSONObject3);
			localJSONArray.add(localJSONObject2);
		}
		localJSONObject1.put("results", Integer.valueOf(localJSONArray.size()));
		localJSONObject1.put("rows", localJSONArray);
		return localJSONObject1.toString();
	}

	public String menu(String paramString) {
		List localList = execute(paramString, null, null);
		String[] arrayOfString1 = getFields(paramString);
		String[] arrayOfString2 = { "id", "text", "handler", "iconCls" };
		JSONArray localJSONArray = getRows(localList, arrayOfString1,
				arrayOfString2, null);
		return localJSONArray.toString();
	}

	public String record(String paramString) {
		List localList = execute(paramString, null, null);
		String[] arrayOfString = getFields(paramString);
		JSONArray localJSONArray = getRows(localList, arrayOfString, null, null);
		if (localJSONArray.size() > 0)
			return localJSONArray.getString(0);
		return "{}";
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
			Object[] arrayOfObject = null;
			if(paramList.get(i1) instanceof Object[]){
				arrayOfObject = (Object[]) paramList.get(i1);
			}else{
				Object obj = paramList.get(i1);
				ArrayList al = new ArrayList();
				al.add(obj);
				arrayOfObject = al.toArray();
			}
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

	private List<Object[]> execute(String strHSql, String strFirstResult,
			String strMaxResult) {
		try {
			if ( StringUtils.isNotEmpty(strHSql) ) {
				String strTemp = strHSql.toLowerCase();
				int idxDistinct = strTemp.indexOf("distinct");
				int idxOrder = strTemp.indexOf("order by");
				if ( idxDistinct > 0 && idxOrder > 0 ) {
					strHSql = strHSql.substring(0, idxOrder);
				}
			}
			List<Object[]> localList = findByhql(strHSql, strFirstResult, strMaxResult, null);
			return localList;
		} catch (Exception e) {
			logger.error("查询出错:" + e.getMessage(), e);
		}
		return null;
	}

	private long getCount(String strHSql) {
		String str = QueryUtils.getQueryCount(strHSql);
		if ( StringUtils.isNotEmpty(str) ) {
			String strTemp = str.toLowerCase();
			int idx = strTemp.indexOf("order by");
			if ( idx > 0 ) {
				str = str.substring(0, idx);
			}
		}
		long l = 0L;
		try {
			l = getRcordCount(str, null).longValue();
		} catch (Exception e) {
			logger.error("获得记录条数出错:" + e.getMessage(), e);
		}
		return l;
	}

	private static final String[] getFields(String strQuerySql) {
		String str = QueryUtils.getQueryField(strQuerySql);
		if (!(StringUtils.isEmpty(str)))
			return str.split(",");
		return null;
	}

	/**
	 * @param sql hql查询语句
	 * @param cla 实体类的Class
	 * @param byname 实体类的别名
	 * @param request
	 * @return
	 */
	public String getDataGrid(StringBuffer sql,Class cla,String byname,HttpServletRequest request) {
		Map map = request.getParameterMap();
        int index = Integer.parseInt(request.getParameter("pageIndex") == null ? "0" : request.getParameter("pageIndex"));
        int size = Integer.parseInt(request.getParameter("pageSize") == null ? "100" : request.getParameter("pageSize"));        
        String sortField = request.getParameter("sortField");
        String sortOrder = request.getParameter("sortOrder");
        if(StringUtils.isNotEmpty(byname)){
        	byname += ".";
        }
        if(map != null){
    		Field[] field = cla.getDeclaredFields();
    		List<String> tempList = null;
    		if(StringUtils.isNotEmpty(filterField)){
				 tempList = Arrays.asList(filterField.split(","));
    		}
    		for (int j = 0; j < field.length; j++) {
    			String fieldName = field[j].getName();
    			String fieldType = field[j].getGenericType().toString();
    			if(tempList != null && tempList.contains(fieldName)){
					 continue;
				 }
    			Object fieldValue = map.get(fieldName);
    			if(fieldValue != null){
    				String value = ((String[])fieldValue)[0].trim();
    				if(StringUtils.isNotEmpty(value)){
    					if (fieldType.equals("class java.lang.String")) {
        					sql.append(" and "+byname+fieldName+" like '%" + value + "%'");
        				}else if (fieldType.equals("class java.lang.Integer")) {
        					sql.append(" and "+byname+fieldName+" = " + value);
        				}else if (fieldType.equals("class java.util.Date")) {
        					if("birthday".equals(fieldName)){//处理出生年月字段
        						Object ageFilter = map.get("ageFilter");
        						if(ageFilter != null){
        							String ageFilterValue = ((String[])ageFilter)[0];
        							if(StringUtils.isNotEmpty(ageFilterValue)){
        								sql.append(" and " + "datediff(year," + byname + fieldName + ",getdate())" + ageFilterValue+value);
        							}
        						}
        					}else{
        						sql.append(" and "+byname+fieldName+" >= '" + value + "'");
        					}
        				}else{
        					sql.append(" and "+byname+fieldName+" = " + value);
        				}
    				}
    			}

    		}
    	}
        if (StringUtil.isNullOrEmpty(sortField) == false){
            if ("desc".equals(sortOrder.toLowerCase()) == false) sortOrder = "asc";
            sql.append(" order by "+byname + sortField + " " + sortOrder);
        }else{
            sql.append(" order by "+byname + "id desc");
        }
        int start = index * size;
        String result = dataGrid(sql.toString(), String.valueOf(start), String.valueOf(size));
        if(StringUtils.isNotEmpty(byname)){
        	byname = byname.substring(0, (byname.length()-1));
        	if(result.contains("\""+byname+"\":{")){
            	JSONObject jsonparam = JSONObject.fromObject(result);
                JSONArray jsonArray = jsonparam.getJSONArray("data");
                if (jsonArray != null && jsonArray.size() > 0) {
                	for(int i=0;i<jsonArray.size();i++){
                		JSONObject json = jsonArray.getJSONObject(i);
                		if(json.containsKey(byname)){
                			JSONObject obj = json.getJSONObject(byname);
                			json.remove(byname);
                			JSONObject newJson = null;
                			if(json.size() > 1){
                				newJson = JSONObject.fromJSONObject(json);
                			}
                			json.putAll(obj);
                			if(newJson != null){
                				json.putAll(newJson);
                			}
                		}
                	}
                }
                return jsonparam.toString();
            }
        }
		return result;
	}
}