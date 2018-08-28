package org.fkit.oa.holiday.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.holiday.domain.APP_HIS;
import org.fkit.oa.holiday.domain.APP_Leave_V;
import org.fkit.oa.holiday.domain.Holiday;
import org.fkit.oa.holiday.domain.Leave;
import org.fkit.oa.holiday.domain.Leave_V;
import org.fkit.oa.holiday.repository.AppHisRepository;
import org.fkit.oa.holiday.repository.AppLeaveVRepository;
import org.fkit.oa.holiday.repository.HolidayRepository;
import org.fkit.oa.holiday.repository.LeaveRepository;
import org.fkit.oa.holiday.service.HolidayService;
import org.fkit.oa.holiday.service.LeaveService;
import org.fkit.oa.holiday.service.LeaveVService;
import org.fkit.oa.identity.domain.User;
import org.fkit.oa.identity.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/leave")
public class HolidayController {
	
	@Resource // by type
	private HolidayService holidayService;
	
	@Resource // by type
	private HolidayRepository holidayRepository;
	
	@Resource // by type
	private LeaveRepository leaveRepository;
	
	@Resource // by type
	private UserRepository userRepository;
	
	@Resource // by type
	private LeaveVService leaveVService;
	
	@Resource // by type
	private LeaveService leaveService;
	
	@Resource // by type
	private AppLeaveVRepository appLeaveVRepository;
	
	@Resource // by type
	private AppHisRepository appHisRepository;
	/**
	 * 分页查询节假日
	 * @param holiday
	 * @param request
	 * @param pageModel
	 * @param model
	 * @return
	 */
	  
