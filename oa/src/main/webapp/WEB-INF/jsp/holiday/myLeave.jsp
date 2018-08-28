<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OA办公管理系统-用户管理</title>
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
			title:'添加节假日',
			msg:"<span style='color:red;'>"+tip+"</span>",
			showType:'slide'
	});
};
/** 文档加载完成*/
$(function(){
	
	var pattern = "yyyy-MM-dd hh:mm";
	$('table tr td[name$=Timer]').each(function(){
		$(this).text(new Date($(this).text()).myDateStrFormat(pattern))
	});
	 
	 if("${tip}"){
		 $.messager.show({
				title:'删除节假日',
				msg:"<span style='color:red;'>${tip}</span>",
				showType:'slide'
			});
	 }
	 
	 /* 单据状态 */
	 $(".leave_status").each(function(){
		 var leave_status= $(this).text();
		switch(leave_status){
		   case "0":
			   $(this).text("草稿单");
			   break;
		   case "1":
			   $(this).text("已提交");
			   break;  
		   case "2":
			   $(this).text("已审批");
			   break;
		   case "3":
			   $(this).text("已退回");
			   break;
		} 
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
    
	 /** 删除请假（必须是草稿单才能删除）  */
     $("#deleteMyLeave").on("click",function(){
  	   /** 获取所有选中的数据行的id 传输到后台删除数据  */
  	   /** 拿到当前选中的单选 */
 		   var checkedBoxs = dataBoxs.filter(":checked");
  	   /** admin,liqin  */
  	   if(checkedBoxs.length > 0 ){
  		   var status = checkedBoxs.parent().parent('tr').find('td:eq(8)').text();
  		   if(status.indexOf('已提交') !=-1){
  			   alert("您选择的单据中存在已提交单子，无法进行删除！");
  			   return;
  		   }
  		   $.messager.confirm('用户提示', '您确认删除吗?', function(r){
 				if (r){
 					 /** 真正删除:   */
 					var maps = checkedBoxs.map(function(){
 						 return this.value;
 					});
 					// alert(maps.get());
 					window.location = "${ctx}/admin/leave/deleteMyLeave?ids="+maps.get()
 							+"&pageIndex=${pageModel.pageIndex}&name=${holiday.name}";
 				}
 			});

  	   }else{
  		   $.messager.alert("用户提示","请选择您要删除的请假！","error");
  	   }
     });
	 
    

       
});
/**修改节假日 */
function updateLeave(leave_id){
	 $("#divDialog").dialog({
			title : "修改请假", // 标题
			cls : "easyui-dialog", // class
			width : 600, // 宽度
			height : 420, // 高度
			maximizable : true, // 最大化
			minimizable : false, // 最小化
			collapsible : true, // 可伸缩
			modal : true, // 模态窗口
			onClose : function(){ // 关闭窗口
				window.location = "${ctx}/admin/leave/myLeave?pageIndex=${pageModel.pageIndex}&name=${holiday.name}";
			}
	    });
	 /** 为此窗口的iframe触发界面请求 */
	 $("#iframe").attr("src","${ctx}/admin/leave/selectUserLeave?leave_id="+leave_id);
}

function showHoliday(code){
	 $("#divDialog").dialog({
			title : "查看假期类型", // 标题
			cls : "easyui-dialog", // class
			width : 600, // 宽度
			height : 420, // 高度
			maximizable : true, // 最大化
			minimizable : false, // 最小化
			collapsible : true, // 可伸缩
			modal : true, // 模态窗口
			onClose : function(){ // 关闭窗口
				window.close();
			}
	    });
		 $("#iframe").attr("src","${ctx}/admin/leave/getHolidayTypeByCode?action=show&code="+code);
	}
	
	function showLeave(leave_id){
		 $("#divDialog").dialog({
				title : "查看请假", // 标题
				cls : "easyui-dialog", // class
				width : 600, // 宽度
				height : 420, // 高度
				maximizable : true, // 最大化
				minimizable : false, // 最小化
				collapsible : true, // 可伸缩
				modal : true, // 模态窗口
				onClose : function(){ // 关闭窗口
					return;
				}
		    });
		 /** 为此窗口的iframe触发界面请求 */
			 $("#iframe").attr("src","${ctx}/admin/leave/selectUserLeave?action=show&leave_id="+leave_id);
		
	}


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
						<input name="leave_type" type="text" class="form-control"
							placeholder="请选择假类型" value="" >
						</td>
						
						<td>	
						<button type="submit" id="selectHoliday" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
					    <a  id="deleteMyLeave" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
					 </td>
					</tr>
					</tbody>
				</table>
		</form>
 		<div class="panel panel-primary" style="padding-left: 10px;">
 			<div class="panel-heading" style="background-color: #11a9e2;">
				<h3 class="panel-title">我的请假信息</h3>
			</div>
			<div class="panel-body" >
				<table class="table table-bordered">
					<thead>
						<tr style="font-size: 12px;" align="center">
							<th style="text-align: center;"><input id="checkAll"
								type="checkbox" /></th>
							<th style="text-align: center;">请假人</th>
							<th style="text-align: center;">请假类型</th>
							<th style="text-align: center;">请假原因</th>
							<th style="text-align: center;">请假开始时间</th>
							<th style="text-align: center;">请假结束时间</th>
							<th style="text-align: center;">请假时长</th>
							<th style="text-align: center;">审批人</th>
							<th style="text-align: center;">请假状态</th>
							<th style="text-align: center;">操作</th>
						</tr>
					</thead>
					<c:forEach items="${leave}" var="leave"
						varStatus="stat">
						<tr id="dataTr_${stat.index}" align="center">
							<td><input type="checkbox" name="box" id="box_${stat.index}"
								value="${leave.leave_id}" /></td>
							<td>${leave.user_name}</td>	
							<td><a href="#" onclick="showHoliday('${leave.leave_typeCode}')">${leave.leave_type}</a></td>	
							<td>${leave.leave_cause}</td>
							<td name="beginTimer">${leave.leave_begin_time}</td>
							<td name="endTimer">${leave.leave_end_time}</td>
							<td>${leave.leave_time}</td>
							<td>${leave.app_user_name}</td>
							<td class="leave_status">${leave.leave_status}</td>
							<td>
							<c:if test="${leave.leave_status==0}">
								 <span id="updateHoliday_${stat.index}"  class="label label-info"><a href="javascript:updateLeave('${leave.leave_id}')" style="color: red;">修改</a></span>
							</c:if>
							 <c:if test="${leave.leave_status > 0}">
								 <span id="updateHoliday_${stat.index}"  class="label label-info"><a href="javascript:showLeave('${leave.leave_id}')" style="color: white;">查看</a></span>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 分页标签区 -->
				<fkjava:pager pageIndex="${pageModel.pageIndex}"
					pageSize="${pageModel.pageSize}"
					recordCount="${pageModel.recordCount}"
					submitUrl="${ctx}/admin/leave/myLeave?pageIndex={0}&name=${user.name}" />
			</div>

		</div>
		
		<div id="divDialog" style="display: none;" >
			 <!-- 放置一个添加用户的界面  -->
			 <iframe id="iframe" frameborder="0" style="width: 100%;height: 100%;"></iframe>
		</div>
	
</body>
</html>