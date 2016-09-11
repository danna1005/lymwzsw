package com.thiscc.xtsz.biz;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.shensoft.tools.utils.AppException;
import com.thiscc.xtsz.dao.UserInfoDao;
import com.thiscc.xtsz.entity.UserInfo;

/**
 * 's Action
 * 
 * @author thy
 * @version 1.0
 * @since 2014-09-30
 */
public class UserInfoBiz {
    // 
    @Resource(name = "userInfoDao")
    private UserInfoDao userInfoDao;
    
    /**
     *
     */
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
    
    /*
     * 根据ID获得对象实例
     * @param strId
     */
    public UserInfo getById(String strId) {
        return userInfoDao.getById(strId);
    }

    /*
     * 根据ID获得对象实例
     * @param strId
     */
    public void delete(String strId) {
        try {
            UserInfo userInfo = getById(strId);
            userInfoDao.delete(userInfo);
        } catch (Exception e) {
            throw new AppException("delete UserInfo fail. " + e.getMessage());
        }
	}

    /*
     * 保存
     * @param userInfo
     */
    public void save(UserInfo userInfo) {
        try {
            userInfoDao.save(userInfo);
        } catch (Exception e) {
            throw new AppException("save UserInfo fail." + e.getMessage());
        }
    }

    /*
     * 获得对象列表
     */
    public List<UserInfo> getUserInfoList() {
        return userInfoDao.getUserInfoList();
    }
    
    public String getPageData(HttpServletRequest request) throws Exception{
    	return this.userInfoDao.getPageData(request);
    }   
    
    public String UpUserPass(String userid,String pass) throws Exception{
    	return this.userInfoDao.UpUserPass(userid, pass);
    }
    
    public String getUserList(String userid)throws Exception{
    	return this.userInfoDao.getUserList(userid);
    }
    
    /**
	 * 根据uuid获得实例对象
     * @param userInfo
     */
    public UserInfo getByUuid(String uuid){
    	return this.userInfoDao.getByUuid(uuid);
    }
}