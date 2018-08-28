package org.fkit.oa.util.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Update;
import org.fkit.oa.holiday.domain.APP_HIS;
import org.fkit.oa.holiday.domain.Leave;
import org.fkit.oa.util.entity.Files;
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
public interface FileRepository extends JpaRepository<Files, String> , JpaSpecificationExecutor<Files>{
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Files set upload_file_id =?1 where file_id=?2")
	public void uploadFile(long upload_file_id,long file_id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("delete from Files  where file_id=?1")
	public void deleteFileById(long file_id);
	
	@Query("from Files where upload_file_id=?1")
	public List<Files> getFileByNoticeId(Long notice_id);
	
	@Query("from Files where file_id=?1")
	public Files getFileById(Long file_id);
}







