package com.thiscc.xtsz.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 记录客户服务业务信息
 * 
 */
public class CustomerServices implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3690110624821261098L;

	private Integer id;

	/** 标题 **/
	private String title;

	/** 联系人 **/
	private String customer;

	/** 联系电话 **/
	private String mobile;

	/** 联系地址 **/
	private String address;

	/** 内容 **/
	private String content;

	/**
	 * 类型: 1.报装申请 2.漏水举报 3.网上保修
	 */
	private Integer type;

	/** 创建时间 **/
	private Date createTime;

	/**图片存放物理路径**/
	private String savePath;

	/**图片路径**/
	private String imgUrl;
	

	public CustomerServices() {
		super();
	}



	public CustomerServices(String title, String customer, String mobile,
			String address, String content, Integer type, Date createTime,
			String savePath, String imgUrl) {
		super();
		this.title = title;
		this.customer = customer;
		this.mobile = mobile;
		this.address = address;
		this.content = content;
		this.type = type;
		this.createTime = createTime;
		this.savePath = savePath;
		this.imgUrl = imgUrl;
	}


	public CustomerServices(Integer id, String title, String customer,
			String mobile, String address, String content, Integer type,
			Date createTime, String savePath, String imgUrl) {
		super();
		this.id = id;
		this.title = title;
		this.customer = customer;
		this.mobile = mobile;
		this.address = address;
		this.content = content;
		this.type = type;
		this.createTime = createTime;
		this.savePath = savePath;
		this.imgUrl = imgUrl;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
