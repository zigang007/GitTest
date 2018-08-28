package org.fkit.oa.holiday.service;

import java.util.List;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.holiday.domain.Leave;

public interface LeaveService {

	public List<Leave> findForPageByUserId(String user_id, PageModel pageModel);
	
	public void deleteMyLeave(String ids);

}
