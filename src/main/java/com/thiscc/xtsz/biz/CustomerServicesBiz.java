package com.thiscc.xtsz.biz;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.shensoft.tools.utils.AppException;
import com.thiscc.xtsz.dao.CustomerServicesDao;
import com.thiscc.xtsz.entity.CustomerServices;
import com.thiscc.xtsz.entity.News;


/**
 * 客户服务业务处理
 *
 */
@Service
public class CustomerServicesBiz {

	@Resource
	private CustomerServicesDao customerServicesDao;
	
	public void saveCustomerServices(CustomerServices customerServices){
		//validate(customerServices)
		try {
			customerServicesDao.save(customerServices);
		} catch (Exception e) {
			throw new AppException("The save CustomerServices fail." + e.getMessage());
		}
	}
	
	public String getPageData(HttpServletRequest request) throws Exception{
    	return customerServicesDao.getPageData(request);
    }
	
    public CustomerServices getById(String strId) {
        return customerServicesDao.getById(strId);
    }
	
}
