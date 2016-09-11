package com.thiscc.xtsz.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.thiscc.tools.common.BeanUtils;
import com.thiscc.tools.common.BizFactory;
import com.thiscc.xtsz.biz.UserInfoBiz;
import com.thiscc.xtsz.entity.UserInfo;
import com.thiscc.zm.JSON;

/**
 * 's Action
 * 
 * @author thy
 * @version 1.0
 * @since 2014-09-30
 */
public class UserInfoAction extends DispatchAction {

    private UserInfoBiz userInfoBiz = (UserInfoBiz) BizFactory.getBean("userInfoBiz");
    
    /**
     * 保存
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
			
				UserInfo userInfo = null;
				String strId = row.get("id") != null ? row.get("id").toString() : "";
				if ((strId == null) || (strId.equals("")))
					userInfo = new UserInfo();
				else
					userInfo = userInfoBiz.getById(strId);
				BeanUtils.setProperties(userInfo, row);
				userInfoBiz.save(userInfo);
			}
        }catch(Exception e){
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
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
        UserInfo userInfo = userInfoBiz.getById(strId);
        if (userInfo == null) {
            userInfo = new UserInfo();
            Map parameterMap = request.getParameterMap();
            BeanUtils.setProperties(userInfo, parameterMap);
        }
        Map properties = BeanUtils.getProperties(userInfo);
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
        UserInfo userInfo = userInfoBiz.getById(strId);
        StringBuffer sbJson = new StringBuffer();
        sbJson.append(BeanUtils.getPropertiesJson(userInfo));
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
                userInfoBiz.delete(strArray[k]);
            }catch(Exception e){
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
    
    //获取查询数据
	public ActionForward getPageData(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	PrintWriter printWriter = response.getWriter();
        String result = this.userInfoBiz.getPageData(request);
        printWriter.write(result);
        return ((ActionForward) null);
    }

    /*
     * 修改密码
     */
    public ActionForward UpUserPass(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        PrintWriter printWriter = response.getWriter();
        HttpSession session = request.getSession();
        String userid="";
        String userpass="";
        try{
           userid = session.getAttribute("userid").toString();
           userpass = session.getAttribute("userpass").toString();
        }catch(Exception e){
      	   userid="";
      	   userpass="";
        }
        if (!userid.equals("")){
            String old = request.getParameter("oldpsw");
            String newpsw = request.getParameter("newpsw");
            if (userpass.equals(old)){
               printWriter.write(this.userInfoBiz.UpUserPass(userid, newpsw));
               request.getSession().setAttribute("userpass",newpsw);
            }else{
               printWriter.write("0");
            }
        }else{
           printWriter.write("2");
        }
        return ((ActionForward) null);
    }
    
    public ActionForward getUserList(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	PrintWriter printWriter = response.getWriter();
    	String userid = (String)request.getSession().getAttribute("userid");
        String result = this.userInfoBiz.getUserList(userid);
        printWriter.write(result);
        return ((ActionForward) null);
    }
}