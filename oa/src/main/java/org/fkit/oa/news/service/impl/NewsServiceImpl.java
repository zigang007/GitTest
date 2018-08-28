package org.fkit.oa.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.news.domain.News;
import org.fkit.oa.news.repository.NewsRepository;
import org.fkit.oa.news.service.NewsService;
import org.fkit.oa.util.OaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsRepository NewsRepository;
	@Override
	public List<News> findForPageByQuery(News News,PageModel pageModel) {
		try {
			Page<News> NewsPager = NewsRepository.findAll(new Specification<News>() {
				@Override
				public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					if(News!=null){
						if(!StringUtils.isEmpty(News.getNews_title())){
							predicates.add(cb.like(root.<String> get("news_title"),"%" +News.getNews_title() + "%"));
						}
					}	
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(NewsPager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<News> Newss = NewsPager.getContent();
			
			return Newss;
	} catch (Exception e) {
		throw new OaException("查询公告信息异常了", e);
	}
	}
	@Override
	public void deleteNewsById(String ids) {
		try {
			List<News> NewsList = new ArrayList<News>();
			for(String id : ids.split(",")){
				News News = new News() ;
				News.setNews_id(Long.valueOf(id));
				NewsList.add(News);
			}
			NewsRepository.deleteInBatch(NewsList);
		} catch (Exception e) {
			throw new OaException("删除公告信息异常了", e);
		}
		
	}

	 

}
