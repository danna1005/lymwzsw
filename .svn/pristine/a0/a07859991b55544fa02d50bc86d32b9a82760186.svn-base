package com.thiscc.xtsz.dao;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import com.shensoft.tools.utils.DatabaseException;
import com.thiscc.tools.common.JSONQueryUtils;
import com.thiscc.xtsz.entity.UserInfo;
import com.thiscc.zm.TestDB;

/**
 * 's Dao
 * 
 * @author thy
 * @version 1.0
 * @since 2014-09-30
 */
public class UserInfoDao extends JSONQueryUtils {
    /**
	 * 更新对象
     * @param userInfo
     */
    public void update(UserInfo userInfo) {
        updateObject(userInfo);
    }
	
    /**
	 * 根据主键获得实例对象
     * @param userInfo
     */
    public UserInfo getById(String strId) {
        UserInfo userInfo = (UserInfo) findByHqlOnly(
            "from UserInfo where id=?", new Integer[] { new Integer(strId) });
        return userInfo;
    }
    
    /**
	 * 根据uuid获得实例对象
     * @param userInfo
     */
    public UserInfo getByUuid(String uuid) {
        UserInfo userInfo = (UserInfo) findByHqlOnly(
            "from UserInfo where uuid=?", new String[] { uuid });
        return userInfo;
    }
	
    /**
	 * 删除实例对象
     * @param userInfo
     */
    public void delete(UserInfo userInfo) {
        try {
            delObject(userInfo);
        } catch (Exception e) {
            throw new DatabaseException("delete UserInfo fail. " + e.getMessage());
        }
    }

    /**
	 * 保存实例对象
     * @param userInfo
     */
	public void save(UserInfo userInfo) {
        try {
            Integer strId = userInfo.getId();
            if (strId == null) {
                saveObject(userInfo);
            } else {
                updateObject(userInfo);
            }
        } catch (Exception e) {
            throw new DatabaseException("save UserInfo fail. " + e.getMessage());
        }
    }

    /**
	 * 获得对象列表
     * @param strArray
     * @param objArray
     */
    @SuppressWarnings("unchecked")
	public List<UserInfo> getUserInfoListByCondition(
            String[] strArray, Object[] objArray) {
        return (List<UserInfo>) findByHqlCondition("UserInfo",strArray, objArray);
	}

    /**
	 * 获得对象列表
     */
    @SuppressWarnings("unchecked")
	public List<UserInfo> getUserInfoList() {
        return (List<UserInfo>) findByHql("from UserInfo", null);
    }
	
	public String getPageData(HttpServletRequest request) throws Exception{
    	StringBuffer sql = new StringBuffer("select a from UserInfo a where 1=1");
        String key = request.getParameter("key");
        if(StringUtils.isNotEmpty(key)){
        	sql.append(" and (a.username like '%" + key + "%' or a.userno like '%" + key + "%')");
        }
        return getDataGrid(sql,UserInfo.class,"a",request);    
    }
    
    public String UpUserPass(String userid,String pass)throws Exception{
        String ret="0";
        String wsql=" update dbo.t_userInfo set userpass ='" + pass + "' where id=" + userid + " ";
        TestDB td = new TestDB();
        try{
     	  td.DBUpdate(wsql);  
     	  ret="1";
        }catch(Exception e){
     	  ret="0";   
        }
        return ret;	
     }
    
    public String getUserList(String userid)throws Exception{
        StringBuffer sql = new StringBuffer("select id,username from dbo.t_userInfo where id<>"+userid+" order by id desc");
        TestDB td = new TestDB();
        JSONArray list=td.DbSelectJSON(sql.toString()); 
        return list.toString();
     }

}
