package com.thiscc.xtsz.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.shensoft.tools.utils.AppException;
import com.thiscc.xtsz.dao.NewsDao;
import com.thiscc.xtsz.entity.News;

/**
 * 's Action
 * 
 * @author wxj
 * @version 1.0
 * @since 2014/11/1
 */
public class NewsBiz {
    
    //~ Static fields/initializers =================================================================
    
    //~ Instance field =============================================================================
    
    // 
    @Resource(name = "newsDao")
    private NewsDao newsDao;
    
    //~ Properties accessors =======================================================================
    
    /**
     *
     */
    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }
    
    //~ Methods ====================================================================================
    
    //~ Private ====================================================================================
    
    //~ Protected ==================================================================================
    
    //~ Publics ====================================================================================
    
    /*
     * 根据ID获得对象实例
     * @param strId
     */
    public News getById(String strId) {
        return newsDao.getById(strId);
    }

    /*
     * 根据type获得对象实例
     * @param strId
     */
    public News getByType(String type) {
        return newsDao.getByType(type);
    }
    
    /*
     * 根据ID获得对象实例
     * @param strId
     */
    public void delete(String strId) {
        try {
            News news = getById(strId);
            newsDao.delete(news);
        } catch (Exception e) {
            throw new AppException("delete News fail. " + e.getMessage());
        }
	}

    /*
     * 保存
     * @param news
     */
    public void save(News news) {
        try {
            newsDao.save(news);
        } catch (Exception e) {
            throw new AppException("save News fail." + e.getMessage());
        }
    }

    /*
     * 获得对象列表
     */
    public List<News> getNewsList() {
        return newsDao.getNewsList();
    }
    
	public String getPageData(HttpServletRequest request) throws Exception{
    	return newsDao.getPageData(request);
    }
	
	public JSONObject getCount(HttpServletRequest request) throws Exception{
    	return newsDao.getCount(request);
    }
}