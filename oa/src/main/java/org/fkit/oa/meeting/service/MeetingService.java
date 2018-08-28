package org.fkit.oa.meeting.service;

import java.util.List;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.meeting.domain.Meeting;

public interface MeetingService {

	public List<Meeting> findForPageByQuery(Meeting Meeting, PageModel pageModel);
	
	public void deleteMeetingById(String ids);
	

}
