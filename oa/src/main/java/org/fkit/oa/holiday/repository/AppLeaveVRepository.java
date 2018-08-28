package org.fkit.oa.holiday.repository;


import java.util.List;

import org.fkit.oa.holiday.domain.APP_Leave_V;
import org.fkit.oa.holiday.domain.Leave;
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
public interface AppLeaveVRepository extends JpaRepository<APP_Leave_V, String> , JpaSpecificationExecutor<APP_Leave_V>{
	
	@Query("from APP_Leave_V where app_user=?1 and leave_status ='1'")
	public List<APP_Leave_V> findAppLeaveByUser(String app_user);
	
	@Query("from APP_Leave_V where leave_id=?1 ")
	public APP_Leave_V findAppLeaveLeaveId(int leave_id);
}







