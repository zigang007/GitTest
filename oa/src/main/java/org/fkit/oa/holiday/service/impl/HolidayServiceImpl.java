package org.fkit.oa.holiday.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.holiday.domain.Holiday;
import org.fkit.oa.holiday.repository.HolidayRepository;
import org.fkit.oa.holiday.service.HolidayService;
import org.fkit.oa.identity.domain.User;
import org.fkit.oa.util.OaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("holidayService")
@Transactional(readOnly=true)
public class HolidayServiceImpl implements HolidayService {

	@Autowired  // bytype
	@Qualifier("holidayRepository") // byName
	private HolidayRepository holidayRepository;
	
	@SuppressWarnings("serial")
	@Override
	public List<Holiday> getHolidayByPager(Holiday holiday, PageModel pageModel) {
		try {
			Page<Holiday> holidayPager = holidayRepository.findAll(new Specification<Holiday>() {
				@Override
				public Predicate toPredicate(Root<Holiday> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					// 本集合用于封装查询条件
					List<Predicate> predicates = new ArrayList<Predicate>();  
					if(holiday!=null){
						/** 是否传入了假期名称来查询  */
						if(!StringUtils.isEmpty(holiday.getName())){
							predicates.add(cb.like(root.<String> get("name"),"%" + holiday.getName() + "%"));
						}
					}
					return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
				}
			}, PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize()));
			pageModel.setRecordCount(holidayPager.getTotalElements());
			/** 取每个用户的延迟加载属性 */
			List<Holiday> holidays = holidayPager.getContent();
			
			return holidays;
	} catch (Exception e) {
		throw new OaException("查询假期信息异常了", e);
	}
	}

	@Override
	@Transactional
	public void addHoliday(Holiday holiday) {
		try {
		    holidayRepository.save(holiday);
		} catch (Exception e) {
			throw new OaException("添加节假日信息异常了", e);
		}
		
	}

	@Override
	@Transactional
	public void deleteUserByHolidayIds(String ids) {
		try {
			List<Holiday> holidays = new ArrayList<Holiday>();
			for(String id : ids.split(",")){
				Holiday holiday = new Holiday() ;
				holiday.setCode(id);
				holidays.add(holiday);
			}
			holidayRepository.deleteInBatch(holidays);
		} catch (Exception e) {
			throw new OaException("删除用户信息异常了", e);
		}
		
	}

	@Override
	@Transactional
	public void updateHoliday(Holiday holiday) {
		try {
			holidayRepository.save(holiday);
		}catch (Exception e) {
			throw new OaException("修改用户信息异常了", e);
		}
		
		
	}

	 

}
