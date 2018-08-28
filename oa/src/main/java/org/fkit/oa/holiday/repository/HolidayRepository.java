package org.fkit.oa.holiday.repository;


import java.util.List;

import org.fkit.oa.holiday.domain.Holiday;
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
public interface HolidayRepository extends JpaRepository<Holiday, String> , JpaSpecificationExecutor<Holiday>{

	@Query("from Holiday where code =?1")	
	Holiday findByCode(String code);
	
	@Query("from Holiday")
	List<Holiday> findAllHoliday();

    


}







