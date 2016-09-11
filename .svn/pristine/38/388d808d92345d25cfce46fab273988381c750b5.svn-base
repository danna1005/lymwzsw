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

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.thiscc.tools.common.BeanUtils;
import com.thiscc.tools.common.BizFactory;
import com.thiscc.xtsz.biz.NewsBiz;
import com.thiscc.xtsz.entity.News;
import com.thiscc.zm.JSON;

/**
 * 's Action
 * 
 * @author wxj
 * @version 1.0
 * @since 2014/11/1
 */
public class NewsAction extends DispatchAction {
    
    //~ Static fields/initializers =================================================================
    
    //~ Instance field =============================================================================
    
    private NewsBiz newsBiz = (NewsBiz) BizFactory.getBean("newsBiz");
    
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
			
				News news = null;
				String strId = row.get("id") != null ? row.get("id").toString() : "";
				if ((strId == null) || (strId.equals(""))){
					news = new News();
					String name = (String)request.getSession().getAttribute("username");
					String zzid = (String)request.getSession().getAttribute("userid");
					news.setZuozhe(name);
					news.setZzid(Integer.valueOf(zzid));
				}
				else
					news = newsBiz.getById(strId);
				BeanUtils.setProperties(news, row);
				newsBiz.save(news);
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
        News news = newsBiz.getById(strId);
        if (news == null) {
            news = new News();
            Map parameterMap = request.getParameterMap();
            BeanUtils.setProperties(news, parameterMap);
        }
        Map properties = BeanUtils.getProperties(news);
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
        News news = newsBiz.getById(strId);
        StringBuffer sbJson = new StringBuffer();
        sbJson.append(BeanUtils.getPropertiesJson(news));
        PrintWriter writer = response.getWriter();
        writer.write(sbJson.toString());
        return null;
    }
    
    /**
     * 通过type获得详细信息的json（公司文化、联系我们）
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward detailJsonByType(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        News news = newsBiz.getByType(type);
        StringBuffer sbJson = new StringBuffer();
        sbJson.append(BeanUtils.getPropertiesJson(news));
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
                newsBiz.delete(strArray[k]);
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
	
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward changeState(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String strId = request.getParameter("id");
        String state = request.getParameter("state");
        if ((strId == null) || (strId.equals("")))
            return null;
            try {
            	News news = newsBiz.getById(strId);
            	news.setState(state);
				newsBiz.save(news);
            } catch (Exception e) {
                log.error("changeState fail." + e.getMessage());
            }
        return null;
    }
    
	public ActionForward getPageData(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	PrintWriter printWriter = response.getWriter();
        String result = newsBiz.getPageData(request);
        printWriter.write(result);
        return ((ActionForward) null);
    }
	
	public ActionForward getCount(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	PrintWriter printWriter = response.getWriter();
        JSONObject result = newsBiz.getCount(request);
        printWriter.write(result.toString());
        return ((ActionForward) null);
    }
}