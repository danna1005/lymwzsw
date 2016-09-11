package com.thiscc.xtsz.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.thiscc.tools.common.BeanUtils;
import com.thiscc.tools.common.BizFactory;
import com.thiscc.xtsz.biz.ImgManageBiz;
import com.thiscc.xtsz.entity.ImgManage;
import com.thiscc.zm.JSON;

/**
 * 's Action
 * 
 * @author xianggx
 * @version 1.0
 * @since 2014/12/26
 */
public class ImgManageAction extends DispatchAction {
    
    //~ Static fields/initializers =================================================================
    
    //~ Instance field =============================================================================
    
    private ImgManageBiz imgManageBiz = (ImgManageBiz) BizFactory.getBean("imgManageBiz");
    
    //~ Properties accessors =======================================================================
       
    //~ Methods ====================================================================================
    
    //~ Private ====================================================================================
    
    //~ Protected ==================================================================================
    
    //~ Publics ====================================================================================

    /**
     * 保存
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward save(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        try {
			String json = request.getParameter("data");
            ArrayList rows = (ArrayList)JSON.Decode(json);
            for(int i=0,l=rows.size(); i<l; i++){
            	HashMap row = (HashMap)rows.get(i);
			
			
				ImgManage imgManage = null;
				String strId = row.get("id") != null ? row.get("id").toString() : "";
				if ((strId == null) || (strId.equals("")))
					imgManage = new ImgManage();
				else
					imgManage = imgManageBiz.getById(strId);
				BeanUtils.setProperties(imgManage, row);
				imgManageBiz.save(imgManage);
			}
        } catch (Exception e) {
			log.error("save fail. " + e.getMessage());
            writer.write("{success:false}");
            return null;
        }
        return null;
    }

    /**
     * 详细信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward detail(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String strId = request.getParameter("id");
        String strPath = request.getParameter("_path");
        try {
            strPath = URLDecoder.decode(strPath, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("decode error. " + e.getMessage());
        }
        ImgManage imgManage = imgManageBiz.getById(strId);
        if (imgManage == null) {
            imgManage = new ImgManage();
            Map parameterMap = request.getParameterMap();
            BeanUtils.setProperties(imgManage, parameterMap);
        }
        Map properties = BeanUtils.getProperties(imgManage);
        request.setAttribute("form", properties);
        return new ActionForward(strPath);
    }

    /**
     * 获得详细信息的json
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward detailJson(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String strId = request.getParameter("id");
        ImgManage imgManage = imgManageBiz.getById(strId);
        StringBuffer sbJson = new StringBuffer();
        sbJson.append(BeanUtils.getPropertiesJson(imgManage));
        PrintWriter writer = response.getWriter();
        writer.write(sbJson.toString());
        return null;
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward delete(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String strId = request.getParameter("id");
        if ((strId == null) || (strId.equals("")))
            return null;
        String[] strArray = strId.split(",");
        int iLen = strArray.length;
        int iFail = 0;
        for (int k = 0; k < iLen; ++k) {
            if (strArray[k].equals(""))
                continue;
            try {
            	ImgManage im = imgManageBiz.getById(strArray[k]);
            	if(StringUtils.isNotEmpty(im.getSavePath())){
            		File file = new File(im.getSavePath());
            		if(file.isFile() && file.exists()){
            			file.delete();
            		}
            	}
                imgManageBiz.delete(strArray[k]);
            } catch (Exception e) {
                log.error("delete fail." + e.getMessage());
                ++iFail;
            }
        }
        if (iFail > 0) {
            PrintWriter writer = response.getWriter();
            writer.write("{success:false,error:'<b>" + iFail + "</b> 条数据删除失败'}");
        }
        return null;
    }
	
	public ActionForward getPageData(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	PrintWriter printWriter = response.getWriter();
        String result = imgManageBiz.getPageData(request);
        printWriter.write(result);
        return ((ActionForward) null);
    }
}