package com.thiscc.xtsz.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.thiscc.xtsz.dao.LoginInfoDao;

public class LoginInfoBiz {
	@Resource(name = "loginInfoDao")
	private LoginInfoDao logininfodao;

	public void setAccountdao(LoginInfoDao logindao) {
		this.logininfodao = logindao;
	}
    public List<Map<String, Object>> LoginCheck(Object param) throws Exception{
       return this.logininfodao.LoginCheck(param);
    }
    public String getMainTree(String userid) throws Exception{
       return this.logininfodao.getMainTree(userid);
    }
	public void setToken(JSONObject param) {
		this.logininfodao.setToken(param);
	}
}