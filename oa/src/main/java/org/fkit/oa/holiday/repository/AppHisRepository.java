package org.fkit.oa.holiday.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Update;
import org.fkit.oa.holiday.domain.APP_HIS;
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
public interface AppHisRepository extends JpaRepository<APP_HIS, String> , JpaSpecificationExecutor<APP_HIS>{
	
	@Query("from APP_HIS where leave_id=?1")
	public List<APP_HIS> findAppHIsByLeaveId(int leave_id);

}







