package org.fkit.oa.holiday.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @Author xlei @tel 13360026135 @qq 251425887
 * @Date 2015年9月6日下午1:12:24
 * @Email dlei0009@163.com
 * @Version 1.0
 * @From http://www.fkit.org
 *
 */
@Entity @Table(name="OA_ID_HOLIDAY")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Holiday implements Serializable {
	
	private static final long serialVersionUID = 459497377750274376L;
	/**
	 * CODE	VARCHAR2(100) 代码 PK主键
	 */
	@Id @Column(name="CODE", length=100)
	private String code;
	/** NAME VARCHAR2(50) 名称 */
	@Column(name="NAME", length=50)
	private String name;
	/**
	 * 假期期限
	 */
	@Column(name="TIME")
	private String time;
	/**
	 * 假期享受人员范围
	 */
	@Column(name="RANGE", length=300)
	private String range;
	
	/**
	 * 假期规定
	 */
	@Column(name="FIX", length=300)
	private String fix;
	
	/** REMARK	VARCHAR2(300) 假期说明*/
	@Column(name="REMARK", length=300)
	private String remark;
	
	/** setter and getter method */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
	
}
