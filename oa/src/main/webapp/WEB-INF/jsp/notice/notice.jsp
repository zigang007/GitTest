<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>捷途软件--智能办公--公告管理</title> 
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
var showTip = function(tip){
	 $.messager.show({
			title:'公告管理',
			msg:"<span style='color:red;'>"+tip+"</span>",
			showType:'slide'
	});
};
/** 文档加载完成*/
$(function(){
	
	
	 
	 if("${tip}"){
		 $.messager.show({
				title:'公告管理',
				msg:"<span style='color:red;'>${tip}</span>",
				showType:'slide'
			});
	 }
	  /** 添加节假日操作*/
	 $("#addNotice").click(function(){
			$("#divDialog").dialog({
				title : "添加公告", // 标题
				cls : "easyui-dialog", // class
				width : 600, // 宽度
				height : 420, // 高度
				maximizable : true, // 最大化
				minimizable : false, // 最小化
				collapsible : true, // 可伸缩
				modal : true, // 模态窗口
				onClose : function(){ // 关闭窗口
					window.location = "${ctx}/notice/manage?pageIndex=${pageModel.pageIndex}&notice_title=${notice_title}";
				}
		    });
			/** 为此窗口的iframe触发界面请求 */
			$("#iframe").attr("src","${ctx}/notice/release");
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
    
	 /** 删除节公告  */
     $("#deleteNotice").on("click",function(){
  	   /** 获取所有选中的数据行的id 传输到后台删除数据  */
  	   /** 拿到当前选中的单选 */
 		   var checkedBoxs = dataBoxs.filter(":checked");
  	   /** admin,liqin  */
  	   if(checkedBoxs.length > 0 ){
  		   $.messager.confirm('用户提示', '您确认删除吗?', function(r){
 				if (r){
 					 /** 真正删除:   */
 					var maps = checkedBoxs.map(function(){
 						 return this.value;
 					});
 					// alert(maps.get());
 					window.location = "${ctx}/notice/deleteNotice?ids="+maps.get()
 							+"&pageIndex=${pageModel.pageIndex}&notice_title=${notice_title}";
 				}
 			});

  	   }else{
  		   $.messager.alert("用户提示","请选择您要删除的用户！","error");
  	   }
     });
	 
   /*   var moduleOperasUrls = "${moduleOperasUrls}";
     if(moduleOperasUrls.indexOf("addLeaveType")==-1){
     	$("#addHoliday").hide();
     }
     
     if(moduleOperasUrls.indexOf("deleteLeaveType")==-1){
     	$("#deleteHoliday").hide();
     }
     if(moduleOperasUrls.indexOf("updateLeaveType")==-1){
     	$("span[id^='updateHoliday_']").css("display","none");
     }
      */


       
});
/**修改节假日 */
function update(notice_id){
	 $("#divDialog").dialog({
			title : "修改公告", // 标题
			cls : "easyui-dialog", // class
			width : 1200, // 宽度
			height : 500, // 高度
			maximizable : true, // 最大化
			minimizable : false, // 最小化
			collapsible : true, // 可伸缩
			modal : true, // 模态窗口
			onClose : function(){ // 关闭窗口
				window.location = "${ctx}/notice/manage?pageIndex=${pageModel.pageIndex}&notice_title=${notice_title}";
			}
	    });
	 /** 为此窗口的iframe触发界面请求 */
	 $("#iframe").attr("src","${ctx}/notice/findById?notice_id="+notice_id);
}	
</script>
</head>
<body style="overflow: hidden; width: 98%; height: 100%;" >
   	<!-- 工具按钮区 -->
	<form class="form-horizontal" 
			action="${ctx }/notice/manage" method="post" style="padding-left: 5px;" >
			<table class="table-condensed">
					<tbody>
					<tr>
					   <td>
						<input name="notice_title" type="text" class="form-control"
							placeholder="姓名"  value = "${notice_title}" >
						</td>
						
						<td>	
						<button type="submit" id="selectHoliday" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
						<a  id="addNotice" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>&nbsp;添加</a>
					    <a  id="deleteNotice" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;删除</a>
					 </td>
					</tr>
					</tbody>
				</table>
		</form>
 		<div class="panel panel-primary" style="padding-left: 10px;">
 			<div class="panel-heading" style="background-color: #11a9e2;">
				<h3 class="panel-title">公告列表信息</h3>
			</div>
			<div class="panel-body" >
				<table class="table table-bordered">
					<thead>
						<tr style="font-size: 12px;" align="center">
							<th style="text-align: center;"><input id="checkAll"
								type="checkbox" /></th>
							<th style="text-align: center;">公告类型</th>
							<th style="text-align: center;">主题</th>
							<th style="text-align: center;">操作</th>
						</tr>
					</thead>
					 <c:forEach items="${notice}" var="notice"
						varStatus="stat">
						<tr id="dataTr_${stat.index}" align="center">
							<td><input type="checkbox" name="box" id="box_${stat.index}"
								value="${notice.notice_id}" /></td>
								<c:if test="${notice.notice_type=='public'}">
									<td>公共业务</td>	
								</c:if>
								<c:if test="${notice.notice_type=='myself'}">
									<td>个人业务</td>	
								</c:if>
								<c:if test="${notice.notice_type=='company'}">
									<td>公司业务</td>	
								</c:if>
							<td>${notice.notice_title}</td>	
							<td>
							   <span id="updateHoliday_${stat.index}"  class="label label-info"><a href="javascript:update('${notice.notice_id}')" style="color: white;">修改</a></span>
							</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 分页标签区 -->
				<fkjava:pager pageIndex="${pageModel.pageIndex}"
					pageSize="${pageModel.pageSize}"
					recordCount="${pageModel.recordCount}"
					submitUrl="${ctx}/notice/manage?pageIndex={0}&name=${user.name}&phone=${user.phone}&dept.id=${user.dept.id}&job.code=${user.job.code}" />
			</div>

		</div>
		
		<div id="divDialog" style="display: none;" >
			 <!-- 放置一个添加用户的界面  -->
			 <iframe id="iframe" frameborder="0" style="width: 100%;height: 100%;"></iframe>
		</div>
	
</body>
</html>