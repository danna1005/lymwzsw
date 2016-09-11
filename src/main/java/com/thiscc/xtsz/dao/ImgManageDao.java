package com.thiscc.xtsz.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.thiscc.tools.common.JSONQueryUtils;
import com.shensoft.tools.utils.DatabaseException;

import com.thiscc.xtsz.entity.ImgManage;

/**
 * 's Dao
 * 
 * @author xianggx
 * @version 1.0
 * @since 2014/12/26
 */
public class ImgManageDao extends JSONQueryUtils {
    
    //~ Methods ====================================================================================
    
    //~ Private ====================================================================================
    
    //~ Protected ==================================================================================
    
    //~ Publics ====================================================================================
    
    /**
	 * 更新对象
     * @param imgManage
     */
    public void update(ImgManage imgManage) {
        updateObject(imgManage);
    }
	
    /**
	 * 根据主键获得实例对象
     * @param imgManage
     */
    public ImgManage getById(String strId) {
        ImgManage imgManage = (ImgManage) findByHqlOnly(
            "from ImgManage where id=?", new Integer[] { new Integer(strId) });
        return imgManage;
    }
	
    /**
	 * 删除实例对象
     * @param imgManage
     */
    public void delete(ImgManage imgManage) {
        try {
            delObject(imgManage);
        } catch (Exception e) {
            throw new DatabaseException("delete ImgManage fail. " + e.getMessage());
        }
    }

    /**
	 * 保存实例对象
     * @param imgManage
     */
	public void save(ImgManage imgManage) {
        try {
            Integer strId = imgManage.getId();
            if (strId == null) {
                saveObject(imgManage);
            } else {
                updateObject(imgManage);
            }
        } catch (Exception e) {
            throw new DatabaseException("save ImgManage fail. " + e.getMessage());
        }
    }

    /**
	 * 获得对象列表
     * @param strArray
     * @param objArray
     */
    public List<ImgManage> getImgManageListByCondition(
            String[] strArray, Object[] objArray) {
        return (List<ImgManage>) findByHqlCondition("ImgManage",
            strArray, objArray);
	}

    /**
	 * 获得对象列表
     */
    public List<ImgManage> getImgManageList() {
        return (List<ImgManage>) findByHql("from ImgManage", null);
    }
	
    /**
	 * 获得对象列表
     */
    public List<ImgManage> getImgManageListByEnterpriseId(
            String strEnterpriseId) {
        return (List<ImgManage>) findByHql(
            "from ImgManage where enterpriseId=?", 
            new String[] { strEnterpriseId });
    }
	
	public String getPageData(HttpServletRequest request) throws Exception
    {
    	StringBuffer sql = new StringBuffer("select a from ImgManage a where 1=1");
    	String key = request.getParameter("key");
    	if(StringUtils.isNotEmpty(key)){
        	sql.append(" and a.remark like '%" + key + "%'");
        }
        return getDataGrid(sql,ImgManage.class,"a",request);
    }
}
