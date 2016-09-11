package com.thiscc.xtsz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.thiscc.tools.common.BeanUtils;
import com.thiscc.tools.common.BizFactory;
import com.thiscc.xtsz.biz.CustomerServicesBiz;
import com.thiscc.xtsz.entity.CustomerServices;
import com.thiscc.xtsz.entity.News;

/**
 * 处理客户服务业务,控制层
 *   
 */
public class CustomerServicesAction  extends DispatchAction {

	private CustomerServicesBiz customerServicesBiz = (CustomerServicesBiz) BizFactory.getBean("customerServicesBiz");
	  
	 public ActionForward  save(ActionMapping mapping,
	            ActionForm form,
	            HttpServletRequest request,
	            HttpServletResponse response) throws IOException {
		String title=request.getParameter("title");
		String customer=request.getParameter("customer");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String content=request.getParameter("content");
		String type=request.getParameter("type");
		String imgUrl = request.getParameter("imgUrl");
	    PrintWriter writer = response.getWriter();
		try {
			customerServicesBiz.saveCustomerServices(new CustomerServices(title
					, customer, mobile, address, content, Integer.valueOf(type), new Date(),"",imgUrl));
			  writer.write("true");
		} catch (NumberFormatException e) {
			 writer.write("false");
		}
		 writer.close();
		 return mapping.findForward(null);
	 }
	 
	 public ActionForward getPageData(ActionMapping mapping,
	            ActionForm form,
	            HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	    	PrintWriter printWriter = response.getWriter();
	        String result = customerServicesBiz.getPageData(request);
	        printWriter.write(result);
	        printWriter.close();
	        return mapping.findForward(null);
	    }
	 
	 
	    public ActionForward detailJson(ActionMapping mapping,
	            ActionForm form,
	            HttpServletRequest request,
	            HttpServletResponse response) throws IOException {
	        String strId = request.getParameter("id");
	        CustomerServices customerServices = customerServicesBiz.getById(strId);
	        StringBuffer sbJson = new StringBuffer();
	        sbJson.append(BeanUtils.getPropertiesJson(customerServices));
	        PrintWriter writer = response.getWriter();
	        writer.write(sbJson.toString());
	        writer.close();
	        return mapping.findForward(null);
	    }
}
