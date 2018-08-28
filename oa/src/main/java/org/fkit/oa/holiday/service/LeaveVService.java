package org.fkit.oa.holiday.service;

import java.util.List;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.holiday.domain.Leave_V;

public interface LeaveVService {

	public List<Leave_V> findForPageByUserId(String user_id, PageModel pageModel);
	
	

}
