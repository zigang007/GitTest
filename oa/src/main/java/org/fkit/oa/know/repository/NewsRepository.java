package org.fkit.oa.know.repository;


import java.util.List;

import org.fkit.oa.news.domain.News;
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
public interface NewsRepository extends JpaRepository<News, String> , JpaSpecificationExecutor<News>{
	
	@Query("from News where news_id =?1")
	public News findeNewsById(long news_id);
	
	@Query("from News where 1=1 order by news_id desc")
	public List<News> findAllNews();
}







