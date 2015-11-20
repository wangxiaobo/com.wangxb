package com.sxt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.jdls.foundation.util.StringUtil;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "image_info")
public class ImageInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3428019409210398010L;
	// Fields
	
	private String imageId;
	private Integer userId;
	private String userName;
	private String path;
	private String content;
	private String flag;
	private String lrsj;
	private String xclx;

	
	/** default constructor */
	public ImageInfo() {
	}
    
	public ImageInfo(ImageInfo info,String userName ){
		this.imageId=info.getImageId();
		this.userId=info.getUserId();
		this.userName=userName;
		this.path=info.getPath();
		this.content= StringUtil.nvl(info.getContent(),"");
		this.flag=info.getFlag();
		this.xclx = info.getXclx();
		this.lrsj=info.getLrsj();	
		
	}
	
	@Id
	@Column(name = "image_id", unique = true, nullable = false)
	public String getImageId() {
		return imageId;
	}
	
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}



	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "flag")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Transient
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLrsj() {
		return lrsj;
	}
	@Column(name = "lrsj")
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	
	@Column(name = "xclx")
	public String getXclx() {
		return xclx;
	}

	public void setXclx(String xclx) {
		this.xclx = xclx;
	}

	
	
}