package com.thiscc.xtsz.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.thiscc.tools.common.BaseDAO;
import com.thiscc.zm.TestDB;

public class LoginInfoDao extends BaseDAO{
	
    public List<Map<String, Object>> LoginCheck(Object param) throws Exception {
       List<Map<String, Object>> list = null;
       JSONObject jsonparam = JSONObject.fromObject(param);
       String userno = jsonparam.get("username") != null ? jsonparam.get("username").toString() : "";
       String userpwd = jsonparam.get("password") != null ? jsonparam.get("password").toString() : "";

       String wsql ="select id,userno,username,userpass from dbo.t_userInfo "+
                   "where userno='"+userno+"' and userpass='"+userpwd+"' ";
       
       TestDB td = new TestDB();
       list = td.DbSelectList(wsql);

       return list;
    }
    public String getMainTree(String userid) throws Exception{
        StringBuilder slist = new StringBuilder();

        String strSQL = "select tid,partid,isnull(name,'') as name,isnull(url,'') as url,image,dbo.f_get_UserRoot("+userid+",tid) as parbol from dbo.t_manageTree "+
                        "where tid in (select moduleid from dbo.t_userpower where userid="+userid+") and type=0 order by OrderBy asc ";

        TestDB td = new TestDB();
        List<Map<String, Object>> list = td.DbSelectList(strSQL);
   
        slist.append("[");
        for (int i=0;i < list.size();i++){
            Map<String, Object> rs = list.get(i);
        	slist.append("{\"name\":\"" + rs.get("name").toString()+ "\",");
            slist.append("\"img\":\"" + rs.get("image").toString() + "\"");
            if (rs.get("parbol").toString().equals("1")){
            	slist.append(",");	
            	slist.append("\"children\":" + getMainPartTree(userid,rs.get("tid").toString()) );	
            }
            slist.append("},");	
        }
        slist.delete(slist.length() - 1, slist.length());
        slist.append("];");
        return slist.toString();
    }
    private static String getMainPartTree(String userid,String partid) throws Exception{
        StringBuilder slist = new StringBuilder();

        String strSQL = "select tid,partid,isnull(name,'') as name,isnull(url,'') as url,image,dbo.f_get_UserRoot("+userid+",tid) as parbol from dbo.t_manageTree "+
                        "where tid in (select moduleid from dbo.t_userpower where userid="+userid+") and partid="+partid+" order by OrderBy asc ";

        TestDB td = new TestDB();
        List<Map<String, Object>> list = td.DbSelectList(strSQL);
   
        slist.append("[");
        for (int i=0 ; i < list.size();i++){
            Map<String, Object> rs = list.get(i);
        	slist.append("{\"name\":\"" + rs.get("name").toString()+ "\",");
        	slist.append("\"id\":\"" + rs.get("tid").toString() + "\"");
        	if (!rs.get("url").toString().equals("")){
               slist.append(",");	
               slist.append("\"url\":\"" + rs.get("url").toString() + "\"");
        	}
            if (rs.get("parbol").equals("1")){
               slist.append(",");	
               slist.append("\"children\":" + getMainPartTree(userid,rs.get("tid").toString()) );	
            }
            slist.append("},");	
        }
        slist.delete(slist.length() - 1, slist.length());
        slist.append("]");
        return slist.toString();	
    }
	public void setToken(JSONObject param) {
		String wsql = "";
		try {
			String userno = param.getString("username");
		    String token = param.getString("token");
			TestDB td = new TestDB();
			wsql = "update dbo.t_userInfo set token='"+token+"' where userno='"+userno+"'";
			td.DBUpdate(wsql);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}