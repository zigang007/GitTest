package org.fkit.oa.notice.repository;


import java.util.List;

import org.fkit.oa.holiday.domain.APP_HIS;
import org.fkit.oa.notice.domain.Notice;
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
public interface NoticeRepository extends JpaRepository<Notice, String> , JpaSpecificationExecutor<Notice>{
	
	@Query("from Notice where notice_id =?1")
	public Notice findeNoticeById(long notice_id);
	
	@Query("from Notice where 1=1 order by  notice_id desc")
	public List<Notice> findAllNotice();
}







