package org.fkit.oa.holiday.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.fkit.oa.identity.domain.Dept;
import org.fkit.oa.identity.domain.User;

/**
 * 用户请假
 * @Author xlei @tel 13360026135 @qq 251425887
 * @Date 2015年9月6日下午1:12:24
 * @Email dlei0009@163.com
 * @Version 1.0
 * @From http://www.fkit.org
 *
 */
@Entity @Table(name="OA_ID_LEAVE")
public class Leave implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6947420982099088862L;
	/**
	 * ID	VARCHAR2(100) 代码 PK主键
	 */
	@Id @Column(name="LEAVE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int LEAVE_ID;
	/** NAME VARCHAR2(50) 用户 */

	@Column(name="USER_ID")
	private String user_id;
	/**
	 * 请假类型
	 */
	@Column(name="LEAVE_TYPE", length=50)
	private String leave_type;
	/**
	 * 请假时长
	 */
	@Column(name="LEAVE_TIME", length=50)
	private String leave_time;
	
	/**
	 * 请假原因
	 */
	@Column(name="LEAVE_CAUSE", length=300)
	private String leave_cause;
	
	/**
	 * 提交给谁审批
	 */
	@Column(name="app_user", length=300)
	private String app_user;
	
	/** REMARK	VARCHAR2(300) 请假开始时间*/
	@Column(name="LEAVE_BEGIN_TIME")
	private Date leave_begin_time;
	
	/** REMARK	VARCHAR2(300) 请假开始时间*/
	@Column(name="LEAVE_END_TIME")
	private Date leave_end_time;
	
	/** REMARK	VARCHAR2(300) 创建时间*/
	@Column(name="LEAVE_CREATE_TIME")
	private Date leave_create_time;
	
	/** REMARK	VARCHAR2(300) 最后修改时间时间*/
	@Column(name="LEAVE_LASTUP_TIME")
	private Date leave_lastup_time;
	/**
	 * 状态
	 */
	@Column(name="LEAVE_STATUS")
	private int leave_status;
	public int getLEAVE_ID() {
		return LEAVE_ID;
	}
	public void setLEAVE_ID(int lEAVE_ID) {
		LEAVE_ID = lEAVE_ID;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getApp_user() {
		return app_user;
	}
	public void setApp_user(String app_user) {
		this.app_user = app_user;
	}
	 
	 
 
	 
}
