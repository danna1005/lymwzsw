package com.thiscc.xtsz.biz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.shensoft.tools.utils.AppException;
import com.thiscc.xtsz.dao.ImgManageDao;
import com.thiscc.xtsz.entity.ImgManage;

/**
 * 's Action
 * 
 * @author xianggx
 * @version 1.0
 * @since 2014/12/26
 */
public class ImgManageBiz {
    
    //~ Static fields/initializers =================================================================
    
    //~ Instance field =============================================================================
    
    // 
    @Resource(name = "imgManageDao")
    private ImgManageDao imgManageDao;
    
    //~ Properties accessors =======================================================================
    
    /**
     *
     */
    public void setImgManageDao(ImgManageDao imgManageDao) {
        this.imgManageDao = imgManageDao;
    }
    
    //~ Methods ====================================================================================
    
    //~ Private ====================================================================================
    
    //~ Protected ==================================================================================
    
    //~ Publics ====================================================================================
    
    /*
     * 根据ID获得对象实例
     * @param strId
     */
    public ImgManage getById(String strId) {
        return imgManageDao.getById(strId);
    }

    /*
     * 根据ID获得对象实例
     * @param strId
     */
    public void delete(String strId) {
        try {
            ImgManage imgManage = getById(strId);
            imgManageDao.delete(imgManage);
        } catch (Exception e) {
            throw new AppException("delete ImgManage fail. " + e.getMessage());
        }
	}

    /*
     * 保存
     * @param imgManage
     */
    public void save(ImgManage imgManage) {
        try {
            imgManageDao.save(imgManage);
        } catch (Exception e) {
            throw new AppException("save ImgManage fail." + e.getMessage());
        }
    }

    /*
     * 获得对象列表
     */
    public List<ImgManage> getImgManageList() {
        return imgManageDao.getImgManageList();
    }
    
	public String getPageData(HttpServletRequest request) throws Exception{
    	return imgManageDao.getPageData(request);
    }
	
	public void saveImgManage(String fileName,String fileExt,String url,String userid,String savePath) {
        try {
        	ImgManage imgManage = new ImgManage();
        	imgManage.setFileName(fileName);
        	imgManage.setFileExt(fileExt);
        	imgManage.setUrl(url);
        	imgManage.setCreateTime(new Date());
        	imgManage.setUserid(userid);
        	imgManage.setSort(1);
        	imgManage.setRemark(fileName.replaceAll("."+fileExt, ""));
        	imgManage.setSavePath(savePath);
        	imgManage.setIsDisplay("1");
            imgManageDao.save(imgManage);
        } catch (Exception e) {
            throw new AppException("saveImgManage fail." + e.getMessage());
        }
    }
}