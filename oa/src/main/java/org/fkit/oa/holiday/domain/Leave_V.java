package org.fkit.oa.holiday.domain;

import java.util.Date;


public class Leave_V {
	
	private int leave_id;
	/** NAME VARCHAR2(50) 用户 */

	private String user_name;
	/**
	 * 请假类型
	 */
	private String leave_type;
	/**
	 * 请假类型编码
	 */
	private String leave_typeCode;
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
	private String app_user_name;
	
	/** REMARK	VARCHAR2(300) 请假开始时间*/
	private Date leave_begin_time;
	
	/** REMARK	VARCHAR2(300) 请假开始时间*/
	private Date leave_end_time;
	
	/** REMARK	VARCHAR2(300) 创建时间*/
	private Date leave_create_time;
	
	/** REMARK	VARCHAR2(300) 最后修改时间时间*/
	private Date leave_lastup_time;
	/**
	 * 状态
	 */
	private int leave_status;
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
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
	public String getApp_user_name() {
		return app_user_name;
	}
	public void setApp_user_name(String app_user_name) {
		this.app_user_name = app_user_name;
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
	public Date getLeave_create_time() {
		return leave_create_time;
	}
	public void setLeave_create_time(Date leave_create_time) {
		this.leave_create_time = leave_create_time;
	}
	public Date getLeave_lastup_time() {
		return leave_lastup_time;
	}
	public void setLeave_lastup_time(Date leave_lastup_time) {
		this.leave_lastup_time = leave_lastup_time;
	}
	public int getLeave_status() {
		return leave_status;
	}
	public void setLeave_status(int leave_status) {
		this.leave_status = leave_status;
	}
	public String getLeave_typeCode() {
		return leave_typeCode;
	}
	public void setLeave_typeCode(String leave_typeCode) {
		this.leave_typeCode = leave_typeCode;
	}
	
	
}
