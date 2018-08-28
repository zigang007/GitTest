package org.fkit.oa.meeting.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oa_id_meeting")
public class Meeting implements Serializable {

	/**
	 * 公告实体
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long meeting_id;
	private String meeting_type;
	private Date begin_time;
	private Date end_time;
	private String meeting_title;
	private String meeting_content;
	public Long getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(Long meeting_id) {
		this.meeting_id = meeting_id;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getMeeting_title() {
		return meeting_title;
	}
	public void setMeeting_title(String meeting_title) {
		this.meeting_title = meeting_title;
	}
	public String getMeeting_content() {
		return meeting_content;
	}
	public void setMeeting_content(String meeting_content) {
		this.meeting_content = meeting_content;
	}
	public String getMeeting_type() {
		return meeting_type;
	}
	public void setMeeting_type(String meeting_type) {
		this.meeting_type = meeting_type;
	}
	 
	

}
