package org.fkit.oa.notice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.notice.domain.Notice;
import org.fkit.oa.notice.repository.NoticeRepository;
import org.fkit.oa.notice.service.NoticeService;
import org.fkit.oa.util.entity.Files;
import org.fkit.oa.util.repository.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Resource
	private NoticeRepository noticeRepository;
	@Resource
	private FileRepository fileRepository;
	@Resource
	private NoticeService noticeService;
	/**
	 * 到公告发布界面
	 * @return
	 */
	@RequestMapping("/release")
	public String showAddNotice() {
		return "notice/addNotice";
	}
	
	/**
	 * 到公告管理界面
	 * @return
	 */
	@RequestMapping("/manage")
	public String  search(Model model,PageModel pageModel,Notice notice) {
		List<Notice> noticeList =noticeService.findForPageByQuery(notice, pageModel);
		model.addAttribute("notice", noticeList);
		model.addAttribute("notice_title", notice.getNotice_title());
		return "notice/notice";
	}
	/**
	 * 根据ID查询公告
	 * @param notice_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findById")
	public String findById(String notice_id,Model model) {
		Notice notice = noticeRepository.findeNoticeById(Long.valueOf(notice_id));
		model.addAttribute("notice", notice);
		List<Files> noticeFiles =fileRepository.getFileByNoticeId(Long.valueOf(notice_id));
		model.addAttribute("files", noticeFiles);
		return "notice/updateNotice";
	}
	
	/**
	 * 查询所有公告
	 * @param notice_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Notice> findAll() {
		List<Notice> noticeList = noticeRepository.findAll();
		return noticeList;
	}
	/**
	 * 查找文件
	 * @param file_id
	 * @param model
	 */
	
	@RequestMapping("/findFile")
	@ResponseBody
	public void findFileById(String file_id,Model model) {
		Files file =fileRepository.getFileById(Long.valueOf(file_id));
		model.addAttribute("file", file);
	}
	/**
	 * 删除文件
	 * @param file_id
	 * @param model
	 */
	
	@RequestMapping("/deleteFile")
	@ResponseBody
	public void deleteFile(String file_id,Model model) {
		fileRepository.deleteFileById(Long.valueOf(file_id));
		model.addAttribute("message", "附件删除成功!");
	}
	
	/**
	 * 发布公告
	 * @param notice
	 * @param beginTime
	 * @param endTime
	 * @param uploadFileId
	 * @return
	 */
	@RequestMapping("/addNotice")
	@ResponseBody
	public String addNotice(Notice notice,String beginTime,String endTime,String uploadFileId) {
		 
	     try {
	    	 SimpleDateFormat beginDateFormat=new SimpleDateFormat(beginTime);
			 notice.setBegin_time(beginDateFormat.parse(beginTime));
			 SimpleDateFormat endDateFormat=new SimpleDateFormat(endTime);
			 notice.setEnd_time(endDateFormat.parse(endTime));
			 Notice no =(Notice)noticeRepository.save(notice);
			 if(uploadFileId !=null && !"".equals(uploadFileId)) {
				 fileRepository.uploadFile(no.getNotice_id(),Long.valueOf(uploadFileId));
			 }
		} catch (ParseException e) {
			e.printStackTrace();
			return "公告发布失败";
		}
		return "公告发布成功！";
	}
	/**
	 * 删除公告
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteNotice")
	public String deleteNotice(String ids,Model model) {
			try {
				/** 批量删除  */
				noticeService.deleteNoticeById(ids);
				model.addAttribute("tip", "删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("tip", "删除失败");
			}
			return "forward:/notice/manage";
	}
	
}
