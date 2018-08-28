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
@Entity @Table(name="OA_ID_APP_HIS")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class APP_HIS implements Serializable {
	
	private static final long serialVersionUID = 459497377750274376L;
	/**
	 * CODE	VARCHAR2(100) 代码 PK主键
	 */
	@Id @Column(name="his_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/**
	 * 单据ID
	 */
	@Column(name="leave_id")
	private int leave_id;
	
	/** NAME VARCHAR2(50) 审批人ID */
	@Column(name="app_user_id", length=50)
	private String app_user_id;
	
	/** NAME VARCHAR2(50) 审批人名称 */
	@Column(name="app_user_name", length=50)
	private String app_user_name;
	
	/**
	 * 审批意见
	 */
	@Column(name="app_idear")
	private String app_idear;
	/**
	 * 审批时间
	 */
	@Column(name="app_time")
	private Date app_time;
	
	/**
	 * 审批状态
	 */
	@Column(name="app_status")
	private int app_status;

	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApp_user_id() {
		return app_user_id;
	}

	public void setApp_user_id(String app_user_id) {
		this.app_user_id = app_user_id;
	}

	public String getApp_user_name() {
		return app_user_name;
	}

	public void setApp_user_name(String app_user_name) {
		this.app_user_name = app_user_name;
	}

	public String getApp_idear() {
		return app_idear;
	}

	public void setApp_idear(String app_idear) {
		this.app_idear = app_idear;
	}

	public Date getApp_time() {
		return app_time;
	}

	public void setApp_time(Date app_time) {
		this.app_time = app_time;
	}

	public int getApp_status() {
		return app_status;
	}

	public void setApp_status(int app_status) {
		this.app_status = app_status;
	}

	public int getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	
	 
	
}
