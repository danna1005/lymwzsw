package com.thiscc.xtsz.entity;

import java.io.Serializable;

/**
 * 
 * 
 * @author wxj
 * @version 1.0
 * @since 2014/11/1
 */
public class News implements Serializable {
    
    //~ Static fields ==============================================================================
    
    //~ Instance field =============================================================================
    
    // 
    private Integer id;
	
    // 
    private String title;
	
    // 
    private java.util.Date time;
	
    // 
    private String contents;
	
    // 
    private String zuozhe;
	
    // 
    private String state;
	
    private Integer zzid;
    
    private String type;//1:公司动态;2:公告通知;3:法律法规
    
    //~ Constructors ===============================================================================
	
    public News() {
    }
	
    //~ Properties accessors =======================================================================
	
    /**
     * 获得
     * return Integer
     */
    public Integer getid() {
        return id;
    }
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getZuozhe() {
		return zuozhe;
	}

	public void setZuozhe(String zuozhe) {
		this.zuozhe = zuozhe;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getZzid() {
		return zzid;
	}

	public void setZzid(Integer zzid) {
		this.zzid = zzid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


    
}
