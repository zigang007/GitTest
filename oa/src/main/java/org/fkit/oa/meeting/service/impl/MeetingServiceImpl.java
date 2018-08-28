package org.fkit.oa.meeting.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.meeting.domain.Meeting;
import org.fkit.oa.meeting.repository.MeetingRepository;
import org.fkit.oa.meeting.service.MeetingService;
import org.fkit.oa.util.OaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {
	@Autowired
	private MeetingRepository MeetingRepository;
	@Override
	public List<Meeting> findForPageByQuery(Meeting meeting,PageModel pageModel) {
		try {
			Page<Meeting> MeetingPager = MeetingRepository.findAll(new Specification<Meeting>() {
				@Override
				public Predicate toPredicate(Root<Meeting> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					if(meeting!=null){
						if(!StringUtils.isEmpty(meeting.getMeeting_title())){
							predicates.add(cb.like(root.<String> get("Meeting_title"),"%" +meeting.getMeeting_title() + "%"));
						}
					}	
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(MeetingPager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<Meeting> Meetings = MeetingPager.getContent();
			
			return Meetings;
	} catch (Exception e) {
		throw new OaException("查询公告信息异常了", e);
	}
	}
	@Override
	public void deleteMeetingById(String ids) {
		try {
			List<Meeting> meetingList = new ArrayList<Meeting>();
			for(String id : ids.split(",")){
				Meeting meeting = new Meeting() ;
				meeting.setMeeting_id(Long.valueOf(id));
				meetingList.add(meeting);
			}
			MeetingRepository.deleteInBatch(meetingList);
		} catch (Exception e) {
			throw new OaException("删除公告信息异常了", e);
		}
		
	}

	 

}
