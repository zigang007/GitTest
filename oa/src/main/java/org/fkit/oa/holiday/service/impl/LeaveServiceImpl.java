package org.fkit.oa.holiday.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.holiday.domain.Holiday;
import org.fkit.oa.holiday.domain.Leave;
import org.fkit.oa.holiday.repository.LeaveRepository;
import org.fkit.oa.holiday.service.LeaveService;
import org.fkit.oa.util.OaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public List<Leave> findForPageByUserId(String user_id, PageModel pageModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void deleteMyLeave(String ids) {
		try {
			List<Leave> leave = new ArrayList<Leave>();
			for(String id : ids.split(",")){
				Leave l = new Leave() ;
				l.setLEAVE_ID(Integer.valueOf(id));
				leave.add(l);
			}
			leaveRepository.deleteInBatch(leave);
		} catch (Exception e) {
			throw new OaException("删除我的请假信息异常了", e);
		}

	}

}