	@RequestMapping("/mgrLeaveType")
	public String getAllHoliday(Holiday holiday,HttpServletRequest request, PageModel pageModel,Model model) {
		try {
			/** 1.自己处理 ：只需要处理get请求的参数 
			 *   post请求的参数不会乱码 
			 *  */
		if(request.getMethod().toLowerCase().indexOf("get")!=-1){
		    if(holiday!=null && !StringUtils.isEmpty(holiday.getName())){
		    	String name = holiday.getName();
				/**
				 * 浏览器到后台乱码 
				 * */
			    name = new String(name.getBytes("ISO-8859-1") , "UTF-8");
			    holiday.setName(name);
		    }
		}
		
		List<Holiday> holidays = holidayService.getHolidayByPager(holiday,pageModel);
		model.addAttribute("holidays", holidays);
	} catch (Exception e) {
		e.printStackTrace();
	}
		return "holiday/holiday";
	}
	/**
	 * 到添加节假日的界面
	 * @return
	 */
	@RequestMapping(value="/showAddHoliday")
	public String showAddUser(){
		return "holiday/addHoliday";
	}
	/**
	 * 添加节假日
	 * @param holiday
	 * @param model
	 * @return
	 */
	@RequestMapping("/addLeaveType")
	public String addHoliday(Holiday holiday,Model model) {
		try {
			holidayService.addHoliday(holiday);
			model.addAttribute("tip", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "添加失败");
		}
		return "holiday/addHoliday";
	}
	/**
	 * 删除节假日
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteLeaveType")
	public String deleteHoliday(String ids ,Model model) {
		try {
			/** 批量删除  */
			holidayService.deleteUserByHolidayIds(ids);
			model.addAttribute("tip", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "删除失败");
		}
		return "forward:/admin/leave/mgrLeaveType";
	}
	
	@RequestMapping("/showUpdateHoliday")
	public String showUpdateLeaveType(String code,Model model) {
		Holiday holiday = holidayRepository.findByCode(code);
		model.addAttribute("holiday", holiday);
		return "holiday/addHoliday"; 
	}
	@RequestMapping("/updateLeaveType")
	public String updateHoliday(Holiday holiday,Model model) {
		try {
			holidayService.updateHoliday(holiday);
			model.addAttribute("tip", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "修改失败");
		}
		return "forward:/admin/leave/mgrLeaveType";
	}
	/**
	 * 用户请假
	 * @param leave
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectUserLeave")
	public String selectUserLeave(String leave_id,Model model,String action) {
		if(leave_id != null && !"".equals(leave_id)) {
			int LEAVE_ID = Integer.valueOf(leave_id);
			Leave leave = leaveRepository.findLeaveById(LEAVE_ID);
			model.addAttribute("leave", leave);
			model.addAttribute("action", action);
			List<APP_HIS> hisList = appHisRepository.findAppHIsByLeaveId(LEAVE_ID);
			model.addAttribute("hisList", hisList);
		}
		return "holiday/leave";
	}
	/**
	 * 获得所有的请假类型
	 * @return
	 */
	@RequestMapping("/findAllHoliday")
	@ResponseBody
	public List<Holiday> findAllHoliday(){
		 List<Holiday> holidays=holidayRepository.findAllHoliday();
		 return holidays;
	}
	
	
	@RequestMapping("/findAllUsers")
	@ResponseBody
	public List<User> findAllUser(){
		 List<User> users =userRepository.findAllUser();
		 List<User> myUsers = new ArrayList<>();
		 if(users != null && users.size()>0) {
			 for(User u:users) {
				 User user = new User();
				 user.setUserId(u.getUserId());
				 user.setName(u.getName());
				 myUsers.add(user);
			 }
		 }
		 return myUsers;
	}
	/**
	 * 存为草稿和提交
	 * @param leave
	 * @param model
	 * @return
	 */
	@RequestMapping("/addLeaveTo_cg")
	public String addLeaveTo_cg(Leave leave,Model model,String begin_time,String end_time,String leave_status,String leave_id) {
		try {
			if(leave_status.equals("0")) {
				leave.setLeave_status(0);	
			}
			if(leave_status.equals("1")) {
				leave.setLeave_status(1);	
			}
			
			
			//计算请假时长
			 long nd = 1000 * 24 * 60 * 60;
			 long nh = 1000 * 60 * 60;
			 long nm = 1000 * 60;
			 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date biginTime = format.parse(begin_time);
			Date endTime = format.parse(end_time);
			Long diff  =Long.valueOf(endTime.getTime()-biginTime.getTime());
			 // 计算差多少天
		    long day = diff / nd;
		    // 计算差多少小时
		    long hour = diff % nd / nh;
		    // 计算差多少分钟
		    long min = diff % nd % nh / nm;
			leave.setLeave_time(day + "天" + hour + "小时" + min + "分钟");
			leave.setLeave_begin_time(biginTime);
			leave.setLeave_end_time(endTime);
			//如果是修改
			if(leave_id !=null && !"".equals(leave_id)) {
				leave.setLEAVE_ID(Integer.valueOf(leave_id));
				leave.setLeave_lastup_time(new Date());
			}else { //若果是新增
				leave.setLeave_create_time(new Date());
				leave.setLeave_lastup_time(new Date());
			}
			leaveRepository.save(leave);
			model.addAttribute("tip", "存为草稿成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "存为草稿失败");
		}
		return "holiday/leave";
	}
	/**
	 * 我的请假
	 * @param model
	 * @return
	 */
	@RequestMapping("/myLeave")
	public String myLeave(Model model,HttpSession session,PageModel pageModel) {
		String user_id =(String) session.getAttribute("user_id");
		List<Leave_V> leavesV =leaveVService.findForPageByUserId(user_id,pageModel);
		model.addAttribute("leave", leavesV);
		return "holiday/myLeave";
	}
	
	@RequestMapping("/getHolidayTypeByCode")
	public String getHolidayTypeByCode(String code,Model model,String action) {
		Holiday holiday =holidayRepository.findByCode(code);
		model.addAttribute("holiday", holiday);
		model.addAttribute("action",action);
		return "holiday/addHoliday";
	}
	/**
	 * 删除我的请假（只能删除草稿单，已提交的是不能进行删除的）
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteMyLeave")
	public String deleteMyLeave(String ids,Model model) {
		try {
			/** 批量删除  */
			leaveService.deleteMyLeave(ids);
			model.addAttribute("tip", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("tip", "删除失败");
		}
		return "forward:/admin/leave/myLeave";
	}
	
	/**
	 * 到假期待我审批界面
	 */
	@RequestMapping("/selectAuditLeave")
	public String selectAuditLeave(Model model,HttpSession session,PageModel pageModel) {
		String user_id =(String) session.getAttribute("user_id");
		PageRequest.of(pageModel.getPageIndex() - 1, pageModel.getPageSize());
		List<APP_Leave_V> appLeavesV = appLeaveVRepository.findAppLeaveByUser(user_id);
		pageModel.setRecordCount(appLeavesV.size());
		model.addAttribute("leavesV", appLeavesV);
		return "holiday/selectAuditLeave";
	}
	/**
	 * 到审批界面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/toAPPLeavePage")
	public String toAPPLeavePage(Model model,String id) {
		int leave_id = Integer.valueOf(id);
		APP_Leave_V appLeave = appLeaveVRepository.findAppLeaveLeaveId(leave_id);
		model.addAttribute("appLeave", appLeave);
		return "holiday/toAPPLeavePage";
	}
	
	/**
	 * 审批同意
	 * @param app_status
	 * @param leave_id
	 * @param model
	 */
	
	@RequestMapping("/appAgree")
	@ResponseBody
	public void appAgree(String app_status,String leave_id,Model model,String app_idear,HttpSession session) {
		//审批同意，将状态修改为2
		leaveRepository.updateStatusById(2,Integer.valueOf(leave_id));
		if(app_idear =="" || app_idear == null) {
			app_idear="同意";
		}
		//保存审批记录
		APP_HIS his = new APP_HIS();
		his.setLeave_id(Integer.valueOf(leave_id));
		User user =(User)session.getAttribute("user_session");
		his.setApp_user_id(user.getUserId());
		his.setApp_user_name(user.getName());
		his.setApp_idear(app_idear);
		his.setApp_status(2);
		his.setApp_time(new Date());
		appHisRepository.save(his);
	}
	/**
	 * 审批不同意
	 * @param app_status
	 * @param leave_id
	 * @param model
	 */
	@RequestMapping("/appReject")
	@ResponseBody
	public void appReject(String app_status,String leave_id,Model model,String app_idear,HttpSession session) {
		//审批不同意，将状态修改为3
		leaveRepository.updateStatusById(3,Integer.valueOf(leave_id));
		//保存审批记录
		APP_HIS his = new APP_HIS();
		his.setLeave_id(Integer.valueOf(leave_id));
		User user =(User)session.getAttribute("user_session");
		his.setApp_user_id(user.getUserId());
		his.setApp_user_name(user.getName());
		his.setApp_idear(app_idear);
		his.setApp_status(3);
		his.setApp_time(new Date());
		appHisRepository.save(his);
	}
}
