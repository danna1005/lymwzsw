package com.thiscc.xtsz.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.shensoft.tools.utils.DatabaseException;
import com.thiscc.tools.common.JSONQueryUtils;
import com.thiscc.tools.utils.StringUtils;
import com.thiscc.xtsz.entity.News;
import com.thiscc.zm.TestDB;

/**
 * 's Dao
 * 
 * @author wxj
 * @version 1.0
 * @since 2014/11/1
 */
public class NewsDao extends JSONQueryUtils {
    
    //~ Methods ====================================================================================
    
    //~ Private ====================================================================================
    
    //~ Protected ==================================================================================
    
    //~ Publics ====================================================================================
    
    /**
	 * 更新对象
     * @param news
     */
    public void update(News news) {
        updateObject(news);
    }
	
    /**
	 * 根据主键获得实例对象
     * @param news
     */
    public News getById(String strId) {
        News news = (News) findByHqlOnly(
            "from News where id=?", new Integer[] { new Integer(strId) });
        return news;
    }
    
    /**
	 * 根据type获得实例对象
     * @param news
     */
    public News getByType(String type) {
        News news = (News) findByHqlOnly(
            "from News where type=?", new String[] { type });
        return news;
    }
	
    /**
	 * 删除实例对象
     * @param news
     */
    public void delete(News news) {
        try {
            delObject(news);
        } catch (Exception e) {
            throw new DatabaseException("delete News fail. " + e.getMessage());
        }
    }

    /**
	 * 保存实例对象
     * @param news
     */
	public void save(News news) {
        try {
            Integer strId = news.getId();
            if (strId == null) {
                saveObject(news);
            } else {
                updateObject(news);
            }
        } catch (Exception e) {
            throw new DatabaseException("save News fail. " + e.getMessage());
        }
    }

    /**
	 * 获得对象列表
     * @param strArray
     * @param objArray
     */
    public List<News> getNewsListByCondition(
            String[] strArray, Object[] objArray) {
        return (List<News>) findByHqlCondition("News",
            strArray, objArray);
	}

    /**
	 * 获得对象列表
     */
    public List<News> getNewsList() {
        return (List<News>) findByHql("from News", null);
    }
	
    /**
	 * 获得对象列表
     */
    public List<News> getNewsListByEnterpriseId(
            String strEnterpriseId) {
        return (List<News>) findByHql(
            "from News where enterpriseId=?", 
            new String[] { strEnterpriseId });
    }
	
	public String getPageData(HttpServletRequest request) throws Exception
    {
    	String key = request.getParameter("key");
    	StringBuffer sql = new StringBuffer("select a from News a where 1=1");
    	if(!StringUtils.isEmpty(key)){
        	sql.append(" and a.title like '%" + key + "%'");
        }
        return getDataGrid(sql,News.class,"a",request);
    }
	
	public JSONObject getCount(HttpServletRequest request) throws Exception
    {
		JSONObject result = null;
    	try {
    		String type = request.getParameter("type");
        	String state = request.getParameter("state");
        	StringBuffer sql = new StringBuffer("select count(*) as num from t_news a where 1=1");
        	if(!StringUtils.isEmpty(state)){
        		sql.append(" and state='" + state + "'");
        	}
        	if(!StringUtils.isEmpty(type)){
        		sql.append(" and type='" + type + "'");
        	}
        	TestDB td = new TestDB();
        	JSONArray array = td.DbSelectJSON(sql.toString());
        	result = array.getJSONObject(0);
		} catch (Exception e) {
			result = new JSONObject();
		}
    	return result;
    }
}
