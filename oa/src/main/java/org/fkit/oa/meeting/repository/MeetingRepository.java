package org.fkit.oa.meeting.repository;


import java.util.List;

import org.fkit.oa.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xlei
 * @Email dlei0006@163.com
 * @QQ 251425887
 * @Tel 18665616520
 * @Date 2017年1月14日上午9:44:47
 * @From http://www.fkjava.org 
 *
 */
public interface MeetingRepository extends JpaRepository<Meeting, String> , JpaSpecificationExecutor<Meeting>{
	
	@Query("from Meeting where Meeting_id =?1")
	public Meeting findeMeetingById(long Meeting_id);
	
	@Query("from Meeting where 1=1 order by  Meeting_id desc")
	public List<Meeting> findAllMeeting();
}







