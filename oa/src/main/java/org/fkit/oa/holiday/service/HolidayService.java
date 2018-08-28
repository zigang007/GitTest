package org.fkit.oa.holiday.service;

import java.util.List;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.holiday.domain.Holiday;

public interface HolidayService {
	/**
	 * 分页查询用户信息 
	 * 
	 * @param user
	 * @param pageModel
	 * @return
	 */
	List<Holiday> getHolidayByPager(Holiday holiday, PageModel pageModel);

	void addHoliday(Holiday holiday);

	void deleteUserByHolidayIds(String ids);

	void updateHoliday(Holiday holiday);

	 


}
