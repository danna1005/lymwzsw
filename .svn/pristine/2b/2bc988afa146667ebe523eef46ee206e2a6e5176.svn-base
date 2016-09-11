package com.thiscc.xtsz.dao;

import javax.servlet.http.HttpServletRequest;

import com.shensoft.tools.utils.DatabaseException;
import com.thiscc.tools.common.JSONQueryUtils;
import com.thiscc.tools.utils.StringUtils;
import com.thiscc.xtsz.entity.CustomerServices;
import com.thiscc.xtsz.entity.News;

/**
 * 客户服务,底层业务处理
 */
public class CustomerServicesDao  extends JSONQueryUtils{

	
	/**
	 * 保存客户服务信息
	 * @param customerServices 
	 */
	public void save(CustomerServices customerServices) {
        try {
        	  saveObject(customerServices);
        } catch (Exception e) {
            throw new DatabaseException("save customerServices fail. " + e.getMessage());
        }
    }
	
	
	public String getPageData(HttpServletRequest request) throws Exception
    {
    	String key = request.getParameter("mobile");
    	StringBuffer sql = new StringBuffer("select a from CustomerServices a where 1=1");
    	if(!StringUtils.isEmpty(key)){
        	sql.append(" and a.mobile like '%" + key + "%'");
        }
        return getDataGrid(sql,CustomerServices.class,"a",request);
    }
	
    public CustomerServices getById(String strId) {
    	CustomerServices customerServices = (CustomerServices) findByHqlOnly(
            "from CustomerServices where id=?", new Integer[] { new Integer(strId) });
        return customerServices;
    }
}
