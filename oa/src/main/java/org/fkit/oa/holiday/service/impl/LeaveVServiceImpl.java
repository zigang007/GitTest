package org.fkit.oa.holiday.service.impl;

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
import org.fkit.oa.holiday.repository.HolidayRepository;
import org.fkit.oa.holiday.repository.LeaveRepository;
import org.fkit.oa.holiday.service.LeaveService;
import org.fkit.oa.holiday.service.LeaveVService;
import org.fkit.oa.identity.domain.User;
import org.fkit.oa.identity.service.IdentityService;
import org.fkit.oa.util.OaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class LeaveVServiceImpl implements LeaveVService {
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private HolidayRepository holidayRepository;
	@Override
	public List<Leave_V> findForPageByUserId(String user_id,PageModel pageModel) {
		try {
			Page<Leave> holidayPager = leaveRepository.findAll(new Specification<Leave>() {
				@Override
				public Predicate toPredicate(Root<Leave> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(holidayPager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<Leave> leaves = holidayPager.getContent();
			List<Leave_V>  leavesV = new ArrayList<>();
			for(Leave l:leaves) {
				Leave_V v = new Leave_V();
				User u = identityService.getUserById(l.getUser_id());
				v.setUser_name(u.getName());
				Holiday h = holidayRepository.findByCode(l.getLeave_type());
				v.setLeave_type(h.getName());
				v.setLeave_cause(l.getLeave_cause());
				v.setLeave_begin_time(l.getLeave_begin_time());
				v.setLeave_end_time(l.getLeave_end_time());
				v.setLeave_time(l.getLeave_time());
				User u2 = identityService.getUserById(l.getApp_user());
				v.setApp_user_name(u2.getName());
				v.setLeave_status(l.getLeave_status());
				v.setLeave_id(l.getLEAVE_ID());
				v.setLeave_typeCode(h.getCode());
				leavesV.add(v);
			}
			return leavesV;
	} catch (Exception e) {
		throw new OaException("查询假期信息异常了", e);
	}
	}

	 

}
