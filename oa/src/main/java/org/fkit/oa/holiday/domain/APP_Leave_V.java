package org.fkit.oa.holiday.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oa_id_leave_V")
public class APP_Leave_V  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4754563728410161974L;

	@Id
	private int leave_id;
	
	/** NAME VARCHAR2(50) 用户ID */
	private String user_id;
	
	/** NAME VARCHAR2(50) 用户名称 */
	private String user_name;
	/**
	 * 请假类型
	 */
	private String holiday_name;
	/**
	 * 请假类型编码
	 */
	private String holiday_code;
	
	private String dept_id;
	
	private String dept_name;
	/**
	 * 请假时长
	 */
	private String leave_time;
	
	/**
	 * 请假原因
	 */
	private String leave_cause;
	
	/**
	 * 提交给谁审批
	 */
	private String app_user;
	
	/** REMARK	VARCHAR2(300) 请假开始时间*/
	private Date leave_begin_time;
	
	/** REMARK	VARCHAR2(300) 请假开始时间*/
	private Date leave_end_time;
	/**
	 * 单据状态
	 */
	private String leave_status;
	
	public int getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getHoliday_name() {
		return holiday_name;
	}

	public void setHoliday_name(String holiday_name) {
		this.holiday_name = holiday_name;
	}

	public String getHoliday_code() {
		return holiday_code;
	}

	public void setHoliday_code(String holiday_code) {
		this.holiday_code = holiday_code;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	 
	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}

	public String getLeave_cause() {
		return leave_cause;
	}

	public void setLeave_cause(String leave_cause) {
		this.leave_cause = leave_cause;
	}

	public String getApp_user() {
		return app_user;
	}

	public void setApp_user(String app_user) {
		this.app_user = app_user;
	}

	public Date getLeave_begin_time() {
		return leave_begin_time;
	}

	public void setLeave_begin_time(Date leave_begin_time) {
		this.leave_begin_time = leave_begin_time;
	}

	public Date getLeave_end_time() {
		return leave_end_time;
	}

	public void setLeave_end_time(Date leave_end_time) {
		this.leave_end_time = leave_end_time;
	}

	public String getLeave_status() {
		return leave_status;
	}

	public void setLeave_status(String leave_status) {
		this.leave_status = leave_status;
	}
 

 
	
}
