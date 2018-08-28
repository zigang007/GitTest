package org.fkit.oa.notice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.holiday.domain.Holiday;
import org.fkit.oa.holiday.domain.Leave;
import org.fkit.oa.holiday.domain.Leave_V;
import org.fkit.oa.identity.domain.User;
import org.fkit.oa.notice.domain.Notice;
import org.fkit.oa.notice.repository.NoticeRepository;
import org.fkit.oa.notice.service.NoticeService;
import org.fkit.oa.util.OaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	@Override
	public List<Notice> findForPageByQuery(Notice notice,PageModel pageModel) {
		try {
			Page<Notice> noticePager = noticeRepository.findAll(new Specification<Notice>() {
				@Override
				public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					if(notice!=null){
						if(!StringUtils.isEmpty(notice.getNotice_title())){
							predicates.add(cb.like(root.<String> get("notice_title"),"%" +notice.getNotice_title() + "%"));
						}
					}	
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(noticePager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<Notice> notices = noticePager.getContent();
			
			return notices;
	} catch (Exception e) {
		throw new OaException("查询公告信息异常了", e);
	}
	}
	@Override
	public void deleteNoticeById(String ids) {
		try {
			List<Notice> noticeList = new ArrayList<Notice>();
			for(String id : ids.split(",")){
				Notice notice = new Notice() ;
				notice.setNotice_id(Long.valueOf(id));
				noticeList.add(notice);
			}
			noticeRepository.deleteInBatch(noticeList);
		} catch (Exception e) {
			throw new OaException("删除公告信息异常了", e);
		}
		
	}

	 

}
