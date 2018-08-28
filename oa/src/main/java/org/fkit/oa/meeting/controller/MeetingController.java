package org.fkit.oa.meeting.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.meeting.domain.Meeting;
import org.fkit.oa.meeting.repository.MeetingRepository;
import org.fkit.oa.meeting.service.MeetingService;
import org.fkit.oa.util.entity.Files;
import org.fkit.oa.util.repository.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
	
	@Resource
	private MeetingRepository meetingRepository;
	@Resource
	private FileRepository fileRepository;
	@Resource
	private MeetingService meetingService;
	/**
	 * 到回复发起界面
	 * @return
	 */
	@RequestMapping("/createMeeting")
	public String showAddmeeting() {
		return "meeting/createMeeting";
	}
	
	/**
	 * 到我的会议界面
	 * @return
	 */
	@RequestMapping("/myMeeting")
	public String myMeeting() {
		return "meeting/myMeeting";
	}
	
	/**
	 * 到公告管理界面
	 * @return
	 */
	@RequestMapping("/manage")
	public String  search(Model model,PageModel pageModel,Meeting meeting) {
		List<Meeting> meetingList =meetingService.findForPageByQuery(meeting, pageModel);
		model.addAttribute("meeting", meetingList);
		model.addAttribute("meeting_title", meeting.getMeeting_title());
		return "meeting/meeting";
	}
	/**
	 * 根据ID查询公告
	 * @param meeting_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findById")
	public String findById(String meeting_id,Model model) {
		Meeting meeting = meetingRepository.findeMeetingById(Long.valueOf(meeting_id));
		model.addAttribute("meeting", meeting);
		List<Files> meetingFiles =fileRepository.getFileByNoticeId(Long.valueOf(meeting_id));
		model.addAttribute("files", meetingFiles);
		return "meeting/updatemeeting";
	}
	
	/**
	 * 查询所有公告
	 * @param meeting_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Meeting> findAll() {
		List<Meeting> meetingList = meetingRepository.findAll();
		return meetingList;
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
	 * @param meeting
	 * @param beginTime
	 * @param endTime
	 * @param uploadFileId
	 * @return
	 */
	@RequestMapping("/addMeeting")
	@ResponseBody
	public String addmeeting(Meeting meeting,String beginTime,String endTime,String uploadFileId) {
		 
	     try {
	    	 SimpleDateFormat beginDateFormat=new SimpleDateFormat(beginTime);
			 meeting.setBegin_time(beginDateFormat.parse(beginTime));
			 SimpleDateFormat endDateFormat=new SimpleDateFormat(endTime);
			 meeting.setEnd_time(endDateFormat.parse(endTime));
			 Meeting no =(Meeting)meetingRepository.save(meeting);
			 if(uploadFileId !=null && !"".equals(uploadFileId)) {
				 fileRepository.uploadFile(no.getMeeting_id(),Long.valueOf(uploadFileId));
			 }
		} catch (ParseException e) {
			e.printStackTrace();
			return "会议申请失败";
		}
		return "会议申请成功！";
	}
	/**
	 * 删除公告
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping("/deletemeeting")
	public String deletemeeting(String ids,Model model) {
			try {
				/** 批量删除  */
				meetingService.deleteMeetingById(ids);
				model.addAttribute("tip", "删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("tip", "删除失败");
			}
			return "forward:/meeting/manage";
	}
	
}
