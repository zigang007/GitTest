<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OA办公管理系统-我的代办</title>
<%@ include file="/WEB-INF/taglib.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link rel="stylesheet"
	href="${ctx }/resources/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
<!-- 导入bootStrap的库 -->
<script type="text/javascript"
	src="${ctx}/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/easyUI/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${ctx}/resources/easyUI/easyui.css">
<script type="text/javascript" src="${ctx}/resources/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript">
Date.prototype.myDateStrFormat = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3),  
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
</script>
<script type="text/javascript">
var showTip = function(tip){
	 $.messager.show({
			title:'审批节假日',
			msg:"<span style='color:red;'>"+tip+"</span>",
			showType:'slide'
	});
};
/** 文档加载完成*/
$(function(){
	
	
	 
	 if("${tip}"){
		 $.messager.show({
				title:'审批节假日',
				msg:"<span style='color:red;'>${tip}</span>",
				showType:'slide'
			});
	 }
	 
	 var pattern = "yyyy-MM-dd hh:mm";
		$('.timer').each(function(){
			$(this).text(new Date($(this).text()).myDateStrFormat(pattern))
		});
	 
	  
	 /** 用户界面效果开发  */
 	/** 得到所有数据行的jquery对象 */
 	var dataTrs = $("tr[id^='dataTr_']");
 	dataTrs.hover(function(){
 		$(this).css({backgroundColor: "#eeecdd" , cursor : "pointer"});
 	},function(){
 		// 判断这一行的单选是否被选中了,如果被选中不要恢复成白色背景 
 		// 得到当前行对应的单选的id 
 		var trBoxId = this.id.replace("dataTr_","box_");
 		var trBox = $("#"+trBoxId);
 		if(!trBox.attr("checked")){
 			$(this).css("backgroundColor","#ffffff");
 		}
 	});  
 	
 	/** 全选  */
 	/**得到所有数据行的选项按钮  */
 	var dataBoxs = $("input[name='box'][id^='box_']");
 	$("#checkAll").click(function(){
 		dataBoxs.attr("checked",this.checked);
 		/** 全选如果被选中,则所有行的背景色被选中 ,反之 */
 		dataTrs.trigger(this.checked?"mouseover":"mouseout");
 	});
 	
 	/** 如果没有全部选中那么全选按钮也应该不选中  */
 	var boxSize = dataBoxs.length;
 	/** 给每个单选绑定点击事件 */
 	dataBoxs.on("click",function(event){
 		/** 取消单选事件的传播,单选点击完成以后,事件就结束了 */
 		event.stopPropagation();
 		/** 拿到当前选中的单选 */
 		var checkedBoxs = dataBoxs.filter(":checked");
 		$("#checkAll").attr("checked",checkedBoxs.length == boxSize);
 	});
 	
    /** 为所有数据行绑定点击事件  */
    dataTrs.click(function(){
 		/** 得到当前所点击行的对应单选按钮对象 */
 	    var trBoxId = this.id.replace("dataTr_","box_");
			var trBox = $("#"+trBoxId);
			trBox.trigger("click");
    });
       
});

</script>
</head>
<body style="overflow: hidden; width: 98%; height: 100%;" >
   	<!-- 工具按钮区 -->
	<form class="form-horizontal" 
			action="${ctx }/admin/leave/mgrLeaveType" method="post" style="padding-left: 5px;" >
			<table class="table-condensed">
					<tbody>
					<tr>
					   <td>
						<input name="name" type="text" class="form-control"
							placeholder="姓名" value="${holiday.name}" >
						</td>
						
						<td>	
						<button type="submit" id="selectHoliday" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
					 </td>
					</tr>
					</tbody>
				</table>
		</form>
 		<div class="panel panel-primary" style="padding-left: 10px;">
 			<div class="panel-heading" style="background-color: #11a9e2;">
				<h3 class="panel-title">假期列表信息</h3>
			</div>
			<div class="panel-body" >
				<table class="table table-bordered">
					<thead>
						<tr style="font-size: 12px;" align="center">
							<th style="text-align: center;"><input id="checkAll"
								type="checkbox" /></th>
							<th style="text-align: center;">请假人</th>
							<th style="text-align: center;">请假原因</th>
							<th style="text-align: center;">请假开始时间</th>
							<th style="text-align: center;">请假结束时间</th>
							<th style="text-align: center;">请假时长</th>
							<th style="text-align: center;">操作</th>
						</tr>
					</thead>
					<c:forEach items="${leavesV}" var="leave"
						varStatus="stat">
						<tr id="dataTr_${stat.index}" align="center">
							<td><input type="checkbox" name="box" id="box_${stat.index}"
								value="${leave.leave_id}" /></td>
							<td>${leave.user_name}</td>	
							<td>${leave.leave_cause}</td>	
							<td class="timer">${leave.leave_begin_time}</td>
							<td class="timer">${leave.leave_end_time}</td>
							<td>${leave.leave_time}</td>
							
							<td>
							   <span id="appLeave_${stat.index}"  class="label label-info"><a href="${ctx}/admin/leave/toAPPLeavePage?id=${leave.leave_id}" style="color: white;">查看</a></span>
							</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 分页标签区 -->
				<fkjava:pager pageIndex="${pageModel.pageIndex}"
					pageSize="${pageModel.pageSize}"
					recordCount="${pageModel.recordCount}"
					submitUrl="${ctx}/admin/leave/selectAuditLeave?pageIndex={0}" />
			</div>

		</div>
		
		<div id="divDialog" style="display: none;" >
			 <!-- 放置一个添加用户的界面  -->
			 <iframe id="iframe" frameborder="0" style="width: 100%;height: 100%;"></iframe>
		</div>
	
</body>
</html>