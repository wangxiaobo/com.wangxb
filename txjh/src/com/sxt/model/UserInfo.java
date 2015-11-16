package com.sxt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -1774850008766592749L;
	private Integer userId;
	private String loginName;
	private String loginPw;
	private String xm;
	private String szbj;//所在班级
	private String jg;
	private String jzd;
	private String hxDm;
	private String nl;
	private String xb;
	private String grgy;//个人感言
	//人生感悟
	private String rshgw;
	//30年感言
	private String gy30;
	
	private String xcap;
	private String jdyq;
	private String cysm;
	private String bz1;
	private String bz2;
	private String grjz;
	private String email;
	private String sjdh;
	private String qq;
	private String wx;
	
	/*** 增加 汇款登记tab页 **add 2015-11-7  开户银行，银行账号，转账金额，备注  加个转账时间**********/
    private String  khyh; //开户银行
    private String  yhzh; //银行账号
    private String  zzje; //转账金额
    private String  zzsj; //转账时间
    private String  zzbz; //备注

	/***  2015-11-8  */
    private  String isgly ;
    private  String isxgmm; 
    private  String cxfs  ;
    private  String tzr   ;

    private  String hksfydz   ;
	// Constructors

	/** default constructor */
	public UserInfo() {
	}
	
	@Column(name = "xb")
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	/** default constructor */
	public UserInfo(String loginName,String loginPw) {
		this.loginName = loginName;
		this.loginPw = loginPw;
	}

	/** full constructor */
	public UserInfo(String loginName, String loginPw, String xm, String jg,
			String jzd, String hxDm, String nl, String xcap, String jdyq,
			String cysm, String bz1, String grjz, String email, String sjdh,
			String qq, String wx) {
		this.loginName = loginName;
		this.loginPw = loginPw;
		this.xm = xm;
		this.jg = jg;
		this.jzd = jzd;
		this.hxDm = hxDm;
		this.nl = nl;
		this.xcap = xcap;
		this.jdyq = jdyq;
		this.cysm = cysm;
		this.bz1 = bz1;
		this.grjz = grjz;
		this.email = email;
		this.sjdh = sjdh;
		this.qq = qq;
		this.wx = wx;
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "login_name", length = 65535)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "login_pw")
	public String getLoginPw() {
		return this.loginPw;
	}

	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}

	@Column(name = "xm")
	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "jg")
	public String getJg() {
		return this.jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	@Column(name = "jzd")
	public String getJzd() {
		return this.jzd;
	}

	public void setJzd(String jzd) {
		this.jzd = jzd;
	}

	@Column(name = "hx_dm")
	public String getHxDm() {
		return this.hxDm;
	}

	public void setHxDm(String hxDm) {
		this.hxDm = hxDm;
	}

	@Column(name = "nl")
	public String getNl() {
		return this.nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	@Column(name = "xcap", length = 65535)
	public String getXcap() {
		return this.xcap;
	}

	public void setXcap(String xcap) {
		this.xcap = xcap;
	}

	@Column(name = "jdyq", length = 65535)
	public String getJdyq() {
		return this.jdyq;
	}

	public void setJdyq(String jdyq) {
		this.jdyq = jdyq;
	}

	@Column(name = "cysm", length = 65535)
	public String getCysm() {
		return this.cysm;
	}

	public void setCysm(String cysm) {
		this.cysm = cysm;
	}

	

	@Column(name = "grjz")
	public String getGrjz() {
		return this.grjz;
	}

	public void setGrjz(String grjz) {
		this.grjz = grjz;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "sjdh")
	public String getSjdh() {
		return this.sjdh;
	}

	public void setSjdh(String sjdh) {
		this.sjdh = sjdh;
	}

	@Column(name = "qq")
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "wx")
	public String getWx() {
		return this.wx;
	}

	public void setWx(String wx) {
		this.wx = wx;
	}
	@Column(name = "grgy")
	public String getGrgy() {
		return grgy;
	}
	
	public void setGrgy(String grgy) {
		this.grgy = grgy;
	}
	@Column(name = "bz1")
	public String getBz1() {
		return bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	@Column(name = "bz2")
	public String getBz2() {
		return bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	@Column(name = "rshgw")
	public String getRshgw() {
		return rshgw;
	}

	public void setRshgw(String rshgw) {
		this.rshgw = rshgw;
	}
	
	@Column(name = "gy30")
	public String getGy30() {
		return gy30;
	}

	public void setGy30(String gy30) {
		this.gy30 = gy30;
	}
	@Column(name = "szbj")
	public String getSzbj() {
		return szbj;
	}

	public void setSzbj(String szbj) {
		this.szbj = szbj;
	}
	
	@Column(name = "khyh")
	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	
	@Column(name = "yhzh")
	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	@Column(name = "zzje")
    public String getZzje() {
		return zzje;
	}

	public void setZzje(String zzje) {
		this.zzje = zzje;
	}
	
	@Column(name = "zzsj")
	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}
	@Column(name = "zzbz")
	public String getZzbz() {
		return zzbz;
	}

	public void setZzbz(String zzbz) {
		this.zzbz = zzbz;
	}

	public String getIsgly() {
		return isgly;
	}
	
	@Column(name = "isgly")
	public void setIsgly(String isgly) {
		this.isgly = isgly;
	}

	@Column(name = "isxgmm")
	public String getIsxgmm() {
		return isxgmm;
	}

	
	public void setIsxgmm(String isxgmm) {
		this.isxgmm = isxgmm;
	}

	@Column(name = "cxfs")
	public String getCxfs() {
		return cxfs;
	}

	public void setCxfs(String cxfs) {
		this.cxfs = cxfs;
	}

	@Column(name = "tzr")
	public String getTzr() {
		return tzr;
	}
	
	public void setTzr(String tzr) {
		this.tzr = tzr;
	}
	
	@Column(name = "hksfydz")
	public String getHksfydz() {
		return hksfydz;
	}

	public void setHksfydz(String hksfydz) {
		this.hksfydz = hksfydz;
	}
	

}