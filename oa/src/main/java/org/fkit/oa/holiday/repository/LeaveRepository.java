package org.fkit.oa.holiday.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Update;
import org.fkit.oa.holiday.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
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
public interface LeaveRepository extends JpaRepository<Leave, String> , JpaSpecificationExecutor<Leave>{
	
	@Query("from Leave where LEAVE_ID=?1")
	public Leave findLeaveById(int LEAVE_ID);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Leave set leave_status =?1 where leave_id =?2")
	public void updateStatusById(int leave_status, int leave_id);
}







