package org.fkit.oa.notice.service;

import java.util.List;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.notice.domain.Notice;

public interface NoticeService {

	public List<Notice> findForPageByQuery(Notice notice, PageModel pageModel);
	
	public void deleteNoticeById(String ids);
	

}
