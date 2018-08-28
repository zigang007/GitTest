package org.fkit.oa.know.service;

import java.util.List;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.news.domain.News;

public interface NewsService {

	public List<News> findForPageByQuery(News News, PageModel pageModel);
	
	public void deleteNewsById(String ids);
	

}
